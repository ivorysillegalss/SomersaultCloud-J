package org.chenzc.purchaserContext.domain.application;

import cn.hutool.core.util.IdUtil;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.service.WxPayService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.chenzc.archiveContext.domain.entity.Archive;
import org.chenzc.archiveContext.domain.service.ArchiveService;
import org.chenzc.common.constant.CommonConstant;
import org.chenzc.common.entity.BasicResult;
import org.chenzc.common.enums.PurchaseEnums;
import org.chenzc.common.utils.QrCodeUtil;
import org.chenzc.purchaserContext.domain.api.dto.OrderDTO;
import org.chenzc.purchaserContext.domain.entity.WxOrder;
import org.chenzc.purchaserContext.domain.service.PurchaserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Objects;

/**
 * 应用服务层服务类
 *
 * @author chenz
 * @date 2024/07/17
 */
@Service
public class PurchaseApplicationService {

    @Resource
    private PurchaserService purchaserService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private WxPayService wxPayService;

    @Resource
    private ArchiveService archiveService;

    /**
     * 创建订单
     *
     * @return {@link BasicResult }
     */
    public BasicResult createOrder(OrderDTO orderDTO) {

//        TODO 导入责任链

        WxOrder order = WxOrder.builder()
                .body("body")
                .spbillCreateIp(orderDTO.ip)
                .totalFee(CommonConstant.ORDER_FEE) // 金额 分
                .tradeType("NATIVE")// 交易类型，NATIVE表示扫码支付
                .notifyUrl("http://localhost:3001/wxpay/notify")  // 回调地址
                .build();
        order.setOutTradeNo(org.chenzc.purchaserContext.domain.util.IdUtil.generateOutTradeNo(order));

        WxPayUnifiedOrderResult wxOrder = purchaserService.createWxOrder(order);

        Archive archive = Archive.builder()
                .time(Long.valueOf(System.currentTimeMillis()).intValue())
//                hutool 雪花算法生成支付时唯一ID
                .cid(IdUtil.getSnowflake().nextIdStr())
                .email(orderDTO.email)
                .ip(orderDTO.ip)
                .state(PurchaseEnums.CREATE_ORDER.getCode())
                .outTradeNo(order.getOutTradeNo())
                .build();

        archiveService.ArchivePayingLog(archive);

//        TODO 保持幂等性 先用redis作为暴力解
        redisTemplate.opsForSet().add(CommonConstant.WAIT_FOR_PAY_LIST,
                StringUtils.join(CommonConstant.WAIT_FOR_PAY_LIST_PRE, CommonConstant.REGEX, order.getOutTradeNo()));

        if (Objects.isNull(wxOrder)) {
            return BasicResult.fail();
        }
        OutputStream outputStream;
        String codeURL = wxOrder.getCodeURL();
        try {
            outputStream = QrCodeUtil.generateQRCodeImage(codeURL, CommonConstant.DEFAULT_WIDTH, CommonConstant.DEFAULT_HEIGHT);
        } catch (Exception e) {
            return BasicResult.fail();
        }
        return BasicResult.success(outputStream);
    }

    /**
     * 支付后拉取支付回执 支付结果
     *
     * @param xmlData
     * @return {@link BasicResult }
     */
    public BasicResult payNotify(String xmlData) {
        try {
            WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);

            // 校验通知内容
            if (CommonConstant.SUCCESS.equals(notifyResult.getResultCode())) {
                // 支付成功处理逻辑，如更新订单状态、记录支付信息等
                String outTradeNo = notifyResult.getOutTradeNo();
                String transactionId = notifyResult.getTransactionId();
                // TODO: 处理支付成功后的业务逻辑
                //  调用Nacos rpc 分配账号TBD


                redisTemplate.opsForSet().remove(CommonConstant.WAIT_FOR_PAY_LIST
                        , StringUtils.join(CommonConstant.WAIT_FOR_PAY_LIST_PRE, CommonConstant.REGEX, outTradeNo));

                // 返回成功结果给微信支付
                return BasicResult.success();
            } else {
                // 处理支付失败的情况
                return BasicResult.fail(notifyResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BasicResult.fail();
        }
    }

}
