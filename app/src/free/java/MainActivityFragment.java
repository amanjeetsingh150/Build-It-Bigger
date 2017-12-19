import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.developers.myjokes_android.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndPointAsyncTask;
import com.udacity.gradle.builditbigger.R;

/**
 * Created by Amanjeet Singh on 19/12/17.
 */


public class MainActivityFragment extends Fragment {
    Button jokeButton;
    InterstitialAd mInterstitialAd;
    String jokeStr;
    ProgressBar progressBar;
    TextView jokeText;

    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        jokeButton = (Button) v.findViewById(R.id.joke_button);
        AdView mAdView = (AdView) v.findViewById(R.id.adView);
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        jokeText = (TextView) v.findViewById(R.id.instructions_text_view);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        setupInterstitialAds();
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                new EndPointAsyncTask(new EndPointAsyncTask.OnRequestFinish() {
                    @Override
                    public void onFinish(String s) {
                        progressBar.setVisibility(View.GONE);
                        jokeStr = s;
                        Intent intent=new Intent(getActivity(),JokeActivity.class);
                        intent.putExtra(JokeActivity.JOKE_KEY,jokeStr);
                        startActivity(intent);
                    }
                }).execute();
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setupInterstitialAds() {
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(getContext(), JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_KEY, jokeStr);
                startActivity(intent);
            }
        });
        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
