package br.iesb.android.tracking.models;

import org.parceler.Parcel;

@Parcel
public class Location {

    String name;
    String imageUrl;
    double latitude;
    double longitude;
    private String pushId;
    String index;

    public Location() {
    }

    public Location(String name, String imageUrl, double latitude, double longitude) {
        this.name = name;
        this.imageUrl = getLargeImageUrl(imageUrl);
        this.latitude = latitude;
        this.longitude = longitude;
        this.index = "not_specified";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
        return largeImageUrl;
    }

    public String getPushId(){
        return pushId;
    }

    public void setPushId(String pushId){
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

}
