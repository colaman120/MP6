package com.example.jasonhu.triviaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;
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
     *
     */
    private int selectedOptionPos;
    /**
     * Request queue for volley requests
     */
    private RequestQueue queue;
    /**
     * URL of request
     */
    private String url;
    /**
     * Tag for logging
     */
    private static final String TAG = "MP6:Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.neolayout);

        //Construction Zone Below
        queue = Volley.newRequestQueue(this);
        url = MainMenu.getUrl();
        optionIDs = new ArrayList<>();
        optionIDs.add(R.id.zero);
        optionIDs.add(R.id.one);
        optionIDs.add(R.id.two);
        optionIDs.add(R.id.three);
//        TextView cat = findViewById(R.id.CatValTextView);
//        cat.setText(url);
        //initialize the list of menu option IDs
        apiCall();
//        TextView diff = findViewById(R.id.DifValTextView);
//        diff.setText(currentCorrectOption + "success");
        //setup question
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
//            incrementCounter(R.id.SCounterTextView);
            //move onto next question
            apiCall();
        } else {
            int currentID = optionIDs.get(selectedOptionPos);
            RadioButton selectedButton = findViewById(optionIDs.get(selectedOptionPos));
            if (selectedButton.getTag().equals(R.string.label_correct)) {
                Toast myToast = Toast.makeText(this, R.string.label_correct,
                        Toast.LENGTH_SHORT);
                myToast.show();
                for (int optionID : optionIDs) {
                    RadioButton everyButton = findViewById(optionID);
                    if (optionID == currentID) {
                        everyButton.setHighlightColor(getResources().getColor(R.color.colorGreen));
                    }
                    everyButton.setEnabled(false);
                }
                //id's submit button
                Button myButton = findViewById(R.id.myButton);
                //set's the button's text to "Continue"
                myButton.setText(getString(R.string.label_continue_button));
                //increment counter
//                incrementCounter(R.id.CCounterValTextView);
            } else {
                Toast myToast = Toast.makeText(this, R.string.label_incorrect,
                        Toast.LENGTH_SHORT);
                myToast.show();
                RadioButton radioButton = findViewById((optionIDs.get(selectedOptionPos)));
                radioButton.setHighlightColor(getResources().getColor(R.color.colorRed));
                Button myButton = findViewById(R.id.myButton);
                myButton.setText(R.string.label_skip_button);
//                incrementCounter(R.id.IValCounterTextView);
            }
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
        final Toast myToast = Toast.makeText(this, "VOLLEY_ERROR",
                Toast.LENGTH_SHORT);
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
//                                TextView cat = findViewById(R.id.CatValTextView);
//                                cat.setText(response.toString() + "PLACE1");
                                JSONObject q = (response.getJSONArray("results")).getJSONObject(0);
                                String question = q.getString("question");
                                TextView qBody = findViewById(R.id.QLabelTextView);
                                qBody.setText(question);
//                                cat.setText(question.getString("question") + "PLACE2");
//                                cat.setText(currentQuestion + "PLACE3");
                                String correctOption = q.getString("correct_answer");
                                int correctOptionId = optionIDs.get(0);
                                RadioButton cOption = findViewById(correctOptionId);
                                cOption.setText(correctOption);
                                cOption.setTag(R.string.label_correct);
//                                cat.setText(currentCorrectOption + "PLACE4");
                                JSONArray incorrectAnswers = q.getJSONArray("incorrect_answers");
                                RadioButton iOption;
                                int incorrectAnswersPos = 0;
                                for (int optionID : optionIDs) {
                                    if (optionID != correctOptionId) {
                                        iOption = findViewById(optionID);
                                        if (incorrectAnswersPos < incorrectAnswers.length()) {
                                            iOption.setText(incorrectAnswers.get(incorrectAnswersPos).toString());
                                            incorrectAnswersPos++;
                                        } else {
                                            iOption.setText(R.string.label_placeholder);
                                        }
                                        cOption.setTag(R.string.label_incorrect);
                                    }
                                }
//                                Log.d(TAG, ((Integer) incorrectAnswersLength).toString());
                            } catch (JSONException e) {
                                Log.e(TAG, e.toString());
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                    error.printStackTrace();
                    myToast.show();
                }
            });
            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
//        TextView cat = findViewById(R.id.CatValTextView);
//        cat.setText(currentCorrectOption + "PLACE5");
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
