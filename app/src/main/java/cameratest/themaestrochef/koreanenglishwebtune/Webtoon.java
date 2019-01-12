package cameratest.themaestrochef.koreanenglishwebtune;

import java.util.ArrayList;

public class Webtoon {
    private static ArrayList<Webtoon> mWebtoon;

    //Default Urls for the webtoonsZ
    private String mKoreanUrl;
    private String mKoreanEposodeURL;
    private String mEnglishUrl;
    private String mEnglishEpisodeUrl;
    private String mImgUrl;
    private String mTitle;

    public ArrayList<Webtoon> getmWebtoon() {
        return mWebtoon;
    }

    // constructor for the new mWebtoons object
    public Webtoon(String koreanUrl, String koreanEpisodeURL, String englishUrl, String englishEpisodeURL, String imgUrl, String title ){
        mKoreanUrl = koreanUrl;
        mKoreanEposodeURL = koreanEpisodeURL;
        mEnglishUrl = englishUrl;
        mEnglishEpisodeUrl = englishEpisodeURL;

        mImgUrl = imgUrl;
        mTitle = title;


    }



    public String getmKoreanUrl() {
        return mKoreanUrl;
    }
    public String getmKoreanEposodeURL() {
        return mKoreanEposodeURL;
    }

    public String getmEnglishUrl() {
        return mEnglishUrl;
    }
    public String getmEnglishEpisodeUrl() {
        return mEnglishEpisodeUrl;
    }
    public String getmImgUrl() {
        return mImgUrl;
    }
    public String getmTitle() {
        return mTitle;
    }


    public void setmKoreanUrl(String mKoreanUrl) {
        this.mKoreanUrl = mKoreanUrl;
    }
    public void setmKoreanEposodeURL(String mKoreanEposodeURL) { this.mKoreanEposodeURL = mKoreanEposodeURL;}

    public void setmEnglishUrl(String mEnglishUrl) {
        this.mEnglishUrl = mEnglishUrl;
    }
    public void setmEnglishEpisodeUrl(String mEnglishEpisodeUrl) {
        this.mEnglishEpisodeUrl = mEnglishEpisodeUrl;
    }

    public void setmImgUrl(String mImgUrl) {
        this.mImgUrl = mImgUrl;
    }
    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }


}
