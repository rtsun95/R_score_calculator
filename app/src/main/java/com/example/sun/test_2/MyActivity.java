package com.example.sun.test_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final TextView average_input = getTextView(R.id.average_input);
        final TextView classaverage_input = getTextView(R.id.classaverage_input);
        final TextView standard_deviation_input = getTextView(R.id.standard_deviation_input);
        final TextView ifg_input = getTextView(R.id.ifg_input);
        final TextView coter_input = getTextView(R.id.coter_input);
        TextView submit = (TextView) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                float average = checkError(average_input);
                float classaverage = checkError(classaverage_input);
                float standard_deviation = checkError(standard_deviation_input);
                float ifg = checkError(ifg_input);
                if (average >= 0.0 && classaverage >= 0.0 && standard_deviation >= 0.0 && ifg>=0.0){
                    if(Math.abs(standard_deviation)<0.001){
                        standard_deviation_input.setText("");
                        ((EditText) standard_deviation_input).setHint("L'écart-type ne peut être 0");
                    }
                    else {
                        float cotez = (average - classaverage) / standard_deviation;
                        float modification = (ifg - (float) 75.0) / (float) 14.0;
                        float result = (cotez + modification + 5) * 5;
                        if (result > 50) {
                            coter_input.setText("Votre Cote-R est de 50");
                        } else if (result < 0) {
                            coter_input.setText("Votre Cote-R est de 0");
                        } else {
                            String cote = Float.toString(result);
                            coter_input.setText("Votre Cote-R est de " + cote);
                        }
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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



    // Private function
    private float getFloat(TextView input) {
        float number = (float) -1.0;
        if (input.getText().toString().equals("")) {}
        else number = Float.valueOf(input.getText().toString());
        return number;
    }

    private boolean checkValue(float n){
        if (n > 100.0 || n < 0.0) return true;
        else return false;
    }

    private float checkError(final TextView input){
        float number = getFloat(input);
        if(checkValue(number)) {
            input.setTextSize(16);
            ((EditText) input).setHint("Veuillez enter un nombre entre 0 et 100");
            return (float) -1.0;
        }
        else return number;
    }

    private TextView getTextView(int n) {
        TextView input = (TextView) findViewById(n);
        return input;
    }
}
