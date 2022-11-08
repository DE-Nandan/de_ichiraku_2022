package com.example.de_ichiraku.Model;

public class Products {
    private String pname, description, price, img, category, pid, date, time,rating;

    public Products() {

    }

    public Products(String pname, String description, String price, String img, String category, String pid, String date, String time) {
        this.pname = pname;
        this.description = description;
        this.price = price;
        this.img = img;
        this.category = category;
        this.pid = pid;
        this.date = date;
        this.time = time;
        this.rating=rating;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}