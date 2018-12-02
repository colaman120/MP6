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
    private List<Integer> optionIDs;
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

        //initialize the list of option IDs
        optionIDs.add(R.id.zero);
        optionIDs.add(R.id.one);
        optionIDs.add(R.id.two);
        optionIDs.add(R.id.three);
    }

    protected void nextQ() {
        QGroup group = groups.get(pos);
        selOption = 0;
        //set Q, options,
        //resetlist
        int correctPos = (int) (Math.random()*4.0);
        int currentPos = 0;
        for ()
//        if (currentPos == correctPos) {
//            setButton(R.id.one, groups.get(pos).getCorrectOption(), 0);
//        } else {
//            setButton(R.id.one, groups.get(pos).getIncorrectOption(currentPos), 0);
//            currentPos++;
//        }
        pos++;
    }
    protected void setButton(int id, String text, int colour) {
//        RadioButton myButton = findViewById(id);
//        myButton.setText(text);
//        myButton.setEnabled(true);
//        myButton.setHighlightColor(colour);
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
