package com.example.zaraky.tictactoe_android.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaraky.tictactoe_android.R;
import com.example.zaraky.tictactoe_android.model.Board;
import com.example.zaraky.tictactoe_android.model.Player;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

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

        Bundle extras = getIntent().getExtras();
        String player1 = extras.getString("player1");
        String player2 = extras.getString("player2");

        board.setPlayerName(Player.X, player1);
        board.setPlayerName(Player.O, player2);

        TextView player1Text = findViewById(R.id.player1);
        player1Text.setText(player1);

        TextView player2Text = findViewById(R.id.player2);
        player2Text.setText(player2);
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
                winner.setText(board.getPlayerName(board.player) + " win!");
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
