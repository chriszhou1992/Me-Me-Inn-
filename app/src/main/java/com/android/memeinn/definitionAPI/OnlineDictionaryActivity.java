package com.android.memeinn.definitionAPI;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.memeinn.R;

public class OnlineDictionaryActivity extends Activity {

    private String word;
    private TextView etResponse;
    private EditText etWord;

    /**
     * On creating the activity view
     * Make an online dictionary API call using the HTTPAsyncTask
     * Set up a listener for the word input field
     * Whenever the word changes, make an online dictionary API call
     * The etResponse view reflect the result of the online dictionary API call
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.definitionapi);

        // get reference to the views
        etWord = (EditText) findViewById(R.id.etWord);
        etResponse = (TextView)findViewById(R.id.etResponse);
        etWord.setText(word);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        Context context = getBaseContext();
        HttpAsyncTask httpAsyncTask = new HttpAsyncTask(connMgr, context, etResponse);
        httpAsyncTask.execute("https://montanaflynn-dictionary.p.mashape.com/define?word=" + etWord.getText().toString());
        etWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    etWord.setHint("");
                }
            }
        });

        etWord.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String searchString = etWord.getText().toString();
                if((searchString!=null) && (!searchString.equals(""))) {
                    // you can call or do what you want with your EditText here
                    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
                    Context context = getBaseContext();
                    HttpAsyncTask httpAsyncTask = new HttpAsyncTask(connMgr, context, etResponse);
                    httpAsyncTask.execute("https://montanaflynn-dictionary.p.mashape.com/define?word=" + searchString);
                }else{
                    etResponse.setText("");
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

    }


}
