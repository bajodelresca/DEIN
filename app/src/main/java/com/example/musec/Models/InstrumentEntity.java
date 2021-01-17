package com.example.musec.Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class InstrumentEntity extends RealmObject {
    @PrimaryKey
    private String id;
    private String Name;
    private String Description;
    private String Price;
    private String Date;
    private String Image;
    public String state;
    public boolean bag;
    public InstrumentEntity() {
        this.id="";
        this.Name="";
        this.Description="";
        this.Price="";
        this.Date="";
        this.Image="";
        this.state="";
        this.bag=false;
       }
    public InstrumentEntity(String id,String Name, String Description, String Image) {
        this.id=id;
        this.Name=Name;
        this.Description=Description;
        this.Price="";
        this.Date="";
        this.Image=Image;
        this.state="";
        this.bag=false;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isBag() {
        return bag;
    }

    public void setBag(boolean bag) {
        this.bag = bag;
    }

    public boolean setName(String name) {
        if(name.length()>5) {
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
        if(image.length()<1){
            Image="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";

    }else{
            Image = image;
        }}
    public void setId(String Id) {
        id = Id;
    }

    @Override
    public String toString() {
        return "InstrumentEntity{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Price='" + Price + '\'' +
                ", Date='" + Date + '\'' +
                ", Image='" + Image + '\'' +
                ", state='" + state + '\'' +
                ", bag=" + bag +
                '}';
    }
}
