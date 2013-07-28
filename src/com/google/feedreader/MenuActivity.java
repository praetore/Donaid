package com.google.feedreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.cloud.backend.android.GuestbookActivity;
import com.google.cloud.backend.android.R;
import com.google.feedreader.adapter.MenuAdapter;
import com.google.feedreader.adapter.MenuItem;

public class MenuActivity extends Activity implements AdapterView.OnItemClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final GridView view = (GridView) findViewById(R.id.menu_grid);
        Resources resources = getResources();
       
        view.setAdapter(new MenuAdapter(
                new MenuItem(resources.getString(R.string.dierenwelzijn), R.drawable.dierenwelzijn),
                new MenuItem(resources.getString(R.string.gezondheid), R.drawable.gezondheid),
                new MenuItem(resources.getString(R.string.kinderhulp), R.drawable.kinderhulp),
                new MenuItem(resources.getString(R.string.milieu), R.drawable.milieu),
                new MenuItem(resources.getString(R.string.ontwikkelingshulp), R.drawable.ontwikkelingshulp),
                new MenuItem(resources.getString(R.string.voedselhulp), R.drawable.voedselhulp)
           
        ));
        view.setOnItemClickListener(this);

        final ImageView IV = (ImageView) findViewById(R.id.IV);
        IV.setOnClickListener(new View.OnClickListener() {

        	
        	// deze activiteit laat hem naar GuestbookActivity class gaan
    	  @Override
    	  public void onClick(View v) {
    		  startActivity(new Intent(
                      MenuActivity.this,GuestbookActivity.class));
    	  }

    	});
        
    }
    public boolean isNetworkConnected() {
        final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.getState() == NetworkInfo.State.CONNECTED;
   }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent feed = new Intent(this, FeedActivity.class);
        String feedUrl = null;
        Resources resources = getResources();
        if (isNetworkConnected() == true) {
            switch (i) {
                case 0:
                    feedUrl = resources.getString(R.string.feed_dierenwelzijn);
                    break;
                case 1:
                    feedUrl = resources.getString(R.string.feed_gezondheid);
                    break;
                case 2:
                    feedUrl = resources.getString(R.string.feed_kinderhulp);
                    break;
                case 3:
                    feedUrl = resources.getString(R.string.feed_milieu);
                    break;
                case 4:
                    feedUrl = resources.getString(R.string.feed_ontwikkelingshulp);
                    break;
                case 5:
                    feedUrl = resources.getString(R.string.feed_voedselhulp);
                    break;
            }

            feed.putExtra("Feed", feedUrl);
            startActivity(feed);

        } else {
        	Context context = getApplicationContext();
        	CharSequence text = "Op het moment heb je geen internetverbinding";
        	int duration = Toast.LENGTH_SHORT;

        	Toast toast = Toast.makeText(context, text, duration);
        	toast.show();
        }
    }


    


}
