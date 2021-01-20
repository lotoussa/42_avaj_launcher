package com.avaj.Tower;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = Math.max(longitude, 0);
        this.latitude = Math.max(latitude, 0);
        this.height = height < 0 ? 0 : Math.min(height, 100);
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }
}
