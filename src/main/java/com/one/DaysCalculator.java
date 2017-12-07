package com.one;

import java.util.stream.IntStream;

/**
 * Created by ben on 06-12-2017.
 */
public class DaysCalculator {


    /**
     * Calculate difference in number of days between two given dates.<p>
     *
     * Dates should be in "dd-MM-yyyy" format now.But this can be changed in date parsing in {@link Date} class
     *
     * Implementation Detail:
     *
     * 1) If dates are from same year , then number of the days are calculated from the beginning
     *   of the year and difference is returned .
     * 2) If date are from different year, then smaller year is taken as reference and days are calculated from the beginning
     *   of the smaller year and difference is returned.
     *
     *
     * @param date1 the String, representing first date in "dd-MM-yyyy" format
     * @param date2 the String, representing second date in "dd-MM-yyyy" format
     * @return absolute value of number of days between given dates
     *
     * @throws IllegalArgumentException if date is in incorrect format
     * @throws NumberFormatException    if illegal character passed in place of digits
     */
    public long calculateDayDifference(String date1, String date2){
        Date d1 = Date.createDate(date1);
        Date d2 = Date.createDate(date2);

        if(d1.getYear()== d2.getYear()){
            return Math.abs(numberOfDaysFromTheBeginningOfThisYear(d1)- numberOfDaysFromTheBeginningOfThisYear(d2));
        }
        Date smallerDate;
        Date biggerDate;
        if(d1.getYear() > d2.getYear()){
            biggerDate=d1;
            smallerDate=d2;
        }else {
            biggerDate=d2;
            smallerDate=d1;
        }
        return Math.abs(
                numberOfDaysFromTheBeginningOfGivenYear(biggerDate,smallerDate.getYear())
                - numberOfDaysFromTheBeginningOfThisYear(smallerDate)
        );
    }

    private boolean isLeapYear(int year){
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    private long numberOfDaysFromTheBeginningOfGivenYear(Date date, int year){
        long daysBeforeYearStart = IntStream.range(year, date.getYear())
                                        .parallel()
                                        .mapToLong(y->numberOfDaysInYear(y))
                                        .sum();

/*                LongStream.range(year, date.getYear())
                .map(y->numberOfDaysInYear((int)y))
                .sum();*/
        return daysBeforeYearStart+ numberOfDaysFromTheBeginningOfThisYear(date);
    }

    private long numberOfDaysFromTheBeginningOfThisYear(Date date){
        return date.getDay()+ numberOfDaysInThisYearBeforeThisMonth(date.getMonth(),date.getYear());
    }
    private long numberOfDaysInMonth(int month, int year){

        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year)? 29 : 28;
            default:
                throw new IllegalArgumentException("month number is incorrect");
        }


/*        if(month== 1 || month==3 || month==5 || month==7 ||month==8||month==10||month==12){
            return 31;
        }else if(month==4||month==6||month==9||month==11){
            return 30;
        }else if(month==2){
            return isLeapYear(year)? 29 : 28;
        }else {
            throw new IllegalArgumentException("month number is incorrect");
        }*/
    }

    private long numberOfDaysInYear(int year){
        if(isLeapYear(year)){
            return 366;
        }else {
            return 365;
        }
    }

    private long numberOfDaysInThisYearBeforeThisMonth(int month,int year){
        //calculating number of days before this month start
/*        return LongStream.range(1,month)
                .map(m -> numberOfDaysInMonth((int) m,year))
                .sum();*/

        return IntStream.range(1,month).parallel()
                .mapToLong(i->numberOfDaysInMonth(i,year))
                .sum();
    }
}
