package com.example.healthyhabits;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import static com.example.healthyhabits.LoginActivity.PREFERENCE_FILENAME;
import static com.example.healthyhabits.FormularioRutinasActivity.vez;


public class PulseActivity extends AppCompatActivity implements SensorEventListener{
    private static final int BODY_SENSOR_PERMISSION_REQUEST_CODE = 0;
    public TextView testoLabel;
    private static final String TAG = "MyActivity";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse);

        testoLabel = (TextView) findViewById(R.id.testoLabel);


        ckeckPermissions();



    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case BODY_SENSOR_PERMISSION_REQUEST_CODE:
                //Si el permiso a sido

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    tomarPulso();
                } else {
                    // mensajeAccionCancelada();
                }
                break;
        }
    }


    public void ckeckPermissions(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int verificarPermisoReadContacts = ContextCompat
                    .checkSelfPermission(this, Manifest.permission.BODY_SENSORS);
            if(verificarPermisoReadContacts != PackageManager.PERMISSION_GRANTED){


                if(shouldShowRequestPermissionRationale(Manifest.permission.BODY_SENSORS)){
                    //Si a rechazado el permiso anteriormente muestro un mensaje
                    // mostrarExplicacion();
                }else{
                    //De lo contrario carga la ventana para autorizar el permiso
                    requestPermissions(new String[]{Manifest.permission.BODY_SENSORS}, BODY_SENSOR_PERMISSION_REQUEST_CODE);
                }

            }else {
                tomarPulso();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public void tomarPulso(){
        SensorManager sMgr;
        sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);

        Sensor battito = null;
        assert sMgr != null;
        battito = sMgr.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        if(battito != null && testoLabel!=null)
            testoLabel.setText("load sensor");
        else if (testoLabel!=null)
            testoLabel.setText("no load sensor");

        sMgr.registerListener(this, battito,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            String msg = " Value sensor: " + (int)event.values[0];


            int val;

            val =(int)event.values[0];


            testoLabel.setText(msg);



            if ( val >= 20 && val<=130 ) {

                val =(int)event.values[0];


                SharedPreferences gameSettings = getSharedPreferences(PREFERENCE_FILENAME, MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = gameSettings.edit();
                //valores agregados
                prefEditor.putString("pulso",String.valueOf(val));

                prefEditor.commit();

                Toast.makeText(this, "Se guardo el pulso del paciente", Toast.LENGTH_SHORT).show();

                vez++;

                this.finish();


            }


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        System.out.println("onAccuracyChanged - accuracy: " + accuracy);
    }
}
