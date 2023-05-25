package com.example.tagomovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    UserData in_user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        TextView out_time = findViewById(R.id.textView2);
        TextView out_numtickets = findViewById(R.id.textView3);
        TextView out_total = findViewById(R.id.textView4);
        Button back = findViewById(R.id.button2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user_data");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                in_user = dataSnapshot.getValue(UserData.class);
                out_time.setText("Movie starts at: " + in_user.getMovie_time());
                out_numtickets.setText("Number of tickets purchased: " + in_user.getNum_tickets());
                out_total.setText("Total cost: " + in_user.getTicket_cost());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
