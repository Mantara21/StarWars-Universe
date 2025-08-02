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
    public List<Jedi> getSortedJedis() {
        return jedis.stream()
                .sorted((j1, j2) -> {
                    int rankCompare = Integer.compare(j1.getRank().getHierarchyLevel(), j2.getRank().getHierarchyLevel());
                    if (rankCompare != 0) return rankCompare;
                    return j1.getName().compareToIgnoreCase(j2.getName());
                })
                .toList();
    }
}
