package com.threeking.service.user.utils;

import com.threeking.service.user.common.Constants;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class LoginUtil {
    public static long getLoginCacheTime() {
        Date startDate = new Date();
        Date endDate = DateUtils.addDays(startDate, Constants.TIME_CACHE_USER);

        return (endDate.getTime() - startDate.getTime()) / 1000 / 60;
    }
}