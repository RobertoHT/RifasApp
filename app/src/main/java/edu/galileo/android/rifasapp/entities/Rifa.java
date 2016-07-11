package edu.galileo.android.rifasapp.entities;

/**
 * Created by praxis on 11/07/16.
 */
public class Rifa {
    private String name;
    private String date;

    public Rifa(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
