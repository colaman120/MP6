package com.example.jasonhu.triviaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<QGroup> groups;
    private int pos;
    private int qListSize;
    private int cOption;
    private int selOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Construction Zone
    }

    protected void nextQ() {
        QGroup group = groups.get(pos);
        selOption = 0;
        //set Q, options,
        //resetlist
        int colour = 0;
        RadioButton myButton = (RadioButton) findViewById(R.id.one);
        myButton.setEnabled(true);
        myButton.setHighlightColor(colour);
        myButton = (RadioButton) findViewById(R.id.two);
        myButton.setEnabled(true);
        myButton.setHighlightColor(colour);
        myButton = (RadioButton) findViewById(R.id.three);
        myButton.setEnabled(true);
        myButton.setHighlightColor(colour);
        myButton = (RadioButton) findViewById(R.id.four);
        myButton.setEnabled(true);
        myButton.setHighlightColor(colour);
    }

    /**
     * if none selected, increment skip, call nextQ
     * if selected is correct, toast correct, color correct, increment correctcounter, change button
     *  to next
     * if incorrect, toast incorrect, color incorrect, increment counter, change button to skip
     * @param view
     */
    protected void onSubmit(View view){
//        //get selected
//        Toast myToast = Toast.makeText(this, "Correct!",
//                Toast.LENGTH_SHORT);
//        myToast.show();
    }

    /**
     * changes skip button to next button
     */
    protected void changeMyButton() {
        //id's the button
        Button myButton = findViewById(R.id.myButton);
        //set's the button's text to "Continue"
        myButton.setText(getString(R.string.nextMyButton));
    }

    /**
     * change button, set selOption to 1
     */
    protected void listClick1() {
        selOption = 1;
        changeMyButton();
    }
    /**
     * change button, set selOption to 2
     */
    protected void listClick2() {
        selOption = 2;
        changeMyButton();
    }
    /**
     * change button, set selOption to 3
     */
    protected void listClick3() {
        selOption = 3;
        changeMyButton();
    }
    /**
     * change button, set selOption to 4
     */
    protected void listClick4() {
        selOption = 4;
        changeMyButton();
    }

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
