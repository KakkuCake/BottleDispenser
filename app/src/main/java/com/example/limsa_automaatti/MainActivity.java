package com.example.limsa_automaatti;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = null;
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView textView2;

    BottleDispenser bdispenser = BottleDispenser.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        textView2 = (TextView) findViewById(R.id.textView2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                openActivity2();
                break;
            case R.id.button2:
                openActivity3();
                break;
            case R.id.button3:
                int money = bdispenser.returnMoney();
                if (money == 0) {
                    Toast.makeText(getApplicationContext(), "Klink klink. All money gone!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Klink klink. Money came out! You got "+ money + "€ back!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button4:
                readFileAndWriteReceipt();
                Toast.makeText(getApplicationContext(), "Your receipt is in file 'receipt.txt'", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivityForResult(intent, 1);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("amount", 0);
                bdispenser.addMoney(result);
                Toast.makeText(MainActivity.this, "You have added " + result + "€!", Toast.LENGTH_SHORT).show();
            }
            if (resultCode == RESULT_CANCELED) {
                textView2.setText("Nothing selected");
            }

        } if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String choice = data.getStringExtra("choice");
                if (choice.equals("Coca-Cola 0.5, 2€")) {
                    if((bdispenser.buyBottle("Coca-Cola", (double) 0.5)) == true) {
                        textView2.setText("The purchase was successful!");
                        writeFile(choice);
                    } else {
                        textView2.setText("The purchase failed");
                    }

                } else if (choice.equals("Coca-Cola 1.5, 2.5€")) {
                    if (bdispenser.buyBottle("Coca-Cola Zero", (double) 1.5) == true) {
                        textView2.setText("The purchase was successful!");
                        writeFile(choice);
                    } else {
                        textView2.setText("The purchase failed");
                    }

                } else if (choice.equals("Coca-Cola Zero 0.5, 2€")) {
                    if (bdispenser.buyBottle("Coca-Cola Zero", (double) 0.5) == true) {
                        textView2.setText("The purchase was successful!");
                        writeFile(choice);
                    } else {
                        textView2.setText("The purchase failed");
                    }

                } else if (choice.equals("Coca-Cola Zero 1.5, 2.5€")) {
                    if (bdispenser.buyBottle("Coca-Cola Zero", (double) 1.5) == true) {
                        textView2.setText("The purchase was successful!");
                        writeFile(choice);
                    } else {
                        textView2.setText("The purchase failed");
                    }

                } else if (choice.equals("Fanta 0.5, 1.95€")) {
                    if (bdispenser.buyBottle("Fanta", (double) 0.5) == true) {
                        textView2.setText("The purchase was successful!");
                        writeFile(choice);
                    } else {
                        textView2.setText("The purchase failed");
                    }

                } else if (choice.equals("Fanta 1.5, 2.45€")) {
                    if (bdispenser.buyBottle("Fanta", (double) 1.5) == true) {
                        textView2.setText("The purchase was successful!");
                        writeFile(choice);
                    } else {
                        textView2.setText("The purchase failed");
                    }

                } else if (choice.equals("Pepsi Max 0.5, 1.8€")) {
                    if (bdispenser.buyBottle("Pepsi Max", (double) 0.5) == true) {
                        textView2.setText("The purchase was successful!");
                        writeFile(choice);
                    } else {
                        textView2.setText("The purchase failed");
                    }

                } else if (choice.equals("Pepsi Max 1.5, 2.2€")) {
                    if (bdispenser.buyBottle("Pepsi Max", (double) 1.5) == true) {
                        textView2.setText("The purchase was successful!");
                        writeFile(choice);
                    } else {
                        textView2.setText("The purchase failed");
                    }
                }
            }
            if (resultCode == RESULT_CANCELED) {
                textView2.setText("Nothing selected");
            }
        }
    }

    public void writeFile(String data) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput("last_purchase.txt", Context.MODE_PRIVATE));
            writer.write(data + "\n");
            writer.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }

    public void readFileAndWriteReceipt() {
        try {
            InputStream is = context.openFileInput("last_purchase.txt");
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput("your_receipt.txt", Context.MODE_PRIVATE));

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String s = "";

            while ((s=reader.readLine()) != null) {
                String elements[] = s.split(",");
                String name_and_size = elements[0];
                String price = elements[1];
                writer.write("You purchased " + name_and_size + "L and it cost " + price + ".");
            }
            writer.close();
            is.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("LUETTU");
        }
    }


}


