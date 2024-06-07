import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.Objects;

/*
	This class initializes the BetCard and implements related JavaFX components.
	It also involves keeping track of BetCard progress while the user is playing.
 */
public class BetCard {
    Stage primaryStage;
    Scene currDrawingScene = null;
    MenuButton menu;
    double width;
    double height;
    Button drawingsButton;
    Button randomSelect;
    int totalWinnings = 0;
    int roundWinnings = 0;
    int numSpots = 0;
    int currentDrawingCount = 0;
    int numDraws = 0;
    int currIndex = 0;
    boolean flag = true;
    boolean initial = true;
    boolean fromNewGame = true;
    GridPane numberSelection;
    GridPane spotsSelection;
    GridPane drawSelection;
    ArrayList<Integer> numbersSelected;
    ArrayList<Integer> randGenerated;
    ArrayList<String> mainTheme = new ArrayList<>(10);
    ArrayList<String> themeTwo = new ArrayList<>(10);
    ArrayList<String> themeOne = new ArrayList<>(10);
    ArrayList<Button> drawingButtons;
    ArrayList<Button> spotsButtons;
    ArrayList<Button> numberButtons;
    ArrayList<Button> selectedNumberButtons = new ArrayList<>(10);
    EventHandler<ActionEvent> chooseDrawings;
    EventHandler<ActionEvent> chooseDrawingsAgain;
    EventHandler<ActionEvent> chooseSpots;
    EventHandler<ActionEvent> chooseNumbers;
    EventHandler<ActionEvent> randomChoose;
    EventHandler<ActionEvent> newTheme;
    PauseTransition pause = new PauseTransition(Duration.seconds(3));

    //Class constructor
    BetCard(Stage mainStage, double mainWidth, double mainHeight){
        //make data fields equal to the parameters passed
        primaryStage = mainStage;
        width = mainWidth;
        height = mainHeight;
    }

    //creates two arrays of strings that are colors and styles
    public void createThemeArrays(){
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

        String MenuStyleTwo = "-fx-padding: 15 40 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #938911 0%, #887f10 100%),\n" +
                "        #8c841f,\n" +
                "        #cec337,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #c9be30, #b7ad27);\n" +
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

        String buttonStyleTwo = "-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #938911 0%, #887f10 100%),\n" +
                "        #8c841f,\n" +
                "        #cec337,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #c9be30, #b7ad27);\n" +
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

        String buttonHoveredTwo = "-fx-padding: 10 15 13 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #938911 0%, #887f10 100%),\n" +
                "        #8c841f,\n" +
                "        #cec337,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #e1d646, #b7ad27);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font: bold 12pt \"Tahoma\";-fx-border-width: 5";

        //clear arrays everytime this function is called
        themeOne.clear();
        themeTwo.clear();
        //populate first array with main colors and styles
        themeOne.add("#bd32ce"); //light pink
        themeOne.add("#e7f3ae"); //light yellow
        themeOne.add("#cfc655"); //dark yellow
        themeOne.add("#13103b"); //dark purple
        themeOne.add(Menustyle);
        themeOne.add(buttonStyle);
        themeOne.add(buttonHovered);
        //populate second array with inverted colors and styles (light mode)
        themeTwo.add("#e7f3ae"); //light yellow
        themeTwo.add("#bd32ce"); //light pink
        themeTwo.add("#13103b"); //dark purple
        themeTwo.add("#cfc655"); //dark yellow
        themeTwo.add(MenuStyleTwo);
        themeTwo.add(buttonStyleTwo);
        themeTwo.add(buttonHoveredTwo);
    }

    //assigns the main theme array that holds the current colors being used to be one of the two theme arrays created
    public void changeThemeArray(){
        //creates the two themes
        createThemeArrays();
        //alternates between which theme array is assigned the the main theme each time this function is called
        if(flag){
            flag = false;
            mainTheme.clear();
            mainTheme.addAll(themeOne);
        }
        else{
            flag = true;
            mainTheme.clear();
            mainTheme.addAll(themeTwo);
        }
    }

    //creates event-handlers used throughout the bet card and drawings scenes
    public void makeEvents(){
        //change the look of the drawing numbers if they're selected or unselected
        chooseDrawings = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Button b = (Button)event.getSource();
                //if button is selected
                if(spotsSelection.isDisabled()) {
                    //change color
                    b.setStyle("-fx-background-color: " + mainTheme.get(0));
                    //store the number chosen
                    numDraws = Integer.parseInt(b.getText());
                    //disable the rest of the draw buttons
                    for (Button drawingButton : drawingButtons) {
                        if (b != drawingButton) {
                            drawingButton.setMouseTransparent(true);
                        }
                    }
                    //enable the next section
                    spotsSelection.setDisable(false);
                }
                //button is deselected
                else{
                    //disable random option
                    randomSelect.setDisable(true);
                    //reset number of draws
                    numDraws = 0;
                    //change color of button back to original
                    b.setStyle("-fx-background-color: #d8d5d9;");
                    //enable the rest of buttons in that section
                    for (Button drawingButton : drawingButtons) {
                        if (b != drawingButton) {
                            drawingButton.setMouseTransparent(false);
                        }
                    }
                    //reset all the buttons of the spots section
                    for (Button spotsButton : spotsButtons) {
                        spotsButton.setStyle(" -fx-background-color: #d8d5d9;");
                        spotsButton.setMouseTransparent(false);
                    }
                    //disable the spots section
                    spotsSelection.setDisable(true);
                    randomSelect.setMouseTransparent(true);
                    drawingsButton.setVisible(false);
                    selectedNumberButtons.clear();

                    //reset all the buttons selected in the numbers section
                    currIndex = 0;
                    for (Button numberButton : numberButtons) {
                        numberButton.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-fill: #e7f3ae;-fx-background-color: #d8d5d9;");
                    }
                    //disable number section
                    numberSelection.setDisable(true);
                }
            }
        };

        //change the look of the spots numbers if they're selected or unselected
        chooseSpots = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Button b = (Button)event.getSource();
                //if the button is selected
                if(numberSelection.isDisabled()) {
                    //enable random option
                    randomSelect.setDisable(false);
                    //change color of button
                    b.setStyle("-fx-background-color: " + mainTheme.get(0));
                    //disable the rest of the buttons from that section
                    for (Button spotButton : spotsButtons) {
                        if (b != spotButton) {
                            spotButton.setMouseTransparent(true);
                        }
                    }
                    //enable the next section
                    numberSelection.setDisable(false);
                    //store the number of spots
                    numSpots = Integer.parseInt(b.getText());
                    randomSelect.setMouseTransparent(false);
                    //initial a int arrayList of size number of spots
                    numbersSelected = new ArrayList<>(numSpots);
                }
                //button is deselected
                else{
                    //enable random option
                    randomSelect.setDisable(true);
                    numSpots = 0;
                    //change color back to original
                    b.setStyle("-fx-background-color: #d8d5d9;");
                    //enable all the buttons in that section
                    for (Button spotButton : spotsButtons) {
                        if (b != spotButton) {
                            spotButton.setMouseTransparent(false);
                        }
                    }
                    //disable the numbers section
                    numberSelection.setDisable(true);
                    randomSelect.setMouseTransparent(true);
                    drawingsButton.setVisible(false);
                    selectedNumberButtons.clear();
                    //reset all the numbers of the numbers section
                    currIndex = 0;
                    for (Button numberButton : numberButtons) {
                        numberButton.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-fill: #e7f3ae;-fx-background-color: #d8d5d9;");
                    }
                }

            }
        };

        //change the look of the player chosen numbers if they're selected or unselected
        chooseNumbers = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Button b = (Button)event.getSource();
                //store the number of the button clicked
                int buttonNum = Integer.parseInt(b.getText());
                //if we still need to select more numbers and the current number selected is a new number then add to array of numbers
                if(currIndex < numSpots && !numbersSelected.contains(buttonNum)){
                    //change color of the button holding that number
                    b.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-fill: #e7f3ae;-fx-background-color: " + mainTheme.get(0));
                    selectedNumberButtons.add(b);
                    //increase amount of buttons selected
                    currIndex++;
                    //store the number value into an array list
                    numbersSelected.add(Integer.valueOf(b.getText()));
                }
                //if the current number picked has already been picked then deselect it
                else if(numbersSelected.contains(buttonNum)){
                    //reset style of the button
                    b.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-fill: #e7f3ae;-fx-background-color: #d8d5d9;");
                    //decrease amount of button selected
                    currIndex--;
                    //remove the number from the array list
                    numbersSelected.remove(Integer.valueOf(b.getText()));
                    selectedNumberButtons.remove(b);
                }
                //when we selected all the number required then the button for the next scene will appear
                drawingsButton.setVisible(currIndex == numSpots);
            }
        };

        //randomly select from the player chosen numbers section
        randomChoose = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //clear the array holding the number chosen
                numbersSelected.clear();
                selectedNumberButtons.clear();
                //reset amount of numbers chosen to 0
                currIndex = 0;
                //reset all the buttons, unselected and already selected
                for (Button numberButton : numberButtons) {
                    numberButton.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-fill: "+mainTheme.get(1)+";-fx-background-color: #d8d5d9");
                }
                //find the first random number and add to array list
                int randNum = (int)(Math.random() * 80 + 1);
                numbersSelected.add(randNum);
                //keep adding number as many times as the number of spots chosen
                for(int i = 0; i < numSpots - 1; i++){
                    //keep looking for new random number if current random has already been used
                    while(numbersSelected.contains(randNum)){
                        randNum = (int)(Math.random() * 80 + 1);
                    }
                    //add new random number to array list
                    numbersSelected.add(randNum);
                }

                //change the color of all the buttons that contain one of the random numbers generated
                for (Button numberButton : numberButtons) {
                    if (numbersSelected.contains(Integer.valueOf(numberButton.getText()))) {
                        numberButton.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-fill: "+mainTheme.get(1)+";-fx-background-color: " + mainTheme.get(0));
                        selectedNumberButtons.add(numberButton);
                    }
                }
                //amount of numbers selected is now the total amount possible
                currIndex = numSpots;
                //reveal button for next scene
                drawingsButton.setVisible(true);

            }
        };
    }

    //rules scene once the game play has begun
    public Scene rulesChanged(EventHandler<ActionEvent> changeMenuQuit, EventHandler<MouseEvent> hoverMenuButton, EventHandler<MouseEvent> hoverMenuButton2, EventHandler<MouseEvent> hoverButton, EventHandler<MouseEvent> hoverButton2,EventHandler<ActionEvent> backHome){
        //create drop down menu
        MenuButton menu = new MenuButton("Menu");
        MenuItem displayRules = new MenuItem("Display Rules");
        displayRules.setStyle(mainTheme.get(4));
        MenuItem oddsWinning = new MenuItem("Odds of Winning");
        oddsWinning.setStyle(mainTheme.get(4));
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setStyle(mainTheme.get(4));
        menu.getItems().add(displayRules);
        menu.getItems().add(oddsWinning);
        menu.getItems().add(exitGame);
        menu.setStyle(mainTheme.get(5));
        menu.setPrefWidth(150);
        menu.setAlignment(Pos.CENTER);
        //add events
        menu.setOnMouseEntered(hoverMenuButton);
        menu.setOnMouseExited(hoverMenuButton2);
        oddsWinning.setOnAction(e->primaryStage.setScene(oddsChanged(changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome)));
        exitGame.setOnAction(changeMenuQuit);

        //container for menu
        HBox menuContainer = new HBox();
        menuContainer.getChildren().addAll(menu);
        menuContainer.setAlignment(Pos.CENTER_LEFT);
        menuContainer.setPadding(new Insets(40,0,0,60));

        //HOME AND RESUME BUTTON
        Button home = new Button("Home");
        home.setStyle(mainTheme.get(5));
        home.setOnMouseEntered(hoverButton);
        home.setOnMouseExited(hoverButton2);
        //add event to return to welcome screen
        home.setOnAction(backHome);

        //resume button to return the previous drawing scene
        Button resumeDrawing = new Button("Resume Drawing");
        resumeDrawing.setStyle(mainTheme.get(5));
        resumeDrawing.setOnMouseEntered(hoverButton);
        resumeDrawing.setOnMouseExited(hoverButton2);
        //return to saved drawing scene
        resumeDrawing.setOnAction(e->primaryStage.setScene(currDrawingScene));

        //holds the resume and home button
        VBox buttonsAligned = new VBox();
        buttonsAligned.getChildren().addAll(resumeDrawing,home);
        buttonsAligned.setSpacing(150);
        buttonsAligned.setAlignment(Pos.CENTER);
        buttonsAligned.setMinWidth(200);
        buttonsAligned.setPrefWidth(200);
        buttonsAligned.setPadding(new Insets(0,30,0,0));

        //Text for center of the scene
        Text header = new Text("RULES");
        header.setStyle(" -fx-font: bold 75pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        Text body = new Text("Keno is a popular gambling game offered in many modern casinos and also offered as a game in many state lotteries.\n" +
                "Players wager by choosing a set amount of numbers (pick 2 numbers, pick 10 numbers, etc.) ranging from 1 to 80. After all players have made their wagers and picked their numbers, twenty numbers are drawn at random, between 1 and 80 with no duplicates. Players win by matching a set amount of their numbers to the numbers that are randomly drawn.\n" +
                "The amount of numbers drawn and the amount of numbers matched that players are allowed to wager on will differ from casino to casino and state lottery to state lottery.");
        body.setStyle(" -fx-font: bold 20pt \"Tahoma\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(1));
        body.setWrappingWidth(800);
        body.setTextAlignment(TextAlignment.CENTER);

        //Holds all the text
        VBox rulesCenter = new VBox();
        rulesCenter.getChildren().addAll(header,body);
        rulesCenter.setAlignment(Pos.CENTER);
        rulesCenter.setMinWidth(800);
        rulesCenter.setPrefWidth(800);
        rulesCenter.setSpacing(50);
        rulesCenter.setPadding(new Insets(0,0,100,0));

        //For spacing and label cut off
        Label leftLabel = new Label();
        leftLabel.setMinWidth(200);
        leftLabel.setPrefWidth(200);
        Label bottomLabel = new Label();
        bottomLabel.setMinWidth(200);
        bottomLabel.setPrefWidth(200);

        //bp holds all the widgets created
        BorderPane bp = new BorderPane();
        bp.setCenter(rulesCenter);
        bp.setTop(menuContainer);
        bp.setLeft(leftLabel);
        bp.setRight(buttonsAligned);
        bp.setBottom(bottomLabel);
        bp.setStyle("-fx-background-color: " + mainTheme.get(3));//6e68bb
        bp.setMinWidth(1000);

        //root is the root node of the scene and holds bp
        ScrollPane root = new ScrollPane();
        root.setContent(bp);
        root.setFitToHeight(true);
        root.setFitToWidth(true);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        //SHOW RULES SCENE
        return new Scene(root, width, height);
    }

    //different odds scene once the game play has begun
    public Scene oddsChanged(EventHandler<ActionEvent> changeMenuQuit,EventHandler<MouseEvent> hoverMenuButton, EventHandler<MouseEvent> hoverMenuButton2, EventHandler<MouseEvent> hoverButton, EventHandler<MouseEvent> hoverButton2,EventHandler<ActionEvent> backHome){
        //Create drop down menu
        MenuButton menu = new MenuButton("Menu");
        MenuItem displayRules = new MenuItem("Display Rules");
        displayRules.setStyle(mainTheme.get(4));
        MenuItem oddsWinning = new MenuItem("Odds of Winning");
        oddsWinning.setStyle(mainTheme.get(4));
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setStyle(mainTheme.get(4));
        menu.getItems().add(displayRules);
        menu.getItems().add(oddsWinning);
        menu.getItems().add(exitGame);
        menu.setStyle(mainTheme.get(5));
        menu.setPrefWidth(150);
        menu.setAlignment(Pos.CENTER);
        //add events
        menu.setOnMouseEntered(hoverMenuButton);
        menu.setOnMouseExited(hoverMenuButton2);
        displayRules.setOnAction(e->primaryStage.setScene(rulesChanged(changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome)));
        exitGame.setOnAction(changeMenuQuit);

        //container that holds the menuButton
        HBox menuRegion = new HBox();
        menuRegion.getChildren().addAll(menu);
        menuRegion.setAlignment(Pos.CENTER_LEFT);
        menuRegion.setPadding(new Insets(40,0,0,60));

        //Home and Resume buttons created
        Button home = new Button("Home");
        home.setStyle(mainTheme.get(5));
        home.setOnAction(backHome);
        home.setOnMouseEntered(hoverButton);
        home.setOnMouseExited(hoverButton2);
        //New button that returns the player to the drawing they left off at
        Button resumeDrawing = new Button("Resume Drawing");
        resumeDrawing.setStyle(mainTheme.get(5));
        resumeDrawing.setOnMouseEntered(hoverButton);
        resumeDrawing.setOnMouseExited(hoverButton2);
        resumeDrawing.setOnAction(e->primaryStage.setScene(currDrawingScene));

        //Holds both the resume and home buttons
        VBox buttonsAligned = new VBox();
        buttonsAligned.getChildren().addAll(resumeDrawing,home);
        buttonsAligned.setSpacing(150);
        buttonsAligned.setAlignment(Pos.CENTER);
        buttonsAligned.setMinWidth(200);
        buttonsAligned.setPrefWidth(200);
        buttonsAligned.setPadding(new Insets(0,30,0,0));

        //Text for middle of the screen
        Text header = new Text("ODDS OF WINNING");
        header.setStyle(" -fx-font: bold 75pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
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
        body.setStyle(" -fx-font: bold 20pt \"Tahoma\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(1));
        body.setWrappingWidth(1000);

        //Holds all the text
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
        bp.setStyle("-fx-background-color: " + mainTheme.get(3));//6e68bb
        bp.setMinWidth(1000);

        //the root holds bp and allows for scrolling of the game
        ScrollPane root = new ScrollPane();
        root.setContent(bp);
        root.setFitToHeight(true);
        root.setFitToWidth(true);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        //SHOW NEW ODDS SCENE
        return new Scene(root, width, height);
    }

    //When New Look menu option is clicked this bet card scene that saves the current progress is made
    public Scene saveBetCard(EventHandler<ActionEvent> changeMenuRules, EventHandler<ActionEvent> changeMenuOdds, EventHandler<ActionEvent> changeMenuQuit, EventHandler<MouseEvent> hoverMenuButton, EventHandler<MouseEvent> hoverMenuButton2, EventHandler<MouseEvent> hoverButton, EventHandler<MouseEvent> hoverButton2, EventHandler<ActionEvent> backHome, Path arrow) {
        //Create new dropdown menu with new meu option New Look
        menu = new MenuButton("Menu");
        MenuItem displayRules = new MenuItem("Display Rules");
        displayRules.setStyle(mainTheme.get(4));
        MenuItem oddsWinning = new MenuItem("Odds of Winning");
        oddsWinning.setStyle(mainTheme.get(4));
        MenuItem newLook = new MenuItem("Change Theme");
        newLook.setStyle(mainTheme.get(4));
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setStyle(mainTheme.get(4));
        menu.getItems().add(displayRules);
        menu.getItems().add(oddsWinning);
        menu.getItems().add(newLook);
        menu.getItems().add(exitGame);
        menu.setStyle(mainTheme.get(5));
        menu.setPrefWidth(150);
        menu.setAlignment(Pos.CENTER);
        //add events
        menu.setOnMouseEntered(hoverMenuButton);
        menu.setOnMouseExited(hoverMenuButton2);
        displayRules.setOnAction(changeMenuRules);
        oddsWinning.setOnAction(changeMenuOdds);
        exitGame.setOnAction(changeMenuQuit);
        //create scene again but with updated color theme
        newLook.setOnAction(e->{
            //change main theme array
            changeThemeArray();
            primaryStage.setScene(saveBetCard(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrow));});

        //Label that shows total winnings
        Label winningsBox = new Label();
        winningsBox.setStyle(mainTheme.get(5));
        winningsBox.setText("Winnings: $" + totalWinnings);
        winningsBox.setAlignment(Pos.CENTER);
        winningsBox.setMinWidth(200);

        //AMOUNT REGION
        //Background border
        StackPane amountBox = new StackPane();
        amountBox.setStyle(mainTheme.get(5));
        amountBox.setMinWidth(450);
        amountBox.setMaxWidth(450);
        amountBox.setMinHeight(105);
        amountBox.setMaxHeight(105);
        //Text of the amount region
        Text amountHeader = new Text("AMOUNT PLAYING");
        Text amountBody = new Text("How Much You're Playing");
        Text amountNum = new Text("$1");
        amountHeader.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        amountBody.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(1));
        amountNum.setStyle(" -fx-font: bold 25pt \"Tahoma\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(1));
        //Holds all of that text
        VBox amountText = new VBox();
        amountText.getChildren().addAll(amountHeader,amountBody,amountNum);
        amountText.setAlignment(Pos.CENTER);
        amountBox.getChildren().addAll(amountText);
        //Groups the amount region with everything at the top of the scene
        StackPane topRegion = new StackPane();
        topRegion.getChildren().addAll(amountBox,menu,winningsBox);
        topRegion.setAlignment(menu,Pos.CENTER_LEFT);
        topRegion.setAlignment(winningsBox,Pos.CENTER_RIGHT);
        topRegion.setPadding(new Insets(0,120,0,120));

        //DRAWS REGION
        //Background border created
        StackPane drawsBox = new StackPane();
        drawsBox.setStyle(mainTheme.get(5));
        drawsBox.setPrefWidth(450);
        drawsBox.setMinWidth(450);
        drawsBox.setMaxWidth(450);
        //Text of the draws region
        Text drawsHeader = new Text("NUMBER OF DRAWS");
        Text drawsBody = new Text("How Many Consecutive Draws Do You Want To Play");
        drawsHeader.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        drawsBody.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(1));

        //Change color of already selected drawing buttons to match theme
        if(!drawSelection.isDisabled()) {
            for(int i = 0; i < drawSelection.getChildren().size(); i++){
                for (Button drawings : drawingButtons) {
                    if(drawSelection.getChildren().get(i) == drawings && Objects.equals(drawings.getText(), String.valueOf(numDraws))){
                        drawSelection.getChildren().get(i).setStyle("-fx-background-color: " + mainTheme.get(0));
                    }
                }
            }
        }

        //Holds all the text and the buttons
        VBox drawsText = new VBox();
        drawsText.getChildren().addAll(drawsHeader,drawsBody,drawSelection);
        drawsText.setAlignment(Pos.CENTER);

        //Contains all the upper middle region
        drawsBox.getChildren().addAll(drawsText);

        //SPOTS REGION
        //Background border created
        StackPane spotsBox = new StackPane();
        spotsBox.setStyle(mainTheme.get(5));
        spotsBox.setPrefWidth(450);
        spotsBox.setMinWidth(450);
        spotsBox.setMaxWidth(450);

        //Text for the spots region
        Text spotsHeader = new Text("NUMBER OF SPOTS");
        Text spotsBody = new Text("How Many Numbers Do You Want To Play");
        spotsHeader.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        spotsBody.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-border-width: 5;-fx-fill: "+ mainTheme.get(1));

        //Change color of already selected spot buttons to match theme
        if(!spotsSelection.isDisabled()) {
            for(int i = 0; i < spotsSelection.getChildren().size(); i++){
                for (Button spot : spotsButtons) {
                    if(spotsSelection.getChildren().get(i) == spot && Objects.equals(spot.getText(), String.valueOf(numSpots))){
                        spotsSelection.getChildren().get(i).setStyle("-fx-background-color: " + mainTheme.get(0));
                    }
                }
            }
        }

        //Holds all the texts and buttons of the spots region
        VBox spotsText = new VBox();
        spotsText.getChildren().addAll(spotsHeader,spotsBody,spotsSelection);
        spotsText.setAlignment(Pos.CENTER);
        spotsBox.getChildren().addAll(spotsText);

        //BUTTON FOR MOVING ON TO DRAWINGS
        drawingsButton = new Button("START");
        drawingsButton.setStyle(mainTheme.get(5));
        drawingsButton.setShape(arrow);
        drawingsButton.setPrefSize(150,80);
        drawingsButton.setVisible(numSpots == currIndex && numSpots != 0);
        drawingsButton.setOnMouseEntered(hoverButton);
        drawingsButton.setOnMouseExited(hoverButton2);
        drawingsButton.setOnAction(e->{fromNewGame = true; primaryStage.setScene(makeCurrentDrawing(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrow));});

        //CLARIFYING COMMENT BOX
        StackPane noteBox = new StackPane();
        noteBox.setStyle(mainTheme.get(5));
        noteBox.setMaxSize(25,25);

        //Text for clarification box
        Text noteText = new Text("NOTE\nClick on a number to select it\nClick on the same number again to deselect it");
        noteText.setStyle(" -fx-font: bold 10pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        noteText.setTextAlignment(TextAlignment.CENTER);
        noteBox.getChildren().addAll(noteText);
        noteBox.setAlignment(Pos.CENTER);

        //Lower Middle region which holds everything the lower middle of the screen including spots region
        StackPane middleRegion = new StackPane(noteBox,spotsBox,drawingsButton);
        middleRegion.setAlignment(drawingsButton,Pos.CENTER_RIGHT);
        middleRegion.setAlignment(noteBox,Pos.TOP_LEFT);
        middleRegion.setPadding(new Insets(0,220,0,220));

        //PLAYER NUMBER REGION
        //Background border created
        StackPane numberBox = new StackPane();
        numberBox.setStyle(mainTheme.get(5));
        numberBox.setPrefWidth(450);
        numberBox.setMinWidth(500);
        numberBox.setMaxWidth(500);

        //Text for player number region
        Text numberHeader = new Text("PICK YOUR NUMBERS");
        Text numberBody = new Text("Or Randomly Select By Choosing Quick Pick");
        numberHeader.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        numberBody.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(1));

        //Change color of already selected number buttons to match theme
        if(!numberSelection.isDisabled()) {
            for(int i = 0; i < numberSelection.getChildren().size(); i++){
                for (Button num : selectedNumberButtons) {
                    if(numberSelection.getChildren().get(i) == num){
                        numberSelection.getChildren().get(i).setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-fill: "+mainTheme.get(1)+";-fx-background-color: " + mainTheme.get(0));
                    }
                }
            }
        }

        //VBox for text and grid pane
        VBox numbersText = new VBox();
        numbersText.getChildren().addAll(numberHeader,numberBody,numberSelection);
        numbersText.setAlignment(Pos.CENTER);
        numberBox.getChildren().addAll(numbersText);

        //BUTTON FOR RANDOM SELECTING
        randomSelect = new Button("QUICK PICK");
        randomSelect.setStyle(mainTheme.get(5));
        randomSelect.setOnAction(randomChoose);
        randomSelect.setOnMouseEntered(hoverButton);
        randomSelect.setOnMouseExited(hoverButton2);

        //Holds all the widgets of the bottom region of the scene
        StackPane bottomRegion = new StackPane(numberBox,randomSelect);
        bottomRegion.setAlignment(randomSelect,Pos.CENTER_RIGHT);
        bottomRegion.setPadding(new Insets(0,240,0,240));

        //Bottom label for spacing
        Label bottom = new Label();
        bottom.setMinHeight(100);
        bottom.setStyle("-fx-background-color: " + mainTheme.get(3));

        //Top label for spacing
        Label top = new Label();
        top.setMinHeight(10);
        top.setStyle("-fx-background-color: " + mainTheme.get(3));

        //CREATE VBOX TO HOLD ALL THE REGIONS
        VBox centerBox = new VBox(topRegion,drawsBox,middleRegion,bottomRegion);
        centerBox.getChildren().addAll();
        centerBox.setStyle("-fx-background-color: " + mainTheme.get(3));
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(20);
        centerBox.setPadding(new Insets(5,0,60,0));

        //CREATE BP WHICH HOLDS THE MAIN VBOX IN ITS CENTER
        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: "+mainTheme.get(3));
        bp.setCenter(centerBox);

        //Create root node and add bp also allows for scrolling of the scene
        ScrollPane root = new ScrollPane();
        root.setContent(bp);
        root.setFitToWidth(true);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        //SHOW BET CARD SCENE
        return new Scene(root, width, height);
    }

    //creates the bet card scene
    public Scene makeBetCard(EventHandler<ActionEvent> changeMenuRules, EventHandler<ActionEvent> changeMenuOdds, EventHandler<ActionEvent> changeMenuQuit, EventHandler<MouseEvent> hoverMenuButton, EventHandler<MouseEvent> hoverMenuButton2, EventHandler<MouseEvent> hoverButton, EventHandler<MouseEvent> hoverButton2, EventHandler<ActionEvent> backHome, Path arrow) {
        //No previous drawing scene at this point
        currDrawingScene = null;

        //Number of drawing scenes is reset
        currentDrawingCount = 0;

        //creates the two color theme arrays
        createThemeArrays();

        //creates event handlers used through the scenes
        makeEvents();

        //if this bet card scene is a new game then set main theme otherwise ignore
        if(initial){
            changeThemeArray();
            initial = false;
        }


        //Create new dropdown menu with new meu option New Look
        menu = new MenuButton("Menu");
        MenuItem displayRules = new MenuItem("Display Rules");
        displayRules.setStyle(mainTheme.get(4));
        MenuItem oddsWinning = new MenuItem("Odds of Winning");
        oddsWinning.setStyle(mainTheme.get(4));
        MenuItem newLook = new MenuItem("Change Theme");
        newLook.setStyle(mainTheme.get(4));
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setStyle(mainTheme.get(4));
        menu.getItems().add(displayRules);
        menu.getItems().add(oddsWinning);
        menu.getItems().add(newLook);
        menu.getItems().add(exitGame);
        menu.setStyle(mainTheme.get(5));
        menu.setPrefWidth(150);
        menu.setAlignment(Pos.CENTER);
        //add events
        menu.setOnMouseEntered(hoverMenuButton);
        menu.setOnMouseExited(hoverMenuButton2);
        displayRules.setOnAction(changeMenuRules);
        oddsWinning.setOnAction(changeMenuOdds);
        exitGame.setOnAction(changeMenuQuit);
        //create scene again but with updated color theme
        newLook.setOnAction(e->{
            //change main theme array
            changeThemeArray();
            primaryStage.setScene(saveBetCard(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrow));});

        //Label that shows total winnings
        Label winningsBox = new Label();
        winningsBox.setStyle(mainTheme.get(5));
        winningsBox.setText("Winnings: $" + totalWinnings);
        winningsBox.setAlignment(Pos.CENTER);
        winningsBox.setMinWidth(200);

        //AMOUNT REGION
        //Background border
        StackPane amountBox = new StackPane();
        amountBox.setStyle(mainTheme.get(5));
        amountBox.setMinWidth(450);
        amountBox.setMaxWidth(450);
        amountBox.setMinHeight(105);
        amountBox.setMaxHeight(105);
        //Text of the amount region
        Text amountHeader = new Text("AMOUNT PLAYING");
        Text amountBody = new Text("How Much You're Playing");
        Text amountNum = new Text("$1");
        amountHeader.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        amountBody.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(1));
        amountNum.setStyle(" -fx-font: bold 25pt \"Tahoma\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(1));
        //Holds all of that text
        VBox amountText = new VBox();
        amountText.getChildren().addAll(amountHeader,amountBody,amountNum);
        amountText.setAlignment(Pos.CENTER);
        amountBox.getChildren().addAll(amountText);
        //Groups the amount region with everything at the top of the scene
        StackPane topRegion = new StackPane();
        topRegion.getChildren().addAll(amountBox,menu,winningsBox);
        topRegion.setAlignment(menu,Pos.CENTER_LEFT);
        topRegion.setAlignment(winningsBox,Pos.CENTER_RIGHT);
        topRegion.setPadding(new Insets(0,120,0,120));

        //DRAWS REGION
        //Background border created
        StackPane drawsBox = new StackPane();
        drawsBox.setStyle(mainTheme.get(5));
        drawsBox.setPrefWidth(450);
        drawsBox.setMinWidth(450);
        drawsBox.setMaxWidth(450);
        //Text of the draws region
        Text drawsHeader = new Text("NUMBER OF DRAWS");
        Text drawsBody = new Text("How Many Consecutive Draws Do You Want To Play");
        drawsHeader.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        drawsBody.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(1));

        //Draw option buttons created and event added
        Button oneDraw = new Button("1");
        Button twoDraw = new Button("2");
        Button threeDraw = new Button("3");
        Button fourDraw = new Button("4");
        drawingButtons = new ArrayList<>();
        drawingButtons.add(oneDraw);
        drawingButtons.add(twoDraw);
        drawingButtons.add(threeDraw);
        drawingButtons.add(fourDraw);
        for (Button drawingButton : drawingButtons) {
            drawingButton.setOnAction(chooseDrawings);
        }

        //Groups all the buttons into a grid pane
        drawSelection = new GridPane();
        drawSelection.addRow(0,oneDraw,twoDraw,threeDraw,fourDraw);
        drawSelection.setAlignment(Pos.CENTER);
        drawSelection.setHgap(20);
        drawSelection.setPadding(new Insets(20,0,10,0));

        //Holds all the text and the buttons
        VBox drawsText = new VBox();
        drawsText.getChildren().addAll(drawsHeader,drawsBody,drawSelection);
        drawsText.setAlignment(Pos.CENTER);

        //Contains all the upper middle region
        drawsBox.getChildren().addAll(drawsText);

        //SPOTS REGION
        //Background border created
        StackPane spotsBox = new StackPane();
        spotsBox.setStyle(mainTheme.get(5));
        spotsBox.setPrefWidth(450);
        spotsBox.setMinWidth(450);
        spotsBox.setMaxWidth(450);

        //Text for the spots region
        Text spotsHeader = new Text("NUMBER OF SPOTS");
        Text spotsBody = new Text("How Many Numbers Do You Want To Play");
        spotsHeader.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        spotsBody.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-border-width: 5;-fx-fill: "+ mainTheme.get(1));

        //SPOTS NUMBER BUTTON SELECTION
        Button oneSpot = new Button("1");
        Button fourSpot = new Button("4");
        Button eightSpot = new Button("8");
        Button tenthSpot = new Button("10");
        spotsButtons = new ArrayList<>();
        spotsButtons.add(oneSpot);
        spotsButtons.add(fourSpot);
        spotsButtons.add(eightSpot);
        spotsButtons.add(tenthSpot);
        //add event to each button
        for (Button spotsButton : spotsButtons) {
            spotsButton.setOnAction(chooseSpots);
        }

        //group all the spots buttons into a grid pane
        spotsSelection = new GridPane();
        spotsSelection.addRow(0,oneSpot,fourSpot,eightSpot,tenthSpot);
        spotsSelection.setAlignment(Pos.CENTER);
        spotsSelection.setHgap(40);
        spotsSelection.setPadding(new Insets(20,0,10,0));
        spotsSelection.setDisable(true);

        //Holds all the texts and buttons of the spots region
        VBox spotsText = new VBox();
        spotsText.getChildren().addAll(spotsHeader,spotsBody,spotsSelection);
        spotsText.setAlignment(Pos.CENTER);
        spotsBox.getChildren().addAll(spotsText);

        //BUTTON FOR MOVING ON TO DRAWINGS
        drawingsButton = new Button("START");
        drawingsButton.setStyle(mainTheme.get(5));
        drawingsButton.setShape(arrow);
        drawingsButton.setPrefSize(150,80);
        drawingsButton.setVisible(false);
        drawingsButton.setOnMouseEntered(hoverButton);
        drawingsButton.setOnMouseExited(hoverButton2);
        drawingsButton.setOnAction(e->{fromNewGame = true; primaryStage.setScene(makeCurrentDrawing(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrow));});

        //CLARIFYING COMMENT BOX
        StackPane noteBox = new StackPane();
        noteBox.setStyle(mainTheme.get(5));
        noteBox.setMaxSize(25,25);

        //Text for clarification box
        Text noteText = new Text("NOTE\nClick on a number to select it\nClick on the same number again to deselect it");
        noteText.setStyle(" -fx-font: bold 10pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        noteText.setTextAlignment(TextAlignment.CENTER);
        noteBox.getChildren().addAll(noteText);
        noteBox.setAlignment(Pos.CENTER);

        //Lower Middle region which holds everything the lower middle of the screen including spots region
        StackPane middleRegion = new StackPane(noteBox,spotsBox,drawingsButton);
        middleRegion.setAlignment(drawingsButton,Pos.CENTER_RIGHT);
        middleRegion.setAlignment(noteBox,Pos.TOP_LEFT);
        middleRegion.setPadding(new Insets(0,220,0,220));

        //PLAYER NUMBER REGION
        //Background border created
        StackPane numberBox = new StackPane();
        numberBox.setStyle(mainTheme.get(5));
        numberBox.setPrefWidth(450);
        numberBox.setMinWidth(500);
        numberBox.setMaxWidth(500);

        //Text for player number region
        Text numberHeader = new Text("PICK YOUR NUMBERS");
        Text numberBody = new Text("Or Randomly Select By Choosing Quick Pick");
        numberHeader.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        numberBody.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(1));

        //Creates 1-80 grid of buttons
        numberSelection = new GridPane();
        numberSelection.setAlignment(Pos.CENTER);
        numberButtons = new ArrayList<>();

        //loop through rows and columns
        int currNum = 1;
        for(int i = 0; i <10; i++){
            for(int j = 0; j <8; j++){
                //create button and set style
                Button currButton = new Button(String.valueOf(currNum));
                currButton.setMinSize(33,22);
                currButton.setStyle(" -fx-font: bold 8pt \"Tahoma\";-fx-fill: " + mainTheme.get(1));
                //add select/deselect event
                currButton.setOnAction(chooseNumbers);
                //add to grid pane
                numberSelection.add(currButton,j,i);
                //store button in an array list
                numberButtons.add(currButton);
                //increase current number created
                currNum++;
            }
        }
        //grid pane starts off disabled
        numberSelection.setDisable(true);
        numberSelection.setHgap(20);
        numberSelection.setVgap(5);
        numberSelection.setPadding(new Insets(10,0,5,0));

        //VBox for text and grid pane
        VBox numbersText = new VBox();
        numbersText.getChildren().addAll(numberHeader,numberBody,numberSelection);
        numbersText.setAlignment(Pos.CENTER);
        numberBox.getChildren().addAll(numbersText);

        //BUTTON FOR RANDOM SELECTING
        randomSelect = new Button("QUICK PICK");
        randomSelect.setStyle(mainTheme.get(5));
        randomSelect.setOnAction(randomChoose);
        randomSelect.setOnMouseEntered(hoverButton);
        randomSelect.setOnMouseExited(hoverButton2);
        randomSelect.setDisable(true);


        //Holds all the widgets of the bottom region of the scene
        StackPane bottomRegion = new StackPane(numberBox,randomSelect);
        bottomRegion.setAlignment(randomSelect,Pos.CENTER_RIGHT);
        bottomRegion.setPadding(new Insets(0,240,0,240));

        //Bottom label for spacing
        Label bottom = new Label();
        bottom.setMinHeight(100);
        bottom.setStyle("-fx-background-color: " + mainTheme.get(3));

        //Top label for spacing
        Label top = new Label();
        top.setMinHeight(10);
        top.setStyle("-fx-background-color: " + mainTheme.get(3));

        //CREATE VBOX TO HOLD ALL THE REGIONS
        VBox centerBox = new VBox(topRegion,drawsBox,middleRegion,bottomRegion);
        centerBox.getChildren().addAll();
        centerBox.setStyle("-fx-background-color: " + mainTheme.get(3));
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(20);
        centerBox.setPadding(new Insets(5,0,60,0));

        //CREATE BP WHICH HOLDS THE MAIN VBOX IN ITS CENTER
        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: "+mainTheme.get(3));
        bp.setCenter(centerBox);

        //Create root node and add bp also allows for scrolling of the scene
        ScrollPane root = new ScrollPane();
        root.setContent(bp);
        root.setFitToWidth(true);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        //SHOW BET CARD SCENE
        return new Scene(root, width, height);
    }

    //When New Look menu option is clicked creates a different drawing scene that saves current progress
    public Scene saveCurrentDrawing(EventHandler<ActionEvent> changeMenuRules, EventHandler<ActionEvent> changeMenuOdds, EventHandler<ActionEvent> changeMenuQuit, EventHandler<MouseEvent> hoverMenuButton, EventHandler<MouseEvent> hoverMenuButton2, EventHandler<MouseEvent> hoverButton, EventHandler<MouseEvent> hoverButton2,EventHandler<ActionEvent> backHome,Path arrow){
        //creates an instance of the GameLogic class which does calculations for the game play
        GameLogic logic = new GameLogic();

        //DROPDOWN MENU WITH NEW OPTION
        MenuButton menuAgain = new MenuButton("Menu");
        MenuItem displayRules = new MenuItem("Display Rules");
        displayRules.setStyle(mainTheme.get(4));
        MenuItem oddsWinning = new MenuItem("Odds of Winning");
        oddsWinning.setStyle(mainTheme.get(4));
        MenuItem newLook = new MenuItem("Change Theme");
        newLook.setStyle(mainTheme.get(4));
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setStyle(mainTheme.get(4));
        menuAgain.getItems().add(displayRules);
        menuAgain.getItems().add(oddsWinning);
        menuAgain.getItems().add(newLook);
        menuAgain.getItems().add(exitGame);
        menuAgain.setStyle(mainTheme.get(5));
        menuAgain.setPrefWidth(150);
        menuAgain.setAlignment(Pos.CENTER);
        //add events
        menuAgain.setOnMouseEntered(hoverMenuButton);
        menuAgain.setOnMouseExited(hoverMenuButton2);
        displayRules.setOnAction(e->primaryStage.setScene(rulesChanged(changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome)));
        oddsWinning.setOnAction(e->primaryStage.setScene(oddsChanged(changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome)));
        //New Look event that allows players to return to drawing scene they left off
        newLook.setOnAction(e->{
            changeThemeArray();
            primaryStage.setScene(saveCurrentDrawing(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrow));});
        exitGame.setOnAction(changeMenuQuit);

        //Box that displays the total winnings
        Label winningsBox = new Label();
        winningsBox.setStyle(mainTheme.get(5));
        winningsBox.setText("Winnings: $" + totalWinnings);
        winningsBox.setAlignment(Pos.CENTER);
        winningsBox.setMinWidth(200);
        //Holds the box that displays total winnings and the menu button
        HBox menuAndWinnings = new HBox(menuAgain, winningsBox);
        menuAndWinnings.setSpacing(700);
        menuAndWinnings.setAlignment(Pos.CENTER);
        menuAndWinnings.setPadding(new Insets(20,0,0,0));

        //Header for the drawing scenes
        StackPane DrawingsLabelBox = new StackPane();
        DrawingsLabelBox.setStyle(mainTheme.get(5));
        DrawingsLabelBox.setMinWidth(450);
        DrawingsLabelBox.setMaxWidth(450);
        DrawingsLabelBox.setMinHeight(105);
        DrawingsLabelBox.setMaxHeight(105);
        //Text for the header
        Text drawingsHeader = new Text("NUMBERS DRAWN");
        drawingsHeader.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        DrawingsLabelBox.getChildren().add(drawingsHeader);

        //create a new grid pane that will display the twenty random generated numbers and the numbers matched
        GridPane numbersPickedAndMatched = new GridPane();

        //array List that will hold pause transitions
        ArrayList<PauseTransition> pauses = new ArrayList<>(20);

        //create the grid pane looping through rows and columns
        int count = 0;
        //starting pause time
        double pauseTime = 0.1;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 10; j++){
                //each time create a new button with the number randomly generated from GameLogic
                Button currButton = new Button(String.valueOf(randGenerated.get(count)));
                //and a new pause transition with the current pause time
                PauseTransition currPause = new PauseTransition(Duration.seconds(pauseTime));
                //set pause transition
                currPause.setOnFinished(e->currButton.setVisible(true));
                //store pause transition into array list
                pauses.add(currPause);
                //increment pause time so buttons appear one after the other
                pauseTime++;

                //check if player numbers matched the random numbers
                //if(numbersSelected.contains(logic.generateNums().get(count))){
                if(logic.matchedNums(numbersSelected,randGenerated).contains(randGenerated.get(count))){
                    //if matched then change color
                    currButton.setStyle("-fx-background-color: "+mainTheme.get(2)+";-fx-font: bold 12pt \"Tahoma\";-fx-fill: "+ mainTheme.get(1));
                    currButton.setTextFill(Paint.valueOf("#bd32ce"));
                }
                else{
                    //else display normally
                    currButton.setStyle("-fx-font: bold 12pt \"Tahoma\";-fx-fill: #000000;");
                }
                count++;

                //change design of each button in the grid pane
                currButton.setShape(new Circle(20));
                currButton.setMinSize(70,70);
                currButton.setVisible(false);

                //add to grid pane
                numbersPickedAndMatched.add(currButton,j,i);
            }
        }

        //loop through array list and play each pause transition
        for (PauseTransition pauseTransition : pauses) {
            pauseTransition.play();
        }

        //layout of grid pane display
        numbersPickedAndMatched.setAlignment(Pos.CENTER);
        numbersPickedAndMatched.setHgap(50);
        numbersPickedAndMatched.setVgap(50);

        //box that displays round winnings and amount matched
        StackPane amountWon = new StackPane();
        amountWon.setStyle(mainTheme.get(5));
        amountWon.setMinWidth(450);
        amountWon.setMaxWidth(450);
        amountWon.setMinHeight(105);
        amountWon.setMaxHeight(105);

        //text that says numbers matched and round winnings
        Text amountText = new Text("NUMBERS MATCHED: " + logic.matchedNums(numbersSelected,randGenerated).size() + "\n       AMOUNT WON: $" + roundWinnings);
        amountText.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        amountWon.getChildren().addAll(amountText);
        amountWon.setAlignment(Pos.CENTER);

        //button that goes to the next drawing
        Button nextDrawing = new Button("NEXT DRAWING");
        nextDrawing.setStyle(mainTheme.get(5));
        nextDrawing.setShape(arrow);
        nextDrawing.setMinSize(50,100);
        nextDrawing.setAlignment(Pos.CENTER);
        nextDrawing.setVisible(false);
        nextDrawing.setOnMouseEntered(hoverButton);
        nextDrawing.setOnMouseExited(hoverButton2);
        nextDrawing.setOnAction(e->primaryStage.setScene(makeCurrentDrawing(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrow)));

        //button that allows the player to play again after the last drawing
        Button playAgain = new Button("PLAY AGAIN");
        playAgain.setStyle(mainTheme.get(5));
        playAgain.setShape(arrow);
        playAgain.setMinSize(100,100);
        playAgain.setOnMouseEntered(hoverButton);
        playAgain.setOnMouseExited(hoverButton2);
        playAgain.setAlignment(Pos.CENTER);
        playAgain.setVisible(false);
        playAgain.setOnAction(e->{numSpots = 0; primaryStage.setScene(makeBetCard(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrow));});

        //button that allows the player to quit after the last drawing
        Button endGame = new Button("EXIT GAME");
        endGame.setStyle(mainTheme.get(5));
        endGame.setMinSize(120,60);
        endGame.setOnMouseEntered(hoverButton);
        endGame.setOnMouseExited(hoverButton2);
        endGame.setAlignment(Pos.CENTER);
        endGame.setVisible(false);
        endGame.setOnAction(changeMenuQuit);

        //displays a congratulations message at the last drawing
        StackPane congratulationAmount = new StackPane();
        congratulationAmount.setStyle(mainTheme.get(5));
        congratulationAmount.setAlignment(Pos.CENTER);

        //Holds bottom region of the drawing scene
        HBox bottomButtons = new HBox(playAgain,nextDrawing,endGame);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setStyle("-fx-background-color: " + mainTheme.get(3));
        bottomButtons.setSpacing(200);

        //Add pause transition to all three widgets to display after 20+ seconds
        PauseTransition endGamePause = new PauseTransition(Duration.seconds(21));
        endGamePause.setOnFinished(e-> endGame.setVisible(true));
        PauseTransition playAgainPause = new PauseTransition(Duration.seconds(21));
        playAgainPause.setOnFinished(e-> playAgain.setVisible(true));
        PauseTransition congratulationAmountPause = new PauseTransition(Duration.seconds(22));
        congratulationAmountPause.setOnFinished(e-> congratulationAmount.setVisible(true));

        //When on last draw display the quit or play again buttons and congratulations message
        if(currentDrawingCount >= numDraws){

            totalWinnings = totalWinnings + roundWinnings;
            //Text of the message
            Text congratulationText = new Text("Congratulations! You Won $"+totalWinnings+ " In Total\n   Play Again Or Quit Game");

            congratulationText.setStyle(" -fx-font: bold 10pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
            congratulationText.setTextAlignment(TextAlignment.CENTER);
            congratulationAmount.getChildren().addAll(congratulationText);
            congratulationAmount.setVisible(false);

            nextDrawing.setVisible(false);
            bottomButtons = new HBox(playAgain,congratulationAmount,endGame);
            bottomButtons.setAlignment(Pos.CENTER);
            bottomButtons.setSpacing(200);
            endGamePause.play();
            playAgainPause.play();
            congratulationAmountPause.play();
        }

        //Add pause transition to play next drawing button
        PauseTransition drawingPause = new PauseTransition(Duration.seconds(20));
        drawingPause.setOnFinished(e->nextDrawing.setVisible(true));
        drawingPause.play();

        //Holds all center visual of the drawing scene
        VBox centerRegion = new VBox();
        centerRegion.getChildren().addAll(DrawingsLabelBox,numbersPickedAndMatched,amountWon);
        centerRegion.setStyle("-fx-background-color: " + mainTheme.get(3));//6e68bb
        centerRegion.setAlignment(Pos.CENTER);
        centerRegion.setSpacing(80);

        //Holds the center visuals and the buttons
        VBox centerRegionWithButton = new VBox();
        centerRegionWithButton.getChildren().addAll(centerRegion,bottomButtons);
        centerRegionWithButton.setSpacing(20);
        centerRegionWithButton.setAlignment(Pos.CENTER);

        //Border pane holds all the widgets previously created
        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: "+ mainTheme.get(3));
        bp.setTop(menuAndWinnings);
        bp.setCenter(centerRegionWithButton);

        //root node of the scene allows for scrolling of the scene
        ScrollPane root = new ScrollPane();
        root.setContent(bp);
        root.setFitToHeight(true);
        root.setFitToWidth(true);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        currDrawingScene = new Scene(root,width,height);
        return currDrawingScene;
    }

    //creates the drawing scene
    public Scene makeCurrentDrawing(EventHandler<ActionEvent> changeMenuRules, EventHandler<ActionEvent> changeMenuOdds, EventHandler<ActionEvent> changeMenuQuit, EventHandler<MouseEvent> hoverMenuButton, EventHandler<MouseEvent> hoverMenuButton2, EventHandler<MouseEvent> hoverButton, EventHandler<MouseEvent> hoverButton2,EventHandler<ActionEvent> backHome,Path arrow){
        //creates an instance of the GameLogic class which does calculations for the game playe
        GameLogic logic = new GameLogic();

        //add to total winnings
        if(!fromNewGame){
            totalWinnings += roundWinnings;
        }
        else{
            fromNewGame = false;
        }

        //increment the amount of drawing scenes created
        currentDrawingCount++;

        //DROPDOWN MENU WITH NEW OPTION
        MenuButton menuAgain = new MenuButton("Menu");
        MenuItem displayRules = new MenuItem("Display Rules");
        displayRules.setStyle(mainTheme.get(4));
        MenuItem oddsWinning = new MenuItem("Odds of Winning");
        oddsWinning.setStyle(mainTheme.get(4));
        MenuItem newLook = new MenuItem("Change Theme");
        newLook.setStyle(mainTheme.get(4));
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setStyle(mainTheme.get(4));
        menuAgain.getItems().add(displayRules);
        menuAgain.getItems().add(oddsWinning);
        menuAgain.getItems().add(newLook);
        menuAgain.getItems().add(exitGame);
        menuAgain.setStyle(mainTheme.get(5));
        menuAgain.setPrefWidth(150);
        menuAgain.setAlignment(Pos.CENTER);
        //add events
        menuAgain.setOnMouseEntered(hoverMenuButton);
        menuAgain.setOnMouseExited(hoverMenuButton2);
        displayRules.setOnAction(e->primaryStage.setScene(rulesChanged(changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome)));
        oddsWinning.setOnAction(e->primaryStage.setScene(oddsChanged(changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome)));
        //New look event that allows players to return to drawing scene they left off
        newLook.setOnAction(e->{
            changeThemeArray();
            primaryStage.setScene(saveCurrentDrawing(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrow));});
        exitGame.setOnAction(changeMenuQuit);

        //Box that displays the total winnings
        Label winningsBox = new Label();
        winningsBox.setStyle(mainTheme.get(5));
        winningsBox.setText("Winnings: $" + totalWinnings);
        winningsBox.setAlignment(Pos.CENTER);
        winningsBox.setMinWidth(200);
        //Holds the box that displays total winnings and the menu button
        HBox menuAndWinnings = new HBox(menuAgain, winningsBox);
        menuAndWinnings.setSpacing(700);
        menuAndWinnings.setAlignment(Pos.CENTER);
        menuAndWinnings.setPadding(new Insets(20,0,0,0));

        //Header for the drawing scenes
        StackPane DrawingsLabelBox = new StackPane();
        DrawingsLabelBox.setStyle(mainTheme.get(5));
        DrawingsLabelBox.setMinWidth(450);
        DrawingsLabelBox.setMaxWidth(450);
        DrawingsLabelBox.setMinHeight(105);
        DrawingsLabelBox.setMaxHeight(105);
        //Text for the header
        Text drawingsHeader = new Text("NUMBERS DRAWN");
        drawingsHeader.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        DrawingsLabelBox.getChildren().add(drawingsHeader);

        //create a new grid pane that will display the twenty random generated numbers and the numbers matched
        GridPane numbersPickedAndMatched = new GridPane();

        //array List that will hold pause transitions
        ArrayList<PauseTransition> pauses = new ArrayList<>(20);

        randGenerated = logic.generateNums();

        //create the grid pane looping through rows and columns
        int count = 0;
        //starting pause time
        double pauseTime = 0.1;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 10; j++){
                //each time create a new button with the number randomly generated from GameLogic
                Button currButton = new Button(String.valueOf(randGenerated.get(count)));
                //and a new pause transition with the current pause time
                PauseTransition currPause = new PauseTransition(Duration.seconds(pauseTime));
                //set pause transition
                currPause.setOnFinished(e->currButton.setVisible(true));
                //store pause transition into array list
                pauses.add(currPause);
                //increment pause time so buttons appear one after the other
                pauseTime++;

                //check if player numbers matched the random numbers
                    //if(numbersSelected.contains(logic.generateNums().get(count))){
                if(logic.matchedNums(numbersSelected,randGenerated).contains(randGenerated.get(count))){
                        //if matched then change color
                        currButton.setStyle("-fx-background-color: "+mainTheme.get(2)+";-fx-font: bold 12pt \"Tahoma\";-fx-fill: "+ mainTheme.get(1));
                        currButton.setTextFill(Paint.valueOf("#bd32ce"));
                }
                else{
                        //else display normally
                        currButton.setStyle("-fx-font: bold 12pt \"Tahoma\";-fx-fill: #000000;");
                }

                count++;

                //change design of each button in the grid pane
                currButton.setShape(new Circle(20));
                currButton.setMinSize(70,70);
                currButton.setVisible(false);

                //add to grid pane
                numbersPickedAndMatched.add(currButton,j,i);
            }
        }

        //loop through array list and play each pause transition
        for (PauseTransition pauseTransition : pauses) {
            pauseTransition.play();
        }

        //layout of grid pane display
        numbersPickedAndMatched.setAlignment(Pos.CENTER);
        numbersPickedAndMatched.setHgap(50);
        numbersPickedAndMatched.setVgap(50);

        //box that displays round winnings and amount matched
        StackPane amountWon = new StackPane();
        amountWon.setStyle(mainTheme.get(5));
        amountWon.setMinWidth(450);
        amountWon.setMaxWidth(450);
        amountWon.setMinHeight(105);
        amountWon.setMaxHeight(105);

        roundWinnings = logic.calculateWinnings(numSpots,logic.matchedNums(numbersSelected,randGenerated));

        //text that says numbers matched and round winnings
        Text amountText = new Text("NUMBERS MATCHED: " + logic.matchedNums(numbersSelected,randGenerated).size() + "\n       AMOUNT WON: $" + roundWinnings);
        amountText.setStyle(" -fx-font: bold 28pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
        amountWon.getChildren().addAll(amountText);
        amountWon.setAlignment(Pos.CENTER);

        //button that goes to the next drawing
        Button nextDrawing = new Button("NEXT DRAWING");
        nextDrawing.setStyle(mainTheme.get(5));
        nextDrawing.setShape(arrow);
        nextDrawing.setMinSize(50,100);
        nextDrawing.setAlignment(Pos.CENTER);
        nextDrawing.setVisible(false);
        nextDrawing.setOnMouseEntered(hoverButton);
        nextDrawing.setOnMouseExited(hoverButton2);
        nextDrawing.setOnAction(e->primaryStage.setScene(makeCurrentDrawing(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrow)));

        //button that allows the player to play again after the last drawing
        Button playAgain = new Button("PLAY AGAIN");
        playAgain.setStyle(mainTheme.get(5));
        playAgain.setShape(arrow);
        playAgain.setMinSize(100,100);
        playAgain.setOnMouseEntered(hoverButton);
        playAgain.setOnMouseExited(hoverButton2);
        playAgain.setAlignment(Pos.CENTER);
        playAgain.setVisible(false);
        playAgain.setOnAction(e->{numSpots = 0;numDraws = 0;primaryStage.setScene(makeBetCard(changeMenuRules,changeMenuOdds,changeMenuQuit,hoverMenuButton,hoverMenuButton2,hoverButton,hoverButton2,backHome,arrow));});

        //button that allows the player to quit after the last drawing
        Button endGame = new Button("EXIT GAME");
        endGame.setStyle(mainTheme.get(5));
        endGame.setMinSize(120,60);
        endGame.setOnMouseEntered(hoverButton);
        endGame.setOnMouseExited(hoverButton2);
        endGame.setAlignment(Pos.CENTER);
        endGame.setVisible(false);
        endGame.setOnAction(changeMenuQuit);

        //displays a congratulations message at the last drawing
        StackPane congratulationAmount = new StackPane();
        congratulationAmount.setStyle(mainTheme.get(5));
        congratulationAmount.setAlignment(Pos.CENTER);

        //Holds bottom region of the drawing scene
        HBox bottomButtons = new HBox(playAgain,nextDrawing,endGame);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setStyle("-fx-background-color: " + mainTheme.get(3));
        bottomButtons.setSpacing(200);

        //Add pause transition to all three widgets to display after 20+ seconds
        PauseTransition endGamePause = new PauseTransition(Duration.seconds(21));
        endGamePause.setOnFinished(e-> endGame.setVisible(true));
        PauseTransition playAgainPause = new PauseTransition(Duration.seconds(21));
        playAgainPause.setOnFinished(e-> playAgain.setVisible(true));
        PauseTransition congratulationAmountPause = new PauseTransition(Duration.seconds(22));
        congratulationAmountPause.setOnFinished(e-> congratulationAmount.setVisible(true));

        //When on last draw display the quit or play again buttons and congratulations message
        if(currentDrawingCount >= numDraws){

            totalWinnings = totalWinnings + roundWinnings;
            //Text of the message
            Text congratulationText = new Text("Congratulations! You Won $"+totalWinnings+ " In Total\n   Play Again Or Quit Game");

            congratulationText.setStyle(" -fx-font: bold 10pt \"Impact\";-fx-border-width: 5;-fx-fill: " + mainTheme.get(2));
            congratulationText.setTextAlignment(TextAlignment.CENTER);
            congratulationAmount.getChildren().addAll(congratulationText);
            congratulationAmount.setVisible(false);

            nextDrawing.setVisible(false);
            bottomButtons = new HBox(playAgain,congratulationAmount,endGame);
            bottomButtons.setAlignment(Pos.CENTER);
            bottomButtons.setSpacing(200);
            endGamePause.play();
            playAgainPause.play();
            congratulationAmountPause.play();
        }

        //Add pause transition to play next drawing button
        PauseTransition drawingPause = new PauseTransition(Duration.seconds(20));
        drawingPause.setOnFinished(e->nextDrawing.setVisible(true));
        drawingPause.play();

        //Holds all center visual of the drawing scene
        VBox centerRegion = new VBox();
        centerRegion.getChildren().addAll(DrawingsLabelBox,numbersPickedAndMatched,amountWon);
        centerRegion.setStyle("-fx-background-color: " + mainTheme.get(3));//6e68bb
        centerRegion.setAlignment(Pos.CENTER);
        centerRegion.setSpacing(80);

        //Holds the center visuals and the buttons
        VBox centerRegionWithButton = new VBox();
        centerRegionWithButton.getChildren().addAll(centerRegion,bottomButtons);
        centerRegionWithButton.setSpacing(20);
        centerRegionWithButton.setAlignment(Pos.CENTER);

        //Border pane holds all the widgets previously created
        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: "+ mainTheme.get(3));
        bp.setTop(menuAndWinnings);
        bp.setCenter(centerRegionWithButton);

        //root node of the scene allows for scrolling of the scene
        ScrollPane root = new ScrollPane();
        root.setContent(bp);
        root.setFitToHeight(true);
        root.setFitToWidth(true);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        currDrawingScene = new Scene(root,width,height);
        return currDrawingScene;
    }
}
