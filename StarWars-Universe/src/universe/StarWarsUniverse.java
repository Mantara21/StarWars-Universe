package universe;

import model.Planet;

import java.util.HashMap;
import java.util.Map;

public class StarWarsUniverse {
    private Map<String, Planet> planets = new HashMap<>();

    public void addPlanet(String name) {
        if (!planets.containsKey(name)) {
            planets.put(name, new Planet(name));
            System.out.println("Planet " + name + " added.");
        } else {
            System.out.println("Planet already exists.");
        }
    }

    public Planet getPlanet(String name) {
        return planets.get(name);
    }

    public void printUniverse() {
        planets.values().forEach(System.out::println);
    }
}