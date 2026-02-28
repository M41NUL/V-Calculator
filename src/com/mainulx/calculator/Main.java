/*
 * ============================================
 * V-CALCULATOR - Main Application Entry Point
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
 * 
 * Project Structure:
 * V-Calculator/
 * ├── src/com/mainulx/calculator/
 * │   ├── Main.java
 * │   ├── CalculatorUI.java
 * │   ├── ScientificFunctions.java
 * │   └── AboutDialog.java
 * ├── resources/
 * │   └── style.css
 * ├── V-Calculator.jar
 * └── README.md
 * ============================================
 */

package com.mainulx.calculator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main class - Entry point for V-CALCULATOR application
 * This class initializes the JavaFX application and loads the calculator UI
 * 
 * @author Md. Mainul Islam
 * @version 1.0.0
 * @since 2026
 */
public class Main extends Application {
    
    // Application constants
    private static final String APP_TITLE = "V-CALCULATOR - by MAINUL - X";
    private static final String APP_VERSION = "1.0.0";
    private static final int APP_WIDTH = 420;
    private static final int APP_HEIGHT = 650;
    
    /**
     * Developer information - Displayed in About dialog
     */
    public static final String DEVELOPER_INFO = 
        "╔════════════════════════════════════════╗\n" +
        "║     V-CALCULATOR - Developer Info     ║\n" +
        "╠════════════════════════════════════════╣\n" +
        "║ Author  : Md. Mainul Islam            ║\n" +
        "║ Owner   : MAINUL - X                   ║\n" +
        "║ GitHub  : M41NUL                       ║\n" +
        "║ URL     : https://github.com/M41NUL    ║\n" +
        "║ WhatsApp: +8801308850528               ║\n" +
        "║ Telegram: @mdmainulislaminfo           ║\n" +
        "║ Email   : githubmainul@gmail.com       ║\n" +
        "║ License : MIT                          ║\n" +
        "║ Copyright: 2026 MAINUL - X             ║\n" +
        "╚════════════════════════════════════════╝";
    
    /**
     * Short developer info for status bar
     */
    public static final String SHORT_DEV_INFO = 
        "© 2026 MAINUL - X | github.com/M41NUL | +8801308850528";
    
    /**
     * Main entry point for JavaFX application
     * 
     * @param primaryStage The primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Create main layout
            BorderPane root = new BorderPane();
            
            // Initialize calculator UI
            CalculatorUI calculatorUI = new CalculatorUI();
            root.setCenter(calculatorUI.getRoot());
            
            // Create scene
            Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);
            
            // Load CSS if available
            try {
                String css = getClass().getResource("/style.css").toExternalForm();
                scene.getStylesheets().add(css);
            } catch (Exception e) {
                // CSS file not found, continue without styling
                System.out.println("CSS file not loaded, using default styles");
            }
            
            // Configure stage
            primaryStage.setTitle(APP_TITLE);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            
            // Set application icon (if available)
            try {
                // You can add an icon file in resources folder
                // Image icon = new Image(getClass().getResourceAsStream("/calculator-icon.png"));
                // primaryStage.getIcons().add(icon);
            } catch (Exception e) {
                // Icon not found, continue without icon
            }
            
            // Show the stage
            primaryStage.show();
            
            // Show welcome dialog after UI is loaded
            Platform.runLater(() -> {
                showWelcomeDialog();
            });
            
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Application Error", 
                "Failed to start V-CALCULATOR:\n" + e.getMessage());
        }
    }
    
    /**
     * Shows welcome dialog with developer information
     */
    private void showWelcomeDialog() {
        AboutDialog.showInfo(
            "🚀 Welcome to V-CALCULATOR " + APP_VERSION,
            "Thank you for using V-CALCULATOR!\n\n" +
            "Developer Information:\n" +
            "═══════════════════════════\n" +
            "👨‍💻 Author  : Md. Mainul Islam\n" +
            "🏢 Owner   : MAINUL - X\n" +
            "🐙 GitHub  : M41NUL\n" +
            "🔗 URL     : https://github.com/M41NUL\n" +
            "📱 WhatsApp: +8801308850528\n" +
            "💬 Telegram: @mdmainulislaminfo\n" +
            "📧 Email   : githubmainul@gmail.com\n" +
            "📝 License : MIT\n" +
            "© Copyright : 2026 MAINUL - X\n\n" +
            "Features:\n" +
            "══════════\n" +
            "✓ Basic Operations (+, -, ×, ÷)\n" +
            "✓ Scientific Functions (sin, cos, tan)\n" +
            "✓ Logarithm (log, ln)\n" +
            "✓ Power (x^y) and Square Root (√)\n" +
            "✓ Factorial (x!) and 1/x\n" +
            "✓ Constants (π, e)\n" +
            "✓ Percentage (%)\n" +
            "✓ Deg/Rad toggle\n" +
            "✓ 2nd mode for inverse functions\n\n" +
            "Press 'Start Calculating' to begin!"
        );
    }
    
    /**
     * Shows error dialog when application fails to start
     * 
     * @param title Dialog title
     * @param message Error message
     */
    private void showErrorDialog(String title, String message) {
        AboutDialog.showError(title, message);
    }
    
    /**
     * Called when application is stopping
     */
    @Override
    public void stop() {
        // Cleanup resources if needed
        System.out.println("V-CALCULATOR is closing. Goodbye!");
        System.out.println(SHORT_DEV_INFO);
    }
    
    /**
     * Main method - Entry point for the application
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting V-CALCULATOR...");
        System.out.println("Developer: " + DEVELOPER_INFO.split("\n")[1]);
        System.out.println("Version: " + APP_VERSION);
        
        // Launch JavaFX application
        launch(args);
    }
}
