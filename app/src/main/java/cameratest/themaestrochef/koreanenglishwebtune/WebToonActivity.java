package cameratest.themaestrochef.koreanenglishwebtune;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class WebToonActivity extends AppCompatActivity {
    private ObservableWebView englishWebView;
    private ObservableWebView koreanWebView;
    boolean koreanViewIsMinimized;
    boolean englishViewisMinimized;
    Button koreanBackButton;
    Button englishBackButton;
    Button windowImageView;
    Button windowImageViewKorean;
    Button englishScrollLink;
    Button koreanScrollLink;
    Button englishPageLink;
    Button koreanPageLink;
    boolean scrollLinked = true;
    LinearLayout englishWebViewToolbar;
    LinearLayout koreanWebViewToolbar;
    boolean pageChangeLinked = true;
    int counter = 1;
    int scrollXEnglish;
    int scrollYEnglish;
    static boolean koreanWebViewTouched = false;
    static boolean englishWebViewTouched = false;
    String koreanUrlTitleID = "131385";
    String englishUrlTitleID= "83";
    private ArrayList<Webtoon> mWebtoons = null;
     //You can be pretty confident that the intent will not be null here.

    // Get the extras (if there are any)
    private ArrayList<Webtoon> newMWebtoon = null;
    int position;

    int[] missingEpisodeArray0 = new int[]{34, 53, 72, 105, 157,208};
    int missingEpisodeArray[];
    //Set the missing episode to 10000, this will make it where it won't change because there aren't any missing episodes and not 10000 episodes
    int[] towerOfGodMissingEpisodesArray = new int[]{80};
    int[] theSecretofAngelMissingEpisode = new int[]{0};

    private  final static String LAST_ENGLISH_WEBVIEW_URL  = "cameratest.themaestrochef.koreanenglishwebtune.ENGLISHLASTWEBVIEWURL";
    private  final static String LAST_KOREAN_WEBVIEW_URL  = "cameratest.themaestrochef.koreanenglishwebtune.KOREANWEBVIEWURL";
    String savedEnglishUrl;
    String englishUrlKey;
    String savedKoreanURL;
    String koreanUrlkey;
    boolean englishWebtoonLoading;
    boolean koreanWebtoonLoading;

    WebViewClient engWebViewClient = new MyWebViewClient(){
        //Todo fix scrolling
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            englishWebtoonLoading=true;

        }

        //onpagefinished for koreanWebclient
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (englishWebViewTouched && pageChangeLinked) {
                if(englishWebView.getUrl().contains("episode_no=")) {
                    String englishEpisodeNumber =  englishWebView.getUrl().substring(englishWebView.getUrl().indexOf("episode_no=" ) + 11);

                    for ( int i = 0; i < missingEpisodeArray.length; i++) {
                        int englishEpisodeNumberInt = Integer.parseInt(englishEpisodeNumber)+ i;
                        if (missingEpisodeArray[0] == 0) {
                            String  koreanUrl = newMWebtoon.get(position).getmKoreanEposodeURL() + "&no=" + Integer.toString(englishEpisodeNumberInt);
                            koreanWebView.loadUrl(koreanUrl);
                            break;

                        }
                        if (englishEpisodeNumberInt<missingEpisodeArray[i]){
                            String  koreanUrl = newMWebtoon.get(position).getmKoreanEposodeURL() + "&no=" + Integer.toString(englishEpisodeNumberInt);
                            koreanWebView.loadUrl(koreanUrl);
                            break;
                        }
                        if(englishEpisodeNumberInt == missingEpisodeArray[i]){
                            int englishEpisodeModifier = Integer.parseInt(englishEpisodeNumber)+ i+1;
                            String koreanUrl = newMWebtoon.get(position).getmKoreanEposodeURL() + "&no=" + Integer.toString(englishEpisodeModifier);
                            koreanWebView.loadUrl(koreanUrl);
                            break;

                        }
                        if (englishEpisodeNumberInt> missingEpisodeArray[missingEpisodeArray.length-1])
                        {
                            int englishEpisodeModifier = englishEpisodeNumberInt+ missingEpisodeArray.length;
                            String koreanUrl = newMWebtoon.get(position).getmKoreanEposodeURL() + "&no=" + Integer.toString(englishEpisodeModifier);
                            koreanWebView.loadUrl(koreanUrl);
                            break;
                        }

                        Log.v("domainLogs", "onPageFinished: " + Integer.toString(i));

                    }
                    Log.v("domainLogs", "onPageFinished: " + englishWebView.getUrl());
                    Log.v("domainLogs", "onPageFinished: " + englishEpisodeNumber);
                   // englishWebViewTouched = false;
                }



            }



            //  save KoreanURL
            SharedPreferences.Editor koreanEditor = getSharedPreferences(LAST_KOREAN_WEBVIEW_URL, MODE_PRIVATE).edit();
            koreanUrlkey = Integer.toString(position);
            koreanEditor.putString(koreanUrlkey, koreanWebView.getUrl());
            koreanEditor.apply();

            // add Save English URL.
            SharedPreferences.Editor englishEditor = getSharedPreferences(LAST_ENGLISH_WEBVIEW_URL, MODE_PRIVATE).edit();
            englishUrlKey = Integer.toString(position);
            englishEditor.putString(englishUrlKey, englishWebView.getUrl());
            englishEditor.apply();
            englishWebtoonLoading=false;
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains(newMWebtoon.get(position).getmEnglishUrl()) ||
                    url.equals(savedEnglishUrl) ||
                    url.equals(savedKoreanURL) ||
                    (url.contains(newMWebtoon.get(position).getmKoreanUrl()))

                    )
                return true;


            else
                return false;
        }
    };
    WebViewClient korWebviewClient = new MyWebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            koreanWebtoonLoading=true;


        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //loadingInprocess set to false so we it can read touch
            //            iWebClientCounter++;
            if ( koreanWebViewTouched) {
                if(koreanWebView.getUrl().contains("no=")) {

                    String koreanEpisodeNumber[] =  koreanWebView.getUrl().substring(koreanWebView.getUrl().indexOf("no=") + 3).split("&");
                    String englishURL = newMWebtoon.get(position).getmEnglishEpisodeUrl() +  koreanEpisodeNumber[0];
                    int koreanEpisodeNumberInt= Integer.parseInt(koreanEpisodeNumber[0]);
                    //Kubera Episode sorting Code

                    for (int i = 0; i < missingEpisodeArray.length; i++) {


                        if (koreanEpisodeNumberInt == missingEpisodeArray[i]) {
                            englishWebView.loadUrl("file:///android_res/drawable/no_translation_exist_image.png");
                            break;

                        }
                        if (missingEpisodeArray[i]==0){
                            String newEnglishURL = newMWebtoon.get(position).getmEnglishEpisodeUrl()+ koreanEpisodeNumber;
                            englishWebView.loadUrl(newEnglishURL);
                            break;
                        }
                        if (koreanEpisodeNumberInt> missingEpisodeArray[missingEpisodeArray.length-1]){
                            int newKoreanEpisodeNumber = koreanEpisodeNumberInt - missingEpisodeArray.length;
                            String newEnglishURL = newMWebtoon.get(position).getmEnglishEpisodeUrl() + Integer.toString(newKoreanEpisodeNumber);
                            englishWebView.loadUrl(newEnglishURL);
                            break;

                        }

                        if (koreanEpisodeNumberInt< missingEpisodeArray[0]) {
                            int newKoreanEpisodeNumber = koreanEpisodeNumberInt;
                            String newEnglishURL = newMWebtoon.get(position).getmEnglishEpisodeUrl() + Integer.toString(newKoreanEpisodeNumber);
                            englishWebView.loadUrl(newEnglishURL);

                            break;

                        }

                        if (koreanEpisodeNumberInt > missingEpisodeArray[i] && koreanEpisodeNumberInt< missingEpisodeArray[i+1]) {
                            int newKoreanEpisodeNumber = koreanEpisodeNumberInt - i -1;
                            String newEnglishURL = newMWebtoon.get(position).getmEnglishEpisodeUrl() + Integer.toString(newKoreanEpisodeNumber);
                            englishWebView.loadUrl(newEnglishURL);
                            Log.v("123kwhatisI", Integer.toString(i));
                            break;
                        }


                    }

                    Log.v("123korean url", "onPageFinished: " + koreanWebView.getUrl());
                    Log.v("123koreanepisodenumber", "onPageFinished: " + koreanEpisodeNumber[0]);
                    Log.v("123kenglishURL", "onPageFinished: " + englishURL);
                   // koreanWebViewTouched =false;
                }


            }

            koreanWebtoonLoading=false;

            //  save KoreanURL
            SharedPreferences.Editor koreanEditor = getSharedPreferences(LAST_KOREAN_WEBVIEW_URL, MODE_PRIVATE).edit();
            koreanUrlkey = Integer.toString(position);
            koreanEditor.putString(koreanUrlkey, koreanWebView.getUrl());
            koreanEditor.apply();

            // add Save English URL.
            SharedPreferences.Editor englishEditor = getSharedPreferences(LAST_ENGLISH_WEBVIEW_URL, MODE_PRIVATE).edit();
            englishUrlKey = Integer.toString(position);
            englishEditor.putString(englishUrlKey, englishWebView.getUrl());
            englishEditor.apply();
        }
        //that was the end of onpage finished for koreanwebview



      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains(newMWebtoon.get(position).getmEnglishUrl()) ||
                    url.equals(savedEnglishUrl) ||
                    url.equals(savedKoreanURL) ||
                    (url.contains(newMWebtoon.get(position).getmKoreanUrl()))

                     )
                return true;


            else
            return false;
      }
    };

    //above is the end of webviewclient


    public void loadKoreanWebView (ObservableWebView v, String url, android.webkit.WebViewClient korWebviewClient){
        v.setWebViewClient(korWebviewClient);
        v.getSettings().setJavaScriptEnabled(true);
        v.getSettings().setDomStorageEnabled(true);
        v.setOverScrollMode(ObservableWebView.OVER_SCROLL_NEVER);
        v.loadUrl(url);

    }

    public void loadEnglishWebView(ObservableWebView v, String url, android.webkit.WebViewClient webViewClientEnglish){
        v.setWebViewClient(webViewClientEnglish);
        v.getSettings().setJavaScriptEnabled(true);
        v.getSettings().setDomStorageEnabled(true);
        v.setOverScrollMode(ObservableWebView.OVER_SCROLL_NEVER);
        v.loadUrl(url);
    }


    public void linkPages(){
        counter++;
        if(counter%2==0){
            pageChangeLinked = false;
            Toast.makeText(getApplicationContext(), getString(R.string.page_unlinked),Toast.LENGTH_LONG).show();// Set your own toast  message    }

        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.page_linked),Toast.LENGTH_LONG).show();// Set your own toast  message    }
            pageChangeLinked = true;
    }}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.hide();
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen
        setContentView(R.layout.activity_toon_layout);

        koreanWebView = findViewById(R.id.korean_web_view);
        englishWebView = findViewById(R.id.english_web_view);
        newMWebtoon = MainActivity.getmWebtoons();

        Log.v("click Webtoon", "works");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");

            //The key argument here must match that used in the other activity
        }



        if (position ==0) {
            missingEpisodeArray = missingEpisodeArray0;
            Log.v("WebtoonActivity100", "kuberaMissingEpisodesArray");

        }
        if (position == 1) {
            missingEpisodeArray = towerOfGodMissingEpisodesArray;
            Log.v("WebtoonActivity100", "towerOfGodMissingEpisodesArray");
        }
        if (position == 2){
            missingEpisodeArray = theSecretofAngelMissingEpisode;
        }

        Log.v("positionWeb", Integer.toString(position));
        SharedPreferences englishPrefs = getSharedPreferences(LAST_ENGLISH_WEBVIEW_URL, MODE_PRIVATE);
        SharedPreferences koreanPrefs = getSharedPreferences(LAST_KOREAN_WEBVIEW_URL, MODE_PRIVATE);

        savedEnglishUrl = englishPrefs.getString(Integer.toString(position), "No name defined");
        savedKoreanURL = koreanPrefs.getString((Integer.toString(position)), "No name defined");
//            mWebtoons.get(position).setmSavedEnglishURL(savedEnglishUrl);


        if (savedEnglishUrl.equals("No name defined")) {
            loadEnglishWebView(englishWebView, newMWebtoon.get(position).getmEnglishUrl(), engWebViewClient);
        }
        else{
            loadEnglishWebView(englishWebView, savedEnglishUrl, engWebViewClient);}
        Log.v("SavedEnglishURL", savedEnglishUrl);

        loadKoreanWebView(koreanWebView, newMWebtoon.get(position).getmKoreanUrl(), korWebviewClient);

        if (savedEnglishUrl.equals("No name defined")){
            loadKoreanWebView(koreanWebView, newMWebtoon.get(position).getmKoreanUrl(), korWebviewClient);
        }
        else {
            loadEnglishWebView(koreanWebView, savedKoreanURL, korWebviewClient);
        }
        Log.v("SavedKoreanURL", savedKoreanURL);
        Log.v("SavedEnglishURL", savedEnglishUrl);



        englishWebView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (koreanWebtoonLoading||englishWebtoonLoading){
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    koreanWebViewTouched = false;
                    englishWebViewTouched = true;
//                    Log.v(domainLogs, savedKoreanURL);
//                    Log.v(domainLogs, savedEnglishUrl);
//                    Log.v(domainLogs, englishWebView.getUrl());
                    // Do what you want
                }

                return false;
            }
        });

        koreanWebView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (koreanWebtoonLoading||englishWebtoonLoading){
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    koreanWebViewTouched = true;
                    englishWebViewTouched = false;
//                    Log.v("SavedKoreanURL", savedKoreanURL);
//                    Log.v("SavedEnglishURL", savedEnglishUrl);
//                    // Do what you want
                }
                return false;

            }

        });

        //set on scroll listeners
        koreanWebView.setOnScrollChangedCallback(new ObservableWebView.OnScrollChangedCallback(){
            public void onScroll(int l, int t){
                if (koreanWebViewTouched & koreanWebView.getUrl().contains("no=" )& scrollLinked) {
                    englishWebView.scrollTo(0, t);
                    //Do stuff
                }}
        });

        englishWebView.setOnScrollChangedCallback(new ObservableWebView.OnScrollChangedCallback(){
            public void onScroll(int l, int t){

                //   t = scrollYKorean;
                if (englishWebViewTouched & englishWebView.getUrl().contains("episode_no") & scrollLinked){
                    koreanWebView.scrollTo(0, t);
                    //Do stuff
                }
            }
        });

        englishWebViewToolbar = findViewById(R.id.english_web_view_toolbar);
        koreanWebViewToolbar = findViewById(R.id.korean_web_view_toolbar);

        englishScrollLink = findViewById(R.id.english_scroll_toggle);
        englishScrollLink.setBackgroundResource(R.drawable.ic_chain_link);

        englishPageLink = findViewById(R.id.english_page_toggle);
        englishPageLink.setBackgroundResource(R.drawable.ic_page_link);

        englishPageLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkPages();
            }
        });


        englishScrollLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(counter%2==0){
                    scrollLinked = false;
                    Toast.makeText(getApplicationContext(), getString(R.string.scroll_unlink),Toast.LENGTH_LONG).show();// Set your own toast  message    }

                } else {
                    scrollLinked = true;
                    Toast.makeText(getApplicationContext(), getString(R.string.scroll_linked),Toast.LENGTH_LONG).show();// Set your own toast  message    }
                }

            }
        });
        koreanScrollLink = findViewById(R.id.korean_scroll_toggle);
        koreanScrollLink.setBackgroundResource(R.drawable.ic_chain_link);
        koreanScrollLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(counter%2==0){
                    scrollLinked =false;
                    Toast.makeText(getApplicationContext(), getString(R.string.scroll_unlink),Toast.LENGTH_LONG).show();// Set your own toast  message    }

                } else {
                    scrollLinked = true;
                    Toast.makeText(getApplicationContext(), getString(R.string.scroll_linked),Toast.LENGTH_LONG).show();// Set your own toast  message    }



                }

            }
        });
        englishBackButton = findViewById(R.id.english_back_button);
        englishBackButton.setBackgroundResource(R.drawable.ic_back_button);
        englishBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                englishWebView.canGoBack();
                englishWebView.goBack();

            }
        });
        windowImageView = findViewById(R.id.window_image_english);
        windowImageView.setBackgroundResource(R.drawable.ic_fullscreen_exit_white_24dp);
        windowImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                counter++;
                if(counter%2==0 && !koreanViewIsMinimized){
                    englishWebView.setVisibility(View.GONE);
                    englishViewisMinimized = true;
                    windowImageView.setBackgroundResource(R.drawable.ic_fullscreen_white_24dp);

                } else {
                    englishWebView.setVisibility(View.VISIBLE);
                    englishViewisMinimized = false;
                    windowImageView.setBackgroundResource(R.drawable.ic_fullscreen_exit_white_24dp);

                    koreanWebView.scrollTo(scrollXEnglish, scrollYEnglish);

                }
                Toast.makeText(getApplicationContext(),"Click Listener Is Working",Toast.LENGTH_LONG).show();// Set your own toast  message    }
            }
        });

        koreanPageLink = findViewById(R.id.korean_page_toggle);
        koreanPageLink.setBackgroundResource(R.drawable.ic_page_link);

        koreanPageLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkPages();
            }
        });
        koreanBackButton = findViewById(R.id.korean_back_button);
        koreanBackButton.setBackgroundResource(R.drawable.ic_back_button);
        koreanBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                koreanWebView.canGoBack();
                koreanWebView.goBack();
            }
        });
        windowImageViewKorean = findViewById(R.id.window_image_korean);
        windowImageViewKorean.setBackgroundResource(R.drawable.ic_fullscreen_exit_white_24dp);
        windowImageViewKorean.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                counter++;
                if(counter%2==0 && !englishViewisMinimized){
                    koreanWebView.setVisibility(View.GONE);
                    windowImageViewKorean.setBackgroundResource(R.drawable.ic_fullscreen_white_24dp);
                    koreanViewIsMinimized = true;

                } else {
                    koreanWebView.setVisibility(View.VISIBLE);
                    windowImageViewKorean.setBackgroundResource(R.drawable.ic_fullscreen_exit_white_24dp);
                    koreanViewIsMinimized = false;

                }
                Toast.makeText(getApplicationContext(),"Click Listener Is Working",Toast.LENGTH_LONG).show();// Set your own toast  message    }
            }
        });


}

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //  save KoreanURL
        SharedPreferences.Editor koreanEditor = getSharedPreferences(LAST_KOREAN_WEBVIEW_URL, MODE_PRIVATE).edit();
        koreanUrlkey = Integer.toString(position);
        koreanEditor.putString(koreanUrlkey, koreanWebView.getUrl());
        koreanEditor.apply();

        //  add Save English URL.
        SharedPreferences.Editor englishEditor = getSharedPreferences(LAST_ENGLISH_WEBVIEW_URL, MODE_PRIVATE).edit();
        englishUrlKey = Integer.toString(position);
        englishEditor.putString(englishUrlKey, englishWebView.getUrl());
        englishEditor.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //  save KoreanURL
        SharedPreferences.Editor koreanEditor = getSharedPreferences(LAST_KOREAN_WEBVIEW_URL, MODE_PRIVATE).edit();
        koreanUrlkey = Integer.toString(position);
        koreanEditor.putString(koreanUrlkey, koreanWebView.getUrl());
        koreanEditor.apply();

        // add Save English URL.
        SharedPreferences.Editor englishEditor = getSharedPreferences(LAST_ENGLISH_WEBVIEW_URL, MODE_PRIVATE).edit();
        englishUrlKey = Integer.toString(position);
        englishEditor.putString(englishUrlKey, englishWebView.getUrl());
        englishEditor.apply();
    }
}
