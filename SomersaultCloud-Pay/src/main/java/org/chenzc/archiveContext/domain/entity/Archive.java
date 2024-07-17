package org.chenzc.archiveContext.domain.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 组装用户支付记录 打入redis 定期打入DB
 * @author chenz
 * @date 2024/07/17
 */
@Data
@Builder
public class Archive {
    /**
     * 支付时间戳
     */
    private Integer time;
    /**
     * 支付时IP
     */
    private String ip;
    /**
     * 唯一标识符
     */
    private String cid;
    /**
     * 唯一订单号
     */
    private String outTradeNo;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 得到的账号名
     */
    private String accountUsername;
    /**
     * 得到的账号Id
     */
    private String accountId;
    /**
     * 埋点
     */
//    TODO 枚举类
    private String state;

}