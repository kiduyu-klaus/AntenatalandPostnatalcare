package com.project.diana.antenatalandpostnatalcare;

/**
 * Created by Kiduyu klaus
 * on 08/10/2020 10:23 2020
 */
public class Appointment {

    String name, age, location, image, gender, description,time, date,status;


    public Appointment() {

    }


    public Appointment(String name, String age, String location, String image, String gender, String description, String time, String date, String status) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.image = image;
        this.gender = gender;
        this.description = description;
        this.time = time;
        this.date = date;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
