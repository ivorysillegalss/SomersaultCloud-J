package org.chenzc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PurchaseEnums implements AbstractEnums {
    CREATE_ORDER("1", "创建订单"),
    PURCHASING("2", "支付中"),
    FINISH("3","支付完成");

//    TODO 可将用户提交邮箱时新建一初始化 或添加其它更多状态

    private final String code;
    private final String message;
}
