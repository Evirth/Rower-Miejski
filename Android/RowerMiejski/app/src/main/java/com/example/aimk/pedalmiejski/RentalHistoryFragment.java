package com.example.aimk.pedalmiejski;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Tomasz on 21.05.2017.
 */

public class RentalHistoryFragment extends Fragment{
    View myView;
    ListView listView;
    String dateTextArray[];
    String dateNumberArray[];
    String rentStationArray[];
    String rentTimeArray[];
    String returnStationArray[];
    String returnTimeArray[];
    String bikeNumberArray[];
    String durationArray[];
    String priceArray[];

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Historia wypożyczeń");
    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.rental_history_layout,container,false);

        dateTextArray = getResources().getStringArray(R.array.rental_history_date_text_strings);
        dateNumberArray = getResources().getStringArray(R.array.rental_history_date_number_strings);
        rentStationArray = getResources().getStringArray(R.array.rental_history_rent_station_strings);
        rentTimeArray = getResources().getStringArray(R.array.rental_history_rent_time_strings);
        returnStationArray = getResources().getStringArray(R.array.rental_history_return_station_strings);
        returnTimeArray = getResources().getStringArray(R.array.rental_history_return_time_strings);
        bikeNumberArray = getResources().getStringArray(R.array.rental_history_bike_number_strings);
        durationArray = getResources().getStringArray(R.array.rental_history_duration_strings);
        priceArray = getResources().getStringArray(R.array.rental_history_price_strings);

        listView = (ListView) myView.findViewById(R.id.listaHistoriaWypozyczen);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);


        return myView;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() { return dateTextArray.length; }

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
            convertView = getActivity().getLayoutInflater().inflate(R.layout.rental_history_element_layout,null);

            dateTextArray = getResources().getStringArray(R.array.rental_history_date_text_strings);
            dateNumberArray = getResources().getStringArray(R.array.rental_history_date_number_strings);
            rentStationArray = getResources().getStringArray(R.array.rental_history_rent_station_strings);
            rentTimeArray = getResources().getStringArray(R.array.rental_history_rent_time_strings);
            returnStationArray = getResources().getStringArray(R.array.rental_history_return_station_strings);
            returnTimeArray = getResources().getStringArray(R.array.rental_history_return_time_strings);
            bikeNumberArray = getResources().getStringArray(R.array.rental_history_bike_number_strings);
            durationArray = getResources().getStringArray(R.array.rental_history_duration_strings);
            priceArray = getResources().getStringArray(R.array.rental_history_price_strings);

            TextView textViewDateText = (TextView)convertView.findViewById(R.id.history_date_text);
            TextView textViewDateNumber = (TextView)convertView.findViewById(R.id.history_date_number);
            TextView textViewRentStation = (TextView)convertView.findViewById(R.id.history_rent_station);
            TextView textViewRentTime = (TextView)convertView.findViewById(R.id.history_rent_time);
            TextView textViewReturnStation = (TextView)convertView.findViewById(R.id.history_return_station);
            TextView textViewReturnTime = (TextView)convertView.findViewById(R.id.history_return_time);
            TextView textViewBikeNumber = (TextView)convertView.findViewById(R.id.history_bike_number);
            TextView textViewDuration = (TextView)convertView.findViewById(R.id.history_duration);
            TextView textViewPrice = (TextView)convertView.findViewById(R.id.history_price);

            textViewDateText.setText(dateTextArray[position]);
            textViewDateNumber.setText(dateNumberArray[position]);
            textViewRentStation.setText(rentStationArray[position]);
            textViewRentTime.setText(rentTimeArray[position]);
            textViewReturnStation.setText(returnStationArray[position]);
            textViewReturnTime.setText(returnTimeArray[position]);
            textViewBikeNumber.setText(bikeNumberArray[position]);
            textViewDuration.setText(durationArray[position]);
            textViewPrice.setText(priceArray[position]);

            return convertView;
        }
    }
}
