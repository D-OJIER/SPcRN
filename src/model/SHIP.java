package model;

import javafx.scene.image.Image;

public enum SHIP {

    BLUE("/view/resources/shipchooser/playerShip3_blue.png"),
    GREEN("/view/resources/shipchooser/playerShip3_green.png"),
    ORANGE("/view/resources/shipchooser/playerShip3_orange.png"),
    RED("/view/resources/shipchooser/playerShip3_red.png");

    private String urlShip;

    private SHIP(String urlShip) {
        this.urlShip = urlShip;
    }

    public String getUrl() {
        return this.urlShip;
    }

    public Image getShipImage() {
        return new Image(getClass().getResourceAsStream(urlShip));
    }
}
