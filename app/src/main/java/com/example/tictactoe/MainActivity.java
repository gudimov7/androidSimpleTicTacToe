package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
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
            buttons[i].setTextSize(16);
            buttons[i].setId((i + 1) * 10000);
            buttons[i].setOnClickListener(this);
            gridLayout.addView(buttons[i]);

        }

    }

    private boolean isWinner() {
        /**
         * Win combinations:
         *
         * 0 == 1 == 2  |   0 == 3 == 6 |   0 == 4 == 8
         * 3 == 4 == 5  |   1 == 4 == 7 |   2 == 4 == 6
         * 6 == 7 == 8  |   2 == 5 == 8
         *
         */

        String [] btnStrings = new String [9];
        for(int i = 0; i < btnStrings.length; i ++) {
            btnStrings[i] = buttons[i].getText().toString();
        }
        if(btnStrings[0].equals(btnStrings[1]) && btnStrings[1].equals(btnStrings[2]) && !btnStrings[0].equals("")) {
            promptWinner(0, 1, 2);
            return true;
        }
        else if(btnStrings[3].equals(btnStrings[4]) && btnStrings[4].equals(btnStrings[5]) && !btnStrings[3].equals("")) {
            promptWinner(3, 4, 5);
            return true;
        }
        else if(btnStrings[6].equals(btnStrings[7]) && btnStrings[7].equals(btnStrings[8]) && !btnStrings[6].equals("")) {
            promptWinner(6, 7, 8);
            return true;
        }
        else if(btnStrings[0].equals(btnStrings[3]) && btnStrings[3].equals(btnStrings[6]) && !btnStrings[0].equals("")) {
            promptWinner(0, 3, 6);
            return true;
        }
        else if(btnStrings[1].equals(btnStrings[4]) && btnStrings[4].equals(btnStrings[7]) && !btnStrings[1].equals("")) {
            promptWinner(1, 4, 7);
            return true;
        }
        else if(btnStrings[2].equals(btnStrings[5]) && btnStrings[5].equals(btnStrings[8]) && !btnStrings[2].equals("")) {
            promptWinner(2, 5, 8);
            return true;
        }
        else if(btnStrings[0].equals(btnStrings[4]) && btnStrings[4].equals(btnStrings[8]) && !btnStrings[0].equals("")) {
            promptWinner(0, 4, 8);
            return true;
        }
        else if(btnStrings[2].equals(btnStrings[4]) && btnStrings[4].equals(btnStrings[6]) && !btnStrings[2].equals("")) {
            promptWinner(2, 4, 6);
            return true;
        }
        else return false;

    }
    private void promptWinner(int btn1, int btn2, int btn3) {
        buttons[btn1].setBackgroundColor(Color.RED);
        buttons[btn1].setTextColor(Color.WHITE);
        buttons[btn1].setTextSize(20);

        buttons[btn2].setBackgroundColor(Color.RED);
        buttons[btn2].setTextColor(Color.WHITE);
        buttons[btn2].setTextSize(20);

        buttons[btn3].setBackgroundColor(Color.RED);
        buttons[btn3].setTextColor(Color.WHITE);
        buttons[btn3].setTextSize(20);

        Toast.makeText(this, "And the Winner is :" + buttons[btn1].getText().toString(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        if (isWinner())
            return;
        for(Button b: buttons) {
            if(b.getId() == v.getId() && b.getText().toString().equals("")) {
                b.setText("X");
                turnsCount ++;
            }
        }
        if(turnsCount %2 == 1)
            playWithAI();
        isWinner();

    }
    public void playWithAI() {
        int rndBtn = 0;
        do {
            rndBtn = (int)(Math.random() * buttons.length);
        }while (!buttons[rndBtn].getText().toString().equals(""));
        buttons[rndBtn].setText("O");
        turnsCount ++;


    }
}
