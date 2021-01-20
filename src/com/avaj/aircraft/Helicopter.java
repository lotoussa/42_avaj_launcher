package com.avaj.aircraft;

import com.avaj.Tower.Coordinates;
import com.avaj.Tower.Main;
import com.avaj.Tower.WeatherTower;

import java.io.IOException;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        String info = new String("");

        switch (weather) {
            case "SUN" -> {
                info = "Don't touch the button please";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 10,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 2
                );
            }
            case "RAIN" -> {
                info = "Si j'avais su..";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 5,
                        coordinates.getLatitude(),
                        coordinates.getHeight()
                );
            }
            case "FOG" -> {
                info = "bip bip... bip bip..";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 1,
                        coordinates.getLatitude(),
                        coordinates.getHeight()
                );
            }
            case "SNOW" -> {
                info = "Papa regarde, les helices sont entrain de geler";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 12
                );
            }
        }
        try {
            Main.writer.write("Helicopter#" + this.name + "(" + this.id + "): " + info + ".\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.coordinates.getHeight() == 0) {
            this.weatherTower.unregister(this);
            try {
                Main.writer.write("Helicopter#" + this.name + "(" + this.id + "): landing.\n");
                Main.writer.write("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        try {
            Main.writer.write("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
