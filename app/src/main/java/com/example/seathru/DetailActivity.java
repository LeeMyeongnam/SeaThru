package com.example.seathru;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class DetailActivity extends NMapActivity {
    private NMapView nMapView;
    private NMapController nMapController;
    private final String Client_ID="fh8tXXrTDki5uvLjf6zL";
    NMapResourceProvider mapResourceProvider =null;
    NMapOverlayManager mapOverlayManager;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        LinearLayout container = findViewById(R.id.mcontainer);
        nMapView = new NMapView(this);
        nMapController = nMapView.getMapController();

        nMapView.setClientId(Client_ID);
        nMapView.setEnabled(true);
        nMapView.setFocusable(true);
        nMapView.setScalingFactor(1.7f);
        nMapView.setFocusableInTouchMode(true);
        nMapView.setClickable(true);
        container.addView(nMapView);


        Intent intent = getIntent();
        String stanm = intent.getExtras().getString("staNm");
        double lon = intent.getExtras().getDouble("lon");
        double lat = intent.getExtras().getDouble("lat");
        String phone= intent.getExtras().getString("phone");
        nMapController.setMapCenter(new NGeoPoint(lon, lat), 11);

        tv1 = findViewById(R.id.beachName);
        tv2 = findViewById(R.id.address);
        tv3 = findViewById(R.id.phoneNumber);

        tv1.setText(stanm);
        tv3.setText(phone);
        getLocation(lat, lon);
        //NMapPOIitem item = poiData.addPOIitem(lat, lon);

    }
    public void getLocation(double lat, double lon){
        String str = null;
        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
        List<Address> address;
        try{
            if(geocoder!=null){
                address=geocoder.getFromLocation(lat, lon, 1);
                if(address!=null && address.size()>0){
                    str=address.get(0).getAddressLine(0).toString();
                }
            }
        }catch(IOException e){e.printStackTrace();}
        tv2.setText(str);
    }
}
