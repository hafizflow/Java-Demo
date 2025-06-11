package com.example.javademo;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    Button nextPage, webView, animationPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nextPage = findViewById(R.id.nextPage);
        nextPage.setOnClickListener(view -> {
            Intent intent = new Intent(this, TasbiActivity.class);
            startActivity(intent);
        });


        webView = findViewById(R.id.webView);
        webView.setOnClickListener(view -> {
            Intent intent = new Intent(this, WebviewActivity.class);
            startActivity(intent);
        });

        animationPage = findViewById(R.id.animationView);
        animationPage.setOnClickListener(view -> {
            Intent intent = new Intent(this, AnimationActivity.class);
            startActivity(intent);
        });

    }
}