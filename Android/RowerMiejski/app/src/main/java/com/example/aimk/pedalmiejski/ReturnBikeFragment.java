package com.example.aimk.pedalmiejski;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Tomasz on 21.05.2017.
 */

public class ReturnBikeFragment extends Fragment{
    View myView;
    ListView listView;
    String bikeNumberArray[];
    String lockNumberArray[];
    String durationArray[];

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Zwrot roweru");
    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.return_bike_layout,container,false);

        bikeNumberArray = getResources().getStringArray(R.array.rented_bike_number_strings);
        lockNumberArray = getResources().getStringArray(R.array.rented_bike_lock_number_strings);
        durationArray = getResources().getStringArray(R.array.rented_bike_duration_strings);

        listView = (ListView) myView.findViewById(R.id.listaZwrotRoweru);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        return myView;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() { return bikeNumberArray.length; }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getActivity().getLayoutInflater().inflate(R.layout.rented_bikes_layout,null);


            TextView textViewBikeNumber = (TextView)convertView.findViewById(R.id.rented_bike_number);
            TextView textViewBikeLockNumber = (TextView)convertView.findViewById(R.id.rented_bike_lock_number);
            TextView textViewDuration = (TextView)convertView.findViewById(R.id.rented_bike_duration);

            textViewBikeNumber.setText(bikeNumberArray[position]);
            textViewBikeLockNumber.setText(lockNumberArray[position]);
            textViewDuration.setText(durationArray[position]);

            return convertView;
        }
    }
}
