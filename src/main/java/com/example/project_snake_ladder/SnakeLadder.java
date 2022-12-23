package com.example.project_snake_ladder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class SnakeLadder extends Application {
    public static final int tileSize  = 40, height = 10,width = 10;
    int lowerLine = tileSize*height;
    Player firstPlayer = new Player(tileSize, Color.BEIGE,"Amit");
    Player secondPlayer = new Player(tileSize,Color.AQUAMARINE,"Sumit");
    Pane createContent() throws FileNotFoundException {
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+50);

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
        root.getChildren().add(boardImage);

        Button playerOneButton = new Button("Player One");
        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(lowerLine+20);
        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setTranslateX(250);
        playerTwoButton.setTranslateY(lowerLine+20);
        root.getChildren().addAll(playerOneButton,playerTwoButton, firstPlayer.getCoin(), secondPlayer.getCoin());
        return root;
    }
    class Pair{
        int x;
        int y;
        Pair(){

        }
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private void map(){
        HashMap<Integer, Pair>map = new HashMap<>();
        int orieantation = 0;
        int tileNo = 100;
        for(int i = 0; i <10; i++){
            if(orieantation % 2 == 0){
                for(int j = 0;j < 10; j++){
                    map.put(tileNo, new Pair(j*tileSize+tileSize/2,i*tileSize/2));
                }
            }else{
                for(int j = 9; j >=0; j--){

                }
            }
            orieantation++;
            tileNo--;
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}