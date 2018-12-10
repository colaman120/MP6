package com.example.jasonhu.triviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    /*
     * URL to be assembled and passed to main activity. Initialized to base with only 1 question
     */
    private static String url = "https://opentdb.com/api.php?amount=1";
    private static String cat;
    private static String dif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);
    }

    /**
     * method that is called when button is clicked
     * @param v ?
     */
    public void openTrivia(View v){
//        urlSetUp();
//        startActivity(new Intent(MainMenu.this, MainActivity.class));
        if (urlSetUp()) {
            startActivity(new Intent(MainMenu.this, MainActivity.class));
        }
        else {
            Toast myToast = Toast.makeText(this, "ERROR",
                    Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

    /**
     * determines the number to put in the api call based on category
     * @param s the category selected
     * @return the number associated with the given category
     */
    public int getCatNumAPICall(String s) {
        switch (s) {
            case "Any Category":
                return 0;
            case "General Knowledge":
                return 9;
            case "Entertainment: Books":
                return 10;
            case "Entertainment: Film":
                return 11;
            case "Entertainment: Music":
                return 12;
            case "Entertainment: Musicals and Theatres":
                return 13;
            case "Entertainment: Television":
                return 14;
            case "Entertainment: Video Games":
                return 15;
            case "Entertainment: Board Games":
                return 16;
            case "Science and Nature":
                return 17;
            case "Science: Computers":
                return 18;
            case "Science: Mathematics":
                return 19;
            case "Mythology":
                return 20;
            case "Sports":
                return 21;
            case "Geography":
                return 22;
            case "History":
                return 23;
            case "Politics":
                return 24;
            case "Art":
                return 25;
            case "Celebrities":
                return 26;
            case "Animals":
                return 27;
            case "Vehicles":
                return 28;
            case "Entertainment: Comics":
                return 29;
            case "Science: Gadgets":
                return 30;
            case "Entertainment: Anime and Manga":
                return 31;
            case "Entertainment: Cartoon and Animation":
                return 32;
            default:
                return 0;
        }
    }

    public boolean urlSetUp() {
        Spinner category = findViewById(R.id.category);
        Spinner difficulty = findViewById(R.id.difficulty);
        cat = category.getSelectedItem().toString();
        dif = difficulty.getSelectedItem().toString();
        int catNum = getCatNumAPICall(cat);
        String diff = dif.toLowerCase();
        //JSON request set up
        //fyi if the catNum == 0 then it should still work. catNum == 0 gives the any category.
        // It just doesn't need to be included in the url
        //also, if diff == any difficulty, it shouldn't add to the url
        if (diff.length() < 1) {
            return false;
        } else  {
            if (!(diff.equals("any difficulty"))) {
                url += "&difficulty=" + diff;
            }
            if (catNum == 0) {
                return true;
            } else {
                url += "&category=" + catNum;
            }
            return true;
        }
    }

    public static String getUrl() {
        return url;
    }

    public static String getCategory() {
        return cat;
    }

    public static String getDifficulty() {
        return dif;
    }
}

