/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;


/**
 *
 * @author Stegger
 */
public class TicTacViewController implements Initializable
{

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;
    
    private static final String TXT_PLAYER = "Player: ";
    private IGameModel game;
    private static final int gameSize = 3;
    private int moveCount = 0;
    // gameState is 0 for game running, 1 for found a winner, -1 for a draw
    private static int gameState = 0;

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            if (game.play(c, r))
            {
                if (game.isGameOver())
                {
                    int winner = game.getWinner();
                    displayWinner(winner);
                }
                else
                {
                    Image imageX = new Image(new FileInputStream("TicTacToe/src/tictactoe/gui/images/X.png"));
                    Image imageO = new Image(new FileInputStream("TicTacToe/src/tictactoe/gui/images/O.png"));
                    ImageView Xview = new ImageView(imageX);
                    ImageView Oview = new ImageView(imageO);
                    Button btn = (Button) event.getSource();
                    String xOrO = lblPlayer.getText().split(" ")[1];
                    if(xOrO.charAt(0) == 'X')
                        btn.setGraphic(Xview);
                    else
                        btn.setGraphic(Oview);
                    //btn.setText(xOrO);
                    btn.setDisable(true);
                    setGameState(checkIfWin(r, c, xOrO));
                    System.out.println(gameState);
                    setPlayer(game.getNextPlayer(xOrO));
                }
            }

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame(); // this
        setPlayer("X");
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard();
        setPlayer("X");
    }

    private void setPlayer(String player)
    {
        lblPlayer.setText(TXT_PLAYER + player);
    }

    private void displayWinner(int winner) // Make sure it works
    {
        String message;
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
            btn.setDisable(false);
        }
    }
    public int checkIfWin(int x, int y, String buttonString){
        moveCount++;

        String[][] buttonArray = new String[3][3];
        buttonArray[0][0] = btn1.getText();
        buttonArray[0][1] = btn2.getText();
        buttonArray[0][2] = btn3.getText();
        buttonArray[1][0] = btn4.getText();
        buttonArray[1][1] = btn5.getText();
        buttonArray[1][2] = btn6.getText();
        buttonArray[2][0] = btn7.getText();
        buttonArray[2][1] = btn8.getText();
        buttonArray[2][2] = btn9.getText();
        // check row
        for (int i = 0; true; i++) {
            if (!buttonArray[x][i].equals(buttonString)) {
                break;
            }
            if (i == gameSize -1 ){
                return 1;
            }
        }
        // check column
        for (int i = 0; true; i++) {
            if (!buttonArray[i][y].equals(buttonString)) {
                break;
            }
            if (i == gameSize - 1){
                return 1;
            }
        }
        // check diagonal
        if (x == y){
            for (int i = 0; true; i++) {
                if(!buttonArray[i][i].equals(buttonString)){
                    break;
                }
                if(i == gameSize-1){
                    return 1;
                }
            }
        }
        // check anti-diagonal
        if(x + y == gameSize - 1){
            for(int i = 0; true; i++){
                if(!buttonArray[i][(gameSize - 1) - i].equals(buttonString)){
                    break;
                }
                if(i == gameSize-1){
                    return 1;
                }
            }
        }
        //check draw
        if(moveCount == (Math.pow(gameSize, 2))){
            return -1;
        }
        return 0;
    }

    public static int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        TicTacViewController.gameState = gameState;
    }
}
