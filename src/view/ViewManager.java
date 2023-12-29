package view;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.InfoLabel;
import model.SHIP;
import model.ShipPicker;
import model.SpaceRunnerButton;
import model.SpaceRunnerSubScene;

public class ViewManager {

	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int Menu_BUTTONS_START_Y=150;
	
	private SpaceRunnerSubScene creditsSubScene;
	private SpaceRunnerSubScene helpSubScene;
	private SpaceRunnerSubScene scoreSubScene;
	private SpaceRunnerSubScene shipChooserScene;
	private SpaceRunnerSubScene sceneToHide;
	
	List<SpaceRunnerButton> menuButtons;
	
	List<ShipPicker> shipsList;
	private SHIP choosenShip;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);

		createButtons();
		createBackground();
		createLogo();
		createSubScenes();
		
		
		
		
	}
	
	private void showSubScene(SpaceRunnerSubScene subScene) {
		if (sceneToHide!=null) {
			sceneToHide.moveSubScene();
		}
		subScene.moveSubScene();
		sceneToHide = subScene;
	}
	
	private void createSubScenes() {
		
		creditsSubScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(creditsSubScene);
		
		helpSubScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(helpSubScene);

		scoreSubScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(scoreSubScene);

		createShipChooserSubScene();
	}
	
	private void createShipChooserSubScene() {
		shipChooserScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(shipChooserScene);
	
		InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
		chooseShipLabel.setLayoutX(110);
		chooseShipLabel.setLayoutY(25);
		shipChooserScene.getPane().getChildren().add(chooseShipLabel);
		shipChooserScene.getPane().getChildren().add(createShipToChoose());
		shipChooserScene.getPane().getChildren().add(createButtonToStart());
	
	}
	
	private HBox createShipToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		shipsList =new ArrayList<>();
		for (SHIP ship : SHIP.values()) {
			ShipPicker shipToPick = new ShipPicker(ship);
			shipsList.add(shipToPick);
			box.getChildren().add(shipToPick);
			shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					for (ShipPicker ship:shipsList) {
						ship.setIsCircleChoosen(false);
					}
					shipToPick.setIsCircleChoosen(true);
					choosenShip = shipToPick.getShip();
				}
			});
		}
		box.setLayoutX(300 - (118*2));
		box.setLayoutY(100);
		return box;
	}
	
	private SpaceRunnerButton createButtonToStart() {
		SpaceRunnerButton startButton = new SpaceRunnerButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(choosenShip!=null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.createNewGame(mainStage, choosenShip);
				}
			}
		});
		return startButton;
	}
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuButton(SpaceRunnerButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(Menu_BUTTONS_START_Y + menuButtons.size()*100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void createButtons() {
		
		createStartButton();
		createScoresButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
	private void createStartButton() {
		SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
		addMenuButton(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				showSubScene(shipChooserScene);
			}
		});
		
	}

	private void createScoresButton() {
		SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORED");
		addMenuButton(scoresButton);
		
		scoresButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoreSubScene);
			}
		});
		
	}
	
	private void createHelpButton() {
		SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
		addMenuButton(helpButton);

		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubScene);
			}
		});
		
	
	}

	private void createCreditsButton() {
		SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
		addMenuButton(creditsButton);
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				showSubScene(creditsSubScene);
			}
		});
		
	}
	
	private void createExitButton() {
		SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
		addMenuButton(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
		});
	}
	
	private void createBackground() {
		Image backgroundImage = new Image(getClass().getResource("/view/resources/blue.png").toExternalForm(), 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));

	}
	
	private void createLogo() {
	    ImageView logo = new ImageView(getClass().getResource("/view/resources/void_arc.png").toExternalForm());
		logo.setLayoutX(400);
		logo.setLayoutY(50);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
		});
		
		mainPane.getChildren().add(logo);
	}
}