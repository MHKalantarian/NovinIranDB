package com.mhksoft.novineiran.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "tag",
        "items"
})
public class Category implements Parcelable {

    public final static Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    };
    @JsonProperty("name")
    private String name;
    @JsonProperty("tag")
    private String tag;
    @JsonProperty("items")
    private List<Course> courses = new ArrayList<Course>();

    protected Category(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.tag = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.courses, (Course.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public Category() {
    }

    /**
     * @param name
     * @param tag
     * @param courses
     */
    public Category(String name, String tag, List<Course> courses) {
        super();
        this.name = name;
        this.tag = tag;
        this.courses = courses;
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

    @JsonProperty("items")
    public List<Course> getCourses() {
        return courses;
    }

    @JsonProperty("items")
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(tag);
        dest.writeList(courses);
    }

    public int describeContents() {
        return 0;
    }

}
