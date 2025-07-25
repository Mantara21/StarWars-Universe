package model;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private String name;
    private List<Jedi> jedis;

    public Planet(String name) {
        this.name = name;
        this.jedis = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Jedi> getJedis() { return jedis; }

    public void addJedi(Jedi jedi) {
        jedis.add(jedi);
    }

    public void removeJedi(Jedi jedi) {
        jedis.remove(jedi);
    }

    @Override
    public String toString() {
        return name + " with " + jedis.size() + " jedis.";
    }
}
