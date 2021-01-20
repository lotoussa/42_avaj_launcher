package com.avaj.Tower;

import com.avaj.aircraft.AircraftFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
    public static FileWriter writer;

    public static void main(String[] args) throws IOException {
        if (args.length != 1)
            System.err.println("Usage: java com.avaj.Main [scenario.txt]");
        else {
            int rerun = 0;
            String scenario = args[0];
            File simulation = new File("simulation.txt");
            try {
                writer = new FileWriter(simulation);
            } catch (FileNotFoundException err) {
                System.err.println("Error: " + err.getMessage());
                return;
            }
            AircraftFactory aircraftFactory = new AircraftFactory();
            WeatherTower weatherTower = new WeatherTower();
            try {
                FileReader fr = new FileReader(scenario);
                BufferedReader br = new BufferedReader(fr);
                String line;
                int count = 1;
                while ((line = br.readLine()) != null) {
                    String[] tmp = line.split(" ");
                    if (tmp.length == 1 && tmp[0].equals(""))
                        continue;
                    if (count == 1) {
                        try {
                            rerun = Integer.parseInt(line);
                            if (rerun < 0)
                                throw new NumberFormatException();
                        } catch (NumberFormatException err) {
                            System.err.println("Error: The first line of the file must contains a positive integer number. " +
                                    "This number represents the number of times the simulation is run.");
                            return;
                        }
                    } else {
                        try {
                            if (tmp.length != 5)
                                throw new NumberFormatException();
                            aircraftFactory.newAircraft(
                                    tmp[0],
                                    tmp[1],
                                    Integer.parseInt(tmp[2]),
                                    Integer.parseInt(tmp[3]),
                                    Integer.parseInt(tmp[4])
                            ).registerTower(weatherTower);
                        } catch (NumberFormatException err) {
                            System.err.println("Error: wrong format line " + count + ": " + line + "." +
                                    " Should be: TYPE NAME LONGITUDE LATITUDE HEIGHT.");
                            return;
                        }
                    }
                    count = count + 1;
                }
                fr.close();
            } catch (Exception err) {
                System.err.println("Error: " + err.getMessage());
                return;
            }
            for (int i = 0; i < rerun; i++) {
                weatherTower.changeWeather();
            }
            writer.close();
        }
    }
}
