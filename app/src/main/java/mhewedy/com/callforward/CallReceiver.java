package mhewedy.com.callforward;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CallReceiver extends StartupReceiver {

    private static String DRIVER_NUMBER = "050XXXXXX";
    private static String MONA_NUMBER = "059XXXXXXX";
    private static int FORWARD_ON_HOUR = 12;      // 12

    @Override
    protected void onIncomingCallReceived(Context ctx, String number, Date start) {

    }

    @Override
    protected void onIncomingCallAnswered(Context ctx, String number, Date start) {

    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {

    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {

    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {

    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {


        DateFormat df = new SimpleDateFormat("HH");
        int hour = Integer.parseInt(df.format(start));

        Log.i("CallReceiver", "missed call from: " + number + ", at hour:" + hour);

        if (hour >= FORWARD_ON_HOUR && hour <= FORWARD_ON_HOUR
                && DRIVER_NUMBER.equals(number)) {

            Log.i("CallReceiver", "Forward to mona");

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("tel:" + MONA_NUMBER));
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Log.i("CallReceiver", "Calling...");
            ctx.startActivity(intent);
        }

    }
}
