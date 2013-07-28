package com.google.feedreader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.feedreader.adapter.FeedListAdapter;
import com.google.feedreader.adapter.RssAtomFeedRetriever;

public class FeedActivity
    extends Activity
{
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setTitle( "Donaid" );

	    initList newsList = new initList();
	    newsList.setContext(this);
	    newsList.execute();
    }

    // Feeds initialiseren in listview
    private View createList( SyndFeed feed, Activity activity )    {

        LinearLayout mainPanel = new LinearLayout( activity );
        ListView listView = new ListView( activity );
        final FeedListAdapter feedListAdapter = new FeedListAdapter(activity, feed);
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            public void onItemClick( AdapterView<?> parentView, View childView, int position, long id )
            {
                feedListAdapter.click( position );
            }
        } );
        listView.setAdapter(feedListAdapter);
        listView.setBackgroundColor(0xFFffeaab);
        mainPanel.addView(listView);
        return mainPanel;
    }

	public class initList extends AsyncTask<String, Integer, String> {
		private SyndFeed feed;
		private Activity context;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {
			String feedUrl = context.getIntent().getExtras().getString("Feed"); // feed als parameter
			RssAtomFeedRetriever feedRetriever = new RssAtomFeedRetriever();
			feed = feedRetriever.getMostRecentNews( feedUrl );
			return null;
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			setContentView(createList(feed, getContext()));
		}

		private void setContext(Activity context) {
			this.context = context;
		}

		private Activity getContext() {
			return context;
		}
	}
}
