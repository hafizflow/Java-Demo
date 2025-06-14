package com.example.javademo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdmobActivity extends AppCompatActivity {

    private Button admob;
    private static final String TAG = "AdmobActivity";
    private InterstitialAd interstitialAd;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admob);

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize button
        admob = findViewById(R.id.admob);
        adView = findViewById(R.id.banner_ad_view);

        new Thread(
                () -> {
                    // Initialize the Google Mobile Ads SDK on a background thread.
                    MobileAds.initialize(this, initializationStatus -> {});
                })
                .start();

        // Banner Ad
        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
        adView.loadAd(adRequest);


        // Full Screen Ad
        loadInterstitialAd();

        // Set button click listener to show the ad
        admob.setOnClickListener(view -> {
            if (interstitialAd != null) {
                interstitialAd.show(this);
            } else {
                Log.d(TAG, "The interstitial ad is not loaded yet.");
                // Optionally reload the ad if it’s not available
                loadInterstitialAd();
            }
        });
    }

    public void loadInterstitialAd() {
        // Use test ad unit ID for development: ca-app-pub-3940256099942544/1033173712
        String adUnitId = getString(R.string.ad_unit_id);
        InterstitialAd.load(this, adUnitId, new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd ad) {
                        Log.d(TAG, "Ad loaded successfully.");
                        AdmobActivity.this.interstitialAd = ad;

                        // Set FullScreenContentCallback to monitor ad states
                        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                                // Called when the user clicks the ad
                                Log.d(TAG, "Ad was clicked.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when the ad is dismissed (user closes it)
                                Log.d(TAG, "Ad was dismissed.");
                                AdmobActivity.this.interstitialAd = null;
                                // Reload a new ad for future use
                                loadInterstitialAd();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                // Called when the ad fails to show
                                Log.d(TAG, "Ad failed to show: " + adError.getMessage());
                                AdmobActivity.this.interstitialAd = null;
                                // Optionally reload a new ad
                                loadInterstitialAd();
                            }

                            @Override
                            public void onAdImpression() {
                                // Called when an impression is recorded
                                Log.d(TAG, "Ad recorded an impression.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when the ad is shown
                                Log.d(TAG, "Ad is now shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Called when the ad fails to load
                        Log.d(TAG, "Ad failed to load: " + loadAdError.getMessage());
                        AdmobActivity.this.interstitialAd = null;
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up ad reference to prevent memory leaks
        interstitialAd = null;
    }
}