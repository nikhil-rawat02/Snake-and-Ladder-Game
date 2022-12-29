package com.example.project_snake_ladder;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Player {
    private Circle coin;

    private String name;
    private int coinPosition;
    private static Board gameBoard = new Board();


    public Player (int tileSize, Color coinColor, String name){
        coinPosition =1;
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        coin.setStroke(Color.BLACK);
        coin.setTranslateX(20);
        coin.setTranslateY(380);
        this.name = name;
    }
    public void movePlayer(int diceValue){
        if(coinPosition + diceValue <= 100){
            coinPosition += diceValue;
//            coin.setTranslateX(gameBoard.getXCCoordinate(coinPosition));
//            coin.setTranslateY(gameBoard.getYCCoordinate(coinPosition));
            translatePlayer();
            int newPosition = gameBoard.getNextPosition(coinPosition);
            if(newPosition != coinPosition){
                coinPosition = newPosition;
                translatePlayer();
            }
        }
    }
    public void initialPosition(){
        coinPosition = 1;
        translatePlayer();
    }
    public String playerWon(){
    if(coinPosition == 100) return this.name + "Won the game!";
    return null;
    }
    private void translatePlayer(){
        TranslateTransition move = new TranslateTransition(Duration.millis(1000),this.coin);
        move.setToX(gameBoard.getXCCoordinate(coinPosition));
        move.setToY(gameBoard.getYCCoordinate(coinPosition));
        move.setAutoReverse(false);
        move.play();
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCoinPosition() {
        return coinPosition;
    }

    public void setName(String name) {
        this.name = name;
    }
}
