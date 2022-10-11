/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;
/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{
    public int gameOver = -2;
    /**
        * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
        * this method will return an integer that we use to identify game state and the winner.
            *  1 is X, 0 is O, -1 if it is a draw, and -2 simply shows the game is still running.
     */
    public int getWinner(int x, int y, int gameSize, String buttonString, String[][] buttonArray) {
        // check row
        for (int i = 0; true; i++) {
            if (!buttonArray[x][i].equals(buttonString)) {
                break;
            }
            if (i == gameSize - 1) {
                gameOver = buttonString.equals("X") ? 1 : 0;
                return gameOver;
            }
        }
        // check column
        for (int i = 0; true; i++) {
            if (!buttonArray[i][y].equals(buttonString)) {
                break;
            }
            if (i == gameSize - 1) {
                gameOver = buttonString.equals("X") ? 1 : 0;
                return gameOver;
            }
        }
        // check diagonal
        if (x == y) {
            for (int i = 0; true; i++) {
                if (!buttonArray[i][i].equals(buttonString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    gameOver = buttonString.equals("X") ? 1 : 0;
                    return gameOver;
                }
            }
        }
        // check anti-diagonal
        if (x + y == gameSize - 1) {
            for (int i = 0; true; i++) {
                if (!buttonArray[i][(gameSize - 1) - i].equals(buttonString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    gameOver = buttonString.equals("X") ? 1 : 0;
                    return gameOver;
                }
            }
        }
        //check draw
        for (int i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttonArray[i][j].equals("")) {
                    break;
                }
                if (i == 2 && j == 2) {
                    gameOver = -1;
                    return gameOver;
                }
            }
        }
        gameOver = -2;
        return gameOver;
    }

    /**
     * Returns X for player X, O for player O.
     *
     * @return String Placer of the next player.
     */
    public String getNextPlayer(String lastPlayer)
    {
        if(lastPlayer.charAt(0) == 'X')
            return "O";
        return "X";
    }



    public boolean isGameOver()
    {
        int g = gameOver;

        return g != -2;


    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        //TODO Implement this method
    }

}
