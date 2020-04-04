package dev.anindya.helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import dev.anindya.helloworld.appdrawer.DrawerActivity;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(@NonNull final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener(this));
    }

    @Override
    public boolean onTouchEvent(@NonNull final MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private Context mContext;

        private MyGestureListener(@NonNull final Context context) {
            mContext = context;
        }

        @Override
        public boolean onFling(@NonNull final MotionEvent event1,
                               @NonNull final MotionEvent event2,
                               final float velocityX, final float velocityY) {
            final Intent intent = new Intent();
            intent.setClass(mContext, DrawerActivity.class);
            startActivity(intent);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        // do nothing.
    }
}
