package com.amitinside.util;

import com.amitinside.annotation.Date;
import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.enterprise.inject.Produces;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
public class DateUtil implements Serializable {

    private static final int month;
    private static final int day;
    private static final int year;
    private static final long serialVersionUID = 1L;

    static {
        GregorianCalendar cal = new GregorianCalendar();
        month = cal.get(GregorianCalendar.MONTH);
        day = cal.get(GregorianCalendar.DAY_OF_MONTH);
        year = cal.get(GregorianCalendar.YEAR);
    }

    @Produces
    @Date
    int getDate() {
        return day;
    }
}
