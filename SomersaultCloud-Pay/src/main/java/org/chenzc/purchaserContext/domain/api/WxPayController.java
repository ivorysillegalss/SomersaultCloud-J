package org.chenzc.purchaserContext.domain.api;


import jakarta.annotation.Resource;
import org.chenzc.common.entity.BasicResult;
import org.chenzc.purchaserContext.domain.api.dto.OrderDTO;
import org.chenzc.purchaserContext.domain.application.PurchaseApplicationService;
import org.springframework.web.bind.annotation.*;

/**
 * 支付相关控制类
 * @author chenz
 * @date 2024/07/17
 */

@RestController
@RequestMapping("/wxpay")
public class WxPayController {

    @Resource
    private PurchaseApplicationService purchaseApplicationService;

    /**
     * 创建订单
     * @param orderDTO
     * @return {@link BasicResult }
     */
    @PostMapping
    public BasicResult CreateOrder(@RequestBody OrderDTO orderDTO){
        return purchaseApplicationService.createOrder(orderDTO);
    }


    /**
     * 支付成功自动返回回执或手动申请请求回执
     * @param xmlData
     * @return {@link BasicResult }
     */
    @PostMapping
    public BasicResult NotifyPay(@RequestBody String xmlData){
        return purchaseApplicationService.payNotify(xmlData);
    }
}
