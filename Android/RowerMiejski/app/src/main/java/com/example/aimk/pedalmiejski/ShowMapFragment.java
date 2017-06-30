package com.example.aimk.pedalmiejski;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Tomasz on 21.05.2017.
 */

public class ShowMapFragment extends Fragment implements OnMapReadyCallback{
    View myView;
    GoogleMap mGoogleMap;
    MapView mMapView;

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Mapa rowerów i stacji");
        mMapView=(MapView) myView.findViewById(R.id.googleMap);
        if(mMapView!= null)
        {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);

        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.show_map_layout,container,false);
        return myView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //MapsInitializer.initialize(getContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MapsInitializer.initialize(getContext());
        }
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689247,-74.044502)).title("Statue").snippet("I hope go there"));

        CameraPosition Liberty = CameraPosition.builder().target(new LatLng(40.689247,-74.044502)).zoom(14).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

    }
}
