package cli;

import io.FileManager;
import universe.StarWarsUniverse;

import java.util.Scanner;

public class CommandHandler {
    private StarWarsUniverse universe = new StarWarsUniverse();
    private FileManager fileManager = new FileManager();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Star Wars Universe CLI!");
        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            handleCommand(command);
        }
    }

    private void handleCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0].toLowerCase()) {
            case "open":
                if (parts.length >= 2) {
                    fileManager.open(parts[1], universe);
                } else {
                    System.out.println("Usage: open <file>");
                }
                break;
            case "close":
                fileManager.close();
                break;
            case "save":
                fileManager.save();
                break;
            case "saveas":
                if (parts.length >= 2) {
                    fileManager.save(universe, parts[1]);
                } else {
                    System.out.println("Usage: saveas <file>");
                }
                break;
            case "help":
                printHelp();
                break;
            case "add_planet":
                if (parts.length >= 2) {
                    universe.addPlanet(parts[1]);
                } else {
                    System.out.println("Usage: add_planet <planet_name>");
                }
                break;
            case "print":
                if (parts.length >= 2) {
                    var planet = universe.getPlanet(parts[1]);
                    if (planet != null) {
                        System.out.println("Planet: " + planet.getName());
                        for (var jedi : planet.getSortedJedis()) {
                            System.out.println(jedi);
                        }
                    } else {
                        System.out.println("Planet not found.");
                    }
                } else {
                    System.out.println("Usage: print <planet_name>");
                }
                break;
            case "create_jedi":
                if (parts.length >= 7) {
                    String planet = parts[1];
                    String name = parts[2];
                    String rank = parts[3];
                    int age = Integer.parseInt(parts[4]);
                    String color = parts[5];
                    double strength = Double.parseDouble(parts[6]);
                    universe.createJedi(planet, name, rank, age, color, strength);
                } else {
                    System.out.println("Usage: create_jedi <planet> <name> <rank> <age> <color> <strength>");
                }
                break;

            default:
                System.out.println("Unknown command. Type 'help' for a list.");
        }
    }

    private void printHelp() {
        System.out.println("""
                open <file> - opens file
                close - closes current file
                save - saves the current file
                saveas <file> - saves current file as new file
                help - shows this message
                exit - exits the program
                """);
    }
}
