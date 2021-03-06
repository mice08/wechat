package com.mk.common.toolutils;

import org.apache.commons.lang3.StringUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {

      /** 时间格式. */
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式. */
    public static final String FORMAT_DATE_CN = "yyyy年MM月dd日";
    /** 时间格式. */
    public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss:SSS";
    /** 到小时分钟的日期格式. */
    public static final String FORMAT_DATETIME_HM = "yyyy-MM-dd HH:mm";
    /** 全时间格式. */
    public static final String FORMAT_FULLTIME = "yyMMddHHmmssSSS";
    /** 日期格式. */
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    /** 日期格式. */
    public static final String FORMAT_YEARMONTH = "yyyy-MM";
    /** 纯时间格式. */
    public static final String FORMAT_TIME = "HH:mm:ss";

    public static final String FORMAT_HOURMIN = "HHmm";


    public static final String FORMATDATETIME = "yyyyMMddHHmmss";

    public static final String FORMATSHORTDATETIME = "yyyyMMdd";

    public static Date toDate(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        if (!StringUtils.isEmpty(time)) {
            try {
                date = sdf.parse(time);
                long datetimes = (date.getTime() / (60 * 1000)) * 60 * 1000;
                return new Date(datetimes);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

    }

    public static Date toBeginTime(String time) {


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mmaa", Locale.US);
        Date date = null;
        if (!StringUtils.isEmpty(time)) {
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static Date gregoriantoDate(XMLGregorianCalendar xmlGregorianCalendar) {
        if (null == xmlGregorianCalendar) {
            return null;
        }
        GregorianCalendar gregorianCalendar = xmlGregorianCalendar
                .toGregorianCalendar();
        return gregorianCalendar.getTime();
    }

    public static String toString(Date time) {
        if (null == time) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }


    /**
     * ������ʱ���ȥ��Ӧ��ʱ�� �磺����ʱ����������dataʱ���ȥ��Сʱ
     */
    public static XMLGregorianCalendar getUTCDate(XMLGregorianCalendar data) {
        if (null == data) {
            return null;
        }
        int duration = data.getTimezone();
        Duration du = null;
        try {
            du = DatatypeFactory.newInstance().newDuration(false, 0, 0, 0, 0,
                    duration, 0);
        } catch (DatatypeConfigurationException e) {
            return null;
        }
        data.add(du);
        return data;
    }

    /**
     * Date����תXMLGregorianCalendar���� * @param date * @return
     */
    public static XMLGregorianCalendar toGregorianCalendarDate(Date date) {
        return toGregorianCalendarDateSMC(date);
    }

    /**
     * Date����תXMLGregorianCalendar���� * @param date * @return ����ԤԼ���ڻ��鷵�ص����
     */
    public static XMLGregorianCalendar toGregorianCalendarDateSMC(Date date) {

        if (null == date) {
            return null;
        }
        GregorianCalendar nowGregorianCalendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDatetime = null;
        try {
            xmlDatetime = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(nowGregorianCalendar);
            nowGregorianCalendar.setTime(date);
            xmlDatetime = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(nowGregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            return null;
        }
        return xmlDatetime;
    }

    /**
     * ��String���͵�dataת����XMLGregorianCalendar���͵�date ����ʱ�����ͣ�yyyy-MM-dd HH:mm:ss
     * ����XMLGregorianCalendar���͵�ʱ��
     *
     * @param date
     * @return
     */
    public static XMLGregorianCalendar toGregorianCalendarDate(String date) {

        if (StringUtils.isEmpty(date)) {
            return null;
        }
        return toGregorianCalendarDateSMC(DateUtil.toDate(date));
    }

    /**
     * ��ȡ��ǰϵͳʱ��
     *
     * @param type Ҫ���ص�ʱ�����ڸ�ʽ���磺yyyy-MM-dd HH:mm:ss
     * @return ת���������ʱ�䣬String����
     */
    public static String getNowDate(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);//�������ڸ�ʽ
        return df.format(new Date());
    }

    /**
     * ��ȡ����ָ������N���Ժ������ʱ��
     *
     * @param date     ���㿪ʼ����ʱ�䣬String����
     * @param interval ���������int����
     * @return ������µ�����ʱ�䣬Date����
     */
    public static Date getNextDate(String date, int interval) {
        Date dt = new Date();
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        dt = DateUtil.toDate(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dt);
        calendar.add(Calendar.DATE, interval);//�������������ӵ�����.����������,������ǰ�ƶ�
        dt = calendar.getTime();   //���ʱ��������������ƵĽ��
        return dt;
    }

    /**
     * �õ������ַ���
     * exmple �������ڸ�ʽ
     *
     * @return
     */
    public static String getStringDate(String exmple) {
        if (null == exmple || exmple.trim().length() == 0) {
            exmple = "yyyy-MM-dd HH:mm:ss";
        }
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat(exmple);
        String result = matter1.format(dt);
        return result;
    }

    public String getStartDateLocalMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //��ȡǰ�µĵ�һ��
        Calendar cal_1 = Calendar.getInstance();//��ȡ��ǰ����
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//����Ϊ1��,��ǰ���ڼ�Ϊ���µ�һ��
        String firstDay = format.format(cal_1.getTime());
        System.out.println("-----1------firstDay:" + firstDay);
        //��ȡǰ�µ����һ��
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);//����Ϊ1��,��ǰ���ڼ�Ϊ���µ�һ��
        String lastDay = format.format(cale.getTime());
        System.out.println("-----2------lastDay:" + lastDay);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//����Ϊ1��,��ǰ���ڼ�Ϊ���µ�һ��
        String first = format.format(c.getTime());
        System.out.println("===============first:" + first);

        //��ȡ��ǰ�����һ��
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:" + last);

        return null;
    }

    /**
     * Parameter lastDay ���㵱ǰʱ���ǰ����
     * Parameter  str  ���ص�ʱ���ʽ
     *
     * @return String���ͣ�yyyy-MM-dd
     */
    public static String getLastDayStr(Integer lastDay, String str) {
        SimpleDateFormat matter1 = new SimpleDateFormat(str);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, lastDay);
        String result = matter1.format(ca.getTime());
        return result;
    }

    /**
     * ��ȡ�������ڵĲ�ֵ
     *
     * @param smdate
     * @param bdate
     * @return bdate -  smdate
     * @throws ParseException
     */
    public static int daysBetween(String bdate, String smdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }




    /*
     获取两日期的天数差
     */
    public static int daysBetween(String bdate, String smdate, String exmple) throws ParseException {
        if (StringUtils.isEmpty(exmple)) {
            exmple = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(exmple);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取两日期的时间差
     */
    public  static  Long  timesBetween(String  btime,String  etime,String exmple)  throws   ParseException{
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date  dbtime = df.parse(btime);
        Date  detime = df.parse(etime);
        Long  diff = detime.getTime()-dbtime.getTime();
        Long  time = diff/(1000);
        return  time;
    }




    public static String getDay(Integer lastDay, String day, String str) throws ParseException {
        SimpleDateFormat matter1 = new SimpleDateFormat(str);
        Date d = matter1.parse(day);
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);
        ca.add(Calendar.DAY_OF_MONTH, lastDay);
        String result = matter1.format(ca.getTime());
        return result;
    }

    /**
     * ����uudi��������
     *
     * @return
     */
    public static String createSid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }


    public static String getStrFormart(String str, String exmple) {
        if (StringUtils.isEmpty(exmple)) {
            exmple = "yyyy-MM-dd";
        }
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        str = str.substring(0, exmple.length());
        return str;
    }

    /**
     * 根据年月日计算星期
     * @param pTime
     * @param exmple
     * @return
     */
    public static int getIntWeek(String pTime, String exmple) throws  ParseException{
        if (StringUtils.isEmpty(exmple)) {
            exmple = "yyyyMMdd";
        }

        SimpleDateFormat format = new SimpleDateFormat(exmple);
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获取时间的时间和分
     * @param time
     * @return
     */
    public  static  String  getTime(String  time,String  exmple)throws  Exception{
        if(StringUtils.isEmpty(exmple)){
            exmple = FORMAT_DATETIME;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(exmple);
        Date da = sdf.parse(time);
        int hours = da.getHours();
        int minutes =da.getMinutes();

        return  hours+":" + (minutes<10?("0"+minutes):minutes);

    }
    public  static  String  getMonthAndDay(String dateTime,String  exmple)throws  Exception{
        if(StringUtils.isEmpty(exmple)){
            exmple = FORMATDATETIME;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(exmple);

        Date  da = sdf.parse(dateTime);
        Calendar ca = Calendar.getInstance();
        ca.setTime(da);
        int month=ca.get(Calendar.MONTH)+1;
        int day=ca.get(Calendar.DATE);
        return (month<10?"0"+month:month)+"-"+(day<10?"0"+day:day);
    }


    public  static void main(String[] args)throws  Exception{
      System.out.println(DateUtil.getTime("20160109101920109",FORMATDATETIME));

    }
}
