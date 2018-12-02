package com.example.jasonhu.triviaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }
    /*
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
