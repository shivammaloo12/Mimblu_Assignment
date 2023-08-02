package com.shivam.mimblu;

public class plans {

    Integer id;
    String title;
    String desc;
    String duration;
    String video_desc;
    String final_price;
    String discPrice;
    String currency_code;
    String discCalcPrice;

    plans(){

    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc=desc;
    }

    public String getVideoDesc(){
        return video_desc;
    }
    public void setVideoDesc(String desc){
        this.video_desc=video_desc;
    }

    public String getDuration(){
        return duration;
    }
    public void setDuration(String duration){
        this.duration=duration;
    }

    public String getPrice(){
        return final_price;
    }
    public void setPrice(String final_price){
        this.final_price=final_price;
    }

    public void setDiscountedPrice(String discounted_price) {
        discPrice=discounted_price;
    }


    public String getDiscountedPrice() {
        return discPrice;
    }

    public String getCurrCode() {
        return currency_code;
    }
    public void setCurrCode(String code){
        currency_code=code;
    }

    public String getDiscCalcPrice() {
        return discCalcPrice;
    }
    public void setDiscCalcPrice(String discCalcPrice){
        this.discCalcPrice=discCalcPrice;
    }
}
