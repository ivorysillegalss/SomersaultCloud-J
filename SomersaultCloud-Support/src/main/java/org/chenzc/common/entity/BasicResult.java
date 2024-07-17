package org.chenzc.common.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.chenzc.common.enums.RespEnums;

/**
 * R类
 * @author chenz
 * @date 2024/07/17
 */
@Builder
@Data
@Accessors(chain = true)
public class BasicResult {
    private String code;
    private String message;
    private Object data;

    public static BasicResult success(RespEnums respEnums) {
        return BasicResult.builder().code(respEnums.getCode())
                .message(respEnums.getMessage())
                .build();
    }

    public static BasicResult success(RespEnums respEnums, Object data) {
        return success(respEnums).setData(data);
    }

    public static BasicResult success(){
        return success(RespEnums.SUCCESS);
    }

    public static BasicResult success(Object data){
        return success(RespEnums.SUCCESS,data);
    }

    public static BasicResult fail(RespEnums respEnums) {
        return BasicResult.builder().code(respEnums.getCode())
                .message(respEnums.getMessage())
                .build();
    }

    public static BasicResult fail(RespEnums respEnums, Object data) {
        return fail(respEnums).setData(data);
    }

    public static BasicResult fail(){
        return fail(RespEnums.FAIL);
    }

    public static BasicResult fail(Object data){
        return success(RespEnums.FAIL,data);
    }
}
