package com.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
        
        // Sort files (directories first, then files, both in alphabetical order)
        Arrays.sort(files, (f1, f2) -> {
            if (f1.isDirectory() && !f2.isDirectory()) return -1;
            if (!f1.isDirectory() && f2.isDirectory()) return 1;
            return f1.getName().compareToIgnoreCase(f2.getName());
        });
        
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm");
        
        for (File file : files) {
            try {
                Path filePath = file.toPath();
                BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
                FileTime modifiedTime = attrs.lastModifiedTime();
                String permissions = getPermissions(file);
                long size = attrs.size();
                String modifiedTimeStr = sdf.format(new Date(modifiedTime.toMillis()));
                
                // Format: permissions size modified_time name
                System.out.printf("%s %8d %s %s%s\n",
                    permissions,
                    size,
                    modifiedTimeStr,
                    file.getName(),
                    file.isDirectory() ? "/" : "");
                
            } catch (Exception e) {
                System.err.println("Error reading file attributes: " + file.getName());
            }
        }
    }
    
    private static String getPermissions(File file) {
        StringBuilder perms = new StringBuilder();
        perms.append(file.isDirectory() ? "d" : "-");
        perms.append(file.canRead() ? "r" : "-");
        perms.append(file.canWrite() ? "w" : "-");
        perms.append(file.canExecute() ? "x" : "-");
        return perms.toString();
    }
} 
