package com.murraycole.ucrrunner.view.dialogfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.murraycole.ucrrunner.R;
import com.murraycole.ucrrunner.view.activities.activities.Profile.ProfileFragments.ProfileSettingsFragment;

/**
 * Created by C on 11/18/2014.
 */
public class HeightSettingDialogFragment extends DialogFragment {
    public static HeightSettingDialogFragment newInstance(int title) {
        HeightSettingDialogFragment hfrag = new HeightSettingDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        hfrag.setArguments(args);
        return hfrag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");

        //Get layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_height_fragment, null);
        final NumberPicker feet = (NumberPicker) view.findViewById(R.id.numberPicker);
        final NumberPicker inches = (NumberPicker) view.findViewById(R.id.numberPicker2);
        setFeetMinMaxValue(feet);
        setInchesMinMaxValue(inches);
        AlertDialog ad = new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_launcher)
                .setTitle(title)
                .setView(view)
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((ProfileSettingsFragment) getFragmentManager().findFragmentById(R.id.container)).doPositiveClick(getValueHeightandFeet(feet, inches), "height");
                            }
                        }
                )
                .setNegativeButton(R.string.alert_dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ProfileSettingsFragment.newInstance(null, null).doNegativeClick();

                            }
                        }
                )

                .create();
        return ad;

    }

    private void setFeetMinMaxValue(NumberPicker numberPicker) {
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(7);
        numberPicker.setValue(5);
    }

    private void setInchesMinMaxValue(NumberPicker numberPicker) {
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(11);
        numberPicker.setValue(7);
    }

    private String getValueHeightandFeet(NumberPicker feet, NumberPicker inches) {
        String output;

        output = feet.getValue() + "'" + inches.getValue() + "\"";
        return output;

    }
}


