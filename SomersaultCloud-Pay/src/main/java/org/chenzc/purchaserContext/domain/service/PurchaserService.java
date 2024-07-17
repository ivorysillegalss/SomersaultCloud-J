package org.chenzc.purchaserContext.domain.service;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付上下文领域服务
 *
 * @author chenz
 * @date 2024/07/17
 */
@Service
public class PurchaserService {

    @Autowired
    private WxPayService wxPayService;

    public WxPayUnifiedOrderResult createWxOrder() {
        // 创建统一下单请求对象
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setBody("商品描述");
        request.setOutTradeNo("商户订单号");
        request.setTotalFee(1); // 订单金额，单位：分
        request.setSpbillCreateIp("用户端IP");
        request.setNotifyUrl("http://localhost:3001/wxpay/notify");
        request.setTradeType("NATIVE"); // 交易类型，NATIVE表示扫码支付

        WxPayUnifiedOrderResult result = null;

        //        TODO 异常处理
        // 调用统一下单接口
        try {
            result = wxPayService.createOrder(request);
        } catch (WxPayException e) {
            System.out.println("1111111");
        }

        return result;
    }
}
