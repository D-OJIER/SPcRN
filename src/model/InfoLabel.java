package model;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.InputStream;

public class InfoLabel extends Label {

    public final static String FONT_PATH = "/model/resources/kenvector_future.ttf";
    public final static String BACKGROUND_IMAGE = "/model/resources/buttonYellow.png";

    public InfoLabel(String text) {
        setPrefWidth(380);
        setPrefHeight(49);
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER);

        InputStream backgroundStream = getClass().getResourceAsStream(BACKGROUND_IMAGE);
        if (backgroundStream != null) {
            Image backgroundImage = new Image(backgroundStream, 380, 49, false, true);
            BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            setBackground(new Background(background));
        } else {
            System.err.println("Error loading background image.");
        }
    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 23));
        } catch (Exception e) {
            System.err.println("Error loading font.");
            setFont(Font.font("Verdana", 23));
        }
    }
}
