package com.example.william.agendacontato.app;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by william on 19/08/2015.
 */
public class DateUtils {


    public static Date getDate(int year, int monthOfYear, int dayOfMonth)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        Date data = calendar.getTime();

        return data;

    }

    public static String DataToString(int year, int monthOfYear, int dayOfMonth) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dt = dateFormat.format(getDate(year, monthOfYear, dayOfMonth));
        return  dt;
    }

    public static String DataToString(Date date) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dt = dateFormat.format(date);
        return  dt;
    }

}
