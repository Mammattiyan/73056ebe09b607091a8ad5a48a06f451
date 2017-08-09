package com.ceemart.ceemart.modules;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.ceemart.ceemart.R;
import com.ceemart.ceemart.controllers.BeaconController;
import com.ceemart.ceemart.models.BeaconDisplayModel;
import com.ceemart.ceemart.modules.notification.SingleMessageActivity;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import io.realm.RealmResults;

public class MonitoringActivity extends AppCompatActivity implements BeaconConsumer {
    protected static final String TAG = "Jibi";
    private BeaconManager beaconManager;
    private BeaconController beaconController;
    private BackgroundPowerSaver backgroundPowerSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

//        backgroundPowerSaver = new BackgroundPowerSaver(this);
//        Button notify = (Button) findViewById(R.id.notify);
//        notify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addNotification();
//            }
//        });

        search();
    }


    public void search() {
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                BeaconController beaconController = new BeaconController();
                if (beacons.size() > 0) {
                    Log.i(TAG, "The first beacon I see is about " + beacons.iterator().next().getDistance() + " meters away.");

                    Log.i(TAG, "Beacon found ");
//                    beaconController.beaconMacthing(beacons);
                    beaconController.beaconMacthingTest(beacons, new NotificationCallback() {
                        @Override
                        public boolean onSuccessResponse(RealmResults result) {
                            return false;
                        }
                    });
                } else {
                    beaconController.beaconMacthingTest(beacons, new NotificationCallback() {
                        @Override
                        public boolean onSuccessResponse(RealmResults results) {
                            List<BeaconDisplayModel> list = new ArrayList<>();
                            list.addAll(results);
                            for (BeaconDisplayModel model : list) {

                                addNotification(model);

                            }
                            return false;
                        }
                    });
                    Log.i(TAG, "No beacon ");
                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
        }
    }

    /* interface NotificationCallback
    *  callback function for notifaication message
    *
    *  @param :
    *
    *  @retun
    */
    public interface NotificationCallback {
        boolean onSuccessResponse(RealmResults result);
    }

    /* interface RealmCallback
    *  callback function for realm
    *
    *  @param :
    *
    *  @retun
   */
    private void addNotification(BeaconDisplayModel model) {
        Intent notificationIntent = new Intent(this, SingleMessageActivity.class);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        notificationIntent.putExtra("messageId", String.valueOf(model.getId()));
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent intent2 = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_menu_report_image)
                        .setTicker(model.getName())
                        .setContentTitle(model.getTitle())
                        .setContentText(model.getContent())
                        .setContentIntent(intent2)
                        .setAutoCancel(true);
        notificationManager.notify(model.getId(), nBuilder.build());
//        startActivity(notificationIntent);
    }
}