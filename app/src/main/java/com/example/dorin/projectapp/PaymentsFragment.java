package com.example.dorin.projectapp;

import android.content.Context;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaymentsFragment extends Fragment implements ParticipatorsHelper.Callback, ExpensesHelper.Callback {

    View v;
    Context context;
    ArrayList<Participator> ParticipatorsList;
    ArrayList<Expenses> ExpensesList;

    int countParticipators;
    Float countExpenses = 0.0f;
    Float cash;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        v = inflater.inflate(R.layout.fragment_payments, container, false);
        context = getContext();

        Log.i("test", "1234 nieuwe run ______________________________");
        ParticipatorsHelper helper = new ParticipatorsHelper(context);
        helper.getParticipators(this);

        return v ;
    }

    @Override
    public void gotParticipators(ArrayList<Participator> ParticipatorsList) {
        this.ParticipatorsList = ParticipatorsList;

        ExpensesHelper helperExpenses = new ExpensesHelper(context);
        helperExpenses.getExpenses(this);
    }

    @Override
    public void gotParticipatorsError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotExpenses(ArrayList<Expenses> ExpensesList) {
        this.ExpensesList = ExpensesList;
        Log.i("test", "1234 here _________________________________");
        calculate();
    }

    @Override
    public void gotExpensesError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public void calculate() {
        countParticipators = ParticipatorsList.size();

        for (Expenses expenses: ExpensesList) {
            countExpenses = countExpenses + Float.parseFloat(expenses.getAmount().replaceAll(",", "."));
        }
        cash = countExpenses / countParticipators;
        Log.i("test", "aantal deelnemers 1234 is " + countParticipators);
        Log.i("test", "totaal bedrag 1234 is " + countExpenses);
        Log.i("test" , "bedrag per deelnemer 1234 is " + cash);

        // order list of expenses
        ArrayList<String> expensUsersList = new ArrayList<>();
        ArrayList<Float> expensAmountList = new ArrayList<>();
        // people who have to pay to others
        ArrayList<String> notPayers = new ArrayList<>();
        ArrayList<String> payers = new ArrayList<>();
        ArrayList<Float> much = new ArrayList<>();
        ArrayList<String> fromWho = new ArrayList<>();
        ArrayList<String> toWho = new ArrayList<>();

        // for all expenses in list
        for (Expenses expenses: ExpensesList) {
            // make float of money from expenseslist
            Float newAmount = Float.parseFloat(expenses.getAmount().replaceAll(",", "."));
            // if somebody payed more then one time
            if (expensUsersList.contains(expenses.getUsername())) {
                // get index of somebody who payed more then one time
                int index = expensUsersList.indexOf(expenses.getUsername());
                // count money that somebody payed
                expensAmountList.set(index, (expensAmountList.get(index) + newAmount));
                }
            // if a new person payed
            else {
                expensUsersList.add(expenses.getUsername());
                expensAmountList.add(newAmount);
            }
        }

        // all participators who don't have pay saved in a list
        for (Participator participator: ParticipatorsList) {
            if (!expensUsersList.contains(participator.getParticipator())) {
                notPayers.add(participator.getParticipator());
            }
        }

        // iterate over all people who have to pay
        for (String payer: notPayers) {
            // iterate over all people who have pay
            for (String expenser: expensUsersList) {
                // get index of person who have pay
                int index = expensUsersList.indexOf(expenser);
                // if person - cash of payer is bigger or same as cash
                if (expensAmountList.get(index) - cash >= cash ) {
                    expensAmountList.set(index, expensAmountList.get(index) - cash);
                    much.add(cash);
                    fromWho.add(payer);
                    toWho.add(expensUsersList.get(index));
                }
            }
        }

        // TO DO
        // als gebruiker kleiner wordt dan dat bedrag, maar wel een gedeelte, dan aan twee mensen betalen
        // als van de betalers het bedrag wat overblijft nog te veel is, dan betalers aan elkaar betalen

        // de gebruiker die ingelogd is veranderen in u (dit moet ook nog bij deelnemersfragment)
    }







}
