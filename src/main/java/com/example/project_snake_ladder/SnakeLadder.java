package com.example.project_snake_ladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class SnakeLadder extends Application {
    MenuItem twoPlayer = new MenuItem("Two Player");
    MenuItem threePlayer = new MenuItem("Three Player");
    MenuItem fourPlayer = new MenuItem("Four Player");
    Button playerOneButton = new Button("Player One");
    Button playerTwoButton = new Button("Player Two");
    Button playerThreeButton = new Button("Player Three");
    Button playerFourButton = new Button("Player Four");
    Label gameStatus = new Label("Click on Play to start the game");
    boolean gameFinished = false;
    public  static Pane home = new Pane();
    public static final int tileSize  = 40, height = 10,width = 10, windowWidth = 600, windowHeight= 550;
    int lowerLine = tileSize*height;
    public int diceValue;
    public Label rollDiceValueLabel;
    Button startGameButton;
    Button rollButton = new Button("roll");
    boolean firstPlayerTurn = false,secondPlayerTurn = false,thirdPlayerTurn = false,fourthPlayerTurn = false,gameStarted = false;
    boolean moveFirst =false,moveSecond = false,moveThird = false, moveFourth = false;
    Player firstPlayer = new Player(tileSize, Color.RED,"Player 1");
    Player secondPlayer = new Player(tileSize,Color.YELLOW,"Player 2");
    Player thirdPlayer  = new Player(tileSize, Color.BLUE,"Player 3");
    Player fourthPlayer = new Player(tileSize, Color.GREEN,"Player 4");
    Random random = new Random();
    Pane createContent(int player) throws FileNotFoundException, InterruptedException {
        Pane root = new Pane();


        home.setStyle("-fx-background-color : #808080;");

        home.setPrefSize(windowWidth,windowHeight);
        root.setPrefSize(windowWidth,windowHeight);

        for(int i =0; i < width; i++){
            for(int j =0; j < height;j++){
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }
        Image image = new Image(new FileInputStream("C:\\Users\\Dpk\\Desktop\\Java\\project_snake_ladder\\src\\image\\board_1.png"));
        ImageView boardImage = new ImageView(image);
        boardImage.setFitWidth(tileSize*width);
        boardImage.setFitHeight(tileSize*height);
        Image clipArt = new Image(new FileInputStream("C:\\Users\\Dpk\\Desktop\\Java\\project_snake_ladder\\src\\image\\clipart.png"));
        ImageView imageViewClipArt = new ImageView(clipArt);
        imageViewClipArt.setFitWidth(150);
        imageViewClipArt.setFitHeight(150);
        imageViewClipArt.setTranslateX(tileSize*10 +25);
        imageViewClipArt.setTranslateY(tileSize);
        Image playButton = new Image(new FileInputStream("C:\\Users\\Dpk\\Desktop\\Java\\project_snake_ladder\\src\\image\\play button.png"));
        ImageView imageViewPlayButton = new ImageView(playButton);
        imageViewPlayButton.setFitWidth(100);
        imageViewPlayButton.setFitHeight(100);
        imageViewPlayButton.setTranslateX(tileSize*10+30);
        imageViewPlayButton.setTranslateY(tileSize*6);
        gameStatus.setTranslateX(imageViewClipArt.getTranslateX());
        gameStatus.setTranslateY(imageViewClipArt.getTranslateY() + imageViewClipArt.getFitHeight()+20);
        gameStatus.setWrapText(true);
        gameStatus.setMaxWidth(imageViewClipArt.getFitWidth());
        playerOneButton.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY,new Insets(0))));
        playerTwoButton.setBackground(new Background(new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY,new Insets(0))));
        playerThreeButton.setBackground(new Background(new BackgroundFill(Color.BLUE,CornerRadii.EMPTY,new Insets(0))));
        playerFourButton.setBackground(new Background(new BackgroundFill(Color.GREEN,CornerRadii.EMPTY,new Insets(0))));
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               if(gameStarted) {
                   gameStatus.setText("");
                   if (firstPlayerTurn) {
                       setDiceValue();
                       if (diceValue == 6 )moveFirst = true;
                       if (moveFirst) {
                           firstPlayer.movePlayer(diceValue);
                           if (firstPlayer.playerWon() != null) {
                               rollDiceValueLabel.setText(firstPlayer.playerWon());
                               gameFinished = true;
                               playerOneButton.setDisable(true);
                           }
                       }
                       firstPlayerTurn = false;
                       secondPlayerTurn = true;
                       thirdPlayerTurn = false;
                       fourthPlayerTurn = false;
                   }
               }
            }
        });
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if (secondPlayerTurn) {
                        setDiceValue();
                        if(diceValue == 6)moveSecond = true;
                        if(moveSecond) {
                            secondPlayer.movePlayer(diceValue);
                            if (secondPlayer.playerWon() != null) {
                                rollDiceValueLabel.setText(secondPlayer.playerWon());
                                gameFinished = true;
                                playerTwoButton.setDisable(true);
                            }
                        }
                        if (player > 2) {
                            firstPlayerTurn = false;
                            secondPlayerTurn = false;
                            thirdPlayerTurn = true;
                            fourthPlayerTurn = false;
                        } else {
                            firstPlayerTurn = true;
                            secondPlayerTurn = false;

                        }
                    }
                }
            }
        });
        playerThreeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if (thirdPlayerTurn) {
                        setDiceValue();
                        if(diceValue == 6) moveThird = true;
                        if (moveThird) {
                            thirdPlayer.movePlayer(diceValue);
                            if (thirdPlayer.playerWon() != null) {
                                rollDiceValueLabel.setText(thirdPlayer.playerWon());
                                gameFinished = true;
                                playerThreeButton.setDisable(true);
                            }
                        }
                        if (player > 3) {
                            firstPlayerTurn = false;
                            secondPlayerTurn = false;
                            thirdPlayerTurn = false;
                            fourthPlayerTurn = true;
                        } else {
                            firstPlayerTurn = true;
                            secondPlayerTurn = false;
                            thirdPlayerTurn = false;
                        }
                    }
                }
            }
        });
        playerFourButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if (fourthPlayerTurn) {
                        setDiceValue();
                        if(diceValue == 6)moveFourth  = true;
                        if(moveFourth) {
                            fourthPlayer.movePlayer(diceValue);
                            if (fourthPlayer.playerWon() != null) {
                                rollDiceValueLabel.setText(fourthPlayer.playerWon());
                                gameFinished = true;
                                playerFourButton.setDisable(true);
                                gameStatus.setText("Game has been finished click of play to start again");
                            }
                        }
                        firstPlayerTurn = true;
                        secondPlayerTurn = false;
                        thirdPlayerTurn = false;
                        fourthPlayerTurn = false;
                    }
                }
            }
        });
        imageViewPlayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gameStarted = true;

                if(player == 2){
                    playerOneButton.setDisable(false);
                    playerTwoButton.setDisable(false);
                    firstPlayer.initialPosition();
                    secondPlayer.initialPosition();
                    moveFirst = false;
                    moveSecond = false;
                }else if(player == 3){
                    playerOneButton.setDisable(false);
                    playerTwoButton.setDisable(false);
                    playerThreeButton.setDisable(false);
                    firstPlayer.initialPosition();
                    secondPlayer.initialPosition();
                    thirdPlayer.initialPosition();
                    moveFirst = false;
                    moveSecond = false;
                    moveThird = false;
                }else{
                    playerOneButton.setDisable(false);
                    playerTwoButton.setDisable(false);
                    playerThreeButton.setDisable(false);
                    playerFourButton.setDisable(false);
                    firstPlayer.initialPosition();
                    secondPlayer.initialPosition();
                    thirdPlayer.initialPosition();
                    fourthPlayer.initialPosition();
                    moveFirst = false;
                    moveSecond = false;
                    moveThird = false;
                    moveFourth = false;
                }
                firstPlayerTurn = true;
                gameStatus.setText("Game is started Player 1 roll the dice");
            }
        });

        rollDiceValueLabel = new Label();
        rollDiceValueLabel.setTranslateY(lowerLine+50);
        rollDiceValueLabel.setTranslateX(130);

        startGameButton = new Button("Start");
        startGameButton.setTranslateX(tileSize*4 +tileSize/2);
        startGameButton.setTranslateY(lowerLine+tileSize);


        //roll dice work is in progress, till line no 211

        ImageView dice = new ImageView();
        dice.setFitWidth(50);
        dice.setFitHeight(50);
        dice.setTranslateX(imageViewPlayButton.getTranslateX());
        dice.setTranslateY(imageViewPlayButton.getTranslateY()+130);
        rollButton.setTranslateX(dice.getTranslateX() +dice.getFitWidth());
        rollButton.setTranslateY(dice.getTranslateY() + dice.getFitHeight() + 20);
        rollButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rollButton.setDisable(true);

                // call run
                Thread thread = new Thread(){
                    public void run(){
                        try{
                            for(int i =0; i < 15; i++){
                                File file = new File("C:\\Users\\Dpk\\Desktop\\Java\\project_snake_ladder\\src\\image\\dice" + (random.nextInt(6)+1)+".png");
                                dice.setImage(new Image(file.toURI().toString()));
                                Thread.sleep(50);
                            }
                            rollButton.setDisable(false);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });

        if(player == 2){
            playerOneButton.setTranslateX(tileSize);
            playerOneButton.setTranslateY(lowerLine+tileSize/2);

            playerTwoButton.setTranslateX(tileSize*7);
            playerTwoButton.setTranslateY(lowerLine+tileSize/2);

            root.getChildren().clear();
            root.getChildren().addAll(boardImage,playerOneButton,playerTwoButton,firstPlayer.getCoin(), secondPlayer.getCoin(),rollDiceValueLabel,imageViewClipArt, imageViewPlayButton,gameStatus,dice,rollButton);
        }else if(player == 3){
            root.getChildren().clear();

            playerOneButton.setTranslateX(tileSize);
            playerOneButton.setTranslateY(lowerLine+tileSize/2);

            playerTwoButton.setTranslateX(tileSize*7);
            playerTwoButton.setTranslateY(lowerLine+tileSize/2);

            playerThreeButton.setTranslateX(tileSize);
            playerThreeButton.setTranslateY(lowerLine+tileSize*2);

            root.getChildren().addAll(boardImage,playerOneButton,playerTwoButton,playerThreeButton,firstPlayer.getCoin(), secondPlayer.getCoin(),thirdPlayer.getCoin(),rollDiceValueLabel,imageViewClipArt, imageViewPlayButton,gameStatus,dice,rollButton);
        }else{
            root.getChildren().clear();

            playerOneButton.setTranslateX(tileSize);
            playerOneButton.setTranslateY(lowerLine+tileSize/2);

            playerTwoButton.setTranslateX(tileSize*7);
            playerTwoButton.setTranslateY(lowerLine+tileSize/2);

            playerThreeButton.setTranslateX(tileSize);
            playerThreeButton.setTranslateY(lowerLine+tileSize*2);

            playerFourButton.setTranslateX(tileSize*7);
            playerFourButton.setTranslateY(lowerLine+tileSize*2);

            root.getChildren().addAll(boardImage,playerOneButton,playerTwoButton,playerThreeButton,playerFourButton,firstPlayer.getCoin(), secondPlayer.getCoin(),thirdPlayer.getCoin(), fourthPlayer.getCoin(),rollDiceValueLabel,imageViewClipArt, imageViewPlayButton,gameStatus,dice,rollButton);
        }
        return root;
    }
    Pane snakeladder(){

        BackgroundImage myBg = new BackgroundImage(new Image("C:\\Users\\Dpk\\Desktop\\Java\\project_snake_ladder\\src\\image\\homePage.png",windowWidth,windowHeight,false,true),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        home.setBackground(new Background(myBg));
        home.setPrefSize(windowWidth,windowHeight);

        SplitMenuButton choiceBox = new SplitMenuButton();
        choiceBox.setText("Select Players");
        choiceBox.setBackground(new Background(new BackgroundFill(Color.PINK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        choiceBox.getItems().addAll(twoPlayer,threePlayer,fourPlayer);

        choiceBox.setTranslateX(tileSize*6 +tileSize/2);
        choiceBox.setTranslateY(lowerLine+tileSize+tileSize/2);
        home.getChildren().add(choiceBox);
        twoPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Pane root = createContent(2);
                    home.getChildren().clear();
                    home.getChildren().add(root);
                } catch (FileNotFoundException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threePlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Pane root = createContent(3);
                    home.getChildren().clear();
                    home.getChildren().add(root);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        fourPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Pane root = createContent(4);
                    home.getChildren().clear();
                    home.getChildren().add(root);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return home;
    }

    private void setDiceValue(){
        diceValue = (int)(Math.random()*6+1);
        rollDiceValueLabel.setText("Dice Value : " + diceValue);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(snakeladder());
        stage.setTitle("Snake & Ladder!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}