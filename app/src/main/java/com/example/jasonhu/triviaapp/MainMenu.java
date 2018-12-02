package com.example.jasonhu.triviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainMenu extends AppCompatActivity {

    Spinner category;
    Spinner difficulty;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);


    }

    public void openTrivia(View v){
        //Starts the set up for the url
        category = findViewById(R.id.category);
        difficulty = findViewById(R.id.difficulty);
        int catNum = getCatNum(category.getSelectedItem().toString());
        String diff = difficulty.getSelectedItem().toString();

        //JSON request set up
        startActivity(new Intent(MainMenu.this, MainActivity.class));
    }

    public int getCatNum(String s) {
        if (s.equals("Any Category")) {
            return 0;
        } else if (s.equals("General Knowledge")) {
            return 9;
        } else if (s.equals("Entertainment: Books")) {
            return 10;
        } else if (s.equals("Entertainment: Film")) {
            return 11;
        } else if (s.equals("Entertainment: Music")) {
            return 12;
        } else if (s.equals("Entertainment: Musicals and Theatres")) {
            return 13;
        } else if (s.equals("Entertainment: Television")) {
            return 14;
        } else if (s.equals("Entertainment: Video Games")) {
            return 15;
        } else if (s.equals("Entertainment: Board Games")) {
            return 16;
        } else if (s.equals("Science and Nature")) {
            return 17;
        } else if (s.equals("Science: Computers")) {
            return 18;
        } else if (s.equals("Science: Mathematics")) {
            return 19;
        } else if (s.equals("Mythology")) {
            return 20;
        } else if (s.equals("Sports")) {
            return 21;
        } else if (s.equals("Geography")) {
            return 22;
        } else if (s.equals("History")) {
            return 23;
        } else if (s.equals("Politics")) {
            return 24;
        } else if (s.equals("Art")) {
            return 25;
        } else if (s.equals("Celebrities")) {
            return 26;
        } else if (s.equals("Animals")) {
            return 27;
        } else if (s.equals("Vehicles")) {
            return 28;
        } else if (s.equals("Entertainment: Comics")) {
            return 29;
        } else if (s.equals("Science: Gadgets")) {
            return 30;
        } else if (s.equals("Entertainment: Anime and Manga")) {
            return 31;
        } else if (s.equals("Entertainment: Cartoon and Animation")) {
            return 32;
        }
        return 0;
    }
}