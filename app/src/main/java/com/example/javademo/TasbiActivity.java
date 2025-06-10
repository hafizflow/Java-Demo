package com.example.javademo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TasbiActivity extends AppCompatActivity {

    int count;
    TextView tasbi;
    Button increase, reset, decrease, toggle, toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.tasbi_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tasbi), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tasbi = findViewById(R.id.tasbi);
        increase = findViewById(R.id.increase);
        reset = findViewById(R.id.reset);
        decrease = findViewById(R.id.decrease);
        toggle = findViewById(R.id.toggle);
        toast = findViewById(R.id.toast);

        increase.setOnClickListener(view -> {
            count++;
            tasbi.setText(String.valueOf(count));
        });

        decrease.setOnClickListener(view -> {
            if(count != 0) {
                count--;
                tasbi.setText(String.valueOf(count));
            }
        });

        reset.setOnClickListener(view -> {
            count = 0;
            tasbi.setText(String.valueOf(count));
        });

        toggle.setOnClickListener(view -> {
            if(tasbi.getVisibility() == View.VISIBLE) {
                tasbi.setVisibility(View.INVISIBLE);
            } else {
                tasbi.setVisibility(View.VISIBLE);
            }
        });

        toast.setOnClickListener(view -> {
            Toast.makeText(this, "My First Java App", Toast.LENGTH_SHORT).show();
        });

    }
}