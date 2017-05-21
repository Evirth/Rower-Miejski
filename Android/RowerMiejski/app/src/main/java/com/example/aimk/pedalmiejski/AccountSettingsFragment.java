package com.example.aimk.pedalmiejski;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Tomasz on 21.05.2017.
 */

public class AccountSettingsFragment extends Fragment implements View.OnClickListener{
    View myView;
    Button ChangePasswordButton;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Ustawienia konta");
    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.account_settings_layout,container,false);
        ChangePasswordButton = (Button) myView.findViewById(R.id.ChangePasswordButton);
        ChangePasswordButton.setOnClickListener(this);
        return myView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ChangePasswordButton:
                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,new ChangePasswordFragment()).commit();
                break;
        }
    }
}
