package com.example.sun.test_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final TextView average_input = (TextView) findViewById(R.id.average_input);
        final TextView classaverage_input = (TextView) findViewById(R.id.classaverage_input);
        final TextView standard_deviation_input = (TextView) findViewById(R.id.standard_deviation_input);
        final TextView ifg_input = (TextView) findViewById(R.id.ifg_input);
        final TextView coter_input = (TextView) findViewById(R.id.coter_input);
        TextView submit = (TextView) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                float average = getFloat(average_input);
                float classaverage = getFloat(classaverage_input);
                float standard_deviation = getFloat(standard_deviation_input);
                float ifg = getFloat(ifg_input);

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

    private float getFloat(TextView input) {
        float number = (float) 1.0;
        if (input.getText().toString().equals("")) {}
        else number = Float.valueOf(input.getText().toString());
        return number;
    }
}

