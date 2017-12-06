package com.one;

/**
 * Created by ben on 06-12-2017.
 *
 */
public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {

        validateDate(day, month, year);

        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Static factory method to create Date from given string.
     *
     * @param date the String representing date in format "dd-MM-yyyy"
     *
     * @throws IllegalArgumentException if date is in incorrect format
     * @throws NumberFormatException if illegal character passed in place of digits
     */
    public static Date createDate(String date) {
        if(date==null || date.isEmpty() ||!date.contains("-")){//additional check can be added as per the format
            throw new IllegalArgumentException("Invalid date.Date should be in dd-MM-yyyy format");
        }
        String[] dateArray=date.split("-");
        return new Date(
                Integer.valueOf(dateArray[0]),
                Integer.valueOf(dateArray[1]),
                Integer.valueOf(dateArray[2])
        );
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    private void validateDate(int day, int month, int year){
        if(day<1 || day >31 ||( month==2 && day > 29)){
            throw new IllegalArgumentException("Illegal day " +day);
        }
        if(month>12 ||month<1){
            throw new IllegalArgumentException("Illegal month " +month);
        }
        if(year<0){
            throw new IllegalArgumentException("Illegal year " +year);
        }
    }
}
