package com.example.dorin.projectapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        // Get textViews
        Payment payment = getItem(position);
        TextView textFromWho = convertView.findViewById(R.id.textFromWho);
        TextView textMuch = convertView.findViewById(R.id.textMuch);
        TextView textToWho = convertView.findViewById(R.id.textToWho);

        // if name equals user
        if (payment.getFromWho().equals(StartActivity.username)) {
            textFromWho.setText("U");
        }
        else {
            textFromWho.setText(payment.getFromWho());
        }

        // if money is lss than one
        String much;
        if (payment.getMuch() < 1) {
            // round with two decimals
            much = String.format("%.2g%n", payment.getMuch());
        }
        // if money more/same than one and less than 10
        else if (payment.getMuch() < 10 && payment.getMuch() >= 1) {
            // round with three decimals
            much = String.format("%.3g%n", payment.getMuch());
        }
        // if money more or equals ten
        else {
            // round with four decimals
            much = String.format("%.4g%n", payment.getMuch());
        }
        textMuch.setText("€ " + much);

        // if name equals user
        if (payment.getToWho().equals(StartActivity.username)) {
            textToWho.setText("u");
        }
        else {
            textToWho.setText(payment.getToWho());
        }

        return convertView;
    }
}
