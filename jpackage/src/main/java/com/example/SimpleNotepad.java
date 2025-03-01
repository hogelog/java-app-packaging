package com.example;

import javax.swing.*;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleNotepad extends JFrame {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    
    public SimpleNotepad() {
        setTitle("シンプルメモ帳");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        textArea = new JTextArea();
        textArea.setFont(new Font("MS Gothic", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        
        menuBar = new JMenuBar();
        fileMenu = new JMenu("ファイル");
        
        JMenuItem newItem = new JMenuItem("新規");
        JMenuItem openItem = new JMenuItem("開く");
        JMenuItem saveItem = new JMenuItem("保存");
        JMenuItem exitItem = new JMenuItem("終了");
        
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        newItem.addActionListener(e -> newFile());
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        exitItem.addActionListener(e -> System.exit(0));
    }
    
    private void newFile() {
        textArea.setText("");
    }
    
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                reader.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "ファイルを開けませんでした。", "エラー", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(textArea.getText());
                writer.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "ファイルを保存できませんでした。", "エラー", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleNotepad notepad = new SimpleNotepad();
            notepad.setVisible(true);
        });
    }
} 
