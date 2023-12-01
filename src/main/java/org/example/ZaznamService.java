package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ZaznamService {
    private Date datum;
    private String text;

    public ZaznamService(Date datum, String text) {
        this.datum = datum;
        this.text = text;
    }

    public Date getDatum() {
        return datum;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Datum: " + dateFormat.format(datum) + "\nText: " + text;
    }
}
