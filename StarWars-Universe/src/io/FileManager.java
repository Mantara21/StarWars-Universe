package io;

import universe.StarWarsUniverse;

import java.io.*;

public class FileManager {
    private File currentFile;

    public boolean open(String filePath, StarWarsUniverse universe) {
        currentFile = new File(filePath);
        if (!currentFile.exists()) {
            System.out.println("File not found. Creating new.");
            return save(universe, filePath);
        }
        // TODO: Load data from file
        System.out.println("Successfully opened " + filePath);
        return true;
    }

    public boolean save(StarWarsUniverse universe, String filePath) {
        // TODO: Save data to file
        System.out.println("Successfully saved " + filePath);
        return true;
    }

    public boolean save() {
        if (currentFile != null) {
            return save(null, currentFile.getPath());
        }
        return false;
    }

    public void close() {
        currentFile = null;
        System.out.println("Successfully closed file.");
    }
}
