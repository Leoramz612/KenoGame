import com.sun.javafx.menu.MenuItemBase;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.text.*;

/*
	Names: Leo Ramirez, Arwa Mazher
	netids: lrami7, amazh2
	lrami7@uic.edu, amazh2@uic.edu
	Description: This project implements the popular casino and state lottery game, Keno, by using classes and JavaFX components.
 */
public class JavaFXTemplate extends Application {

	String Menustyle = "-fx-padding: 15 40 15 15;\n" +
			"    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
			"    -fx-background-radius: 8;\n" +
			"    -fx-background-color: \n" +
			"        linear-gradient(from 0% 93% to 0% 100%, #891298 0%, #7e118c 100%),\n" +
			"        #862193,\n" +
			"        #c238d3,\n" +
			"        radial-gradient(center 50% 50%, radius 100%, #bd32ce, #ac29bd);\n" +
			"    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
			"    -fx-font-weight: bold;\n" +
			"    -fx-font-size: 1.1em;";
	String buttonStyle = "-fx-padding: 8 15 15 15;\n" +
			"    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
			"    -fx-background-radius: 8;\n" +
			"    -fx-background-color: \n" +
			"        linear-gradient(from 0% 93% to 0% 100%, #891298 0%, #7e118c 100%),\n" +
			"        #862193,\n" +
			"        #c238d3,\n" +
			"        radial-gradient(center 50% 50%, radius 100%, #bd32ce, #ac29bd);\n" +
			"    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
			"    -fx-font: bold 12pt \"Tahoma\";-fx-border-width: 5";
	String buttonHovered = "-fx-padding: 10 15 13 15;\n" +
			"    -fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;\n" +
			"    -fx-background-radius: 8;\n" +
			"    -fx-background-color: \n" +
			"        linear-gradient(from 0% 93% to 0% 100%, #891298 0%, #7e118c 100%),\n" +
			"        #862193,\n" +
			"        #c238d3," +
			"        radial-gradient(center 50% 50%, radius 100%, #d448e5, #ac29bd);\n" +
			"    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
			"    -fx-font: bold 12pt \"Tahoma\";-fx-border-width: 5";
	double width = 1400;
	double height = 730;
	Scene resumeBetCard = null;
	EventHandler<ActionEvent> changeMenuRules;
	EventHandler<ActionEvent> changeMenuOdds;
	EventHandler<ActionEvent> changeMenuQuit;
	EventHandler<ActionEvent> backHome;
	EventHandler<ActionEvent> playGame;
	EventHandler<ActionEvent> resumeGame;
	EventHandler<MouseEvent> hoverButton;
	EventHandler<MouseEvent> hoverButton2;
	EventHandler<MouseEvent> hoverMenuButton;
	EventHandler<MouseEvent> hoverMenuButton2;


	//main method of JavaFXTemplate Class
	public static void main(String[] args) {
		launch(args);
	}

	//start method class of Application
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle("Welcome to KENO");
//		primaryStage.setWidth(1000);
//		primaryStage.setHeight(800);

		Image iconLogo = new Image("keno.png");
		primaryStage.getIcons().add(iconLogo);

		//BUTTON EVENT HANDLERS FOR MENU, HOME, AND PLAY
		//Go to rules scene
		changeMenuRules = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.setScene(rules());
			}
		};
		//Go to odds scene
		changeMenuOdds = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.setScene(odds());
			}
		};
		//Go to quit scene and then close application
		changeMenuQuit = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.setScene(quit());
				PauseTransition exitPause = new PauseTransition(Duration.seconds(1));
				exitPause.setOnFinished(e->Platform.exit());
				exitPause.play();

			}
		};
		//Go to welcome scene
		backHome = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.setScene(welcome());
			}
		};
		//Go to bet card scene that was previously made
		resumeGame = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.setScene(resumeBetCard);
			}
		};
		//Go to new bet card scene
		playGame = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.setScene(gamePlay(primaryStage));
			}
		};
		//Change look of button when mouse enters it
		hoverButton = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Button b = (Button)event.getSource();
				b.setStyle(buttonHovered);
			}
		};
		//Change look of button back when mouse exits it
		hoverButton2 = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Button b = (Button)event.getSource();
				b.setStyle(buttonStyle);
			}
		};
		//Change look of menu Button when mouse enters it
		hoverMenuButton = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				MenuButton b = (MenuButton)event.getSource();
				b.setStyle(buttonHovered);
			}
		};
		//Change look of menu Button back when mouse exits it
		hoverMenuButton2 = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				MenuButton b = (MenuButton)event.getSource();
				b.setStyle(buttonStyle);
			}
		};

		//Set the first scene as the welcome scene
		primaryStage.setScene(welcome());
		primaryStage.show();
	}

	//Scene that the quit menu option goes to
	public Scene quit(){
		//Text of the scene
		Text header = new Text("THANK YOU FOR");
		header.setStyle(" -fx-font: bold 75pt \"Impact\";-fx-border-width: 5;-fx-fill: #cfc655;");

		Text header2 = new Text("PLAYING KENO");
		header2.setStyle(" -fx-font: bold 75pt \"Impact\";-fx-border-width: 5;-fx-fill: #e7f3ae;");

		Text body = new Text("Goodbye!");
		body.setStyle(" -fx-font: bold 20pt \"Tahoma\";-fx-border-width: 5;-fx-fill: #cfc655;");
		body.setWrappingWidth(200);
		body.setTextAlignment(TextAlignment.CENTER);

		//holds all the text
		VBox quitCenter = new VBox();
		quitCenter.getChildren().addAll(header,header2,body);
		quitCenter.setAlignment(Pos.CENTER);
		quitCenter.setMinWidth(200);
		quitCenter.setPrefWidth(200);

		//root of the scene
		BorderPane root = new BorderPane();
		root.setCenter(quitCenter);
		root.setStyle("-fx-background-color: #13103b;");//6e68bb

		//SHOW QUIT SCENE
		return new Scene(root, width, height);

	}

	//Scene that the odds menu option goes to
	public Scene odds(){
		//Create drop down menu
		MenuButton menu = new MenuButton("Menu");
		MenuItem displayRules = new MenuItem("Display Rules");
		displayRules.setStyle(Menustyle);
		MenuItem oddsWinning = new MenuItem("Odds of Winning");
		oddsWinning.setStyle(Menustyle);
		MenuItem exitGame = new MenuItem("Exit Game");
		exitGame.setStyle(Menustyle);
		menu.getItems().add(displayRules);
		menu.getItems().add(oddsWinning);
		menu.getItems().add(exitGame);
		menu.setStyle(buttonStyle);
		menu.setPrefWidth(150);
		menu.setAlignment(Pos.CENTER);
		menu.setOnMouseEntered(hoverMenuButton);
		menu.setOnMouseExited(hoverMenuButton2);

		displayRules.setOnAction(changeMenuRules);
		exitGame.setOnAction(changeMenuQuit);

		//container that holds the menuButton
		HBox menuRegion = new HBox();
		menuRegion.getChildren().addAll(menu);
		menuRegion.setAlignment(Pos.CENTER_LEFT);
		menuRegion.setPadding(new Insets(40,0,0,60));

		//Home and Play buttons created
		Button home = new Button("Home");
		home.setStyle(buttonStyle);
		home.setOnAction(backHome);
		home.setOnMouseEntered(hoverButton);
		home.setOnMouseExited(hoverButton2);

		Button play = new Button("Play");
		play.setStyle(buttonStyle);
		play.setOnMouseEntered(hoverButton);
		play.setOnMouseExited(hoverButton2);
		//if a betcard was already made,then return that otherwise create a new betcard scene
		if(resumeBetCard != null){
			play.setText("Resume Bet Card");
			play.setOnAction(resumeGame);
		}
		else{
			play.setText("Play");
			play.setOnAction(playGame);
		}

		//container for play and home button
		VBox buttonsAligned = new VBox();
		buttonsAligned.getChildren().addAll(play,home);
		buttonsAligned.setSpacing(150);
		buttonsAligned.setAlignment(Pos.CENTER);
		buttonsAligned.setMinWidth(200);
		buttonsAligned.setPrefWidth(200);
		buttonsAligned.setPadding(new Insets(0,30,0,0));

		//Text for middle of the screen
		Text header = new Text("ODDS OF WINNING");
		header.setStyle(" -fx-font: bold 75pt \"Impact\";-fx-border-width: 5;-fx-fill: #cfc655;");
		// Display odds of winning prizes based on spots and matches
		Text body = new Text("\tSpots\t\t\tOdds\t\t\tMatch\t\t\tPrize\n" +
				"\t    1\t\t\t     1 in 4.00\t\t\t    1\t\t\t\t  $2\n" +
				"\t    4\t\t\t     1 in 3.86\t\t\t    2\t\t\t\t  $1\n" +
				"\t    4\t\t\t     1 in 3.86\t\t\t    3\t\t\t\t  $5\n" +
				"\t    4\t\t\t     1 in 3.86\t\t\t    4\t\t\t\t $75\n" +
				"\t    8\t\t\t     1 in 9.77\t\t\t    4\t\t\t\t  $2\n" +
				"\t    8\t\t\t     1 in 9.77\t\t\t    5\t\t\t\t $12\n" +
				"\t    8\t\t\t     1 in 9.77\t\t\t    6\t\t\t\t $50\n" +
				"\t    8\t\t\t     1 in 9.77\t\t\t    7\t\t\t\t$750\n" +
				"\t    8\t\t\t     1 in 9.77\t\t\t    8\t\t\t     $10,000\n" +
				"\t   10\t\t\t     1 in 9.05\t\t\t    0\t\t\t\t  $5\n" +
				"\t   10\t\t\t     1 in 9.05\t\t\t    5\t\t\t\t  $2\n" +
				"\t   10\t\t\t     1 in 9.05\t\t\t    6\t\t\t\t $15\n" +
				"\t   10\t\t\t     1 in 9.05\t\t\t    7\t\t\t\t $40\n" +
				"\t   10\t\t\t     1 in 9.05\t\t\t    8\t\t\t\t$450\n" +
				"\t   10\t\t\t     1 in 9.05\t\t\t    9\t\t\t       $4250\n" +
				"\t   10\t\t\t     1 in 9.05\t\t\t   10\t\t\t    $100,000\n");
		body.setStyle(" -fx-font: bold 20pt \"Tahoma\";-fx-border-width: 5;-fx-fill: #e7f3ae;");
		body.setWrappingWidth(1000);

		//holds all the text
		VBox oddsCenter = new VBox();
		oddsCenter.getChildren().addAll(header,body);
		oddsCenter.setAlignment(Pos.CENTER);
		oddsCenter.setMinWidth(800);
		oddsCenter.setPrefWidth(800);
		oddsCenter.setSpacing(50);
		oddsCenter.setPadding(new Insets(0,0,100,0));

		//For spacing and cutting off label
		Label leftLabel = new Label();
		leftLabel.setMinWidth(200);
		leftLabel.setPrefWidth(200);

		Label bottomLabel = new Label();
		bottomLabel.setMinWidth(200);
		bottomLabel.setPrefWidth(200);

		//bp holds all the widgets created
		BorderPane bp = new BorderPane();
		bp.setCenter(oddsCenter);
		bp.setTop(menuRegion);
		bp.setLeft(leftLabel);
		bp.setRight(buttonsAligned);
		bp.setBottom(bottomLabel);
		bp.setStyle("-fx-background-color: #13103b;");//6e68bb

		//the root holds bp and allows for scrolling of the game
		ScrollPane root = new ScrollPane();
		root.setContent(bp);
		root.setFitToHeight(true);
		root.setFitToWidth(true);
		root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		//SHOW WELCOME SCENE
		return new Scene(root, width, height);
	}

	//Scene that the rules menu option goes to
	public Scene rules(){
		//Create dropdown menu
		MenuButton menu = new MenuButton("Menu");
		MenuItem displayRules = new MenuItem("Display Rules");
		displayRules.setStyle(Menustyle);
		MenuItem oddsWinning = new MenuItem("Odds of Winning");
		oddsWinning.setStyle(Menustyle);
		MenuItem exitGame = new MenuItem("Exit Game");
		exitGame.setStyle(Menustyle);
		menu.getItems().add(displayRules);
		menu.getItems().add(oddsWinning);
		menu.getItems().add(exitGame);
		menu.setStyle(buttonStyle);
		menu.setPrefWidth(150);
		menu.setAlignment(Pos.CENTER);
		menu.setOnMouseEntered(hoverMenuButton);
		menu.setOnMouseExited(hoverMenuButton2);
		//Add events
		oddsWinning.setOnAction(changeMenuOdds);
		exitGame.setOnAction(changeMenuQuit);

		//Holds the menu Button
		HBox menuAndPlay = new HBox();
		menuAndPlay.getChildren().addAll(menu);
		menuAndPlay.setAlignment(Pos.CENTER_LEFT);
		menuAndPlay.setPadding(new Insets(40,0,0,60));

		//HOME AND PLAY BUTTON
		Button home = new Button("Home");
		home.setStyle(buttonStyle);
		home.setOnMouseEntered(hoverButton);
		home.setOnMouseExited(hoverButton2);
		home.setOnAction(backHome);
		Button play = new Button("Play");
		play.setStyle(buttonStyle);
		play.setOnMouseEntered(hoverButton);
		play.setOnMouseExited(hoverButton2);

		//If bet card was already made then return to it otherwise make a new betcard scene
		if(resumeBetCard != null){
			play.setText("Resume Bet Card");
			play.setOnAction(resumeGame);
		}
		else{
			play.setText("Play");
			play.setOnAction(playGame);
		}

		//holds the play and home button
		VBox buttonsAligned = new VBox();
		buttonsAligned.getChildren().addAll(play,home);
		buttonsAligned.setSpacing(150);
		buttonsAligned.setAlignment(Pos.CENTER);
		buttonsAligned.setMinWidth(200);
		buttonsAligned.setPrefWidth(200);
		buttonsAligned.setPadding(new Insets(0,30,0,0));


		//text for center of the screen
		Text header = new Text("RULES");
		header.setStyle(" -fx-font: bold 75pt \"Impact\";-fx-border-width: 5;-fx-fill: #cfc655;");
		Text body = new Text("Keno is a popular gambling game offered in many modern casinos and also offered as a game in many state lotteries.\n" +
				"Players wager by choosing a set amount of numbers (pick 2 numbers, pick 10 numbers, etc.) ranging from 1 to 80. After all players have made their wagers and picked their numbers, twenty numbers are drawn at random, between 1 and 80 with no duplicates. Players win by matching a set amount of their numbers to the numbers that are randomly drawn.\n" +
				"The amount of numbers drawn and the amount of numbers matched that players are allowed to wager on will differ from casino to casino and state lottery to state lottery.");
		body.setStyle(" -fx-font: bold 20pt \"Tahoma\";-fx-border-width: 5;-fx-fill: #e7f3ae;");
		body.setWrappingWidth(800);
		body.setTextAlignment(TextAlignment.CENTER);

		//holds the text
		VBox rulesCenter = new VBox();
		rulesCenter.getChildren().addAll(header,body);
		rulesCenter.setAlignment(Pos.CENTER);
		rulesCenter.setMinWidth(800);
		rulesCenter.setPrefWidth(800);
		rulesCenter.setSpacing(50);
		rulesCenter.setPadding(new Insets(0,0,100,0));

		//For spacing and cutting off labels
		Label leftLabel = new Label();
		leftLabel.setMinWidth(200);
		leftLabel.setPrefWidth(200);
		Label bottomLabel = new Label();
		bottomLabel.setMinWidth(200);
		bottomLabel.setPrefWidth(200);

		//holds all the widgets created
		BorderPane bp = new BorderPane();
		bp.setCenter(rulesCenter);
		bp.setTop(menuAndPlay);
		bp.setLeft(leftLabel);
		bp.setRight(buttonsAligned);
		bp.setBottom(bottomLabel);
		bp.setStyle("-fx-background-color: #13103b;");

		//create root node
		ScrollPane root = new ScrollPane();
		root.setContent(bp);
		root.setFitToHeight(true);
		root.setFitToWidth(true);
		root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		//SHOW RULES SCENE
		return new Scene(root, width, height);
	}

	//Scene that the welcome menu option goes to
	public Scene welcome(){
		//there is no previous bet card
		resumeBetCard = null;

		//Create dropdown menu
		MenuButton menu = new MenuButton("Menu");
		MenuItem displayRules = new MenuItem("Display Rules");
		displayRules.setStyle(Menustyle);
		MenuItem oddsWinning = new MenuItem("Odds of Winning");
		oddsWinning.setStyle(Menustyle);
		MenuItem exitGame = new MenuItem("Exit Game");
		exitGame.setStyle(Menustyle);
		menu.getItems().add(displayRules);
		menu.getItems().add(oddsWinning);
		menu.getItems().add(exitGame);
		menu.setStyle(buttonStyle);
		menu.setPrefWidth(150);
		menu.setAlignment(Pos.CENTER);
		menu.setOnMouseEntered(hoverMenuButton);
		menu.setOnMouseExited(hoverMenuButton2);
		//add events
		displayRules.setOnAction(changeMenuRules);
		oddsWinning.setOnAction(changeMenuOdds);
		exitGame.setOnAction(changeMenuQuit);

		//Add images to the middle of the scene
		//IMAGE BORDER
		ImageView icon = new ImageView("lightFrame.png");
		icon.setPreserveRatio(true);
		icon.setFitHeight(400);
		icon.setTranslateY(-70);
		//IMAGE OF NUMBERS
		ImageView balls = new ImageView("live-keno-2.png");
		balls.setPreserveRatio(true);
		balls.setFitHeight(200);
		balls.setTranslateY(100);
		balls.setTranslateX(300);
		//IMAGE OF COINS
		ImageView stacks = new ImageView("Stacks.png");
		stacks.setPreserveRatio(true);
		stacks.setFitHeight(300);
		stacks.setTranslateY(-70);
		stacks.setTranslateX(-350);
		//IMAGE OF COINS
		ImageView stacks2 = new ImageView("Stacks.png");
		stacks2.setPreserveRatio(true);
		stacks2.setFitHeight(300);
		stacks2.setTranslateY(0);
		stacks2.setTranslateX(-420);
		//IMAGE OF FLASHING LIGHTS
		ImageView lights = new ImageView("Spotlight.png");
		lights.setPreserveRatio(true);
		lights.setPreserveRatio(true);
		lights.setFitHeight(500);
		lights.setRotate(180);
		lights.setTranslateY(-300);
		//IMAGE OF FLASHING LIGHTS
		ImageView lights2 = new ImageView("MultiplrLights.png");
		lights2.setPreserveRatio(true);
		lights2.setFitHeight(500);
		lights2.setRotate(180);
		lights2.setTranslateY(155);
		//IMAGE OF FLASHING LIGHTS
		ImageView lights22 = new ImageView("MultiplrLights.png");
		lights22.setPreserveRatio(true);
		lights22.setFitHeight(500);
		lights22.setRotate(180);
		lights22.setTranslateY(155);
		lights22.setTranslateX(500);
		//IMAGE OF FLASHING LIGHTS
		ImageView lights23 = new ImageView("MultiplrLights.png");
		lights23.setPreserveRatio(true);
		lights23.setFitHeight(500);
		lights23.setRotate(180);
		lights23.setTranslateY(155);
		lights23.setTranslateX(-500);
		//IMAGE OF FLASHING LIGHTS
		ImageView lights3 = new ImageView("Spotlight.png");
		lights3.setPreserveRatio(true);
		lights3.setFitHeight(500);
		lights3.setRotate(210);
		lights3.setTranslateY(-250);
		lights3.setTranslateX(300);
		//IMAGE OF FLASHING LIGHTS
		ImageView lights4 = new ImageView("Spotlight.png");
		lights4.setPreserveRatio(true);
		lights4.setFitHeight(500);
		lights4.setRotate(150);
		lights4.setTranslateY(-250);
		lights4.setTranslateX(-300);

		//WELCOME LABEL
		Label label = new Label("Welcome To\n        KENO");
		label.setStyle("-fx-background-color: #e7f3ae;-fx-font: bold 75pt \"Impact\";");
		label.setTextFill(Paint.valueOf("#cfc655"));
		label.setAlignment(Pos.TOP_CENTER);
		label.setPadding(new Insets(10,20,30,20));

		//PLAY TEXT
		Text pressPlay = new Text();
		pressPlay.setText("Press 'Play' to Start a New Game");
		pressPlay.setStyle("-fx-font: bold 30pt \"Tahoma\";");
		pressPlay.setFill(Paint.valueOf("#ab6bb3"));
		pressPlay.setStroke(Paint.valueOf("#cfc655"));
		pressPlay.setStrokeWidth(2);
		pressPlay.setTextAlignment(TextAlignment.CENTER);
		pressPlay.setTranslateY(100);

		//PLAY BUTTON CREATED
		Button playButton = new Button("PLAY");
		playButton.setShape(arrowShape());
		playButton.setText("PLAY");
		playButton.setPrefWidth(170);
		playButton.setPrefHeight(100);
		playButton.setStyle(buttonStyle);
		playButton.setOnMouseEntered(hoverButton);
		playButton.setOnMouseExited(hoverButton2);
		playButton.setOnAction(playGame);

		//Add all the images and texts to the center of the screen and arrange them
		StackPane stackedWelcome = new StackPane();
		stackedWelcome.getChildren().addAll(lights,lights3,lights4,balls,label,icon,stacks,stacks2,pressPlay,lights2,lights22,lights23,playButton);
		stackedWelcome.setAlignment(label,Pos.TOP_CENTER);
		stackedWelcome.setAlignment(icon,Pos.TOP_CENTER);
		stackedWelcome.setAlignment(pressPlay,Pos.CENTER);
		stackedWelcome.setAlignment(playButton,Pos.BOTTOM_CENTER);
		stackedWelcome.setStyle("-fx-background-color: #13103b;");
		stackedWelcome.setPadding(new Insets(120,0,100,0));

		//Add container for menu Button
		HBox menuContainer = new HBox();
		menuContainer.getChildren().addAll(menu);
		menuContainer.setAlignment(Pos.CENTER_LEFT);
		menuContainer.setPadding(new Insets(40,0,0,60));

		//CREATE BORDERPANE THAT HOLD ALL THE PREVIOUS WIDGETS
		BorderPane bp = new BorderPane();
		bp.setCenter(stackedWelcome);
		bp.setTop(menuContainer);
		bp.setStyle("-fx-background-color: #13103b;");

		//root node of the scene allows for scolling scene
		ScrollPane root = new ScrollPane();
		root.setContent(bp);
		root.setFitToHeight(true);
		root.setFitToWidth(true);
		root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		//SHOW WELCOME SCENE
		return new Scene(root, width, height);
	}

	//Scene that the play button goes to and is the game play
	public Scene gamePlay(Stage primaryStage){
		//create BetCard class object
		BetCard currCard = new BetCard(primaryStage,width,height);
		//save current bet card scene
		resumeBetCard = currCard.makeBetCard(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrowShape());
		//return current bet card scene
		return resumeBetCard;
	}

	//Create a shape of an arrow
	public Path arrowShape(){
		//Draws the arrow
		Path path = new Path();

		MoveTo moveTo = new MoveTo();
		moveTo.setX(0.0f);
		moveTo.setY(0.0f);

		LineTo lineTo = new LineTo();
		lineTo.setX(100.0f);
		lineTo.setY(0.0f);

		LineTo lineTo2 = new LineTo();
		lineTo2.setX(100.0f);
		lineTo2.setY(40.0f);

		LineTo lineTo3 = new LineTo();
		lineTo3.setX(160.0f);
		lineTo3.setY(-25.0f);

		LineTo lineTo4 = new LineTo();
		lineTo4.setX(100.0f);
		lineTo4.setY(-90.0f);

		LineTo lineTo5 = new LineTo();
		lineTo5.setX(100.0f);
		lineTo5.setY(-50.0f);

		LineTo lineTo6 = new LineTo();
		lineTo6.setX(0.0f);
		lineTo6.setY(-50.0f);

		LineTo lineTo7 = new LineTo();
		lineTo7.setX(0.0f);
		lineTo7.setY(0.0f);

		path.getElements().add(moveTo);
		path.getElements().add(lineTo);
		path.getElements().add(lineTo2);
		path.getElements().add(lineTo3);
		path.getElements().add(lineTo4);
		path.getElements().add(lineTo5);
		path.getElements().add(lineTo6);
		path.getElements().add(lineTo7);

		//returns the path in the shape of an arrow
		return path;
	}

}
