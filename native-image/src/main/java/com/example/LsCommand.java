package com.example;

import java.io.File;

public class LsCommand {
    public static void main(String[] args) {
        String path = args.length > 0 ? args[0] : ".";
        File directory = new File(path);
        
        if (!directory.exists()) {
            System.err.println("Error: Directory does not exist: " + path);
            System.exit(1);
        }
        
        if (!directory.isDirectory()) {
            System.err.println("Error: Not a directory: " + path);
            System.exit(1);
        }
        
        File[] files = directory.listFiles();
        if (files == null) {
            System.err.println("Error: Could not list directory: " + path);
            System.exit(1);
        }
        
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
} 
