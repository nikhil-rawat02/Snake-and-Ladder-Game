package com.example.project_snake_ladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private Circle coin;
    private String name;
    private int coinPosition;

    public Player (int tileSize, Color coinColor, String name){
        coinPosition =1;
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        coin.setStroke(Color.BLACK);
        coin.setTranslateX(20);
        coin.setTranslateY(380);
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCoinPosition() {
        return coinPosition;
    }

    public String getName() {
        return name;
    }
}
