package com.murraycole.ucrrunner.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.murraycole.ucrrunner.R;

import java.util.ArrayList;

/**
 * Created by C on 11/22/2014.
 */
public class FriendsAdapter extends ArrayAdapter {
    public FriendsAdapter(Context context, ArrayList resource) {
        super(context, 0, resource);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.friend_list_item,parent,false);

            ImageView profileIcon = (ImageView) view.findViewById(R.id.friend_profile_icon);
            TextView  contactName = (TextView) view.findViewById(R.id.friend_contact_name);


        }
        return view;
    }
}