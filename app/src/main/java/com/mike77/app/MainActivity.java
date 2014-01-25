package com.mike77.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private ActionBar actionBar;
    private int speed;
    private EditText minutes;
    private EditText weight;
    private int calories;
    private double calories_burned;
    private Context context = this;
    private Button slow;
    private Button medium;
    private Button fast;
    private Button running;
    private Button swimming;
    private Button biking;
    private Button calculation;
    private Boolean speed_flag;
    private Boolean activity_flag;
    private int alpha = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        initialize();


    }

    private void initialize(){
        actionBar = getSupportActionBar();
        actionBar.hide();
        minutes = (EditText)findViewById(R.id.txt_minutes);
        minutes.setGravity(Gravity.CENTER_HORIZONTAL);
        weight = (EditText) findViewById(R.id.txt_pounds);
        weight.setGravity(Gravity.CENTER_HORIZONTAL);

        slow =  (Button)findViewById( R.id.btn_slow );
        medium = (Button)findViewById(R.id.btn_medium);
        fast = (Button)findViewById(R.id.btn_fast);

        running = (Button) findViewById(R.id.btn_run);
        biking = (Button) findViewById(R.id.btn_bike);
        swimming = (Button) findViewById(R.id.btn_swim);

        calculation = (Button) findViewById(R.id.btn_calculate);

        calculation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int id = view.getId();
                int temp = alpha;
                int action = motionEvent.getAction();
                switch(action){

                    case MotionEvent.ACTION_DOWN:
                        calculation.getBackground().setAlpha(temp / 2);
                        break;

                    case MotionEvent.ACTION_UP:
                        calculation.getBackground().setAlpha(alpha);
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    public void calculateWeightLoss(View view) {
        //Slow: calories burned = (weight * 177)/130; per hour
        String str_weight = weight.getText().toString();
        String str_minutes = minutes.getText().toString();

        if(str_weight.trim().equals("") || str_minutes.trim().equals(""))
        {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Please enter both your weight and the time.", Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 90); //xOffset, yOffset);
            toast1.show();
        }
        else
        {
            double w = Double.valueOf(str_weight);
            int m = Integer.valueOf(str_minutes);

            calories_burned = (w * calories) / 130;
            calories_burned = calories_burned / 60 * m;
            calories_burned = (float)Math.round(calories_burned);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("You burned " + calories_burned + " total calories!");
            builder.setPositiveButton("ok",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    public void getSpeed(View view) {
        int temp = alpha;
        slow.getBackground().setAlpha(alpha);
        medium.getBackground().setAlpha(alpha);
        fast.getBackground().setAlpha(alpha);
        switch (view.getId())
        {
            case R.id.btn_slow:
                slow.getBackground().setAlpha(temp / 2);
                speed = 1;
                break;
            case R.id.btn_medium:
                medium.getBackground().setAlpha(temp/2);
                speed = 2;
                break;
            case R.id.btn_fast:
                fast.getBackground().setAlpha(temp/2);
                speed = 3;
                break;
        }

    }
    public void getCalories(View view) {

        int temp = alpha;
        running.getBackground().setAlpha(alpha);
        swimming.getBackground().setAlpha(alpha);
        biking.getBackground().setAlpha(alpha);

        switch (view.getId())
        {
            case R.id.btn_run:
                running.getBackground().setAlpha(temp/2);
                if(speed == 1)
                {
                    calories = 472;
                }
                else if(speed == 2)
                {
                    calories = 590;

                }
                else
                {
                    calories = 738;

                }
                break;

            case R.id.btn_swim:
                swimming.getBackground().setAlpha(temp/2);
                if(speed == 1)
                {
                    calories = 354;
                }
                else if(speed == 2)
                {
                    calories = 413;
                }
                else
                {
                    calories = 590;
                }
                break;

            case R.id.btn_bike:
                biking.getBackground().setAlpha(temp/2);
                if(speed == 1)
                {
                    calories = 354;
                }
                else if(speed == 2)
                {
                    calories = 472;
                }
                else
                {
                    calories = 590;
                }
                break;
        }

    }

}
