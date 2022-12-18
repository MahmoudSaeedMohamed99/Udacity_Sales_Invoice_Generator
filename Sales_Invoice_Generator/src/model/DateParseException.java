package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParseException {

    //Custom Function to handle Date Parse Exception with String date parameter
    public String parseDate(String date) throws Exception {

        //Creating object from date which get date of the day
        Date dayDate = new Date();

        //Initialize date format that we want to use
        DateFormat df = new SimpleDateFormat("(dd/MM/yyyy)");

        //Using format function to format date into our form
        String dateFormat = df.format(dayDate);

        //Check if date that we send as a parameter if equals the date we have formatted then throw ParseException if it false
        if (!date.equals(dateFormat)) {
            throw new ParseException("Wrong Date Format" , 0);
        } else {
            return date;
        }
    }
}
