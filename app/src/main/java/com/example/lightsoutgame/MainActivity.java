package com.example.lightsoutgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //array of button id and true or false of if they are clicked
    public Button[] buttonId = new Button[25];
    public boolean[] buttonColor = new boolean[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = findViewById(R.id.LightsOutBoard);

        //sets background color to blue at the start
        setBackgroundColor(0);

        //fill array with button ids
        buttonId[0] = findViewById(R.id.zero_zero);
        buttonId[1] = findViewById(R.id.one_zero);
        buttonId[2] = findViewById(R.id.two_zero);
        buttonId[3] = findViewById(R.id.three_zero);
        buttonId[4] = findViewById(R.id.four_zero);
        buttonId[5] = findViewById(R.id.zero_one);
        buttonId[6] = findViewById(R.id.one_one);
        buttonId[7] = findViewById(R.id.two_one);
        buttonId[8] = findViewById(R.id.three_one);
        buttonId[9] = findViewById(R.id.four_one);
        buttonId[10] = findViewById(R.id.zero_two);
        buttonId[11] = findViewById(R.id.one_two);
        buttonId[12] = findViewById(R.id.two_two);
        buttonId[13] = findViewById(R.id.three_two);
        buttonId[14] = findViewById(R.id.four_two);
        buttonId[15] = findViewById(R.id.zero_three);
        buttonId[16] = findViewById(R.id.one_three);
        buttonId[17] = findViewById(R.id.two_three);
        buttonId[18] = findViewById(R.id.three_three);
        buttonId[19] = findViewById(R.id.four_three);
        buttonId[20] = findViewById(R.id.zero_four);
        buttonId[21] = findViewById(R.id.one_four);
        buttonId[22] = findViewById(R.id.two_four);
        buttonId[23] = findViewById(R.id.three_four);
        buttonId[24] = findViewById(R.id.four_four);

        //gives the buttons random true or false
        setRandButtonClick();

        //new game button refreshes game
        Button newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //randomizes the color of each button
                setRandButtonClick();
                //resets the background color to blue
                setBackgroundColor(0);
            }
        });
        //set listener for each button
        for (int i = 0; i < 25; i++) {
            final int j = i;
            buttonId[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //change color of clicked button
                    buttonIsClicked(j);
                    //check if game was won
                    if (isGameWon() == true) {
                        setBackgroundColor(1);
                    }
                }
            });
        }
    }

    /*
         sets background color
         starts off as blue
         if game is won its changed to white
     */
    public void setBackgroundColor(int color){
        View viewy = this.getWindow().getDecorView();
        if(color == 0){
            viewy.setBackgroundColor(Color.parseColor("#FFADD8E6"));
        }
        else{
            viewy.setBackgroundColor(Color.WHITE);
        }
    }

    /*
        randomizes the color of the buttons
     */
    private void setRandButtonClick(){
        int i;
        for(i = 0; i < 25; i++){
            Random random;
            int rNum;
            random = new Random();
            rNum = random.nextInt(11); // sets rNum equal to a random number
            buttonColor[i] = rNum%2 == 0; // sets it equal to whether rNum%2 == 0 is true or false
        }
        for(i = 0; i < 25; i++){ // runs through the arrays
            if(buttonColor[i]){ //if the button is clicked is true
                buttonId[i].setBackgroundColor(Color.WHITE); //sets color to white
            }
            else{ //if buttons is clicked is false
                buttonId[i].setBackgroundColor(Color.BLACK); //sets color to black
            }
        }

    }

    /*
        checks if game was won
    */
    private boolean isGameWon(){
        for(int i = 0; i < 25; i++){
            if(buttonColor[i] == true){
                return false;
            }
        }
        return true;
    }

    /* finds the surrounding buttons of the clicked button */
    private void buttonIsClicked(int j){
        if(buttonColor[j] == true){
            buttonId[j].setBackgroundColor(Color.BLACK);
            buttonColor[j] = false;
        }
        else{
            buttonId[j].setBackgroundColor(Color.WHITE);
            buttonColor[j] = true;
        }

        //if button is on left side
        if(j == 5 || j == 10 || j == 15){
            findRight(j);
            findTop(j);
            findBottom(j);
        }
        // if button is at the top
        else if(j == 1 || j == 2 || j == 3){
            findBottom(j);
            findLeft(j);
            findRight(j);
        }
        //if button is on the bottom
        else if(j == 21 || j == 22 || j == 23){
            findTop(j);
            findLeft(j);
            findRight(j);
        }
        //if button is on the right
        else if(j == 9 || j == 14 || j == 19){
            findTop(j);
            findBottom(j);
            findLeft(j);
        }
        //if button is top right
        else if(j == 4){
            findLeft(j);
            findBottom(j);
        }
        //if button is top left
        else if(j == 0){
            findRight(j);
            findBottom(j);
        }
        //if button is bottom left
        else if(j == 20){
            findTop(j);
            findRight(j);
        }
        //if button is bottom right
        else if(j == 24){
            findTop(j);
            findLeft(j);
        }
        //if button is not on any other the sides or corners
        else{
            findTop(j);
            findLeft(j);
            findRight(j);
            findBottom(j);
        }
    }

    /* finds top button */
    private void findTop(int j){
        if(buttonColor[j] == true){
            buttonId[j-5].setBackgroundColor(Color.BLACK);
            buttonColor[j-5] = false;
        }
        else{
            buttonId[j-5].setBackgroundColor(Color.WHITE);
            buttonColor[j-5] = true;
        }
    }

    /* finds bottom button */
    private void findBottom(int j){
        if(buttonColor[j] == true){
            buttonId[j+5].setBackgroundColor(Color.BLACK);
            buttonColor[j+5] = false;
        }
        else{
            buttonId[j+5].setBackgroundColor(Color.WHITE);
            buttonColor[j+5] = true;
        }
    }

    /* finds left button */
    private void findLeft(int j){
        if(buttonColor[j] == true){
            buttonId[j-1].setBackgroundColor(Color.BLACK);
            buttonColor[j-1] = false;
        }
        else{
            buttonId[j-1].setBackgroundColor(Color.WHITE);
            buttonColor[j-1] = true;
        }
    }

    /* finds right button */
    private void findRight(int j){
        if(buttonColor[j] == true){
            buttonId[j+1].setBackgroundColor(Color.BLACK);
            buttonColor[j+1] = false;
        }
        else{
            buttonId[j+1].setBackgroundColor(Color.WHITE);
            buttonColor[j+1] = true;
        }
    }
}