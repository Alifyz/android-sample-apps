package com.android.justapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
        int additionalPrice = 0;
        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.checkBox);         // WhippedCream
        CheckBox hasChocolateCream = (CheckBox) findViewById(R.id.chocolate); // Chocolate Cream
        EditText customerName = (EditText) findViewById(R.id.customer_name);

        boolean ischecked_chock = hasChocolateCream.isChecked();
        boolean ischecked = hasWhippedCream.isChecked();
        String name = customerName.getText().toString();

        // Checking if Toppings is selected and Update the finalPrice
        if(hasWhippedCream.isChecked() && hasChocolateCream.isChecked()) {
            additionalPrice = 3;
        } else if(hasChocolateCream.isChecked() && !hasWhippedCream.isChecked()) {
            additionalPrice = 2;
        } else if (hasWhippedCream.isChecked() && !hasChocolateCream.isChecked()) {
            additionalPrice = 1;
        }

        int finalAdditionprice = additionalPrice * quantity;
        price += finalAdditionprice;
        String summary;
        summary  = "Customer Name: " + name + "\n";
        summary += "Do you want Toppings? ";
        summary += ischecked + "\n";
        summary += "Do you want chocolate? ";
        summary += ischecked_chock + "\n";
        summary += "Quantity: " + quantity + "\n";
        summary += "Total: R$" + price + "\n";
        summary += "Thank you!";
        return summary;
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
