package com.android.memeinn.learn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.memeinn.R;
import com.android.memeinn.review.ReviewActivity;


public class VocabActivity extends Activity{

    public static final String EXTRA_MESSAGE = "vocab.MESSAGE";
    public int flag;//0 for review mode, 1 for learning mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabpage);

        Intent intent = getIntent();
        flag = intent.getFlags();
    }

    /**
     * Event callback triggering intent to Chapter lists or Review system.
     * @param view
     */
    public void gotoChapOrReview(View view) {
        if(flag == 1){
            Intent goToChapIntent = new Intent(this, ChapterActivity.class);
            String vocabType = ((Button) view).getText().toString();
            Log.d("MyApp", "VocabType" + vocabType);
            goToChapIntent.putExtra(EXTRA_MESSAGE, vocabType);
            startActivity(goToChapIntent);
        } else if (flag == 0){
            Intent goToReviewIntent = new Intent(this, ReviewActivity.class);
            String vocabType = ((Button) view).getText().toString();
            Log.d("MyApp", "VocabType" + vocabType);
            goToReviewIntent.putExtra(EXTRA_MESSAGE, vocabType);
            startActivity(goToReviewIntent);
        }

    }
}
