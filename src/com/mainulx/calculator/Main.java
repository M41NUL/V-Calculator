/*
 * ============================================
 * V-CALCULATOR - Advanced Scientific Calculator
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

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    
    // Calculator state variables
    private TextField display;
    private Label statusBar;
    private double currentValue = 0;
    private String lastOperator = "";
    private boolean startNewNumber = true;
    private boolean isDegree = true;
    private boolean is2ndMode = false;
    private String memory = "0";
    
    // Developer info constant
    private static final String DEVELOPER_INFO = 
        "👨‍💻 Developer: Md. Mainul Islam\n" +
        "🏢 Owner: MAINUL - X\n" +
        "🐙 GitHub: M41NUL\n" +
        "🔗 URL: https://github.com/M41NUL\n" +
        "📱 WhatsApp: +8801308850528\n" +
        "💬 Telegram: @mdmainulislaminfo\n" +
        "📧 Email: githubmainul@gmail.com\n" +
        "📝 License: MIT\n" +
        "© 2026 MAINUL - X. All rights reserved.";
    
    @Override
    public void start(Stage primaryStage) {
        // Create main layout
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e1e1e;");
        
        // Create header with developer info
        root.setTop(createHeader());
        
        // Create center (calculator)
        root.setCenter(createCalculator());
        
        // Create footer
        root.setBottom(createFooter());
        
        // Setup scene
        Scene scene = new Scene(root, 420, 650);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        // Setup stage
        primaryStage.setTitle("V-CALCULATOR - by MAINUL - X");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        // Show welcome dialog
        showWelcomeDialog();
    }
    
    /**
     * Creates the header section with logo and developer credit
     */
    private VBox createHeader() {
        VBox header = new VBox(5);
        header.setPadding(new Insets(15, 15, 5, 15));
        header.setStyle("-fx-background-color: #2d2d2d; -fx-border-color: #87CEEB; -fx-border-width: 0 0 2 0;");
        
        // Title
        Label title = new Label("V-CALCULATOR");
        title.setStyle("-fx-text-fill: #87CEEB; -fx-font-size: 28px; -fx-font-weight: bold;");
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        
        // Subtitle with developer
        Label subtitle = new Label("by MAINUL - X");
        subtitle.setStyle("-fx-text-fill: #888888; -fx-font-size: 14px;");
        subtitle.setAlignment(Pos.CENTER);
        subtitle.setMaxWidth(Double.MAX_VALUE);
        
        // GitHub link
        Hyperlink githubLink = new Hyperlink("github.com/M41NUL/V-C");
        githubLink.setStyle("-fx-text-fill: #87CEEB; -fx-font-size: 12px; -fx-border-color: transparent;");
        githubLink.setAlignment(Pos.CENTER);
        githubLink.setMaxWidth(Double.MAX_VALUE);
        githubLink.setOnAction(e -> {
            // Open GitHub in browser
            try {
                java.awt.Desktop.getDesktop().browse(
                    new java.net.URI("https://github.com/M41NUL/V-C")
                );
            } catch (Exception ex) {
                showAlert("Error", "Cannot open browser: " + ex.getMessage());
            }
        });
        
        header.getChildren().addAll(title, subtitle, githubLink);
        return header;
    }
    
    /**
     * Creates the calculator interface
     */
    private GridPane createCalculator() {
        GridPane calculator = new GridPane();
        calculator.setPadding(new Insets(15));
        calculator.setHgap(8);
        calculator.setVgap(8);
        calculator.setStyle("-fx-background-color: #1e1e1e;");
        
        // Display
        display = new TextField();
        display.setEditable(false);
        display.setStyle("-fx-font-size: 36px; -fx-alignment: center-right; " +
                        "-fx-background-color: #2d2d2d; -fx-text-fill: white; " +
                        "-fx-padding: 15px; -fx-border-radius: 8px; " +
                        "-fx-font-family: 'Monospaced'; -fx-border-color: #87CEEB;");
        display.setPrefHeight(80);
        display.setText("0");
        calculator.add(display, 0, 0, 5, 1);
        
        // Status bar
        statusBar = new Label("DEG    |    © 2026 MAINUL - X");
        statusBar.setStyle("-fx-text-fill: #87CEEB; -fx-font-size: 11px; -fx-alignment: center-right;");
        statusBar.setMaxWidth(Double.MAX_VALUE);
        calculator.add(statusBar, 0, 1, 5, 1);
        
        // Row 1: Function buttons
        addButton(calculator, "2nd", "#4a4a4a", 0, 2, this::toggle2nd);
        addButton(calculator, "deg", "#4a4a4a", 1, 2, this::toggleDeg);
        addButton(calculator, "sin", "#4a4a4a", 2, 2, () -> scientificFunction("sin"));
        addButton(calculator, "cos", "#4a4a4a", 3, 2, () -> scientificFunction("cos"));
        addButton(calculator, "tan", "#4a4a4a", 4, 2, () -> scientificFunction("tan"));
        
        // Row 2
        addButton(calculator, "x^y", "#4a4a4a", 0, 3, () -> operatorClick("^"));
        addButton(calculator, "log", "#4a4a4a", 1, 3, () -> scientificFunction("log"));
        addButton(calculator, "ln", "#4a4a4a", 2, 3, () -> scientificFunction("ln"));
        addButton(calculator, "(", "#4a4a4a", 3, 3, () -> display.setText(display.getText() + "("));
        addButton(calculator, ")", "#4a4a4a", 4, 3, () -> display.setText(display.getText() + ")"));
        
        // Row 3
        addButton(calculator, "√", "#ff6b6b", 0, 4, () -> scientificFunction("sqrt"));
        addButton(calculator, "AC", "#ff6b6b", 1, 4, this::clear);
        addButton(calculator, "%", "#ff6b6b", 2, 4, this::percent);
        addButton(calculator, "÷", "#ff9f43", 3, 4, 2, 1, () -> operatorClick("÷"));
        
        // Row 4
        addButton(calculator, "x!", "#4a4a4a", 0, 5, () -> scientificFunction("fact"));
        addNumberButton(calculator, "7", 1, 5);
        addNumberButton(calculator, "8", 2, 5);
        addNumberButton(calculator, "9", 3, 5);
        addButton(calculator, "×", "#ff9f43", 4, 5, () -> operatorClick("×"));
        
        // Row 5
        addButton(calculator, "1/x", "#4a4a4a", 0, 6, () -> scientificFunction("inv"));
        addNumberButton(calculator, "4", 1, 6);
        addNumberButton(calculator, "5", 2, 6);
        addNumberButton(calculator, "6", 3, 6);
        addButton(calculator, "−", "#ff9f43", 4, 6, () -> operatorClick("−"));
        
        // Row 6
        addButton(calculator, "π", "#4a4a4a", 0, 7, () -> display.setText(String.valueOf(Math.PI)));
        addNumberButton(calculator, "1", 1, 7);
        addNumberButton(calculator, "2", 2, 7);
        addNumberButton(calculator, "3", 3, 7);
        addButton(calculator, "+", "#ff9f43", 4, 7, () -> operatorClick("+"));
        
        // Row 7
        addEqualButton(calculator, "=", 0, 8, this::calculate);
        addButton(calculator, "e", "#4a4a4a", 1, 8, () -> display.setText(String.valueOf(Math.E)));
        addNumberButton(calculator, "0", 2, 8);
        addNumberButton(calculator, ".", 3, 8);
        addEqualButton(calculator, "=", 4, 8, this::calculate);
        
        return calculator;
    }
    
    /**
     * Helper method to add buttons
     */
    private void addButton(GridPane grid, String text, String color, int col, int row, Runnable action) {
        Button btn = new Button(text);
        btn.setPrefSize(70, 60);
        btn.setStyle(String.format("-fx-font-size: %s; -fx-background-color: %s; " +
                                  "-fx-text-fill: white; -fx-font-weight: bold; " +
                                  "-fx-background-radius: 8px;", 
                                  text.length() > 2 ? "16px" : "20px", color));
        btn.setOnAction(e -> action.run());
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        GridPane.setVgrow(btn, Priority.ALWAYS);
        grid.add(btn, col, row);
    }
    
    private void addButton(GridPane grid, String text, String color, int col, int row, int colSpan, int rowSpan, Runnable action) {
        Button btn = new Button(text);
        btn.setPrefSize(70 * colSpan, 60 * rowSpan);
        btn.setStyle(String.format("-fx-font-size: %s; -fx-background-color: %s; " +
                                  "-fx-text-fill: white; -fx-font-weight: bold; " +
                                  "-fx-background-radius: 8px;", 
                                  text.length() > 2 ? "16px" : "24px", color));
        btn.setOnAction(e -> action.run());
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        GridPane.setVgrow(btn, Priority.ALWAYS);
        grid.add(btn, col, row, colSpan, rowSpan);
    }
    
    private void addNumberButton(GridPane grid, String text, int col, int row) {
        Button btn = new Button(text);
        btn.setPrefSize(70, 60);
        btn.setStyle("-fx-font-size: 20px; -fx-background-color: #3c3c3c; " +
                    "-fx-text-fill: white; -fx-font-weight: bold; " +
                    "-fx-background-radius: 8px;");
        btn.setOnAction(e -> numberClick(text));
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        GridPane.setVgrow(btn, Priority.ALWAYS);
        grid.add(btn, col, row);
    }
    
    private void addEqualButton(GridPane grid, String text, int col, int row, Runnable action) {
        Button btn = new Button(text);
        btn.setPrefSize(70, 60);
        btn.setStyle("-fx-font-size: 24px; -fx-background-color: #51cf66; " +
                    "-fx-text-fill: white; -fx-font-weight: bold; " +
                    "-fx-background-radius: 8px;");
        btn.setOnAction(e -> action.run());
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        GridPane.setVgrow(btn, Priority.ALWAYS);
        grid.add(btn, col, row);
    }
    
    /**
     * Creates footer with contact info
     */
    private HBox createFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
        footer.setAlignment(Pos.CENTER);
        footer.setStyle("-fx-background-color: #2d2d2d; -fx-border-color: #87CEEB; -fx-border-width: 1 0 0 0;");
        
        Label contact = new Label("📱 +8801308850528 | 💬 @mdmainulislaminfo | 📧 githubmainul@gmail.com");
        contact.setStyle("-fx-text-fill: #87CEEB; -fx-font-size: 10px;");
        contact.setMaxWidth(Double.MAX_VALUE);
        contact.setAlignment(Pos.CENTER);
        
        footer.getChildren().add(contact);
        return footer;
    }
    
    /**
     * Calculator logic methods
     */
    private void numberClick(String value) {
        if (startNewNumber) {
            display.setText(value);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + value);
        }
    }
    
    private void operatorClick(String op) {
        currentValue = Double.parseDouble(display.getText());
        lastOperator = op;
        startNewNumber = true;
    }
    
    private void scientificFunction(String func) {
        double value = Double.parseDouble(display.getText());
        double result = 0;
        
        switch(func) {
            case "sin":
                result = isDegree ? Math.sin(Math.toRadians(value)) : Math.sin(value);
                break;
            case "cos":
                result = isDegree ? Math.cos(Math.toRadians(value)) : Math.cos(value);
                break;
            case "tan":
                result = isDegree ? Math.tan(Math.toRadians(value)) : Math.tan(value);
                break;
            case "log":
                result = Math.log10(value);
                break;
            case "ln":
                result = Math.log(value);
                break;
            case "sqrt":
                result = Math.sqrt(value);
                break;
            case "fact":
                result = factorial((int)value);
                break;
            case "inv":
                result = 1 / value;
                break;
        }
        
        display.setText(String.valueOf(result));
        startNewNumber = true;
    }
    
    private long factorial(int n) {
        if (n < 0) return 0;
        if (n == 0 || n == 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    private void calculate() {
        if (lastOperator.isEmpty()) return;
        
        double secondNum = Double.parseDouble(display.getText());
        double result = 0;
        
        switch(lastOperator) {
            case "+":
                result = currentValue + secondNum;
                break;
            case "−":
                result = currentValue - secondNum;
                break;
            case "×":
                result = currentValue * secondNum;
                break;
            case "÷":
                if (secondNum != 0) {
                    result = currentValue / secondNum;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
            case "^":
                result = Math.pow(currentValue, secondNum);
                break;
        }
        
        display.setText(String.valueOf(result));
        startNewNumber = true;
        lastOperator = "";
    }
    
    private void clear() {
        display.setText("0");
        currentValue = 0;
        lastOperator = "";
        startNewNumber = true;
    }
    
    private void percent() {
        double value = Double.parseDouble(display.getText());
        display.setText(String.valueOf(value / 100));
        startNewNumber = true;
    }
    
    private void toggleDeg() {
        isDegree = !isDegree;
        statusBar.setText((isDegree ? "DEG" : "RAD") + "    |    © 2026 MAINUL - X");
    }
    
    private void toggle2nd() {
        is2ndMode = !is2ndMode;
        // For inverse trig functions (sin⁻¹, cos⁻¹, tan⁻¹)
        // Will be implemented in next version
    }
    
    /**
     * Show welcome dialog with developer info
     */
    private void showWelcomeDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Welcome to V-CALCULATOR");
        dialog.setHeaderText("🚀 V-CALCULATOR v1.0.0");
        
        // Set the button types
        ButtonType okButton = new ButtonType("Start Calculating", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CLOSE);
        
        // Create content
        VBox content = new VBox(10);
        content.setPadding(new Insets(20));
        
        Label devInfo = new Label(DEVELOPER_INFO);
        devInfo.setStyle("-fx-font-family: 'Monospaced'; -fx-font-size: 12px;");
        devInfo.setWrapText(true);
        
        Hyperlink githubLink = new Hyperlink("Visit GitHub Repository: github.com/M41NUL/V-C");
        githubLink.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(
                    new java.net.URI("https://github.com/M41NUL/V-C")
                );
            } catch (Exception ex) {
                // Ignore
            }
        });
        
        content.getChildren().addAll(devInfo, githubLink);
        dialog.getDialogPane().setContent(content);
        
        dialog.showAndWait();
    }
    
    /**
     * Show alert dialog
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
