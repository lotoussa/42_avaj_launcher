package com.avaj.aircraft;

import com.avaj.Tower.Coordinates;
import com.avaj.Tower.WeatherTower;
import com.avaj.Tower.Main;

import java.io.IOException;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        String info = new String("");

        switch (weather) {
            case "SUN" -> {
                info = "Regardez en bas, c'est Jack Sparrow !";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4
                );
            }
            case "RAIN" -> {
                info = "Ah non ce sont des sirenes";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5
                );
            }
            case "FOG" -> {
                info = "Pas mal ce sauna";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3
                );
            }
            case "SNOW" -> {
                info = "*claquement de dents*";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 15
                );
            }
        }
        try {
            Main.writer.write("Baloon#" + this.name + "(" + this.id + "): " + info + ".\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.coordinates.getHeight() == 0) {
            this.weatherTower.unregister(this);
            try {
                Main.writer.write("Baloon#" + this.name + "(" + this.id + "): landing.\n");
                Main.writer.write("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        try {
            Main.writer.write("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
