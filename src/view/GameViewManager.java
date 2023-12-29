package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.SHIP;

public class GameViewManager {

	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	
	private Stage menuStage;
	private ImageView ship;
	
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private int angle;
	private AnimationTimer gameTimer;
	
	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String BACKGROUND_IMAGE = "/view/resources/blue.png";
	
	public GameViewManager() {
		initializeStage();
		createKeyListeners();
	}
	
	private void createKeyListeners() {
		
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed =true;
				} else if (event.getCode()==KeyCode.RIGHT) {
					isRightKeyPressed =true;
				}
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				} else if (event.getCode()==KeyCode.RIGHT) {
					isRightKeyPressed = false;	
				}
			}
		});
				
	}
	
	private void initializeStage() {
		gamePane =new AnchorPane();
		gameScene =new Scene(gamePane,GAME_WIDTH,GAME_HEIGHT);
		gameStage =new Stage();
		gameStage.setScene(gameScene)
		;
	}
	public void createNewGame(Stage menuStage, SHIP choosenShip) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		createShip(choosenShip);
		createGameLoop();
		gameTimer.start();
		gameStage.show();
	}
	
	private void createShip(SHIP choosenShip) {
		ship = new ImageView(choosenShip.getShipImage());
		ship.setLayoutX(GAME_WIDTH/2);
		ship.setLayoutY(GAME_HEIGHT-200);
		gamePane.getChildren().add(ship);
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {
			@Override
			public void handle (long now) {
				moveBackground();
				moveShip();
			}
		};
	}
	
	private void moveShip() {
		if(isLeftKeyPressed && !isRightKeyPressed) {
			if(angle>-30) {
				angle-=5;
			}
			ship.setRotate(angle);
			if(ship.getLayoutX()>-20) {
				ship.setLayoutX(ship.getLayoutX()-3);
			}
		}
		if(isRightKeyPressed && !isLeftKeyPressed) {
			if(angle<30) {
				angle +=5;
			}
			ship.setRotate(angle);
			if(ship.getLayoutX()<522) {
				ship.setLayoutX(ship.getLayoutX()+3);
			}
		}
		if(!isLeftKeyPressed && !isRightKeyPressed) {
			if(angle<0) {
				angle +=5;
			}else if(angle>0) {
				angle=-5;
			}
			ship.setRotate(angle);
		}
		if(isLeftKeyPressed && isRightKeyPressed) {
			if(angle>0) {
				angle +=5;
			}else if(angle>0) {
				angle=-5;
			}
			ship.setRotate(angle);
		}
	}
	
    private void createBackground() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            ImageView backgroundImage1 = new ImageView(getClass().getResource(BACKGROUND_IMAGE).toExternalForm());
            ImageView backgroundImage2 = new ImageView(getClass().getResource(BACKGROUND_IMAGE).toExternalForm());

            // Set the size of the background images
            backgroundImage1.setFitWidth(GAME_WIDTH);
            backgroundImage1.setFitHeight(GAME_HEIGHT);
            backgroundImage2.setFitWidth(GAME_WIDTH);
            backgroundImage2.setFitHeight(GAME_HEIGHT);

            GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroundImage2, (i % 3) + 3, i / 3); // Adjusted column index
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }

        gridPane2.setLayoutX(0);
        gridPane2.setLayoutY(-GAME_HEIGHT);
        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }
	
	private void moveBackground() {

		gridPane1.setLayoutY(gridPane1.getLayoutY()+5);
		gridPane2.setLayoutY(gridPane2.getLayoutY()+5);
		if (gridPane1.getLayoutY()>=1024) {
			gridPane1.setLayoutY(-1024);
		}
		if (gridPane2.getLayoutY()>=1024) {
			gridPane2.setLayoutY(-1024);
		}
	}
}


















