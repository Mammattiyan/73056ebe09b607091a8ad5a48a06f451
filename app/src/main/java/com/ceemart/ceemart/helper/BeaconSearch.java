package com.ceemart.ceemart.helper;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.altbeacon.beacon.BeaconConsumer;

/**
 * Created by achu on 3/8/17.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BeaconSearch extends AppCompatActivity implements BeaconConsumer{

    Context context = this;
    Integer dis;


    @Override
    public void onBeaconServiceConnect() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
