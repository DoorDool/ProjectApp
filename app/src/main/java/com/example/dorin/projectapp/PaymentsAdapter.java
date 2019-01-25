package com.example.dorin.projectapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PaymentsAdapter extends ArrayAdapter<Payment> {

    public PaymentsAdapter(Context context, ArrayList<Payment> paymentsList) {
        super(context, 0, paymentsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.payments_item, parent, false);
        }

        // Get and set textViews and imageViews from item
        Payment payment = getItem(position);
        TextView textFromWho = convertView.findViewById(R.id.textFromWho);
        TextView textMuch = convertView.findViewById(R.id.textMuch);
        TextView textToWho = convertView.findViewById(R.id.textToWho);

        textFromWho.setText(payment.getFromWho());
        String much = String.format("%.3g%n", payment.getMuch());
        textMuch.setText("€ " + much);
        textToWho.setText(payment.getToWho());

        return convertView;
    }
}
