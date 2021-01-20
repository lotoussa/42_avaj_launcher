package com.avaj.aircraft;

import com.avaj.Tower.Coordinates;
import com.avaj.Tower.WeatherTower;
import com.avaj.Tower.Main;

import java.io.IOException;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        String info = new String("");

        switch (weather) {
            case "SUN" -> {
                info = "Avec ce temps nous ne risquons rien";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 10,
                        coordinates.getHeight() + 2
                );
            }
            case "RAIN" -> {
                info = "I'm singing in the plane";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 5,
                        coordinates.getHeight()
                );
            }
            case "FOG" -> {
                info = "J'ai jamais aime mettre le FOG dans minecraft";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 1,
                        coordinates.getHeight()
                );
            }
            case "SNOW" -> {
                info = "On peut dire qu'on a le nez en plein dedans";
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 7
                );
            }
        }
        try {
            Main.writer.write("JetPlane#" + this.name + "(" + this.id + "): " + info + ".\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.coordinates.getHeight() == 0) {
            this.weatherTower.unregister(this);
            try {
                Main.writer.write("JetPlane#" + this.name + "(" + this.id + "): landing.\n");
                Main.writer.write("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        try {
            Main.writer.write("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
