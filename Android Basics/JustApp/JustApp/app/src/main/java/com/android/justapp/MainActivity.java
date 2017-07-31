package com.android.justapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void submitOrder(View view)
    {
        int priceFinal = calculatePrice();
        //displayMessage("Total: $" + priceFinal);
        displayMessage(createOrderSummary(priceFinal));
        createOrderSummary(priceFinal);

    }

    public void increment(View view)
    {
        quantity++;
        display(quantity);

    }

    public void decrement(View view)
    {
        quantity--;
        display(quantity);
    }

    public int calculatePrice()
    {
        return quantity * 5;
    }

    public String createOrderSummary(int price)
    {
        CheckBox chbox = (CheckBox) findViewById(R.id.checkBox);
        boolean ischecked = chbox.isChecked();
        return "Customer Name \n" + "Do you want Toppings? " + ischecked + "\n" +"Quantity: "
                + quantity + "\n" + "Total: R$" + price + "\n" + "Thank you!";
    }

    /**
     * This method display the quantity of coffee.
     */

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}
