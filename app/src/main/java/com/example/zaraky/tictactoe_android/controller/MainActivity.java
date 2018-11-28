package com.example.zaraky.tictactoe_android.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaraky.tictactoe_android.R;
import com.example.zaraky.tictactoe_android.model.Board;

public class MainActivity extends AppCompatActivity {
    Board board = new Board();
    Button[] allButtons;
    TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        winner = findViewById(R.id.winnerText);
        allButtons = getAllButtons();
    }

    public void onCellClick(View v) {
        Button btn = (Button) v;

        // mark the cell
        String tag = btn.getTag().toString();
        int row = Integer.valueOf(tag.substring(0, 1));
        int col = Integer.valueOf(tag.substring(1, 2));

        if (board.markCell(row, col)) {
            btn.setText(board.player.toString());

            // check if win
            if (board.isCurrentPlayerWin(row, col)) {
                winner.setText(board.player.toString() + " win!");
                board.gameOver();
            } else if (board.isDraw()) { // check if draw
                winner.setText("Draw!");
                board.gameOver();
            } else {
                board.changePlayer();
            }
        }
    }


    public void resetGame(View v) {
        for (int i = 0; i < 9; i++) {
            allButtons[i].setText("");
        }
        board.reset();
        winner.setText("");
    }

    private Button[] getAllButtons () {
        Button[] buttons = new Button[9];
        for (int i = 0; i < 9; i++) {
            int id = getResources().getIdentifier("btn"+i, "id", getPackageName());
            buttons[i] = (Button) findViewById(id);
        }
        return buttons;
    }
}
