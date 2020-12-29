package com.kpu.howareu.common.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateManager {

    public static int SAVE_ALL = 0x0000;

    private ArrayList<Integer> saveActivityList = new ArrayList<>();

    {
        saveActivityList.add(SAVE_ALL);
    }

    private Map<Integer, Long> saveMonthMap = new HashMap<>();
    private static int DATE_TYPE_PERIOD = 0x0100;
    private static int DATE_TYPE_START  = 0x0200;
    private static int DATE_TYPE_END    = 0x0400;

    /**
     * Default Date format
     */
    private Map<String, SimpleDateFormat> simpleDateFormat = new HashMap<>();
    {
        simpleDateFormat.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
        simpleDateFormat.put("yy-MM-dd", new SimpleDateFormat("yy-MM-dd"));
        simpleDateFormat.put("yyyy.MM.dd", new SimpleDateFormat("yyyy.MM.dd"));
        simpleDateFormat.put("yy.MM.dd", new SimpleDateFormat("yy.MM.dd"));
        simpleDateFormat.put("yyyyMMdd", new SimpleDateFormat("yyyyMMdd"));
    }

    private static DateManager instance;

    private Calendar calNow;
    private Calendar calPeriod;
    private Calendar calStart;
    private Calendar calEnd;
    private Calendar calNowPeriodBase;

    /**
     * DateManager 생성자
     * @return
     */
    public static synchronized DateManager getInstance() {
        if (instance == null) {
            instance = new DateManager();
        }
        return instance;
    }

    private DateManager() {
        calNow = Calendar.getInstance();
        calPeriod = Calendar.getInstance();
        calStart = Calendar.getInstance();
        calEnd = Calendar.getInstance();
        calNowPeriodBase = Calendar.getInstance();

        setDefaultDate(SAVE_ALL);
    }

    private void setDefaultDate(int saveActivity) {
        int periodDate = 1;
        setPeriodDate(periodDate);

        setMonth(periodDate);

        setStartDate(periodDate);
        setEndDate(periodDate);

        saveTimeAll();
    }

    private void setMonth(int periodDate) {
        // 기준일이 1~15일 인경우..
        if (periodDate <= 15) {
            // 현재일이 기준일 보다 작으면 기본은 전월로 표시
            if (calNowPeriodBase.getTimeInMillis() < calPeriod.getTimeInMillis()) {
                calStart.add(Calendar.MONTH, -1);
                calEnd.add(Calendar.MONTH, -1);
                calPeriod.add(Calendar.MONTH, -1);
            }
            // 현재일이 기준일 보다 크면 현재월로 표시
            else {
            }
        }
        // 기준일이 16이후 인경우..
        else {
            // 현재일이 기준일 보다 크면 다음월로 표시
            if (calNowPeriodBase.getTimeInMillis() >= calPeriod.getTimeInMillis()) {
                calPeriod.add(Calendar.MONTH, 1);
            }
            // 현재일이 기준일 보다 작으면 기본은 현재월로 표시
            else {
                calStart.add(Calendar.MONTH, -1);
                calEnd.add(Calendar.MONTH, -1);
            }
        }
    }

    private void setPeriodDate(int periodDate) {
        if (periodDate == -1) {
            calPeriod.set(Calendar.DATE, calPeriod.getActualMaximum(Calendar.DAY_OF_MONTH) + 1);
        } else if (periodDate > 28) {
            calPeriod.set(Calendar.DATE, calPeriod.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else {
            calPeriod.set(Calendar.DATE, periodDate);
        }
        calPeriod.set(Calendar.HOUR_OF_DAY, 0);
        calPeriod.set(Calendar.MINUTE, 0);
        calPeriod.set(Calendar.SECOND, 0);
        calPeriod.set(Calendar.MILLISECOND, 0);
    }

    private void setStartDate(int periodDate) {
        if (periodDate > 28) {
            if (calStart.getActualMaximum(Calendar.DAY_OF_MONTH) == 28) {
                calStart.set(Calendar.DATE, 28);
            } else {
                calStart.set(Calendar.DATE, periodDate);
            }
        } else if(periodDate == -1) {
            calStart.set(Calendar.DATE, calStart.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else {
            calStart.set(Calendar.DATE, periodDate);
        }
        calStart.set(Calendar.HOUR_OF_DAY, 0);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calStart.set(Calendar.MILLISECOND, 0);
    }

    private void setEndDate(int periodDate) {
        if (periodDate == 1) {
            calEnd.set(Calendar.DATE, calEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else {
            calEnd.add(Calendar.MONTH, 1);
            if (calEnd.getActualMaximum(Calendar.DAY_OF_MONTH) == 28 || periodDate == -1) {
                calEnd.set(Calendar.DATE, calEnd.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
            } else {
                if (calEnd.getActualMaximum(Calendar.DAY_OF_MONTH) < periodDate) {
                    calEnd.set(Calendar.DATE, calEnd.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
                } else {
                    calEnd.set(Calendar.DATE, periodDate - 1);
                }
            }
        }
        calEnd.set(Calendar.HOUR_OF_DAY, 23);
        calEnd.set(Calendar.MINUTE, 59);
        calEnd.set(Calendar.SECOND, 59);
        calEnd.set(Calendar.MILLISECOND, 999);
    }

    private SimpleDateFormat getSimpleDateFormat(String format) {
        SimpleDateFormat sdf;
        try {
            sdf = simpleDateFormat.get(format);
            if (sdf != null) {
                return sdf;
            } else {
                sdf = new SimpleDateFormat(format);
                simpleDateFormat.put(format, sdf);
            }
        } catch (Exception e) {
            sdf = simpleDateFormat.get("yyyy-MM-dd");
        }
        return sdf;
    }

    private void saveTimeAll() {
        for (int saveActivity : saveActivityList) {
            saveTime(saveActivity);
        }
    }

    private void saveTime(int saveActivity) {
        saveMonthMap.put(saveActivity | DATE_TYPE_PERIOD, calPeriod.getTimeInMillis());
        saveMonthMap.put(saveActivity | DATE_TYPE_START, calStart.getTimeInMillis());
        saveMonthMap.put(saveActivity | DATE_TYPE_END, calEnd.getTimeInMillis());
    }

    /**
     * 기준일로부터 요청된 월을 더한 월을 반환한다.
     * @param month 이동할 월
     * @return
     */
    public int getPeriodMonth(int month) {
        Calendar cal = (Calendar) calPeriod.clone();
        cal.add(Calendar.MONTH, month);
        return (cal.get(Calendar.MONTH) + 1);
    }

    /**
     * 기준일의 년도를 반환한다.
     * @return
     */
    public int getPeriodYear() {
        return calPeriod.get(Calendar.YEAR);
    }

    /**
     * 기준일의 월을 반환한다.
     * @return
     */
    public int getPeriodMonth() {
        return (calPeriod.get(Calendar.MONTH) + 1);
    }

    /**
     * 기준일의 시간(DateTime)을 반환한다.
     * @return
     */
    public long getLongPeriodDt() {
        return calPeriod.getTimeInMillis();
    }

    /**
     * 현재 월인지 판단한다.
     * @return true or false
     */
    public boolean isThisMonth() {
        if (calNow.getTimeInMillis() >= calStart.getTimeInMillis() &&
                calNow.getTimeInMillis() <= calEnd.getTimeInMillis()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 요청된 값만큼 월을 이동하고, 변경된 시간값은 saveActivity 별로 저장함.
     * @param month 이동하려는 월(-1 or 1)
     * @param saveActivity
     * @see #saveActivityList
     */
    public void moveMonthAndSaveMonth(int month, int saveActivity) {
        calPeriod.setTimeInMillis(saveMonthMap.get(saveActivity | DATE_TYPE_PERIOD));
        calStart.setTimeInMillis(saveMonthMap.get(saveActivity | DATE_TYPE_START));
        calEnd.setTimeInMillis(saveMonthMap.get(saveActivity | DATE_TYPE_END));

        moveMonth(month);
        saveTime(saveActivity);
    }

    /**
     * 요청된 값만큼 월을 이동한다.<br/>
     * @param month
     */
    public void moveMonth(int month) {
        calPeriod.add(Calendar.MONTH, month);
        calStart.add(Calendar.MONTH, month);
        calEnd.add(Calendar.MONTH, month);
        calNowPeriodBase.add(Calendar.MONTH, month);

        if (calPeriod.get(Calendar.DATE) == 1) {
            calEnd.set(Calendar.DATE, calEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
    }

    /**
     * 기준일의 월 시작 시간을 반환한다.<br/>
     * 기준실적 계산용 1일 (월) 말일
     * @return
     */
    public long getPeriodStartDt() {
        Calendar cal = (Calendar) calPeriod.clone();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 기준일의 월 끝 시간을 반환한다.<br/>
     * 기준실적 계산용 1일 (월) 말일
     * @return
     */
    public long getPeriodEndDt() {
        Calendar cal = (Calendar) calPeriod.clone();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 요청된 시간(DateTime)을 일 기준으로 시작,종료 시간을 반환한다.
     * @param dateTime
     * @return 시작 00시~종료24시
     */
    public static Calendar[] getDailyStartEndTime(long dateTime) {

        Calendar calBase = Calendar.getInstance();
        calBase.setTimeInMillis(dateTime);

        Calendar start = Calendar.getInstance();
        start.set(calBase.get(Calendar.YEAR), calBase.get(Calendar.MONTH), calBase.get(Calendar.DATE));
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        Calendar end = Calendar.getInstance();
        end.set(calBase.get(Calendar.YEAR), calBase.get(Calendar.MONTH), calBase.get(Calendar.DATE));
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 999);

        return new Calendar[] {start, end};
    }

}
