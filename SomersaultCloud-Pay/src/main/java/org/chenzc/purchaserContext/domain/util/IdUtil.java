package org.chenzc.purchaserContext.domain.util;

import org.apache.commons.lang3.StringUtils;
import org.chenzc.common.utils.TimestampConverterUtil;
import org.chenzc.purchaserContext.domain.entity.WxOrder;

public class IdUtil {
    public static String generateOutTradeNo(WxOrder wxOrder) {
        String s = TimestampConverterUtil.timestampToFormattedString(System.currentTimeMillis());
        return StringUtils.join(s, cn.hutool.core.util.IdUtil.getSnowflake().nextIdStr());
    }
}