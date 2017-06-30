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

public class FaqFragment extends Fragment {
    View myView;
    ListView listView;
    String titleArray[];
    String textArray[];

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("FAQ");
    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.faq_layout,container,false);

        titleArray = getResources().getStringArray(R.array.FAQ_title_strings);
        textArray = getResources().getStringArray(R.array.FAQ_text_strings);

        listView = (ListView) myView.findViewById(R.id.listaFAQ);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        return myView;
    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return titleArray.length;
        }

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
            convertView = getActivity().getLayoutInflater().inflate(R.layout.faq_element_layout,null);

            final String[] titleArray = getResources().getStringArray(R.array.FAQ_title_strings);
            final String[] textArray = getResources().getStringArray(R.array.FAQ_text_strings);

            TextView textViewTitle = (TextView)convertView.findViewById(R.id.textViewTitle);
            TextView textViewText = (TextView)convertView.findViewById(R.id.textViewText);

            textViewTitle.setText(titleArray[position]);
            textViewText.setText(textArray[position]);

            return convertView;
        }
    }
}
