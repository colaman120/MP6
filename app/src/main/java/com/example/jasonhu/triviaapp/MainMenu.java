package com.example.jasonhu.triviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainMenu extends AppCompatActivity {

    private Spinner category;
    private Spinner difficulty;
    private String url = "https://opentdb.com/api.php?amount=10";

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

    public String getUrl() {
        return url;
    }
}

