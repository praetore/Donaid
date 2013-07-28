package nl.hr.Donaid;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.GridView;

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

        	
        	// deze activiteit laat hem naar ForumActivity class gaan
    	  @Override
    	  public void onClick(View v) {
    		  startActivity(new Intent(
                      MenuActivity.this,ForumActivity.class));
    	  }

    	});
        
    }
    

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent feed = new Intent(this, FeedActivity.class);
        String feedUrl = null;
        Resources resources = getResources();

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
    }


    


}
