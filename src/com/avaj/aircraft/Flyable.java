package com.avaj.aircraft;

import com.avaj.Tower.WeatherTower;

public interface Flyable {
    void updateConditions();

    void registerTower(WeatherTower weatherTower);
}
