package demo;

import java.util.Calendar;
import java.util.Date;

public class ADemo {
    public static int timeSeconds() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        long start = calendar.getTime().getTime();
        long end = System.currentTimeMillis();
        int seconds = (int) ((start - end) / 1000);
        return seconds;
    }

    public static int time2DayLast() {
        long dayMillSecond = 24 * 3600 * 1000L;
        return (int) (dayMillSecond - ((System.currentTimeMillis() + 8 * 3600 * 1000) % dayMillSecond)) / 1000;
    }

    public static void main(String[] args) {

    }

}