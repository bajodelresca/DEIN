package com.example.musec.Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstrumentEntity {
    private String id;
    private String Name;
    private String Description;
    private String Price;
    private String Date;
    private String Image;
    public InstrumentEntity() {
       }
    public String getId() {
        return id;
    }
    public String getName() {
        return Name;
    }
    public String getDescription() {
        return Description;
    }
    public String getPrice() {
        return Price;
    }
    public String getDate() {
        return Date;
    }
    public String getImage() {
        return Image;
    }

    public boolean setName(String name) {
        if(name.startsWith("a")) {
            Name = name;
            return true;
        }else{
            return false;
        }
    }
    public boolean setDescription(String description) {
        if(description.length()>5) {
            Description = description;
            return true;
        }else{
            return false;
        }
    }
    public boolean setPrice(String price) {
        Pattern pat = Pattern.compile("[0-9]");
        Matcher mat = pat.matcher(price);
        if (mat.find()) {
            this.Price = price;
            return true;
        } else {
            return false;
        }
    }
    public boolean setDate(String date) {
        Pattern pat = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher mat = pat.matcher(date);
        if (mat.find()) {
            this.Date = date;
            return true;
        } else {
            return false;
        }
    }

    public void setImage(String image) {
        Image = image;
    }
    public void setId(String Id) {
        id = Id;
    }
}
