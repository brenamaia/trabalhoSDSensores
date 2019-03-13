package com.example.pasid.trabalhosdsensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static Socket socket;
    //private static ServerSocket ssocket;
    private static InputStreamReader inputSR;
    private static BufferedReader br;
    private static PrintWriter printWriter;

    Sensor acelerometro;
    SensorManager sensorManager;
    float sensorX;
    float sensorY;
    float sensorZ;
    float sensorA;

    boolean power;

    String dlog="", esp="\n";

    String message = "Deu certo!";
    private String ip = "192.168.0.104";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void send_text(View v){
        myTask task = new myTask();
        task.execute();

        Toast.makeText(getApplicationContext(), "Dados Enviados", Toast.LENGTH_LONG).show();
    }

    public void iniciaColeta(View v){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        power=true;
        Toast.makeText(getApplicationContext(), "Coleta Iniciada", Toast.LENGTH_LONG).show();
    }

    public void finalizaColeta (View v){
        power = false;
        Toast.makeText(getApplicationContext(), "Coleta Finalizada", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(power==true){
            sensorX = event.values[0];
            sensorY = event.values[1];
            sensorZ = event.values[2];
            sensorA = (event.values[0] + event.values[1] + event.values[2]);
            dlog = (dlog + "X= " + sensorX + "; Y=" + sensorY + "; Z= " + sensorZ + ";" + "|");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class myTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                socket = new Socket(ip, 5000);
                printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.write(dlog);

                printWriter.flush();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
