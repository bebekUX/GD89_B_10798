package com.gd.gd8_proximity_b_10798

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var sensorStatusTV : TextView
    lateinit var proximitySensor : Sensor
    lateinit var sensorManager: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // on below line we are initializing our all variables.
        var proximitySensorEventListener : SensorEventListener? = object : SensorEventListener{
            override fun onAccuracyChanged(sensor: Sensor, Accuracy: Int) {

            }
            override fun onSensorChanged(event: SensorEvent) {
                if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                    if(currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK){
                        currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
                    }
                    else {
                        currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
                    }

                }
            }
        }
        sensorStatusTV = findViewById(R.id.idTVSensorStatus)
        // on below line we are initializing our sensor manager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // on below line we are initializing our proximity sensor variable
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        // on below line we are checking if the proximity sensor is null
        if (proximitySensor == null) {
            // on below line we are displaying a toast if no sensor is available
            Toast.makeText(this, "No proximity sensor found in device..", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            // on below line we are registering
            // our sensor with sensor manager
            sensorManager.registerListener(
                proximitySensorEventListener,
                proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

    }
}