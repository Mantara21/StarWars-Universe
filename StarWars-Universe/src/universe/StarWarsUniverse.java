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
    public boolean removeJedi(String name, String planetName) {
        Planet planet = planets.get(planetName);
        if (planet == null) {
            System.out.println("Planet not found.");
            return false;
        }

        for (Jedi j : planet.getJedis()) {
            if (j.getName().equalsIgnoreCase(name)) {
                planet.removeJedi(j);
                System.out.println("Jedi removed.");
                return true;
            }
        }
        System.out.println("Jedi not found on this planet.");
        return false;
    }

    public void promoteJedi(String name, double multiplier) {
        for (Planet p : planets.values()) {
            for (Jedi j : p.getJedis()) {
                if (j.getName().equalsIgnoreCase(name)) {
                    Rank[] ranks = Rank.values();
                    int current = j.getRank().ordinal();
                    if (current < ranks.length - 1) {
                        j.setRank(ranks[current + 1]);
                        j.setStrength(j.getStrength() + multiplier * j.getStrength());
                        System.out.println("Jedi promoted.");
                    } else {
                        System.out.println("Jedi is already GRAND_MASTER.");
                    }
                    return;
                }
            }
        }
        System.out.println("Jedi not found.");
    }
    public void demoteJedi(String name, double multiplier) {
        for (Planet p : planets.values()) {
            for (Jedi j : p.getJedis()) {
                if (j.getName().equalsIgnoreCase(name)) {
                    int current = j.getRank().ordinal();
                    if (current > 0) {
                        j.setRank(Rank.values()[current - 1]);
                        j.setStrength(j.getStrength() - multiplier * j.getStrength());
                        System.out.println("Jedi demoted.");
                    } else {
                        System.out.println("Jedi is already YOUNGLING.");
                    }
                    return;
                }
            }
        }
        System.out.println("Jedi not found.");
    }
    public void getStrongestJedi(String planetName) {
        Planet planet = planets.get(planetName);
        if (planet == null) {
            System.out.println("Planet not found.");
            return;
        }

        Jedi strongest = planet.getJedis().stream()
                .max((j1, j2) -> Double.compare(j1.getStrength(), j2.getStrength()))
                .orElse(null);

        if (strongest != null) {
            System.out.println(strongest);
        } else {
            System.out.println("No jedis on this planet.");
        }
    }
    public void getYoungestJedi(String planetName, String rankStr) {
        Planet planet = planets.get(planetName);
        if (planet == null) {
            System.out.println("Planet not found.");
            return;
        }

        Rank rank;
        try {
            rank = Rank.valueOf(rankStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid rank.");
            return;
        }

        Jedi youngest = planet.getJedis().stream()
                .filter(j -> j.getRank() == rank)
                .sorted((j1, j2) -> {
                    int ageCompare = Integer.compare(j1.getAge(), j2.getAge());
                    if (ageCompare != 0) return ageCompare;
                    return j1.getName().compareToIgnoreCase(j2.getName());
                })
                .findFirst()
                .orElse(null);

        if (youngest != null) {
            System.out.println(youngest);
        } else {
            System.out.println("No jedis with rank " + rank + " on this planet.");
        }
    }
    public void getMostUsedSaberColor(String planetName, String rankStr) {
        Planet planet = planets.get(planetName);
        if (planet == null) {
            System.out.println("Planet not found.");
            return;
        }

        Rank rank;
        try {
            rank = Rank.valueOf(rankStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid rank.");
            return;
        }

        Map<String, Integer> colorCount = new HashMap<>();
        for (Jedi j : planet.getJedis()) {
            if (j.getRank() == rank) {
                colorCount.put(j.getSaberColor(), colorCount.getOrDefault(j.getSaberColor(), 0) + 1);
            }
        }

        String mostUsedColor = colorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (mostUsedColor != null) {
            System.out.println("Most used color: " + mostUsedColor);
        } else {
            System.out.println("No jedis with that rank.");
        }
    }
    public void getMostUsedSaberColorByGrandMasters(String planetName) {
        Planet planet = planets.get(planetName);
        if (planet == null) {
            System.out.println("Planet not found.");
            return;
        }

        Map<String, Integer> colorCount = new HashMap<>();
        for (Jedi j : planet.getJedis()) {
            if (j.getRank() == Rank.GRAND_MASTER) {
                colorCount.put(j.getSaberColor(), colorCount.getOrDefault(j.getSaberColor(), 0) + 1);
            }
        }

        String mostUsedColor = colorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (mostUsedColor != null) {
            System.out.println("Most used color (by GRAND_MASTERS): " + mostUsedColor);
        } else {
            System.out.println("No GRAND_MASTER jedis on this planet.");
        }
    }


}