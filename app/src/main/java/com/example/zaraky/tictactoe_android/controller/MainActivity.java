package com.example.zaraky.tictactoe_android.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaraky.tictactoe_android.R;
import com.example.zaraky.tictactoe_android.model.Board;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Board board = new Board();
    Button[] allButton;
    TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        winner = findViewById(R.id.winnerText);
        allButton = getAllButton();
    }

    public void onCellClick(View v) {
        Button btn = (Button) v;
        btn.setText(board.player.toString());
        btn.setEnabled(false);

        // mark the cell
        String tag = btn.getTag().toString();
        int row = Integer.valueOf(tag.substring(0, 1));
        int col = Integer.valueOf(tag.substring(1, 2));
        board.markCell(row, col);

        // check if win
        if (board.isCurrentPlayerWin(row, col)) {
            winner.setText(board.player.toString() + " win!");
            disableBoard();
        } else if (board.isDraw()) { // check if draw
            winner.setText("Draw!");
            disableBoard();
        } else {
            board.changePlayer();
        }
    }


    private void disableBoard () {
        for (int i = 0; i < 9; i++) {
            allButton[i].setEnabled(false);
        }
    }

    public void resetGame(View v) {
        for (int i = 0; i < 9; i++) {
            allButton[i].setText("");
            allButton[i].setEnabled(true);
        }

        board.reset();

        winner.setText("");
    }

    private Button[] getAllButton () {
        Button[] buttons = new Button[9];
        for (int i = 0; i < 9; i++) {
            int id = getResources().getIdentifier("btn"+i, "id", getPackageName());
            buttons[i] = (Button) findViewById(id);
        }
        return buttons;
    }
}
