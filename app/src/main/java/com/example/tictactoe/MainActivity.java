package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button [] buttons;
    private GridLayout gridLayout;
    private static int turnsCount = 0;

    //mission 1 - connect all the buttons and write X on them
    //mission 2 - write X/O each turn
    //mission 3 - check who is the winner and notify the user with Toast

    //mission 4 (Bonus) - play against the computer

    //Oncreate is the first function that starts the activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.grid_layout);
        buttons = new Button[9];

        for(int i = 0; i < buttons.length; i ++) {

            buttons[i] = new Button(this);
            buttons[i].setWidth(50);
            buttons[i].setHeight(50);
            buttons[i].setId((i + 1) * 10000);
            buttons[i].setOnClickListener(this);
            gridLayout.addView(buttons[i]);

        }

    }

    private void winner() {
        if(buttons[0].getText().toString().equals("X"))
            Toast.makeText(this,"Winner",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        for(Button b: buttons) {
            if(b.getId() == v.getId())
                b.setText(turnsCount % 2 == 0 ? "X": "O");
        }
        turnsCount ++;

    }
}
