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

    @PostMapping
    public BasicResult CreateOrder(@RequestBody OrderDTO orderDTO){
        return purchaseApplicationService.createOrder(orderDTO);
    }


}
