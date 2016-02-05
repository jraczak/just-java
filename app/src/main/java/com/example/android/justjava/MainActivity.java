package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Currency;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 1;
    int baseCoffeePrice = 5;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean whippedCreamAdded = whippedCreamCheckBox.isChecked();
        boolean chocolateAdded = chocolateCheckBox.isChecked();

        EditText customerNameTextInput = (EditText) findViewById(R.id.customer_name_input_field);
        String customerName = customerNameTextInput.getText().toString();
        Log.v("Main Activity", "Has whipped cream: " + whippedCreamAdded);
        displayQuantity(quantity);
        displayMessage(calculatePrice(quantity, baseCoffeePrice), whippedCreamAdded, chocolateAdded, customerName);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method calculates the order total based on the
     * number of coffees ordered and provided price.
     * @param numberOfCoffees the number of coffees being ordered
     * @param coffeePrice the price of each coffee
     * @return the calculated order price
     */
    private int calculatePrice(int numberOfCoffees, int coffeePrice) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean whippedCreamAdded = whippedCreamCheckBox.isChecked();
        boolean chocolateAdded = chocolateCheckBox.isChecked();

        if (whippedCreamAdded) {
            coffeePrice = coffeePrice + 1;
        }
        if (chocolateAdded) {
            coffeePrice = coffeePrice + 2;
        }
        return numberOfCoffees * coffeePrice;
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method 2
     * @param price the price of the coffee
     * @param hasWhippedCream if the coffees should include whipped cream
     * @param hasChocolate is the coffees should include chocolate topping
     */
    private void displayMessage(int price, boolean hasWhippedCream, boolean hasChocolate, String customerName) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        String priceNumber = NumberFormat.getCurrencyInstance().format(price);

        String orderDescription = " ";
        if (hasChocolate && hasWhippedCream) {
            orderDescription = " coffees with whipped cream and chocolate.";
        }
        else if (hasChocolate) {
            orderDescription = " coffees with chocolate.";
        }
        else if (hasWhippedCream) {
            orderDescription = " coffees with whipped cream.";
        }
        else {
            orderDescription = " coffees.";
        }

        Intent sendOrderEmail = new Intent(Intent.ACTION_SENDTO);
        sendOrderEmail.setData(Uri.parse("mailto:"));
        sendOrderEmail.putExtra(Intent.EXTRA_EMAIL, "jraczak@gmail.com");
        sendOrderEmail.putExtra(Intent.EXTRA_SUBJECT, "New coffee order from " + customerName);
        sendOrderEmail.putExtra(Intent.EXTRA_TEXT,
                "You have a new order from " + customerName + "!\n" +
                        "The order is for " + quantity + orderDescription + "\n" +
                        "The total cost is " + priceNumber + "\n"
                );
        if (sendOrderEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendOrderEmail);
        }

//        priceTextView.setText("Thank you, " + customerName + "!\n" +
//                "Your order for " + quantity + " coffees has been submitted.\n" +
//                "Your total is " + priceNumber + "\n" +
//                "Whipped Cream: " + hasWhippedCream + "\n" +
//                "Chocolate: " + hasChocolate + "\n");

    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast tooManyCoffeesToast = Toast.makeText(getApplicationContext(), "You cannot order more than 100 coffees", Toast.LENGTH_SHORT);
            tooManyCoffeesToast.show();
            return;
        }
        else {
            quantity = quantity + 1;
            displayQuantity(quantity);
            displayPrice(calculatePrice(quantity, baseCoffeePrice));
        }
    }

    public void decrement(View view) {
        if (quantity <= 1) {
            Toast tooFewCoffeesToast = Toast.makeText(getApplicationContext(), "You must have at least 1 coffee", Toast.LENGTH_SHORT);
            tooFewCoffeesToast.show();
            return;
        }
        else {
            quantity = quantity - 1;
            displayQuantity(quantity);
            displayPrice(calculatePrice(quantity, baseCoffeePrice));
        }
    }

}