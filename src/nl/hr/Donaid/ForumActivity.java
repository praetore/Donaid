package nl.hr.Donaid;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.app.ProgressDialog;  
import android.os.AsyncTask;  

public class ForumActivity extends Activity{
	private ProgressDialog progressDialog;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		

		super.onCreate(savedInstanceState);
		setContentView(R.layout.forum);
		 new LoadViewTask().execute();    

// deze code is voor het aanzetten van javascript en uitzetten van bepaalde functies.
		final WebView browser = (WebView) findViewById(R.id.wvBrowser);
		final WebView browser2 = (WebView) findViewById(R.id.wvBrowser2);
	
		browser.getSettings().setJavaScriptEnabled(true);
		browser.setVerticalScrollBarEnabled(false);
		browser.setHorizontalScrollBarEnabled(false);
		browser.setInitialScale(110);

		browser2.getSettings().setJavaScriptEnabled(true);
		browser2.setVerticalScrollBarEnabled(false);
		browser2.setHorizontalScrollBarEnabled(false);
		browser2.getSettings().setBuiltInZoomControls(false);
		browser2.getSettings().setDefaultZoom(ZoomDensity.FAR);
		

		browser2.setInitialScale(100);



// alles onder deze lijn is voor het uitzetten van dragging in webview		
		browser.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 return (event.getAction() == MotionEvent.ACTION_MOVE);
				
				}
	  });
browser2.setOnTouchListener(new View.OnTouchListener() {
			
		@Override
			public boolean onTouch(View v, MotionEvent event) {
			 return (event.getAction() == MotionEvent.ACTION_MOVE);
			
				}
  });

// alles onder deze lijn is het uitzetten van bepaalde dingen in javascript en om de browser automatisch te laten scrollen naar het opgegeven punt.
		browser.setWebViewClient(new WebViewClient() {
			 @Override
			public void onPageFinished(WebView view, String url)
			{
				 browser.loadUrl("javascript:document.getElementById('talk').style.display = 'none';");
					browser.scrollTo(-10, 220);
			}
			});
		browser.loadUrl("http://todaysmeet.com/oefening");
		browser2.setWebViewClient(new WebViewClient() {	
			@Override
			public void onPageFinished(WebView view, String url)
			{
				 browser2.loadUrl("javascript:document.getElementById('listen').style.display = 'none';");		
					browser2.scrollTo(-10, 210);
			}
			});
		browser2.loadUrl("http://todaysmeet.com/oefening");
		
	}
	
	
	private class LoadViewTask extends AsyncTask<Void, Integer, Void>  
    {  
        //Before running code in separate thread  
        @Override  
        protected void onPreExecute()  
        {  
            //nieuwe progres dialoog
            progressDialog = new ProgressDialog(ForumActivity.this);  
             
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
            
            progressDialog.setTitle("Loading...");  
           
            progressDialog.setMessage("Loading application View, please wait...");  
            //kan niet met back key worden uitgezet
            progressDialog.setCancelable(false);  
     
            progressDialog.setIndeterminate(false);  
            //maximum nummer is 100
            progressDialog.setMax(100);  
            //progress begint bij 0  
            progressDialog.setProgress(0);  
            
            progressDialog.show();  
        }  
        protected Void doInBackground(Void... params)  
        {  
            /* hier wordt hij iedere 800 milliseconde 25% groter 
             */  
            try  
            {  
             
                synchronized (this)  
                {  
                     
                    int counter = 0;  
                   
                    while(counter <= 4)  
                    {  
                      
                        this.wait(800);  
                      
                        counter++;  
                    
                        // dit zorgt ervoor dat de counter inprogressbar iedere 800 milliseconde met 25 groter wordt  
                        publishProgress(counter*25);  
                    }  
                }  
            }  
            catch (InterruptedException e)  
            {  
                e.printStackTrace();  
            }  
            return null;  
        }  
  
        //update het
        @Override  
        protected void onProgressUpdate(Integer... values)  
        {  
          
            progressDialog.setProgress(values[0]);  
        }  
  
        // na executie van de code  wordt het progressdialoog gesloten
        @Override  
        protected void onPostExecute(Void result)  
        {  
          
            progressDialog.dismiss();  
          
           
        }  
	}
}

	


