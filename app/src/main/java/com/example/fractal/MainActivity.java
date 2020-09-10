package com.example.fractal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Stack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import static java.lang.Math.PI;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    static int angle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Clase view= new Clase(this);
        setContentView(R.layout.activity_main);

        RelativeLayout mainLayout =
                (RelativeLayout) findViewById(R.id.relativeLayout);
        mainLayout.addView(view);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                angle=i/2;
                view.invalidate();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public class Clase extends View {

        public Clase(Context context){
            super(context);
        }

        public void onDraw(Canvas canvas){
            Paint paint=new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            paint.setColor(Color.BLUE);
            int ancho=canvas.getWidth();
            int alto=canvas.getHeight();
            //canvas.drawLine(ancho/2,alto,ancho/2,alto-len,paint);
            canvas.translate(ancho/2,alto);
            branch(300,canvas,paint);

        }

        void branch(float len,Canvas canvas,Paint paint){
            canvas.drawLine(0, 0, 0,-len, paint);
            canvas.translate(0,-len);
            if(len>1) {
                canvas.save();
                canvas.rotate(angle);
                branch((float) (len*0.7),canvas,paint);
                canvas.restore();
                canvas.save();
                canvas.rotate(-angle);
                branch((float) (len*0.7),canvas,paint);
                canvas.restore();
            }
        }

    }
};