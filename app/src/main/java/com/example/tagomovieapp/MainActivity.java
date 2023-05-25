package com.example.tagomovieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    String[] times = {"PM", "AM"};
    Integer num_tickets;
    Double ticket_cost;
    String movie_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        EditText in_tickets = findViewById(R.id.editTextTextPersonName);
        EditText in_time = findViewById(R.id.editTextTextPersonName2);
        Button bt_submit = findViewById(R.id.button);

        WebView myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://musicboxtheatre.com/");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user_data");

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (in_tickets.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Enter a number of tickets.", Toast.LENGTH_SHORT).show();
                }else{
                    num_tickets = Integer.parseInt(in_tickets.getText().toString());
                    ticket_cost = num_tickets * 12.50;
                    ticket_cost *= 1.10;
                    ticket_cost = Math.round(ticket_cost * 100.0)/100.0;
                }

                if (in_time.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Enter a movie time.", Toast.LENGTH_SHORT).show();
                }else{
                    movie_time = in_time.getText().toString() + " " + spinner.getSelectedItem().toString();
                }

                UserData user = new UserData(num_tickets, ticket_cost, movie_time);

                myRef.setValue(user);

                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);


            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }
}