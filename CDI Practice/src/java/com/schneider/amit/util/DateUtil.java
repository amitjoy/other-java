package com.schneider.amit.util;

import com.schneider.amit.annotation.Date;
import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class DateUtil implements Serializable {

    private static final int month;
    private static final int day;
    private static final int year;

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
