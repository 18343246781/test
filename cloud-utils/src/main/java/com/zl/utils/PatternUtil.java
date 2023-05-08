package com.zl.utils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

    public PatternUtil() {


    }

    /**
     * 匹配邮箱正则
     */
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * 验证只包含中英文和数字的字符串
     *
     * @param keyword
     * @return
     */
    public static Boolean validKeyword(String keyword) {
        String regex = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(keyword);
        return match.matches();
    }


    /**
     * 判断是否是邮箱
     *
     * @param emailStr
     * @return
     */
    public static boolean isEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    /**
     * 判断是否是网址
     *
     * @param urlString
     * @return
     */
    public static boolean isURL(String urlString) {
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(urlString).matches()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断是否为11位电话号码
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0-8])|(18[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * 生成指定长度的随机数
     *
     * @param length
     * @return
     */
    public static int genRandomNum(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 生成订单流水号
     *
     * @return
     */
    public static String genOrderNo() {
        StringBuffer buffer = new StringBuffer(String.valueOf(System.currentTimeMillis()));
        int num = genRandomNum(4);
        buffer.append(num);
        return buffer.toString();
    }


    /**
     * 返回当前时间 列2022-07-05 14:33:29
     */
    public static String getNowDateTime(){
        String s = LocalDateTime.now().toString().replace("T"," ");
        return s.substring(0,s.length()-4);
    }

    /**
     * 返回当前日期 列2022-07-05
     */
    public static String getNowDate(){
        String s = LocalDateTime.now().toString().replace("T"," ");
        return s.substring(0,s.length()-12);
    }

    /**
     * 返回当前日期 列2022-07
     */
    public static String getNowMonth(){
        String s = LocalDateTime.now().toString().replace("T"," ");
        return s.substring(0,s.length()-16);
    }

    public static String getNowTime(){
        String[] ts = LocalDateTime.now().toString().split("T");
        return ts[1].split("\\.")[0];
    }

    public static String compareToDate(String beginDate,String endDate){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date begin=df.parse(beginDate);
            Date end=df.parse(endDate);
            if (end.after(begin)){
                return "无效";
            }else {
                return "有效";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getHours(){
        Date date = new Date();
        int hours = date.getHours();
        return hours;
    }

    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static void main(String[] args) throws ParseException {
        System.out.println(getNowTime());
    }
}
