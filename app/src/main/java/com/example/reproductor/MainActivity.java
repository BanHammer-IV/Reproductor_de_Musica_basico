package com.example.reproductor;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button play_pause, btn_repetir;
    MediaPlayer mp;
    ImageView iv;
    int repetir = 2, posicion = 0;

    MediaPlayer vectormp[] = new MediaPlayer[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_pause = (Button)findViewById(R.id.btn_play);
        btn_repetir = (Button)findViewById(R.id.btn_repeat);
        iv = (ImageView)findViewById(R.id.imageView);

        vectormp[0] = MediaPlayer.create(this, R.raw.race);
        vectormp[1] = MediaPlayer.create(this, R.raw.sound);
        vectormp[2] = MediaPlayer.create(this, R.raw.tea);

    }

    //Metodo para el boton de PlayPause
    public void PlayPause(View view)
    {
        if(vectormp[posicion].isPlaying())
        {
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }
        else
        {
            vectormp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para el boton de Stop
    public void Stop(View view)
    {
        if(vectormp[posicion] != null)
        {
            vectormp[posicion].stop();

            vectormp[0] = MediaPlayer.create(this, R.raw.race);
            vectormp[1] = MediaPlayer.create(this, R.raw.sound);
            vectormp[2] = MediaPlayer.create(this, R.raw.tea);
            posicion = 0;

            play_pause.setBackgroundResource(R.drawable.reproducir);
            iv.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para repetir una pista
    public void Repetir(View view)
    {
        if(repetir == 1)
        {
            btn_repetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "Don't Loop", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(false);
            repetir = 2;
        }
        else
        {
            btn_repetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Loop", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(true);
            repetir = 1;
        }

    }

    //Metodo para repetir ala siguiente cancion
    public void Siguiente(View view)
    {
        if(posicion < vectormp.length - 1)
        {
                if( vectormp[posicion].isPlaying() )
                {
                    vectormp[posicion].stop();
                    posicion++;
                    vectormp[posicion].start();

                    if(posicion == 0)
                    {
                        iv.setImageResource(R.drawable.portada1);
                    }
                    if(posicion == 1)
                    {
                        iv.setImageResource(R.drawable.portada2);
                    }
                    if(posicion == 2)
                    {
                        iv.setImageResource(R.drawable.portada3);
                    }
                }
                else
                {
                    posicion++;

                    if(posicion == 0)
                    {
                        iv.setImageResource(R.drawable.portada1);
                    }
                    if(posicion == 1)
                    {
                        iv.setImageResource(R.drawable.portada2);
                    }
                    if(posicion == 2)
                    {
                        iv.setImageResource(R.drawable.portada3);
                    }
                }
        }
        else
        {
            Toast.makeText(this, "No more songs :c", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para regresar a la cancion anterior
    public void Anterior(View view)
    {
        if(posicion >= 1)
        {
            if(vectormp[posicion].isPlaying())
            {
                vectormp[posicion].stop();
                vectormp[0] = MediaPlayer.create(this, R.raw.race);
                vectormp[1] = MediaPlayer.create(this, R.raw.sound);
                vectormp[2] = MediaPlayer.create(this, R.raw.tea);

                posicion--;

                vectormp[posicion].start();
                if(posicion == 0){
                    iv.setImageResource(R.drawable.portada1);
                }
                if(posicion == 1) {
                    iv.setImageResource(R.drawable.portada2);
                }
                if(posicion == 2) {
                    iv.setImageResource(R.drawable.portada3);
                }
            }
            else
            {
                posicion--;
                if(posicion == 0)
                {
                    iv.setImageResource(R.drawable.portada1);
                }
                if(posicion == 1)
                {
                    iv.setImageResource(R.drawable.portada2);
                }
                if(posicion == 2)
                {
                    iv.setImageResource(R.drawable.portada3);
                }
            }
        }
        else
        {
            Toast.makeText(this, "No more songs :c", Toast.LENGTH_SHORT).show();
        }
    }
}