package universe;

import model.Planet;
import model.*;

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

    public boolean createJedi(String planetName, String name, String rankStr, int age, String color, double strength) {
        Planet planet = planets.get(planetName);
        if (planet == null) {
            System.out.println("Planet not found.");
            return false;
        }

        for (Planet p : planets.values()) {
            for (Jedi j : p.getJedis()) {
                if (j.getName().equalsIgnoreCase(name)) {
                    System.out.println("Jedi with this name already exists.");
                    return false;
                }
            }
        }

        Rank rank;
        try {
            rank = Rank.valueOf(rankStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid rank.");
            return false;
        }

        Jedi newJedi = new Jedi(name, rank, age, color, strength);
        planet.addJedi(newJedi);
        System.out.println("Jedi created successfully.");
        return true;
    }
}