package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * Simple tic tac toe game
     * for android devices
     * TODO: * home work:   - connect all the buttons and write X on them
     *                      - write X/O each turn
     *                      - check who is the winner and notify the user with Toast
     *                      - (Bonus) - play against the computer
     *       * future work  - add an reset button if there is a winner, or out of turns
     */

    private Button [] buttons;          // Global variable for playing buttons
    private GridLayout gridLayout;      // Global variable for game layout
    private static int turnsCount = 0;  // Global static variable for turns count


    //Oncreate is the first function that starts the activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.grid_layout);    //get layout variable from View
        buttons = new Button[9];                        //create 9 new buttons array

        for(int i = 0; i < buttons.length; i ++) {      // loop on buttons array to create new buttons and add them to the layout

            buttons[i] = new Button(this);      //create new button
            buttons[i].setWidth(50);                //set button width to 50px
            buttons[i].setHeight(50);               //set button height to 50px
            buttons[i].setTextSize(16);             //set font size on 16px
            buttons[i].setId((i + 1) * 10000);      //set button id with loop index * 10000
            buttons[i].setOnClickListener(this);    //add button on click listener
            gridLayout.addView(buttons[i]);         //add button to layout

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
         * return boolean true value if there is a winner, return false if there isn't
         */

        String [] btnStrings = new String [9];          //create new strings array to store buttons values
        for(int i = 0; i < btnStrings.length; i ++) {   //loop over btnStrings array and set values from view
            btnStrings[i] = buttons[i].getText().toString();
        }
        /* check all winner combinations every turn, */
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
        /**
         * Prompt A winner if there is one
         * by changing background color to RED
         * and font color to White
         * additionally prompt with Toast message on the screen
         */
        buttons[btn1].setBackgroundColor(Color.RED);    //set btn background color RED
        buttons[btn1].setTextColor(Color.WHITE);        //set btn font Color White

        buttons[btn2].setBackgroundColor(Color.RED);    //set btn background color RED
        buttons[btn2].setTextColor(Color.WHITE);        //set btn font Color White

        buttons[btn3].setBackgroundColor(Color.RED);    //set btn background color RED
        buttons[btn3].setTextColor(Color.WHITE);        //set btn font Color White
        /*Toast message of the winner*/
        Toast.makeText(this, "And the Winner is :" + buttons[btn1].getText().toString(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        /**
         * function that runs every time button clicked from listener
         * check if there is a winner or turns maxed out
         * every time player's play, initiate AI player
         */
        if (isWinner() || turnsCount == 9)             // do nothing if there is a winner already or turns maxed out
            return;
        for(Button b: buttons) {    //loop over buttons and match with button that initiated click listener
            if(b.getId() == v.getId() && b.getText().toString().equals("")) {   //if button match and its empty
                b.setText("X");                                                 //set button text with player's 'X'
                turnsCount ++;                                                  //increase turns count by one
            }
        }
        if(turnsCount %2 == 1)                                                  //run AI method every second turn
            playWithAI();                                                       //call AI method
        isWinner();                                                             //check if there is a winner

    }
    public void playWithAI() {
        /**
         * randomize turns of AI
         * every time AI plays generate new random btn
         * check if its empty
         * and set text to AI's 'O'
         */
        int rndBtn = 0;                                             //initiate new variable for random btn place
        do {
            rndBtn = (int)(Math.random() * buttons.length);
        }while (!buttons[rndBtn].getText().toString().equals(""));  //generate new numbers with buttons length till it matches an empty button
        buttons[rndBtn].setText("O");                               //set btn text with AI's 'O'
        turnsCount ++;                                              //increase turns count by one


    }
}
