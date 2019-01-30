package com.example.dorin.projectapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

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
        calculate();
    }

    @Override
    public void gotExpensesError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void calculate() {
        countParticipators = ParticipatorsList.size();

        if (StartActivity.groupsname == null) {
            TextView text = v.findViewById(R.id.noText);
            text.setText("geen groep geselecteerd");
        }
        else if (StartActivity.categoriename == null) {
            TextView text = v.findViewById(R.id.noText);
            text.setText("geen categorie geselecteerd");
        }
        else if (ExpensesList.size() == 0) {
            TextView text = v.findViewById(R.id.noText);
            text.setText("er zijn geen uitgaven");
        }

        for (Expenses expenses: ExpensesList) {
            countExpenses = countExpenses + Float.parseFloat(expenses.getAmount().replaceAll(",", "."));
        }
        cash = countExpenses / countParticipators;

        // order list of expenses
        ArrayList<String> expensUsersList = new ArrayList<>();
        ArrayList<Float> expensAmountList = new ArrayList<>();
        // list with people who have to pay to others
        ArrayList<String> notPayers = new ArrayList<>();
        // list with people who have payd
        //ArrayList<String> payers = new ArrayList<>();  //////// kan weg
        // list with payments
        ArrayList<Payment> payments = new ArrayList<>();
        // list with how much have to pay
        ArrayList<Float> much = new ArrayList<>();
        // list with who have to pay
        ArrayList<String> fromWho = new ArrayList<>();
        // list with who get money
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
            Boolean havePayed = false;
            // iterate over all people who have pay
            for (String expenser: expensUsersList) {
                // get index of person who get the money
                int index = expensUsersList.indexOf(expenser);
                // if person - cash of payer is bigger or same as cash
                if (expensAmountList.get(index) - cash >= cash ) {
                    expensAmountList.set(index, expensAmountList.get(index) - cash);
                    // make new payment and add to list
                    Payment payment = new Payment(cash, payer, expensUsersList.get(index));
                    payments.add(payment);
                    much.add(cash);
                    fromWho.add(payer);
                    toWho.add(expensUsersList.get(index));
                    havePayed = true;
                }
            }
            if (!havePayed) {
                // maximal cash
                float limit = 0;
                do {
                    // iterate over all people who have pay
                    for (String expenser: expensUsersList) {
                        // get index of person who have pay
                        int index = expensUsersList.indexOf(expenser);
                        // if money from payer minus cash is more than zero
                        if (expensAmountList.get(index) - cash > 0) {
                            // money which not payer has to pay to payer
                            float pay = expensAmountList.get(index) - cash;
                            // may not be more than cash
                            if (limit + pay <= cash) {
                                expensAmountList.set(index, expensAmountList.get(index) - pay);
                                // make new payment and add to list
                                Payment payment = new Payment(pay, payer, expensUsersList.get(index));
                                payments.add(payment);
                                much.add(pay);
                                fromWho.add(payer);
                                toWho.add(expensUsersList.get(index));
                                limit = limit + pay;
                            }
                            // else pay rest of not payer has to pay until they pay cash
                            else {
                                pay = limit - cash;
                                expensAmountList.set(index, expensAmountList.get(index) - pay);
                                // make new payment and add to list
                                Payment payment = new Payment(pay, payer, expensUsersList.get(index));
                                payments.add(payment);
                                much.add(pay);
                                fromWho.add(payer);
                                toWho.add(expensUsersList.get(index));
                                limit = limit + pay;

                            }
                        }
                    }
                // do until limit is less than cash minus 0.02 (for round)
                } while (limit < (cash - 0.02));
            }
        }

        // iterate over all payers
        for (float expens: expensAmountList) {
            // get index of payer
            int index = expensAmountList.indexOf(expens);
            // if payer have not payed enough
            if (expens < cash) {
                // set limit
                float limit = expens;
                do {
                    // iterate again over all payers
                    for (float ex: expensAmountList) {
                        // get index of other payer
                        int indexGet = expensAmountList.indexOf(ex);
                        // if this payer has payed to much
                        if (ex - cash > 0) {
                            // first payer have to pay to them
                            float pay = ex - cash;
                            // if that is not more than cash
                            if (limit + pay <= cash) {
                                // set all amounts
                                expensAmountList.set(index, expensAmountList.get(index) + pay);
                                expensAmountList.set(indexGet, expensAmountList.get(indexGet) - pay);
                                // set payment and add to list
                                Payment payment = new Payment (pay, expensUsersList.get(index), expensUsersList.get(indexGet));
                                payments.add(payment);
                                much.add(pay);
                                fromWho.add(expensUsersList.get(index));
                                toWho.add(expensUsersList.get(indexGet));
                                // set limit
                                limit = limit + pay;

                            }
                            // pay rest that other payer have pay to much
                            else {
                                pay = cash - limit;
                                expensAmountList.set(index, expensAmountList.get(index) + pay);
                                expensAmountList.set(indexGet, expensAmountList.get(indexGet) - pay);
                                Payment payment = new Payment (pay, expensUsersList.get(index), expensUsersList.get(indexGet));
                                payments.add(payment);
                                much.add(pay);
                                fromWho.add(expensUsersList.get(index));
                                toWho.add(expensUsersList.get(indexGet));
                                limit = limit + pay;
                            }
                        }
                    }
                // do while first payer have pay enough
                } while(limit < (cash - 0.02));
            }
        }
        // set adapter for payments
        ListView listView = v.findViewById(R.id.listViewPayments);
        PaymentsAdapter adapter = new PaymentsAdapter(context, payments);
        listView.setAdapter(adapter);

    }
}
