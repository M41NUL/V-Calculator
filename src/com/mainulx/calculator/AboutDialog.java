/*
 * ============================================
 * V-CALCULATOR - About Dialog
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * AboutDialog class - Displays information about the application
 * Shows developer info, version, license, and other details
 */
public class AboutDialog {
    
    // Developer information constants
    private static final String APP_NAME = "V-CALCULATOR";
    private static final String APP_VERSION = "1.0.0";
    private static final String APP_BUILD = "2026.02.28";
    
    private static final String DEV_NAME = "Md. Mainul Islam";
    private static final String DEV_OWNER = "MAINUL - X";
    private static final String DEV_GITHUB = "M41NUL";
    private static final String DEV_GITHUB_URL = "https://github.com/M41NUL";
    private static final String DEV_WHATSAPP = "+8801308850528";
    private static final String DEV_TELEGRAM = "@mdmainulislaminfo";
    private static final String DEV_EMAIL = "githubmainul@gmail.com";
    private static final String DEV_LICENSE = "MIT License";
    private static final String DEV_COPYRIGHT = "Copyright (c) 2026 MAINUL - X";
    
    // Colors
    private static final String BG_DARK = "#1e1e1e";
    private static final String BG_DARKER = "#2d2d2d";
    private static final String TEXT_PRIMARY = "#87CEEB";
    private static final String TEXT_SECONDARY = "#888888";
    private static final String TEXT_WHITE = "#ffffff";
    private static final String ACCENT_COLOR = "#51cf66";
    private static final String ERROR_COLOR = "#ff6b6b";
    
    /**
     * Show information dialog
     * @param title Dialog title
     * @param message Message to display
     */
    public static void showInfo(String title, String message) {
        showDialog(title, message, "info");
    }
    
    /**
     * Show error dialog
     * @param title Dialog title
     * @param message Error message
     */
    public static void showError(String title, String message) {
        showDialog(title, message, "error");
    }
    
    /**
     * Show warning dialog
     * @param title Dialog title
     * @param message Warning message
     */
    public static void showWarning(String title, String message) {
        showDialog(title, message, "warning");
    }
    
    /**
     * Show confirmation dialog
     * @param title Dialog title
     * @param message Confirmation message
     * @return true if user confirms
     */
    public static boolean showConfirmation(String title, String message) {
        return showConfirmDialog(title, message);
    }
    
    /**
     * Show about dialog with developer information
     * @param owner Parent stage
     */
    public static void showAbout(Stage owner) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setTitle("About " + APP_NAME);
        
        VBox content = createAboutContent();
        
        Button closeButton = new Button("Close");
        closeButton.setStyle(String.format(
            "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px; " +
            "-fx-padding: 10px 30px; -fx-font-weight: bold; -fx-background-radius: 5px; " +
            "-fx-cursor: hand;", TEXT_PRIMARY
        ));
        closeButton.setOnAction(e -> dialog.close());
        
        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: " + BG_DARK + ";");
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(content, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 500, 600);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    
    /**
     * Show license dialog
     * @param owner Parent stage
     */
    public static void showLicense(Stage owner) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setTitle("MIT License");
        
        VBox layout = new VBox(15);
        layout.setStyle("-fx-background-color: " + BG_DARK + ";");
        layout.setPadding(new Insets(20));
        
        Label title = new Label("MIT License");
        title.setStyle(String.format(
            "-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: %s;", TEXT_PRIMARY
        ));
        
        TextArea licenseText = new TextArea(getMITLicense());
        licenseText.setEditable(false);
        licenseText.setWrapText(true);
        licenseText.setStyle(
            "-fx-control-inner-background: " + BG_DARKER + ";" +
            "-fx-text-fill: " + TEXT_WHITE + ";" +
            "-fx-font-family: 'Monospaced'; -fx-font-size: 12px;"
        );
        licenseText.setPrefRowCount(20);
        
        Button closeButton = new Button("Close");
        closeButton.setStyle(String.format(
            "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px; " +
            "-fx-padding: 10px 30px; -fx-font-weight: bold; -fx-background-radius: 5px; " +
            "-fx-cursor: hand;", TEXT_PRIMARY
        ));
        closeButton.setOnAction(e -> dialog.close());
        
        layout.getChildren().addAll(title, licenseText, closeButton);
        
        Scene scene = new Scene(layout, 600, 500);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    
    /**
     * Show exception dialog with stack trace
     * @param owner Parent stage
     * @param exception The exception to display
     */
    public static void showException(Stage owner, Exception exception) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setTitle("Application Error");
        
        VBox layout = new VBox(15);
        layout.setStyle("-fx-background-color: " + BG_DARK + ";");
        layout.setPadding(new Insets(20));
        
        Label title = new Label("An Error Occurred");
        title.setStyle(String.format(
            "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: %s;", ERROR_COLOR
        ));
        
        Label message = new Label(exception.getMessage());
        message.setStyle("-fx-text-fill: " + TEXT_WHITE + "; -fx-font-size: 14px;");
        message.setWrapText(true);
        
        // Stack trace
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        
        TextArea stackTrace = new TextArea(sw.toString());
        stackTrace.setEditable(false);
        stackTrace.setStyle(
            "-fx-control-inner-background: " + BG_DARKER + ";" +
            "-fx-text-fill: " + TEXT_SECONDARY + ";" +
            "-fx-font-family: 'Monospaced'; -fx-font-size: 11px;"
        );
        stackTrace.setPrefRowCount(10);
        
        Button closeButton = new Button("Close");
        closeButton.setStyle(String.format(
            "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px; " +
            "-fx-padding: 10px 30px; -fx-font-weight: bold; -fx-background-radius: 5px; " +
            "-fx-cursor: hand;", ERROR_COLOR
        ));
        closeButton.setOnAction(e -> dialog.close());
        
        layout.getChildren().addAll(title, message, stackTrace, closeButton);
        
        Scene scene = new Scene(layout, 600, 500);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    
    /**
     * Show developer contact dialog
     * @param owner Parent stage
     */
    public static void showContact(Stage owner) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setTitle("Contact Developer");
        
        VBox layout = new VBox(15);
        layout.setStyle("-fx-background-color: " + BG_DARK + ";");
        layout.setPadding(new Insets(20));
        
        Label title = new Label("Contact Information");
        title.setStyle(String.format(
            "-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: %s;", TEXT_PRIMARY
        ));
        
        GridPane contactGrid = new GridPane();
        contactGrid.setHgap(15);
        contactGrid.setVgap(10);
        contactGrid.setAlignment(Pos.CENTER);
        
        addContactRow(contactGrid, "👨‍💻 Developer:", DEV_NAME, 0);
        addContactRow(contactGrid, "🏢 Owner:", DEV_OWNER, 1);
        addContactRow(contactGrid, "🐙 GitHub:", DEV_GITHUB, 2);
        addContactRow(contactGrid, "🔗 URL:", DEV_GITHUB_URL, 3);
        addContactRow(contactGrid, "📱 WhatsApp:", DEV_WHATSAPP, 4);
        addContactRow(contactGrid, "💬 Telegram:", DEV_TELEGRAM, 5);
        addContactRow(contactGrid, "📧 Email:", DEV_EMAIL, 6);
        addContactRow(contactGrid, "📝 License:", DEV_LICENSE, 7);
        
        Button closeButton = new Button("Close");
        closeButton.setStyle(String.format(
            "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px; " +
            "-fx-padding: 10px 30px; -fx-font-weight: bold; -fx-background-radius: 5px; " +
            "-fx-cursor: hand;", TEXT_PRIMARY
        ));
        closeButton.setOnAction(e -> dialog.close());
        
        layout.getChildren().addAll(title, contactGrid, closeButton);
        
        Scene scene = new Scene(layout, 400, 350);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    
    /**
     * Helper method to create about content
     */
    private static VBox createAboutContent() {
        VBox content = new VBox(15);
        content.setAlignment(Pos.CENTER);
        
        // App name
        Label appName = new Label(APP_NAME);
        appName.setStyle(String.format(
            "-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: %s;", TEXT_PRIMARY
        ));
        
        // Version
        Label version = new Label("Version " + APP_VERSION + " (Build " + APP_BUILD + ")");
        version.setStyle("-fx-text-fill: " + TEXT_SECONDARY + "; -fx-font-size: 14px;");
        
        // Separator
        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: " + TEXT_PRIMARY + ";");
        
        // Developer info
        Label devTitle = new Label("Developer Information");
        devTitle.setStyle(String.format(
            "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: %s;", TEXT_WHITE
        ));
        
        GridPane devGrid = new GridPane();
        devGrid.setHgap(10);
        devGrid.setVgap(8);
        devGrid.setAlignment(Pos.CENTER);
        
        addDevRow(devGrid, "Name:", DEV_NAME, 0);
        addDevRow(devGrid, "Owner:", DEV_OWNER, 1);
        addDevRow(devGrid, "GitHub:", DEV_GITHUB, 2);
        addDevRow(devGrid, "WhatsApp:", DEV_WHATSAPP, 3);
        addDevRow(devGrid, "Telegram:", DEV_TELEGRAM, 4);
        addDevRow(devGrid, "Email:", DEV_EMAIL, 5);
        
        // Features
        Label featuresTitle = new Label("Features");
        featuresTitle.setStyle(String.format(
            "-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: %s;", TEXT_WHITE
        ));
        
        VBox features = new VBox(5);
        features.setAlignment(Pos.CENTER_LEFT);
        features.getChildren().addAll(
            createFeatureLabel("✓ Basic Operations (+, -, ×, ÷)"),
            createFeatureLabel("✓ Scientific Functions (sin, cos, tan)"),
            createFeatureLabel("✓ Logarithm (log, ln)"),
            createFeatureLabel("✓ Power (x^y) and Square Root (√)"),
            createFeatureLabel("✓ Factorial (x!) and 1/x"),
            createFeatureLabel("✓ Constants (π, e)"),
            createFeatureLabel("✓ Percentage (%)"),
            createFeatureLabel("✓ Deg/Rad toggle"),
            createFeatureLabel("✓ 2nd mode for inverse functions")
        );
        
        // Copyright
        Label copyright = new Label(DEV_COPYRIGHT);
        copyright.setStyle("-fx-text-fill: " + TEXT_SECONDARY + "; -fx-font-size: 11px;");
        
        content.getChildren().addAll(
            appName, version, separator,
            devTitle, devGrid, separator,
            featuresTitle, features, separator,
            copyright
        );
        
        return content;
    }
    
    /**
     * Helper method to add developer info row
     */
    private static void addDevRow(GridPane grid, String label, String value, int row) {
        Label lbl = new Label(label);
        lbl.setStyle("-fx-text-fill: " + TEXT_SECONDARY + "; -fx-font-size: 14px; -fx-font-weight: bold;");
        
        Label val = new Label(value);
        val.setStyle("-fx-text-fill: " + TEXT_WHITE + "; -fx-font-size: 14px;");
        
        grid.add(lbl, 0, row);
        grid.add(val, 1, row);
    }
    
    /**
     * Helper method to add contact info row
     */
    private static void addContactRow(GridPane grid, String label, String value, int row) {
        Label lbl = new Label(label);
        lbl.setStyle("-fx-text-fill: " + TEXT_SECONDARY + "; -fx-font-size: 13px; -fx-font-weight: bold;");
        
        Label val = new Label(value);
        val.setStyle("-fx-text-fill: " + TEXT_WHITE + "; -fx-font-size: 13px;");
        
        if (label.contains("GitHub") || label.contains("Email")) {
            Hyperlink link = new Hyperlink(value);
            link.setStyle("-fx-text-fill: " + TEXT_PRIMARY + "; -fx-font-size: 13px; -fx-border-color: transparent;");
            link.setOnAction(e -> {
                try {
                    if (label.contains("GitHub")) {
                        java.awt.Desktop.getDesktop().browse(
                            new java.net.URI("https://github.com/" + DEV_GITHUB)
                        );
                    } else if (label.contains("Email")) {
                        java.awt.Desktop.getDesktop().mail(
                            new java.net.URI("mailto:" + DEV_EMAIL)
                        );
                    }
                } catch (Exception ex) {
                    // Ignore
                }
            });
            grid.add(lbl, 0, row);
            grid.add(link, 1, row);
        } else {
            grid.add(lbl, 0, row);
            grid.add(val, 1, row);
        }
    }
    
    /**
     * Helper method to create feature label
     */
    private static Label createFeatureLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: " + TEXT_WHITE + "; -fx-font-size: 12px;");
        return label;
    }
    
    /**
     * Show generic dialog
     */
    private static void showDialog(String title, String message, String type) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);
        
        VBox layout = new VBox(15);
        layout.setStyle("-fx-background-color: " + BG_DARK + ";");
        layout.setPadding(new Insets(20));
        
        // Icon based on type
        String icon = "ℹ️";
        String color = TEXT_PRIMARY;
        
        switch(type) {
            case "error":
                icon = "❌";
                color = ERROR_COLOR;
                break;
            case "warning":
                icon = "⚠️";
                color = "#ffa500";
                break;
        }
        
        Label iconLabel = new Label(icon);
        iconLabel.setStyle("-fx-font-size: 48px;");
        
        Label msgLabel = new Label(message);
        msgLabel.setStyle("-fx-text-fill: " + TEXT_WHITE + "; -fx-font-size: 14px;");
        msgLabel.setWrapText(true);
        
        Button closeButton = new Button("OK");
        closeButton.setStyle(String.format(
            "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px; " +
            "-fx-padding: 8px 25px; -fx-font-weight: bold; -fx-background-radius: 5px; " +
            "-fx-cursor: hand;", color
        ));
        closeButton.setOnAction(e -> dialog.close());
        
        layout.getChildren().addAll(iconLabel, msgLabel, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 350, 250);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    
    /**
     * Show confirmation dialog
     */
    private static boolean showConfirmDialog(String title, String message) {
        final boolean[] result = new boolean[1];
        
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);
        
        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: " + BG_DARK + ";");
        layout.setPadding(new Insets(20));
        
        Label msgLabel = new Label(message);
        msgLabel.setStyle("-fx-text-fill: " + TEXT_WHITE + "; -fx-font-size: 14px;");
        msgLabel.setWrapText(true);
        
        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER);
        
        Button yesButton = new Button("Yes");
        yesButton.setStyle(String.format(
            "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px; " +
            "-fx-padding: 8px 25px; -fx-font-weight: bold; -fx-background-radius: 5px; " +
            "-fx-cursor: hand;", ACCENT_COLOR
        ));
        yesButton.setOnAction(e -> {
            result[0] = true;
            dialog.close();
        });
        
        Button noButton = new Button("No");
        noButton.setStyle(String.format(
            "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px; " +
            "-fx-padding: 8px 25px; -fx-font-weight: bold; -fx-background-radius: 5px; " +
            "-fx-cursor: hand;", ERROR_COLOR
        ));
        noButton.setOnAction(e -> {
            result[0] = false;
            dialog.close();
        });
        
        buttons.getChildren().addAll(yesButton, noButton);
        layout.getChildren().addAll(msgLabel, buttons);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 300, 150);
        dialog.setScene(scene);
        dialog.showAndWait();
        
        return result[0];
    }
    
    /**
     * Get MIT License text
     */
    private static String getMITLicense() {
        return "MIT License\n\n" +
               "Copyright (c) 2026 MAINUL - X\n\n" +
               "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
               "of this software and associated documentation files (the \"Software\"), to deal\n" +
               "in the Software without restriction, including without limitation the rights\n" +
               "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
               "copies of the Software, and to permit persons to whom the Software is\n" +
               "furnished to do so, subject to the following conditions:\n\n" +
               "The above copyright notice and this permission notice shall be included in all\n" +
               "copies or substantial portions of the Software.\n\n" +
               "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
               "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
               "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
               "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
               "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
               "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
               "SOFTWARE.";
    }
}
