package com.avaj.aircraft;

import com.avaj.Tower.Coordinates;

public class AircraftFactory {
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        return switch (type.toLowerCase()) {
            case "baloon" -> new Baloon(name, coordinates);
            case "jetplane" -> new JetPlane(name, coordinates);
            case "helicopter" -> new Helicopter(name, coordinates);
            default -> null;
        };
    }
}
