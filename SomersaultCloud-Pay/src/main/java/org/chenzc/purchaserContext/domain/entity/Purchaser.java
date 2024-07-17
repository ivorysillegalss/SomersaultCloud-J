package org.chenzc.purchaserContext.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

/**
 * 记录购买者的信息
 *
 * @author chenz
 * @date 2024/07/17
 */
@Data
@Builder
public class Purchaser {
    private String qrCode;
    private String email;

    /**
     * 验证邮箱格式
     * @param email
     * @return {@link String }
     */
    public Boolean validateEmail(String email) {
//        判断是否为空 & 符合邮箱格式
        return !email.isBlank() || !EmailValidator.getInstance().isValid(email);
    }

}
