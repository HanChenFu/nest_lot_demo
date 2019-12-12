package com.hc.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


public final class MyDateUtil {
    private static SimpleDateFormat[] sdfs = {new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
            new SimpleDateFormat("yyyy-MM-dd HH:mm"), new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("HH:mm:ss"), new SimpleDateFormat("HH:mm"), new SimpleDateFormat("HHmm"), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("yyyy年MM月dd日")};

    // 创建 Pattern 对象
    private static String rex = "\\d";

    public static void main(String[] args) throws ParseException {
    }
    /**
     * 日期加上几小时后的日期
     * @param day
     * @param hour
     * @return
     */
    public static String addDateMinut(String day, int hour){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }

    /**
     * String 转日期
     *
     * @param source
     * @return
     */
    public static Date getDate(String source) {
//		System.out.println("时间转换：" + source);
        // 现在创建 matcher 对象 判断是不是时间戳的形式
        if (Pattern.matches(rex, source)) {
            Long time = Long.parseLong(source);
            // 转成直接返回
            return new Date(time);
        } else {
            for (SimpleDateFormat sdf : sdfs) {
                try {
                    // 转成直接返回
                    return sdf.parse(source);
                } catch (ParseException e) {

                }
            }
        }
        // 如果参数绑定失败返回 null
        return null;
    }

    //由出生日期获得年龄
    /**
     * 通过天数计算年龄
     *
     * @author yjw
     * @date 2019年4月8日上午10:10:24
     */
    public static String getAge(int day) {
        int days = Math.abs(day);
        if (day >= 365) {
            int year = days / 365;
            int temp = days - 365;
            int month = temp / 30;
            int d = temp % 30;
            return year + "岁" + month + "个月" + "+" + d + "天";
        }
        if (day < 365) {
            int month = day / 30;
            int d = day % 30;
            System.out.println(month + "个月" + "+" + d + "天");
            return month + "个月" + "+" + d + "天";
        }
        return null;
    }


    /**
     * 获取当前日期：yyyy-MM-dd
     *
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat sdf = sdfs[2];
        return sdf.format(new Date());
    }

    /**
     * 获取当前日期时间：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNowDateTime() {
        SimpleDateFormat sdf = sdfs[0];
        return sdf.format(new Date());
    }

    /**
     * 获取当前日期类型：格式自定义
     *
     * @param pattern
     * @return
     */
    public static String getNowByCustom(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    /**
     * 格式化日期：格式自定义
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 格式化日期：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getString(Date date) {
        SimpleDateFormat sdf = sdfs[0];
        return sdf.format(date);
    }

    /**
     * 计算两个日期相差的天数
     *
     * @author DDM 2018年9月4日
     */
    public static int getTimeDistance(Date beginDate, Date endDate) {
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(beginDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        long beginTime = beginCalendar.getTime().getTime();
        long endTime = endCalendar.getTime().getTime();
        int betweenDays = (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24));// 先算出两时间的毫秒数之差大于一天的天数
        endCalendar.add(Calendar.DAY_OF_MONTH, -betweenDays);// 使endCalendar减去这些天数，将问题转换为两时间的毫秒数之差不足一天的情况
        endCalendar.add(Calendar.DAY_OF_MONTH, -1);// 再使endCalendar减去1天
        if (beginCalendar.get(Calendar.DAY_OF_MONTH) == endCalendar.get(Calendar.DAY_OF_MONTH))// 比较两日期的DAY_OF_MONTH是否相等
            return betweenDays + 1; // 相等说明确实跨天了
        else
            return betweenDays + 0; // 不相等说明确实未跨天
    }

    /**
     * 通过天数计算周和天
     *
     * @author JH
     * @date 2019年4月8日上午10:10:24
     */
    public static String getWeekDay(int day) {
        int days = Math.abs(day);
        int week = days / 7;
        int d = days % 7;
        System.out.println(week + "周" + "+" + d % 7 + "天");
        return week + "周" + "+" + d + "天";
    }

    /**
     * 通过天数计算月和天
     *
     * @author yjw
     * @date 2019年4月8日上午10:10:24
     */
    public static String getMonthDay(int day) {
        int days = Math.abs(day);
        if (day >= 365) {
            int year = days / 365;
            int temp = days - 365;
            int month = temp / 30;
            int d = temp % 30;
            return year + "年" + month + "月" + "+" + d + "天";
        }
        if (day < 365) {
            int month = day / 30;
            int d = day % 30;
            System.out.println(month + "月" + "+" + d + "天");
            return month + "月" + "+" + d + "天";
        }
        return null;
    }

    /**
     * 输入天数输出月日
     *
     * @param day
     * @return
     */
    public static String getDay(int day) {
        int days = Math.abs(day);
        if (day >= 365) {
            int year = days / 365;
            int temp = days - 365;
            int month = temp / 30;
            int d = temp % 30;
            System.out.println(year + "年" + month + "月" + "+" + d + "天");
            return year + "年" + month + "月" + "+" + d + "天";
        }
        if (day < 365) {
            int month = day / 30;
            int d = day % 30;
            System.out.println(month + "月" + "+" + d + "天");
            return month + "月" + "+" + d + "天";
        }
        return null;
    }

    /**
     * 通过天数计算10个月之前的日期
     *
     * @author JH
     * @date 2019年4月8日上午10:10:24
     */
    public static Date beforeDate(Date sourceDate, int month) {
        DateFormat DATE_FORMAT = sdfs[0];
        System.out.println("当前日期：" + sourceDate);
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);
        Date newDate = c.getTime();
        System.out.println("当前时间前10个月的日期：" + DATE_FORMAT.format(newDate));
        return newDate;
    }


    /**
     * 判断选择的日期是否是今天
     */
    public static boolean isToday(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

    /**
     * 获取两个日期之间相差多少月
     *
     * @param date1 <String>
     * @param date2 <String>
     * @return int
     * @throws ParseException
     */
    public static int getMonthSpace(String date1, String date2)
            throws ParseException {
        int result = 0;
        SimpleDateFormat sdf = sdfs[2];
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        return result == 0 ? 1 : Math.abs(result);
    }

    /**
     * 指定日期加上天数后的日期
     *
     * @param num     为增加的天数
     * @param newDate 创建时间
     * @return
     * @throws ParseException
     */
    public static String addDate(long day, String newDate) throws ParseException {
        SimpleDateFormat format = sdfs[2];
        Date date = format.parse(newDate);
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return format.format(new Date(time)); // 将毫秒数转换成日期
    }

    /**
     * 计算两个日期时间差多少个小时
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static double getDistanceTime2(String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = sdfs[0];
        double hour = 0;
        long time1 = format.parse(startTime).getTime();
        long time2 = format.parse(endTime).getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        hour = (diff / (60 * 60 * 1000));
        return hour;
    }

    /**
     * 判断时间是不是今天
     *
     * @param date
     * @return 是返回true，不是返回false
     * @throws ParseException
     */
    public static boolean isNow(String d) throws ParseException {
        SimpleDateFormat sf = sdfs[2];
        Date date = sf.parse(d);
        //当前时间
        Date now = new Date();
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    /**
     * 计算两个日期之间的相差天数
     *
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static long getBetweenDate(String start, String end) throws ParseException {
        //设置转换的日期格式
        SimpleDateFormat sdf = sdfs[2];
        //开始时间
        Date startDate = sdf.parse(start);
        //结束时间
        Date endDate = sdf.parse(end);
        //得到相差的天数 betweenDate
        return (endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000);
    }
    
    /*
     * 这边是判断两个日期前后
     * time1 之前的时间
     * time2 之后的时间
     */
    public static boolean compare(String time1,String time2) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date a=sdf.parse(time1);
		Date b=sdf.parse(time2);
		if(a.before(b))
			return true;
		else
			return false;
	}

}
