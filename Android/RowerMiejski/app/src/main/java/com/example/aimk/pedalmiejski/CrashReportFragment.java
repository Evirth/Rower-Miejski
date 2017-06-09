package com.example.aimk.pedalmiejski;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz on 21.05.2017.
 */

public class CrashReportFragment extends Fragment{
    View myView;
    Spinner spinner;
    EditText nrRoweru, nrStacji, opis;
    ArrayAdapter<String> adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Zgłoszenie awarii");
    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.crash_report_layout,container,false);
        spinner=(Spinner)myView.findViewById(R.id.spinner);
        nrRoweru=(EditText)myView.findViewById(R.id.NumerRoweruText);
        nrStacji=(EditText)myView.findViewById(R.id.NumerStacjiText);
        opis=(EditText)myView.findViewById(R.id.opisProblemuText);
        final String[] array = getResources().getStringArray(R.array.category_names);
        adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_layout,array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View myView, int position, long id) {
                parent.getItemAtPosition(position);
                if(array[position].equals("Rower")) {
                    nrStacji.setVisibility(myView.GONE);
                    nrRoweru.setVisibility(myView.VISIBLE);
                }

                else if (array[position].equals("Stacja")) {
                    nrStacji.setVisibility(myView.VISIBLE);
                    nrRoweru.setVisibility(myView.GONE);
                }

                else if (array[position].equals("Inne")) {
                    nrStacji.setVisibility(myView.VISIBLE);
                    nrRoweru.setVisibility(myView.VISIBLE);
                }
                //Toast.makeText(getApplicationContext(), parent.getItemIdAtPosition(position)+ " selected", Toast.LENGTH_LONG.show());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return myView;
    }



}
