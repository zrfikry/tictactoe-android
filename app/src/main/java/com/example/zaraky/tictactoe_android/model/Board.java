package com.example.zaraky.tictactoe_android.model;

public class Board {
    public Cell[][] cells = new Cell[3][3];
    public Player player;
    private GameState state;

    private enum GameState { PLAYING, GAMEOVER };

    public Board() {
        reset();
    }

    public void reset() {
        clearCells();
        player = Player.X;
        state = GameState.PLAYING;
    }

    private void clearCells() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void changePlayer() {
        if (player == Player.X) {
            player = Player.O;
        } else {
            player = Player.X;
        }
    }

    public boolean isDraw() {
        int emptyCell = 9;
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (cells[i][j].getPlayer() != null) {
                    emptyCell--;
                }
            }
        }
        return emptyCell == 0;
    }

    public boolean markCell(int row, int col) {
        if (state != GameState.GAMEOVER) {
            cells[row][col].setPlayer(player);
            return true;
        }
        return false;
    }

    public void gameOver() {
        state = GameState.GAMEOVER;
    }

    public boolean isCurrentPlayerWin(int row, int col) {
        return (cells[row][0].getPlayer() == player // checking horizontally
                && cells[row][1].getPlayer() == player
                && cells[row][2].getPlayer() == player
                || cells[0][col].getPlayer() == player // checking vertically
                && cells[1][col].getPlayer() == player
                && cells[2][col].getPlayer() == player
                || row == col // checking 1st diagonal
                && cells[0][0].getPlayer() == player
                && cells[1][1].getPlayer() == player
                && cells[2][2].getPlayer() == player
                || row + col == 2 // checking 2nd diagonal
                && cells[0][2].getPlayer() == player
                && cells[1][1].getPlayer() == player
                && cells[2][0].getPlayer() == player);
    }
}
