package com.example.aimk.pedalmiejski;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Tomasz on 21.05.2017.
 */

public class RentBikeFragment extends Fragment implements View.OnClickListener{
    View myView;
    Button button1; Button button2; Button button3; Button button4; Button button5; Button button6; Button button7; Button button8; Button button9; Button button0; Button buttonTorch; Button buttonDelete;
    EditText numberEditText;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Wypożycz rower");

    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.rent_bike_layout,container,false);
        numberEditText=(EditText) myView.findViewById(R.id.RentBikeNumber);
        button0=(Button) myView.findViewById(R.id.KeypadButtonNumber0); button0.setOnClickListener(this);
        button1=(Button) myView.findViewById(R.id.KeypadButtonNumber1); button1.setOnClickListener(this);
        button2=(Button) myView.findViewById(R.id.KeypadButtonNumber2); button2.setOnClickListener(this);
        button3=(Button) myView.findViewById(R.id.KeypadButtonNumber3); button3.setOnClickListener(this);
        button4=(Button) myView.findViewById(R.id.KeypadButtonNumber4); button4.setOnClickListener(this);
        button5=(Button) myView.findViewById(R.id.KeypadButtonNumber5); button5.setOnClickListener(this);
        button6=(Button) myView.findViewById(R.id.KeypadButtonNumber6); button6.setOnClickListener(this);
        button7=(Button) myView.findViewById(R.id.KeypadButtonNumber7); button7.setOnClickListener(this);
        button8=(Button) myView.findViewById(R.id.KeypadButtonNumber8); button8.setOnClickListener(this);
        button9=(Button) myView.findViewById(R.id.KeypadButtonNumber9); button9.setOnClickListener(this);
        buttonTorch=(Button) myView.findViewById(R.id.KeypadButtonTorch); buttonTorch.setOnClickListener(this);
        buttonDelete=(Button) myView.findViewById(R.id.KeypadButtonDelete); buttonDelete.setOnClickListener(this);

        return myView;
    }
public void onClick(View v) {
        switch (v.getId()) {
            case R.id.KeypadButtonNumber0:
                numberEditText.append("0");
                break;
            case R.id.KeypadButtonNumber1:
                numberEditText.append("1");
                break;
            case R.id.KeypadButtonNumber2:
                numberEditText.append("2");
                break;
            case R.id.KeypadButtonNumber3:
                numberEditText.append("3");
                break;
            case R.id.KeypadButtonNumber4:
                numberEditText.append("4");
                break;
            case R.id.KeypadButtonNumber5:
                numberEditText.append("5");
                break;
            case R.id.KeypadButtonNumber6:
                numberEditText.append("6");
                break;
            case R.id.KeypadButtonNumber7:
                numberEditText.append("7");
                break;
            case R.id.KeypadButtonNumber8:
                numberEditText.append("8");
                break;
            case R.id.KeypadButtonNumber9:
                numberEditText.append("9");
                break;
            case R.id.KeypadButtonDelete:
                String text = numberEditText.getText().toString();
                if(text.length()>0) {
                    numberEditText.setText(text.substring(0, text.length() - 1));
                }
                break;
        }
        }
}
