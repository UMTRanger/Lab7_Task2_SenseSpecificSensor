package com.example.lab7_task2_sensespecificsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager senseMan;
    Sensor lightSensor, humiditySensor, proximitySensor;
    TextView lighttext, humiditytext, proximitytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mapping textview to TextView resources
        lighttext = (TextView) findViewById(R.id.lighttext);
        humiditytext = (TextView) findViewById(R.id.humiditytext);
        proximitytext = (TextView) findViewById(R.id.proximitytext);

        //Mapping sensor manager to system service
        senseMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Mapping sensor to to their sensors
        lightSensor = senseMan.getDefaultSensor(Sensor.TYPE_LIGHT);
        humiditySensor = senseMan.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        proximitySensor = senseMan.getDefaultSensor(Sensor.TYPE_PROXIMITY);


        //Register listener for sensors
        //Determine if sensors are in the system
        if (lightSensor != null){
            Toast.makeText(this, "Light Sensor Found", Toast.LENGTH_LONG).show();
            senseMan.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Toast.makeText(this, "Light Sensor NOT Found", Toast.LENGTH_LONG).show();
        }

        if (humiditySensor != null){
            Toast.makeText(this, "Temperature Sensor Found", Toast.LENGTH_LONG).show();
            senseMan.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Toast.makeText(this, "Temperature Sensor NOT Found", Toast.LENGTH_LONG).show();
        }

        if (proximitySensor != null){
            Toast.makeText(this, "Proximity Sensor Found", Toast.LENGTH_LONG).show();
            senseMan.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Toast.makeText(this, "Proximity Sensor NOT Found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Check the sensor type and update the corresponding TextView
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lighttext.setText("Light: " + Float.toString(event.values[0]));

        } else if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            humiditytext.setText("Relative Humidity: " + Float.toString(event.values[0]));

        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximitytext.setText("Proximity: " + Float.toString(event.values[0]));
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        senseMan.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        senseMan.unregisterListener(this);
    }
}