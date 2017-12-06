package com.one;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Created by ben on 06-12-2017.
 */
public class DaysCalculatorTest {

    DaysCalculator daysCalculator;

    @Before
    public void setup() {
        daysCalculator= new DaysCalculator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void exception_is_thrown_if_given_date_is_in_incorrect_format(){
        String date1="01.01.2017";
        daysCalculator.calculateDayDifference(date1,date1);
    }

    @Test
    public void difference_in_dates_within_same_year_is_calculated_correctly(){
        String date1="01-01-2017";
        String date2="27-12-2017";
        Assert.assertEquals("days difference should be same"
                ,dayDifferenceUsingJava8(date1,date2)
                ,daysCalculator.calculateDayDifference(date1,date2));
    }
    @Test
    public void difference_in_dates_from_different_years_should_be_calculated_correctly(){
        String date1="01-01-2017";
        String date2="27-12-2015";
        Assert.assertEquals("days difference should be same"
                ,dayDifferenceUsingJava8(date1,date2)
                ,daysCalculator.calculateDayDifference(date1,date2));

    }
    @Test
    public void difference_in_dates_should_calculated_correctly_when_leap_year_fall_in_between(){
        String date1="01-01-2017";
        String date2="27-12-1991";
        Assert.assertEquals("days difference should be same"
                ,dayDifferenceUsingJava8(date1,date2)
                ,daysCalculator.calculateDayDifference(date1,date2));
    }

    private long dayDifferenceUsingJava8(String date1, String date2){
        DateTimeFormatter df= DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate1= LocalDate.parse(date1,df);
        LocalDate localDate2= LocalDate.parse(date2,df);
        return Math.abs(ChronoUnit.DAYS.between(localDate1, localDate2));
    }
}
