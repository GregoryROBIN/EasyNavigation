package fr.unice.polytech.easynavigation;

import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Detection d'un click long
 */

public class LongClickDetector implements View.OnTouchListener {

    private long mShakeTimestamp;
    private boolean down;
    private OnLongClick mListener;
    private float x1;
    private float y1;


    public void setOnLongClick(OnLongClick listener) {
        this.mListener = listener;
    }


    public interface OnLongClick {
        void onLongTouch(float x, float y);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(mListener != null){
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x1 = event.getX();
                    y1 = event.getY();
                    if(!down) {
                        mShakeTimestamp = System.currentTimeMillis();
                        down = true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    down = false;
                    break;
            }
            long now = System.currentTimeMillis();
            if(down && now - mShakeTimestamp > 1000){
                mListener.onLongTouch(event.getX(), event.getY());
            }
        }
        return true;
    }

}
