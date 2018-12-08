package com.example.jasonhu.triviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
//import android.widget.ArrayAdapter;
import android.widget.Spinner;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//
//import org.json.JSONArray;
//import org.json.JSONObject;

public class MainMenu extends AppCompatActivity {

    private Spinner category;
    private Spinner difficulty;
    private static String url = "https://opentdb.com/api.php?amount=10";

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
        urlSetUp();
        startActivity(new Intent(MainMenu.this, MainActivity.class));
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

    public void urlSetUp() {
        category = findViewById(R.id.category);
        difficulty = findViewById(R.id.difficulty);
        int catNum = getCatNumAPICall(category.getSelectedItem().toString());
        String diff = difficulty.getSelectedItem().toString();
        //JSON request set up
        if (catNum != 0) {
            url += "&category=" + catNum;
        }
        if (diff.length() > 0) {
            url += "&difficulty=" + diff;
        }
    }

    public static String getUrl() {
        return url;
    }
}

