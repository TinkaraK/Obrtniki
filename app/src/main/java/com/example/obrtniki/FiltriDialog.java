package com.example.obrtniki;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class FiltriDialog extends AppCompatDialogFragment{
    private FilterDialogListener listener;
    private Spinner regija_spinner;
    private Spinner min_ocena_spinner;
    private Spinner cenovni_razred_spinner;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.filtri_dialog, null);
        builder.setView(view)
                .setNegativeButton("zapri", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("filtriraj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.applyTexts(regija_spinner.getSelectedItemPosition(),
                                min_ocena_spinner.getSelectedItemPosition(),
                                cenovni_razred_spinner.getSelectedItemPosition());
                    }
                });
        regija_spinner = (Spinner) view.findViewById(R.id.regija_spinner);
        min_ocena_spinner = (Spinner) view.findViewById(R.id.min_ocena_spinner);
        cenovni_razred_spinner = (Spinner) view.findViewById(R.id.cenovni_razred_spinner);
        ArrayAdapter<String> regijaAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.regija_list));
        regijaAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        regija_spinner.setAdapter(regijaAdapter);
        ArrayAdapter<String> minRatingAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.rating_list));
        minRatingAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        min_ocena_spinner.setAdapter(minRatingAdapter);
        ArrayAdapter<String> cenovniRazredAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.cenovni_razred_list));
        cenovniRazredAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cenovni_razred_spinner.setAdapter(cenovniRazredAdapter);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (FilterDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement FilterDialogListener");
        }
    }

    public interface FilterDialogListener{
        void applyTexts(int reg_from_spn, int ocn_from_spn, int cen_from_spn);
    }
}
