
package com.mhksoft.novineiran.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "tag",
        "rate",
        "price"
})
public class Course implements Parcelable {

    public final static Creator<Course> CREATOR = new Creator<Course>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        public Course[] newArray(int size) {
            return (new Course[size]);
        }

    };
    @JsonProperty("name")
    private String name;
    @JsonProperty("tag")
    private String tag;
    @JsonProperty("rate")
    private double rate;
    @JsonProperty("price")
    private int price;

    protected Course(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.tag = ((String) in.readValue((String.class.getClassLoader())));
        this.rate = ((double) in.readValue((double.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Course() {
    }

    /**
     * @param rate
     * @param price
     * @param name
     * @param tag
     */
    public Course(String name, String tag, double rate, int price) {
        super();
        this.name = name;
        this.tag = tag;
        this.rate = rate;
        this.price = price;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("tag")
    public String getTag() {
        return tag;
    }

    @JsonProperty("tag")
    public void setTag(String tag) {
        this.tag = tag;
    }

    @JsonProperty("rate")
    public double getRate() {
        return rate;
    }

    @JsonProperty("rate")
    public void setRate(double rate) {
        this.rate = rate;
    }

    @JsonProperty("price")
    public int getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(int price) {
        this.price = price;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(tag);
        dest.writeValue(rate);
        dest.writeValue(price);
    }

    public int describeContents() {
        return 0;
    }

}
