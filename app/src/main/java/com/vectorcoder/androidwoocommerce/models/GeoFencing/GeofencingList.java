
package com.vectorcoder.androidwoocommerce.models.GeoFencing;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeofencingList {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("langitude")
    @Expose
    private double langitude;
    @SerializedName("radius")
    @Expose
    private float radius;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    public double getLangitude() {
        return langitude;
    }
    
    public void setLangitude(double langitude) {
        this.langitude = langitude;
    }
    
    public float getRadius() {
        return radius;
    }
    
    public void setRadius(float radius) {
        this.radius = radius;
    }
    
}
