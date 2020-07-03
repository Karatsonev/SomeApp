package com.example.someapp;

import android.os.SystemClock;
import android.widget.Button;

public class SomeRunnable implements Runnable {

    private Button btn;
    //private Handler handler;

    public SomeRunnable(Button btn) {
        this.btn = btn;
        //handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        for(int i=0; i<11; i++){
            if(i==10){
                btn.post(()->btn.setText(R.string.hundretPercent));
            }else if(i==5){
                btn.post(()->btn.setText(R.string.fifthyPercent));
            }else{
                int finalI = i;
                btn.post(()->btn.setText(String.valueOf(finalI)));
            }
            SystemClock.sleep(1000);
        }
    }

//    @Override
//    public void run() {
//        for(int i=0; i<11; i++){
//            if(i == 10){
//                handler.post(()->btn.setText(R.string.hundretPercent));
//            }else if(i==5){
//               handler.post(()-> btn.setText(R.string.fifthyPercent));
//            }else{
//                int finalI = i;
//                handler.post(()-> btn.setText(String.valueOf(finalI)));
//            }
//            SystemClock.sleep(1000);
//        }
//    }
}
