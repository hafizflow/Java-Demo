package com.example.javademo;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WebviewActivity extends AppCompatActivity {

    WebView webView;
    String url = "https://www.youtube.com/embed/";
    String sBaqarah = "X2YnP50cwNU";
    String sImran = "tNEvDUx16E0";
    String sRahman = "t5SZG6KEVFQ";
    Button baqarah,imran, rahman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.webview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        baqarah = findViewById(R.id.baqarah);
        imran = findViewById(R.id.imran);
        rahman = findViewById(R.id.rahman);

        webView.loadUrl(url + sBaqarah);

        baqarah.setOnClickListener(view -> {
            webView.loadUrl(url + sBaqarah);
        });

        imran.setOnClickListener(view -> {
            webView.loadUrl(url + sImran);
        });

        rahman.setOnClickListener(view -> {
            webView.loadUrl(url + sRahman);
        });
    }
}