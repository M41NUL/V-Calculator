/*
 * ============================================
 * V-CALCULATOR - Calculator User Interface
 * ============================================
 * 
 * Developer Information:
 * --------------------------------------------
 * Author     : Md. Mainul Islam
 * Owner      : MAINUL - X
 * GitHub     : M41NUL
 * GitHub URL : https://github.com/M41NUL
 * WhatsApp   : +8801308850528
 * Telegram   : @mdmainulislaminfo
 * Email      : githubmainul@gmail.com
 * License    : MIT License
 * Copyright  : Copyright (c) 2026 MAINUL - X
 * ============================================
 */

package com.mainulx.calculator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * CalculatorUI class - Creates the main calculator interface
 * Handles all UI components and user interactions
 */
public class CalculatorUI {
    
    // Main container
    private VBox root;
    
    // Display components
    private TextField display;
    private Label statusBar;
    private Label developerCredit;
    
    // Calculator state
    private double currentValue = 0;
    private String lastOperator = "";
    private boolean startNewNumber = true;
    private boolean isDegree = true;
    private boolean is2ndMode = false;
    
    // Scientific functions instance
    private ScientificFunctions sciFunctions;
    
    // Developer information
    private static final String DEV_NAME = "Md. Mainul Islam";
    private static final String DEV_OWNER = "MAINUL - X";
    private static final String DEV_GITHUB = "M41NUL";
    private static final String DEV_WHATSAPP = "+8801308850528";
    private static final String DEV_TELEGRAM = "@mdmainulislaminfo";
    private static final String COPYRIGHT = "© 2026 MAINUL - X";
    
    /**
     * Constructor - Initializes the calculator UI
     */
    public CalculatorUI() {
        sciFunctions = new ScientificFunctions();
        createRoot();
        createHeader();
        createDisplay();
        createDeveloperCredit();
        createButtons();
        createFooter();
    }
    
    /**
     * Creates the root container
     */
    private void createRoot() {
        root = new VBox(5);
        root.setPadding(new Insets(0));
        root.setStyle("-fx-background-color: #1e1e1e;");
        root.setPrefWidth(400);
        root.setPrefHeight(600);
    }
    
    /**
     * Creates the header section with logo
     */
    private void createHeader() {
        VBox header = new VBox(2);
        header.setPadding(new Insets(15, 15, 5, 15));
        header.setStyle("-fx-background-color: #2d2d2d; -fx-border-color: #87CEEB; -fx-border-width: 0 0 2 0;");
        
        // Title
        Label title = new Label("V-CALCULATOR");
        title.setStyle("-fx-text-fill: #87CEEB; -fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Segoe UI';");
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        
        // Subtitle with owner
        Label subtitle = new Label("by " + DEV_OWNER);
        subtitle.setStyle("-fx-text-fill: #888888; -fx-font-size: 14px; -fx-font-family: 'Segoe UI';");
        subtitle.setAlignment(Pos.CENTER);
        subtitle.setMaxWidth(Double.MAX_VALUE);
        
        // GitHub link
        Hyperlink githubLink = new Hyperlink("github.com/" + DEV_GITHUB + "/V-C");
        githubLink.setStyle("-fx-text-fill: #87CEEB; -fx-font-size: 11px; -fx-border-color: transparent;");
        githubLink.setAlignment(Pos.CENTER);
        githubLink.setMaxWidth(Double.MAX_VALUE);
        githubLink.setOnAction(e -> openGitHub());
        
        header.getChildren().addAll(title, subtitle, githubLink);
        root.getChildren().add(header);
    }
    
    /**
     * Creates the display section
     */
    private void createDisplay() {
        VBox displayBox = new VBox(5);
        displayBox.setPadding(new Insets(15, 15, 5, 15));
        
        // Main display
        display = new TextField();
        display.setEditable(false);
        display.setStyle("-fx-font-size: 36px; -fx-alignment: center-right; " +
                        "-fx-background-color: #2d2d2d; -fx-text-fill: white; " +
                        "-fx-padding: 15px; -fx-border-radius: 8px; " +
                        "-fx-font-family: 'Monospaced'; -fx-border-color: #87CEEB; " +
                        "-fx-border-width: 2px;");
        display.setPrefHeight(80);
        display.setText("0");
        
        // Status bar
        statusBar = new Label("DEG    |    " + COPYRIGHT);
        statusBar.setStyle("-fx-text-fill: #87CEEB; -fx-font-size: 11px; -fx-alignment: center-right; " +
                          "-fx-font-family: 'Segoe UI';");
        statusBar.setMaxWidth(Double.MAX_VALUE);
        
        displayBox.getChildren().addAll(display, statusBar);
        root.getChildren().add(displayBox);
    }
    
    /**
     * Creates developer credit line
     */
    private void createDeveloperCredit() {
        developerCredit = new Label("👨‍💻 " + DEV_NAME + " | 📱 " + DEV_WHATSAPP + " | 💬 " + DEV_TELEGRAM);
        developerCredit.setStyle("-fx-text-fill: #87CEEB; -fx-font-size: 10px; -fx-alignment: center; " +
                                "-fx-font-family: 'Segoe UI';");
        developerCredit.setMaxWidth(Double.MAX_VALUE);
        developerCredit.setPadding(new Insets(0, 15, 5, 15));
        root.getChildren().add(developerCredit);
    }
    
    /**
     * Creates all calculator buttons
     */
    private void createButtons() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 15, 10, 15));
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setStyle("-fx-background-color: #1e1e1e;");
        
        // Row 1: Function buttons
        addButton(grid, "2nd", "#4a4a4a", 0, 0, e -> toggle2nd());
        addButton(grid, "deg", "#4a4a4a", 1, 0, e -> toggleDeg());
        addButton(grid, "sin", "#4a4a4a", 2, 0, e -> scientificFunction("sin"));
        addButton(grid, "cos", "#4a4a4a", 3, 0, e -> scientificFunction("cos"));
        addButton(grid, "tan", "#4a4a4a", 4, 0, e -> scientificFunction("tan"));
        
        // Row 2
        addButton(grid, "x^y", "#4a4a4a", 0, 1, e -> operatorClick("^"));
        addButton(grid, "log", "#4a4a4a", 1, 1, e -> scientificFunction("log"));
        addButton(grid, "ln", "#4a4a4a", 2, 1, e -> scientificFunction("ln"));
        addButton(grid, "(", "#4a4a4a", 3, 1, e -> addParenthesis("("));
        addButton(grid, ")", "#4a4a4a", 4, 1, e -> addParenthesis(")"));
        
        // Row 3
        addButton(grid, "√", "#ff6b6b", 0, 2, e -> scientificFunction("sqrt"));
        addButton(grid, "AC", "#ff6b6b", 1, 2, e -> clear());
        addButton(grid, "%", "#ff6b6b", 2, 2, e -> percent());
        addButton(grid, "÷", "#ff9f43", 3, 2, 2, 1, e -> operatorClick("÷"));
        
        // Row 4
        addButton(grid, "x!", "#4a4a4a", 0, 3, e -> scientificFunction("fact"));
        addNumberButton(grid, "7", 1, 3);
        addNumberButton(grid, "8", 2, 3);
        addNumberButton(grid, "9", 3, 3);
        addButton(grid, "×", "#ff9f43", 4, 3, e -> operatorClick("×"));
        
        // Row 5
        addButton(grid, "1/x", "#4a4a4a", 0, 4, e -> scientificFunction("inv"));
        addNumberButton(grid, "4", 1, 4);
        addNumberButton(grid, "5", 2, 4);
        addNumberButton(grid, "6", 3, 4);
        addButton(grid, "−", "#ff9f43", 4, 4, e -> operatorClick("−"));
        
        // Row 6
        addButton(grid, "π", "#4a4a4a", 0, 5, e -> setConstant("pi"));
        addNumberButton(grid, "1", 1, 5);
        addNumberButton(grid, "2", 2, 5);
        addNumberButton(grid, "3", 3, 5);
        addButton(grid, "+", "#ff9f43", 4, 5, e -> operatorClick("+"));
        
        // Row 7
        addEqualButton(grid, "=", 0, 6, e -> calculate());
        addButton(grid, "e", "#4a4a4a", 1, 6, e -> setConstant("e"));
        addNumberButton(grid, "0", 2, 6);
        addNumberButton(grid, ".", 3, 6);
        addEqualButton(grid, "=", 4, 6, e -> calculate());
        
        root.getChildren().add(grid);
    }
    
    /**
     * Creates the footer with additional info
     */
    private void createFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(5, 15, 10, 15));
        footer.setAlignment(Pos.CENTER);
        footer.setStyle("-fx-background-color: #1e1e1e;");
        
        Label versionLabel = new Label("Version 1.0.0 | MIT License");
        versionLabel.setStyle("-fx-text-fill: #888888; -fx-font-size: 10px; -fx-font-family: 'Segoe UI';");
        
        footer.getChildren().add(versionLabel);
        root.getChildren().add(footer);
    }
    
    /**
     * Helper method to add a button to the grid
     */
    private void addButton(GridPane grid, String text, String color, int col, int row, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = new Button(text);
        btn.setPrefSize(65, 55);
        btn.setStyle(String.format("-fx-font-size: %s; -fx-background-color: %s; " +
                                  "-fx-text-fill: white; -fx-font-weight: bold; " +
                                  "-fx-background-radius: 8px; -fx-cursor: hand;",
                                  text.length() > 2 ? "14px" : "18px", color));
        btn.setOnAction(handler);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        GridPane.setVgrow(btn, Priority.ALWAYS);
        grid.add(btn, col, row);
    }
    
    /**
     * Helper method to add a button with column/row span
     */
    private void addButton(GridPane grid, String text, String color, int col, int row, int colSpan, int rowSpan, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = new Button(text);
        btn.setPrefSize(65 * colSpan, 55 * rowSpan);
        btn.setStyle(String.format("-fx-font-size: %s; -fx-background-color: %s; " +
                                  "-fx-text-fill: white; -fx-font-weight: bold; " +
                                  "-fx-background-radius: 8px; -fx-cursor: hand;",
                                  text.length() > 2 ? "14px" : "22px", color));
        btn.setOnAction(handler);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        GridPane.setVgrow(btn, Priority.ALWAYS);
        grid.add(btn, col, row, colSpan, rowSpan);
    }
    
    /**
     * Helper method to add a number button
     */
    private void addNumberButton(GridPane grid, String text, int col, int row) {
        Button btn = new Button(text);
        btn.setPrefSize(65, 55);
        btn.setStyle("-fx-font-size: 20px; -fx-background-color: #3c3c3c; " +
                    "-fx-text-fill: white; -fx-font-weight: bold; " +
                    "-fx-background-radius: 8px; -fx-cursor: hand;");
        btn.setOnAction(e -> numberClick(text));
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        GridPane.setVgrow(btn, Priority.ALWAYS);
        grid.add(btn, col, row);
    }
    
    /**
     * Helper method to add an equal button
     */
    private void addEqualButton(GridPane grid, String text, int col, int row, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = new Button(text);
        btn.setPrefSize(65, 55);
        btn.setStyle("-fx-font-size: 24px; -fx-background-color: #51cf66; " +
                    "-fx-text-fill: white; -fx-font-weight: bold; " +
                    "-fx-background-radius: 8px; -fx-cursor: hand;");
        btn.setOnAction(handler);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        GridPane.setVgrow(btn, Priority.ALWAYS);
        grid.add(btn, col, row);
    }
    
    /**
     * Number button click handler
     */
    private void numberClick(String value) {
        if (startNewNumber) {
            display.setText(value);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + value);
        }
    }
    
    /**
     * Operator button click handler
     */
    private void operatorClick(String op) {
        currentValue = Double.parseDouble(display.getText());
        lastOperator = op;
        startNewNumber = true;
    }
    
    /**
     * Scientific function handler
     */
    private void scientificFunction(String func) {
        double value = Double.parseDouble(display.getText());
        double result = 0;
        boolean useDegree = isDegree;
        
        switch(func) {
            case "sin":
                result = sciFunctions.sin(value, useDegree);
                break;
            case "cos":
                result = sciFunctions.cos(value, useDegree);
                break;
            case "tan":
                result = sciFunctions.tan(value, useDegree);
                break;
            case "log":
                result = sciFunctions.log(value);
                break;
            case "ln":
                result = sciFunctions.ln(value);
                break;
            case "sqrt":
                result = sciFunctions.sqrt(value);
                break;
            case "fact":
                result = sciFunctions.factorial(value);
                break;
            case "inv":
                result = sciFunctions.inverse(value);
                break;
        }
        
        // Check for error
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            display.setText("Error");
        } else {
            display.setText(String.valueOf(result));
        }
        startNewNumber = true;
    }
    
    /**
     * Calculate result
     */
    private void calculate() {
        if (lastOperator.isEmpty()) return;
        
        double secondNum = Double.parseDouble(display.getText());
        double result = 0;
        
        switch(lastOperator) {
            case "+":
                result = sciFunctions.add(currentValue, secondNum);
                break;
            case "−":
                result = sciFunctions.subtract(currentValue, secondNum);
                break;
            case "×":
                result = sciFunctions.multiply(currentValue, secondNum);
                break;
            case "÷":
                result = sciFunctions.divide(currentValue, secondNum);
                if (Double.isNaN(result)) {
                    display.setText("Error");
                    return;
                }
                break;
            case "^":
                result = sciFunctions.power(currentValue, secondNum);
                break;
        }
        
        display.setText(String.valueOf(result));
        startNewNumber = true;
        lastOperator = "";
    }
    
    /**
     * Clear all
     */
    private void clear() {
        display.setText("0");
        currentValue = 0;
        lastOperator = "";
        startNewNumber = true;
    }
    
    /**
     * Calculate percentage
     */
    private void percent() {
        double value = Double.parseDouble(display.getText());
        display.setText(String.valueOf(value / 100));
        startNewNumber = true;
    }
    
    /**
     * Toggle degree/radian
     */
    private void toggleDeg() {
        isDegree = !isDegree;
        statusBar.setText((isDegree ? "DEG" : "RAD") + "    |    " + COPYRIGHT);
    }
    
    /**
     * Toggle 2nd mode (for inverse functions)
     */
    private void toggle2nd() {
        is2ndMode = !is2ndMode;
        // Update button text for inverse functions
        // This will be implemented in next version
    }
    
    /**
     * Add parenthesis
     */
    private void addParenthesis(String p) {
        display.setText(display.getText() + p);
        startNewNumber = false;
    }
    
    /**
     * Set constant value
     */
    private void setConstant(String constant) {
        if (constant.equals("pi")) {
            display.setText(String.valueOf(Math.PI));
        } else if (constant.equals("e")) {
            display.setText(String.valueOf(Math.E));
        }
        startNewNumber = true;
    }
    
    /**
     * Open GitHub repository
     */
    private void openGitHub() {
        try {
            java.awt.Desktop.getDesktop().browse(
                new java.net.URI("https://github.com/" + DEV_GITHUB + "/V-C")
            );
        } catch (Exception ex) {
            showAlert("Error", "Cannot open browser: " + ex.getMessage());
        }
    }
    
    /**
     * Show alert dialog
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Get the root container
     * @return VBox root container
     */
    public VBox getRoot() {
        return root;
    }
    
    /**
     * Get the display text
     * @return Current display text
     */
    public String getDisplayText() {
        return display.getText();
    }
    
    /**
     * Set display text
     * @param text Text to display
     */
    public void setDisplayText(String text) {
        display.setText(text);
    }
}
