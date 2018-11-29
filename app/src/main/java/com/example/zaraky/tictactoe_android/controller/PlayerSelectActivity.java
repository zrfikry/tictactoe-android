package com.example.zaraky.tictactoe_android.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zaraky.tictactoe_android.R;
import com.example.zaraky.tictactoe_android.controller.retrofit.RetrofitClientInstance;
import com.example.zaraky.tictactoe_android.controller.retrofit.User;
import com.example.zaraky.tictactoe_android.controller.retrofit.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerSelectActivity extends AppCompatActivity {

    public ArrayList<String> userList = new ArrayList<>();
    Spinner list1;
    Spinner list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);

        UserService service = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
        Call<List<User>> call = service.getAllUser();

        list1 = findViewById(R.id.selectPlayer1);
        list2 = findViewById(R.id.selectPlayer2);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                for (User user : response.body()) {
                    userList.add(user.getName());
                }
                initializePlayer();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(PlayerSelectActivity.this, "Try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initializePlayer() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, userList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list1.setAdapter(adapter);
        list2.setAdapter(adapter);
    }

    public void startGame(View v) {
        Intent intent = new Intent(PlayerSelectActivity.this, MainActivity.class);
        String player1 = list1.getSelectedItem().toString();
        String player2 = list2.getSelectedItem().toString();

        if (player1.equals(player2)) {
            Toast.makeText(this, "Player Name should be different", Toast.LENGTH_LONG).show();
        } else {
            intent.putExtra("player1", player1);
            intent.putExtra("player2", player2);
            startActivity(intent);
        }
    }
}
