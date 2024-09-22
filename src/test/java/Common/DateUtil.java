package Common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
   public static Calendar setYear(Calendar cal, int year) {
       cal.set(Calendar.YEAR, year);
       return cal;
   }
   public static Calendar setMonth(Calendar cal, int month) {
       cal.set(Calendar.MONTH, month);
       return cal;
   }
   public static Calendar setDaOfMonth(Calendar cal, int day) {
       cal.set(Calendar.DAY_OF_MONTH, day);
       return cal;
   }
    public static Calendar setDayOfWeek(Calendar cal, int day) {
        cal.set(Calendar.DAY_OF_WEEK, day);
        return cal;
    }
    public static Calendar setDay(Calendar cal, int day) {
        cal.set(Calendar.DATE, day);
        return cal;
    }
   public static Calendar setHour(Calendar cal, int hour) {
       cal.set(Calendar.HOUR_OF_DAY, hour);
       return cal;
   }
   public static Calendar setMinute(Calendar cal, int minute) {
       cal.set(Calendar.MINUTE, minute);
       return cal;
   }
   public static Calendar setSecond(Calendar cal, int second) {
       cal.set(Calendar.SECOND, second);
       return cal;
   }
   public static Calendar setMillisecond(Calendar cal, int millisecond) {
       cal.set(Calendar.MILLISECOND, millisecond);
       return cal;
   }
   public static Calendar setDate(Calendar cal, Date date) {
       cal.setTime(date);
       return cal;
   }
  public static String getDateYear(String format, int year) {
       Calendar cal = setYear(Calendar.getInstance(), year);
      return getDate(cal, format);
  }

  public static String getDateMonth(String format, int month) {
       Calendar cal = setMonth(Calendar.getInstance(), month);
       return getDate(cal, format);
  }

  public static String getDateDayOfWeek(String format, int day) {
      Calendar cal = setDayOfWeek(Calendar.getInstance(), day);
      return getDate(cal, format);
  }

    public static String getDateDay(String format, int day) {
        Calendar cal = setDay(Calendar.getInstance(), day);
        return getDate(cal, format);
    }
    public static String getDateHour(String format, int hour) {
        Calendar cal = setDay(Calendar.getInstance(), hour);
        return getDate(cal, format);
    }
    public static String getDateMinute(String format, int minute) {
       Calendar cal = setDay(Calendar.getInstance(), minute);
       return getDate(cal, format);
    }
    public static String getDateSecond(String format, int second) {
       Calendar cal = setDay(Calendar.getInstance(), second);
       return getDate(cal, format);
    }
    public static String getDateMillisecond(String format, int millisecond) {
       Calendar cal = setDay(Calendar.getInstance(), millisecond);
       return getDate(cal, format);
    }
    public static String getDate(Calendar cal, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
       return sdf.format(cal.getTime());
  }
}
