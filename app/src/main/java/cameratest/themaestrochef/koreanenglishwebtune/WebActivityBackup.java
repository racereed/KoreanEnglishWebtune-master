package cameratest.themaestrochef.koreanenglishwebtune;

public class WebActivityBackup {
//
//    package cameratest.themaestrochef.koreanenglishwebtune;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.webkit.JavascriptInterface;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.Button;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//    public class WebToonActivity extends AppCompatActivity {
//        private ObservableWebView englishWebView;
//        static  int iWebClientCounter=0;
//        private ObservableWebView koreanWebView;
//        private MainAdapter mMainAdapter;
//
//        Button windowImageView;
//        Button windowImageViewKorean;
//        int counter = 1;
//        int scrollXEnglish;
//        int scrollYEnglish;
//        static boolean koreanWebViewTouched = false;
//        static boolean englishWebViewTouched = false;
//        String koreanUrlTitleID = "131385";
//        String englishUrlTitleID= "83";
//
//        private ArrayList<Webtoon> mWebtoons = null;
//
//        //You can be pretty confident that the intent will not be null here.
//
//        // Get the extras (if there are any)
//        private ArrayList<Webtoon> newMWebtoon = null;
//
//        int position;
//        String koreanUrlBase = "https://m.comic.naver.com/webtoon/detail.nhn?titleId=131385&no=";
//        String englishUrlBase= "https://www.webtoons.com/en/fantasy/kubera/list?title_no=83&episode_no=";
//        String koreanEpisodeGuideUrl = "https://m.comic.naver.com/webtoon/list.nhn?titleId=131385";
//        String englishEpisodeGuideUrl = "https://www.webtoons.com/en/fantasy/kubera/list?title_no=83";
//        //String koreanEpisodeGuideUrl = newMWebtoon.get(0).getmKoreanUrl();
////    String englishEpisodeGuideUrl = newMWebtoon.get(0).getmEnglishUrl();
//        String koreanUrl;
//
//        WebViewClient korWebviewClient = new MyWebViewClient() {
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//
//                //   view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
//                iWebClientCounter++;
//                if (iWebClientCounter > 0 & koreanWebViewTouched ) {
//                    if(koreanWebView.getUrl().contains("no=")) {
//
//                        // String koreanEpisodeNumberHelper = koreanWebView.getUrl().substring(koreanWebView.getUrl().indexOf("no=" ) + 3);
//                        String koreanEpisodeNumber[] =  koreanWebView.getUrl().substring(koreanWebView.getUrl().indexOf("no=") + 3).split("&");
//                        koreanUrl = koreanUrlBase + koreanEpisodeNumber[0];
//
//                        String englishURL = englishUrlBase + koreanEpisodeNumber[0];
//                        int koreanEpisodeNumberInt= Integer.parseInt(koreanEpisodeNumber[0]);
//                        if (koreanEpisodeNumberInt<24){
//                            englishWebView.loadUrl(englishURL);
//                        }
//                        if(koreanEpisodeNumberInt ==24){
//                            englishWebView.loadUrl("file:///android_res/drawable/no_translation_exist_image.png");
//                        }
//                        if(koreanEpisodeNumberInt>24){
//                            int newKoreanEpisdodeNumber = koreanEpisodeNumberInt - 1;
//                            String newEnglishUrl = englishUrlBase + Integer.toString(newKoreanEpisdodeNumber);
//                            englishWebView.loadUrl(newEnglishUrl);
//                        }
//
//                        Log.v("korean url", "onPageFinished: " + koreanWebView.getUrl());
//                        Log.v("lastkoreanepisodenumber", "onPageFinished: " + koreanEpisodeNumber[0]);
//                        Log.v("englishURL", "onPageFinished: " + englishURL);
//
//
//                        koreanWebViewTouched = false;
//                    }
//                }
//                if (iWebClientCounter > 0 & englishWebViewTouched) {
//                    if(englishWebView.getUrl().contains("episode_no=")) {
//
//                        // String koreanEpisodeNumberHelper = koreanWebView.getUrl().substring(koreanWebView.getUrl().indexOf("no=" ) + 3);
//                        String englishEpisodeNumber =  englishWebView.getUrl().substring(englishWebView.getUrl().indexOf("episode_no=" ) + 11);
//                        int englishEpisodeNumberInt = Integer.parseInt(englishEpisodeNumber);
//                        if (englishEpisodeNumberInt<=23){
//                            String koreanUrl = koreanUrlBase + englishEpisodeNumber;
//                            koreanWebView.loadUrl(koreanUrl);
//                        }
//                        if(englishEpisodeNumberInt>23) {
//                            int newEnglishEpisodeNumber = englishEpisodeNumberInt + 1;
//                            String koreanUrl = koreanUrlBase + Integer.toString(newEnglishEpisodeNumber);
//                            koreanWebView.loadUrl(koreanUrl);
//
//                            Log.e("englishurl", "onPageFinished: " + englishWebView.getUrl());
//                            Log.wtf("lastEnglishEpisodeNum", "onPageFinished: " + englishEpisodeNumber);
//                            Log.wtf("koreandURL", "onPageFinished: " + koreanUrl);
//
//                        }
//                        englishWebViewTouched = false;
//
//                    }
//                };
//
//
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if(url.contains(koreanUrlTitleID)){
//                    return false;
//                }
//                if(url.contains(englishUrlTitleID)){
//                    return false;
//                }
//                return false;
//            }
//        };
//
//        public void loadWebview (ObservableWebView v, String url, android.webkit.WebViewClient korWebviewClient){
//            v.setWebViewClient(korWebviewClient);
//            v.getSettings().setJavaScriptEnabled(true);
//            v.getSettings().setDomStorageEnabled(true);
//            v.setOverScrollMode(ObservableWebView.OVER_SCROLL_NEVER);
//            v.loadUrl(url);
//
//        }
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_toon_layout);
//            koreanWebView = findViewById(R.id.korean_web_view);
//            englishWebView = findViewById(R.id.english_web_view);
//            newMWebtoon = MainActivity.getmWebtoons();
//            Log.v("click Webtoon", "works");
//
//            Bundle extras = getIntent().getExtras();
//            if (extras != null) {
//                position = extras.getInt("position");
//
//                //The key argument here must match that used in the other activity
//            }
//
//            loadWebview (koreanWebView, newMWebtoon.get(position).getmKoreanUrl(), korWebviewClient);
//            loadWebview(englishWebView, newMWebtoon.get(position).getmEnglishUrl(), korWebviewClient);
//
//
//            koreanWebView.setOnTouchListener(new View.OnTouchListener() {
//                public boolean onTouch(View v, MotionEvent event) {
//                    if(event.getAction() == MotionEvent.ACTION_UP){
//                        koreanWebViewTouched = true;
//                        englishWebViewTouched = false;
//
//                        // Do what you want
//                    }
//                    return false;
//                }
//            });
//
//
//            korWebviewClient.onPageFinished(koreanWebView, koreanEpisodeGuideUrl);
//            korWebviewClient.onPageFinished(englishWebView, englishEpisodeGuideUrl);
//
//            englishWebView.setOnTouchListener(new View.OnTouchListener() {
//                public boolean onTouch(View v, MotionEvent event) {
//                    if(event.getAction() == MotionEvent.ACTION_UP){
//                        koreanWebViewTouched = false;
//                        englishWebViewTouched = true;
//
//                        // Do what you want
//                    }
//
//                    return false;
//                }
//            });
//
//
//
//            //set on scroll listeners
//            koreanWebView.setOnScrollChangedCallback(new ObservableWebView.OnScrollChangedCallback(){
//                public void onScroll(int l, int t){
//                    if (koreanWebViewTouched & koreanWebView.getUrl().contains("no=")) {
//                        englishWebView.scrollTo(0, t);
//                        //Do stuff
//                    }}
//            });
//
//            englishWebView.setOnScrollChangedCallback(new ObservableWebView.OnScrollChangedCallback(){
//                public void onScroll(int l, int t){
//                    //   t = scrollYKorean;
//                    if (englishWebViewTouched & englishWebView.getUrl().contains("episode_no")){
//                        koreanWebView.scrollTo(0, t);
//                        //Do stuff
//                    }
//                }
//            });
//            windowImageView = findViewById(R.id.window_image_english);
//            windowImageView.setBackgroundResource(R.drawable.ic_fullscreen_exit_white_24dp);
//            windowImageView.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View v)
//                {
//                    counter++;
//                    if(counter%2==0){
//                        englishWebView.setVisibility(View.GONE);
//                        windowImageView.setBackgroundResource(R.drawable.ic_fullscreen_white_24dp);
//
//                    } else {
//                        englishWebView.setVisibility(View.VISIBLE);
//                        windowImageView.setBackgroundResource(R.drawable.ic_fullscreen_exit_white_24dp);
//
//                        koreanWebView.scrollTo(scrollXEnglish, scrollYEnglish);
//
//                    }
//                    Toast.makeText(getApplicationContext(),"Click Listener Is Working",Toast.LENGTH_LONG).show();// Set your own toast  message    }
//                }
//            });
//            windowImageViewKorean = findViewById(R.id.window_image_korean);
//            windowImageViewKorean.setBackgroundResource(R.drawable.ic_fullscreen_exit_white_24dp);
//            windowImageViewKorean.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View v)
//                {
//                    counter++;
//                    if(counter%2==0){
//                        koreanWebView.setVisibility(View.GONE);
//                        windowImageViewKorean.setBackgroundResource(R.drawable.ic_fullscreen_white_24dp);
//
//
//                    } else {
//                        koreanWebView.setVisibility(View.VISIBLE);
//                        windowImageViewKorean.setBackgroundResource(R.drawable.ic_fullscreen_exit_white_24dp);
//
//
//                    }
//                    Toast.makeText(getApplicationContext(),"Click Listener Is Working",Toast.LENGTH_LONG).show();// Set your own toast  message    }
//                }
//            });
//
//
//
//
//            class MyJavaScriptInterface {
//                @JavascriptInterface
//                public void onUrlChange(String url) {
//                    Log.d("hydrated", "onUrlChange" + url);
//                    Toast.makeText(getApplicationContext(),"WebViewCientworking",Toast.LENGTH_SHORT).show();// Set your own toast  message    }
//
//                }}
//
//        }
//
//
////    // Logo AsyncTask
////    private class Logo extends AsyncTask<Void, Void, Void> {
////        Bitmap bitmap;
////
////
////
////        @Override
////        protected Void doInBackground(Void... params) {
////
////            try {
////                // Connect to the web site
////                Document document = Jsoup.connect(koreanUrl).get();
////                // Using Elements to get the class data
////                Elements img = document.select("div[class=comicinfo] img[src]");
////                // Locate the src attribute
////                String imgSrc = img.attr("src");
////                // Download image from URL
////                InputStream input = new java.net.URL(imgSrc).openStream();
////                // Decode Bitmap
////                bitmap = BitmapFactory.decodeStream(input);
////
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            return null;
////        }
////
////        @Override
////        protected void onPostExecute(Void result) {
////
////
////        }
////    }
//    }
}
