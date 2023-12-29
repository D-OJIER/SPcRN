package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class SpaceRunnerSubScene extends SubScene {

    private final static String FONT_PATH = "/model/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "/model/resources/yellow_panel.png";

    private boolean isHidden;
    
    public SpaceRunnerSubScene() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

        Image backgroundImage = new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm());
        BackgroundImage image = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));
        
        isHidden=true;
        setLayoutX(1024);
        setLayoutY(180);
        
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);

        root2.getChildren().add(imageView);
    }
    
    public void moveSubScene() {
    	TranslateTransition transition = new TranslateTransition();
    	transition.setDuration(Duration.seconds(0.3));
    	transition.setNode(this);
    	
    	if(isHidden) {
    		transition.setToX(-676);
        	isHidden = false;	
    	}
    	else {
    		transition.setToX(0);
    		isHidden = true;
    	}
    	
    	transition.play();
    }
    
    public AnchorPane getPane() {
    	return (AnchorPane)this.getRoot();
    }
}
