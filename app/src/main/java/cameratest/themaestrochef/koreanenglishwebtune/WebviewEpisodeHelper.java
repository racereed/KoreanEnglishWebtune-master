package cameratest.themaestrochef.koreanenglishwebtune;

import android.util.Log;
import android.webkit.WebView;

import java.util.ArrayList;

public class WebviewEpisodeHelper {

    public void koreaWebtoonHelper(WebView koreanWebView, WebView englishWebView, int position, int[] missingEpisodes, ArrayList<Webtoon> newMWebtoon){

        String koreanEpisodeNumber[] =  koreanWebView.getUrl().substring(koreanWebView.getUrl().indexOf("no=") + 3).split("&");
        int koreanEpisodeNumberInt= Integer.parseInt(koreanEpisodeNumber[0]);

        for (int i = 0; i < missingEpisodes.length; i++) {


            if (koreanEpisodeNumberInt == missingEpisodes[i]) {
                englishWebView.loadUrl("file:///android_res/drawable/no_translation_exist_image.png");
                break;

            }
            if (koreanEpisodeNumberInt> missingEpisodes[missingEpisodes.length-1]){
                int newKoreanEpisodeNumber = koreanEpisodeNumberInt - missingEpisodes.length;
                String newEnglishURL = newMWebtoon.get(position).getmEnglishEpisodeUrl() + Integer.toString(newKoreanEpisodeNumber);
                englishWebView.loadUrl(newEnglishURL);
                break;

            }

            if (koreanEpisodeNumberInt< missingEpisodes[0]) {
                int newKoreanEpisodeNumber = koreanEpisodeNumberInt;
                String newEnglishURL = newMWebtoon.get(position).getmEnglishEpisodeUrl() + Integer.toString(newKoreanEpisodeNumber);
                englishWebView.loadUrl(newEnglishURL);

                break;

            }

            if (koreanEpisodeNumberInt > missingEpisodes[i] && koreanEpisodeNumberInt< missingEpisodes[i+1]) {
                int newKoreanEpisodeNumber = koreanEpisodeNumberInt - i -1;
                String newEnglishURL = newMWebtoon.get(position).getmEnglishEpisodeUrl() + Integer.toString(newKoreanEpisodeNumber);
                englishWebView.loadUrl(newEnglishURL);
                Log.v("123kwhatisI", Integer.toString(i));

            }
            }}
}

