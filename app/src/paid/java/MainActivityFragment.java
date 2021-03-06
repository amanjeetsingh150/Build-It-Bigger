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
import com.udacity.gradle.builditbigger.EndPointAsyncTask;
import com.udacity.gradle.builditbigger.R;

/**
 * Created by Amanjeet Singh on 19/12/17.
 */

public class MainActivityFragment extends Fragment {

    Button jokeButton;
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
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        jokeText = (TextView) v.findViewById(R.id.instructions_text_view);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                new EndPointAsyncTask(new EndPointAsyncTask.OnRequestFinish() {
                    @Override
                    public void onFinish(String s) {
                        progressBar.setVisibility(View.GONE);
                        jokeStr = s;
                        Intent intent = new Intent(getActivity(), JokeActivity.class);
                        intent.putExtra(JokeActivity.JOKE_KEY, jokeStr);
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

}
