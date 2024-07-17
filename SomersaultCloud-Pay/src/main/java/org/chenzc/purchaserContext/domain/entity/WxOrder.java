package org.chenzc.purchaserContext.domain.entity;

import lombok.Data;

@Data
public class WxOrder {
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 订单金额
     */
    private Integer totalFee;
    /**
     * 用户端IP
     */
    private String spbillCreateIp;
    /**
     * 支付结果通知地址
     */
    private String notifyUrl;
    /**
     * 交易类型
     */
    private String tradeType;
}
