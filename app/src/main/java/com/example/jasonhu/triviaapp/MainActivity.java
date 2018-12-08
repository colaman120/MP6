package com.example.jasonhu.triviaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * List of gui menu option ids
     */
    private List<Integer> optionIDs;
    /**
     * current question
     */
    private String currentQuestion;
    /**
     * current Correct Option
     */
    private String currentCorrectOption;
    /**
     * current inorrect Option
     */
    private String[] currentIncorrectOptions;
    /**
     * The position of the correct option in the gui menu
     */
    private int correctOptionPos;
    /**
     * The position of the currently selected option in the gui menu
     */
    private int selectedOptionPos = -1;
    /**
     * Request queue for volley requests
     */
    private RequestQueue queue = Volley.newRequestQueue(this);
    /**
     * URL of request
     */
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.neolayout);

        //Construction Zone Below
        url = MainMenu.getUrl();
        //initialize the list of menu option IDs
        optionIDs = new ArrayList<>();
        optionIDs.add(R.id.zero);
        optionIDs.add(R.id.one);
        optionIDs.add(R.id.two);
        optionIDs.add(R.id.three);
        //setup question
        nextQ();
    }

    protected void nextQ() {
        //get new question
        apiCall();
        //Set the question body
        TextView qBody = findViewById(R.id.QBodyTextView);
        qBody.setText(currentQuestion);
        //randomly set the position of the correct option in the menu list
        correctOptionPos = (int) (Math.random()*4.0);
        //initialize the current position of the menu list to iterate and initialize
        int currentMenuPos = 0;
        //initialize the current position of the incorrect options list
        int incorrectOptionPos = 0;
        //for every option in optionIDs:
        for (int optionID : optionIDs) {
            RadioButton myButton = findViewById(optionID);
            if (currentMenuPos == correctOptionPos) {
                myButton.setText(currentCorrectOption);
            } else {
                myButton.setText(currentIncorrectOptions[incorrectOptionPos]);
                incorrectOptionPos++;
            }
            myButton.setEnabled(true);
            myButton.setHighlightColor(0);
            currentMenuPos++;
        }
        //reset selected gui option
        selectedOptionPos = -1;
    }
    /**
     * if none selected, increment skip, call nextQ
     * if selected is correct, toast correct, color correct, increment correctcounter, change button
     *  to next
     * if incorrect, toast incorrect, color incorrect, increment counter, change button to skip
     * @param view the current view
     */
    public void onSubmit(View view){
        //if no selected list item
        if (selectedOptionPos == -1) {
            //increment skip counter
            incrementCounter(R.id.SCounterTextView);
            //move onto next question
            nextQ();
        } else if (selectedOptionPos == correctOptionPos) {
            Toast myToast = Toast.makeText(this, R.string.label_correct,
                    Toast.LENGTH_SHORT);
            myToast.show();
            int currentMenuPos = 0;
            for (int optionID : optionIDs) {
                RadioButton radioButton = findViewById(optionID);
                if (currentMenuPos == correctOptionPos) {
                    radioButton.setHighlightColor(getResources().getColor(R.color.colorGreen));
                }
                radioButton.setEnabled(false);
                currentMenuPos++;
            }
            //id's submit button
            Button myButton = findViewById(R.id.myButton);
            //set's the button's text to "Continue"
            myButton.setText(getString(R.string.label_continue_button));
            incrementCounter(R.id.CCounterValTextView);
        } else {
            Toast myToast = Toast.makeText(this, R.string.label_incorrect,
                    Toast.LENGTH_SHORT);
            myToast.show();
            RadioButton radioButton = findViewById((optionIDs.get(selectedOptionPos)));
            radioButton.setHighlightColor(getResources().getColor(R.color.colorRed));
            Button myButton = findViewById(R.id.myButton);
            myButton.setText(R.string.label_skip_button);
            incrementCounter(R.id.IValCounterTextView);
        }
    }

    /**
     * changes skip button to submit
     */
    protected void changeMyButton() {
        //id's the button
        Button myButton = findViewById(R.id.myButton);
        //set's the button's text to "Continue"
        myButton.setText(getString(R.string.label_submit_button));
    }

    /**
     * change button, set selOption to 0
     */
    public void listClick0(View view) {
        selectedOptionPos = 0;
        changeMyButton();
    }
    /**
     * change button, set selOption to 1
     */
    public void listClick1(View view) {
        selectedOptionPos = 1;
        changeMyButton();
    }
    /**
     * change button, set selOption to 2
     */
    public void listClick2(View view) {
        selectedOptionPos = 2;
        changeMyButton();
    }
    /**
     * change button, set selOption to 3
     */
    public void listClick3(View view) {
        selectedOptionPos = 3;
        changeMyButton();
    }

    /**
     * change the specified counter
     */
    public void incrementCounter(int id) {
        // Get the text view.
        TextView counterView = findViewById(id);
        // Convert value to a number and increment it.
        Integer count = Integer.parseInt(counterView.getText().toString());
        count++;
        // Display the new value in the text view.
        String back = count.toString();
        counterView.setText(back);
    }

    public void apiCall() {
        try {
            //Create new request with get, url of url,
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                JSONObject question = response.getJSONArray("results").getJSONObject(0);
                                currentQuestion = question.getString("question");
                                currentCorrectOption = question.getString("correct_answer");
                                JSONArray incorrectAnswers = question.getJSONArray("incorrect_answers");
                                int incorrectAnswersLength = incorrectAnswers.length();
                                currentIncorrectOptions = new String[incorrectAnswersLength];
                                for (int i = 0; i < incorrectAnswersLength; i++) {
                                    currentIncorrectOptions[i] = incorrectAnswers.get(i).toString();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    error.printStackTrace();
                }
            });
            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    TODO: implement api caller & parser
     */

    /*
     * General idea:
     * Instance var list of QGroups
     * instance int pos
     * Instance int qlistsize
     * set counters to 0
     * Function to make api call, parse returned string into array/list (whatever included method
     *  does) create qgroupobjects then add to instance list
     * QGroupObject: List of wrongq strings, one rightq string
     * nextQ: if pos == qlistsize - 1, call api again to add; gen random value between 0 and size of
     *  wrongqs to set as cpos, set each list element, increment cpos
     * OnSubmit: if sel is correct, disable list, set correct as green, display correct
     *  notification, change skip to next; else disable sel item & set red, display incor not.incre.
     * OnSkip: increment skipped, call nextQ;
     *
     */
}
