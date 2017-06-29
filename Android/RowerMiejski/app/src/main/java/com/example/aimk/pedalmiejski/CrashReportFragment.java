package com.example.aimk.pedalmiejski;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz on 21.05.2017.
 */

public class CrashReportFragment extends Fragment implements View.OnClickListener{
    View myView;
    Spinner spinner;
    EditText nrRoweru, nrStacji, opis;
    ArrayAdapter<String> adapter;
    Button sendMessage;
    int globalPosition;
    NavigationView navigationView;


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
        sendMessage = (Button) myView.findViewById(R.id.wyslijButton);
        sendMessage.setOnClickListener(this);
        nrRoweru=(EditText)myView.findViewById(R.id.NumerRoweruText);
        nrStacji=(EditText)myView.findViewById(R.id.NumerStacjiText);
        opis=(EditText)myView.findViewById(R.id.opisProblemuText);
        final String[] array = getResources().getStringArray(R.array.category_names);
        adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_layout,array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        navigationView = (NavigationView) myView.findViewById(R.id.nav_view);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View myView, int position, long id) {
                parent.getItemAtPosition(position);
                globalPosition=position;
                if(array[position].equals("Rower")) {
                    nrStacji.setError(null);
                    opis.setError(null);
                    nrStacji.setVisibility(myView.GONE);
                    nrRoweru.setVisibility(myView.VISIBLE);
                }

                else if (array[position].equals("Stacja")) {
                    nrRoweru.setError(null);
                    opis.setError(null);
                    nrStacji.setVisibility(myView.VISIBLE);
                    nrRoweru.setVisibility(myView.GONE);
                }

                else if (array[position].equals("Inne")) {
                    nrStacji.setError(null);
                    nrRoweru.setError(null);
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

    public void onClick(View view){
        final String[] array = getResources().getStringArray(R.array.category_names);
        switch(view.getId())
        {
            case R.id.wyslijButton:
                android.app.FragmentManager fragmentManager = getFragmentManager();
                if(array[globalPosition].equals("Rower")) {
                    if( nrRoweru.getText().toString().length() == 0 )
                    {
                        nrRoweru.setError( "Pole wymagane" );
                    }

                    else if( nrRoweru.getText().toString().length() != 5 )
                    {
                        nrRoweru.setError( "Wprowadz 5 cyfrowy numer" );
                    }

                    else
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Zgłoszenie przyjęte", Toast.LENGTH_SHORT).show();
                        fragmentManager.beginTransaction().replace(R.id.content_frame,new RentBikeFragment()).commit();
                    }
                }

                if(array[globalPosition].equals("Stacja")) {
                    if( nrStacji.getText().toString().length() == 0 )
                    {
                        nrStacji.setError( "Pole wymagane!" );
                    }

                    else if( nrStacji.getText().toString().length() != 5 )
                    {
                        nrRoweru.setError( "Wprowadz 5 cyfrowy numer" );
                    }

                    else
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Zgłoszenie przyjęte", Toast.LENGTH_SHORT).show();
                        fragmentManager.beginTransaction().replace(R.id.content_frame,new RentBikeFragment()).commit();
                    }
                }

                if(array[globalPosition].equals("Inne")) {
                    if( opis.getText().toString().length() == 0 )
                    {
                        opis.setError("Pole wymagane");
                    }

                    else
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Zgłoszenie przyjęte", Toast.LENGTH_SHORT).show();
                        fragmentManager.beginTransaction().replace(R.id.content_frame,new RentBikeFragment()).commit();
                    }
                }
                break;
        }
    }


}
