package com.pkr.pushup;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mProximity;
    private static final int SENSOR_SENSITIVITY = 4;
    public TextView pushUpCountText;
    public int pushUpCount = 0;
    public LinearLayout mainLayout;
    boolean proximity=true, touch=false;
    Button proximityButton, touchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        proximityButton = (Button) findViewById(R.id.proximity);
        touchButton = (Button) findViewById(R.id.touch);

        pushUpCountText = (TextView) findViewById(R.id.push_up_count);
        pushUpCountText.setText(""+pushUpCount);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        proximityButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        proximityButton.setTextColor(getResources().getColor(R.color.colorPrimary));
        touchButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        touchButton.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (proximity == true) {
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
//                Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
                    pushUpCount++;
                    pushUpCountText.setText("" + pushUpCount);
//                mainLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
//            else
//                mainLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void reset(View view){
        pushUpCount = 0;
        pushUpCountText.setText(""+pushUpCount);
    }

    public void sProximityButton(View v){
        proximity = true;
        touch = false;
//        Toast.makeText(getApplicationContext(), "Proximity = "+ proximity +"\nTouch = "+ touch, Toast.LENGTH_SHORT).show();
        proximityButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        proximityButton.setTextColor(getResources().getColor(R.color.colorPrimary));
        touchButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        touchButton.setTextColor(getResources().getColor(R.color.colorAccent));
    }
    public void sTouchButton(View v){
        touch = true;
        proximity = false;
//        Toast.makeText(getApplicationContext(), "Proximity = "+ proximity +"\nTouch = "+ touch, Toast.LENGTH_SHORT).show();
        touchButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        touchButton.setTextColor(getResources().getColor(R.color.colorPrimary));
        proximityButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        proximityButton.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    public void touchFunc(View v){
        if (touch == true) {
            pushUpCount++;
            pushUpCountText.setText("" + pushUpCount);
        }
    }
}
