package com.oboenikui.rsssearch;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.button1);
        final EditText request = (EditText)findViewById(R.id.request);
        b.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String[] s = {request.getText().toString()};
                new findRSSTask().execute(s);
            }
        });
    }

    public class findRSSTask extends AsyncTask<String, Void, String[]>{

        @Override
        protected String[] doInBackground(String... params) {
            try {
                getHtmlSource g = new getHtmlSource(params[0]);
                String[] s = {g.getRssUrl(),g.getAtomUrl()};
                return s;
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        
        @Override
        protected void onPostExecute(String[] result) {
            super.onPostExecute(result);
            EditText result_rss = (EditText)findViewById(R.id.result_rss);
            EditText result_atom = (EditText)findViewById(R.id.result_atom);
            result_rss.setText(result[0]);
            result_atom.setText(result[1]);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
