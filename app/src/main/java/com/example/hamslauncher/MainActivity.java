package com.example.hamslauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    TextView textView;
    public ArrayList<AppInfo> res;
    private int in;
    ImageView imageView;
    //int appsize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

        List<PackageInfo> apps = getPackageManager().getInstalledPackages(0);
        res = new ArrayList<AppInfo>();
        for(int i=0;i<apps.size();i++) {
            PackageInfo p = apps.get(i);

            AppInfo newInfo = new AppInfo();
            newInfo.appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
            newInfo.packageName = p.packageName;
            //newInfo.versionName = p.versionName;
            //newInfo.versionCode = p.versionCode;
            newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
            res.add(newInfo);
        }
        //int maximum = apps.size();
        Random rand = new Random();
        //appsize = apps.size();
        in = rand.nextInt(apps.size());
        imageView = (ImageView) findViewById(R.id.iconButton);
        imageView.setImageDrawable(res.get(in).icon);
    }

        //ImageView imageView = (ImageView) findViewById(R.id.chromeButton);
        //imageView.setImageDrawable(getActivityIcon("com.android.chrome","com.google.android.apps.chrome.MAIN"));


    public Drawable getActivityIcon(String packageName, String activityName){
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName,activityName));
        ResolveInfo resolveInfo = packageManager.resolveActivity(intent,0);
        return resolveInfo.loadIcon(packageManager);
    }


    public void launchApp(View view){

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(res.get(in).packageName);

        startActivity(launchIntent);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("On Down");
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("On Fling");

        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("On Long Press");
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("On Scroll");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("On Show Press");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("On Single Tap up");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("On Double Tap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("On Dowble Tap event");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("On single tap confirmed");
        return true;
    }



}
