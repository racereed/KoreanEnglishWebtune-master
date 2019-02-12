package cameratest.themaestrochef.koreanenglishwebtune;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //ToDo add an onclick listener to the picture item in recycler and set that to open the webtoon webview
    // URL Address
    private static final String TAG = "MainActivity";
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    public static ArrayList<Webtoon> mWebtoons = null;
    String title;


    public static ArrayList<Webtoon> getmWebtoons(){
        return mWebtoons;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mWebtoons = new ArrayList<Webtoon>();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher_round);
        actionBar.setTitle(R.string.subtitle);

        //Kubera
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=131385",null,
                "https://www.webtoons.com/en/fantasy/kubera/list?title_no=83",
                "https://www.webtoons.com/en/fantasy/kubera/season-3-ep-81-crime-and-punishment-8/viewer?title_no=83&episode_no=",
                "https://shared-comic.pstatic.net/thumb/webtoon/131385/thumbnail/thumbnail_IMAG06_f48e0c33-9204-474d-bebc-0ccf2ef47732.jpg",
                null));
        //Tower of god
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=183559",null,
                "https://www.webtoons.com/en/fantasy/tower-of-god/list?title_no=95",
                "https://www.webtoons.com/en/fantasy/tower-of-god/season-2-ep-322/viewer?title_no=95&episode_no=",
                "https://shared-comic.pstatic.net/thumb/webtoon/183559/thumbnail/title_thumbnail_20160516123017_t125x101.jpg",
                null));

//WindBreaker
           mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=602910", null,
                "https://m.webtoons.com/en/action/wind-breaker/list?title_no=372",
                        "https://www.webtoons.com/en/action/wind-breaker/ep-0/viewer?title_no=372&episode_no=",
                "https://shared-comic.pstatic.net/thumb/webtoon/602910/thumbnail/thumbnail_IMAG19_1f4a6964-1b51-4d64-a84d-f644ca72a5c3.jpg",
                null));

        // 8 days of hannah
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=694805", null, "https://www.webtoons.com/en/romance/days-of-hana/list?title_no=1246&page=1",
                "https://www.webtoons.com/en/romance/days-of-hana/ep-83/viewer?title_no=1246&episode_no=",
                "https://shared-comic.pstatic.net/thumb/webtoon/694805/thumbnail/thumbnail_IMAG09_eba71d8b-fa7a-4350-aa6a-748687bdaf80.jpg",
                null));
        //Noblesse
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=25455", null, "https://www.webtoons.com/en/fantasy/noblesse/list?title_no=87",

                "https://www.webtoons.com/en/fantasy/noblesse/ep-532/viewer?title_no=87&episode_no=","https://shared-comic.pstatic.net/thumb/webtoon/25455/thumbnail/title_thumbnail_20100614120245_t125x101.jpg",
                null
        ));

        //Super Secret Webtoon
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=650304", null, "https://www.webtoons.com/en/romance/super-secret/list?title_no=666",

                "https://www.webtoons.com/en/romance/super-secret/epilogue-2/viewer?title_no=666&episode_no=","https://shared-comic.pstatic.net/thumb/webtoon/650304/thumbnail/title_thumbnail_20150323144620_t125x101.jpg",
                null
        ));
        //BitterSweet Life
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=387518", null,
                "https://www.webtoons.com/en/slice-of-life/a-bittersweet-life/list?title_no=294",
                "https://www.webtoons.com/en/slice-of-life/a-bittersweet-life/ep-1-member-introduction-nani/viewer?title_no=294&episode_no=",
                "https://shared-comic.pstatic.net/thumb/webtoon/387518/thumbnail/title_thumbnail_20120309181013_t125x101.jpg",
                null
        ));
        //Lookism
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=641253", null,
                "https://www.webtoons.com/en/drama/lookism/list?title_no=1049",
                "https://www.webtoons.com/en/drama/lookism/ep-214/viewer?title_no=1049&episode_no=",
                "https://shared-comic.pstatic.net/thumb/webtoon/641253/thumbnail/title_thumbnail_20141120112141_t125x101.jpg",
                null
        ));

        //TheGamer
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=552960", null,
                "https://www.webtoons.com/en/fantasy/the-gamer/list?title_no=88",
                "https://www.webtoons.com/en/fantasy/the-gamer/season-4-ep-63/viewer?title_no=88&episode_no=",
                "https://shared-comic.pstatic.net/thumb/webtoon/552960/thumbnail/title_thumbnail_20130905153633_t125x101.jpg",
                null
        ));
        for (int i = 0; i <mWebtoons.size() ; i++) {
            String koreanEpisodeGuide = mWebtoons.get(i).getmKoreanUrl().replace("list", "detail");
            Log.v("MainActivity100", koreanEpisodeGuide);
            mWebtoons.get(i).setmKoreanEposodeURL(koreanEpisodeGuide);

        }
        if (DetectConnecton.checkInternetConnection(this)) {
            new DowloadAsyncTask().execute();
        }
        else
            Toast.makeText(this, R.string.check_internet_string, Toast.LENGTH_SHORT).show();
    }

    // DowloadAsyncTask AsyncTask
    private class DowloadAsyncTask extends AsyncTask<Void, Void, ArrayList<Webtoon>> {

        final ArrayList<Webtoon> mTitle = new ArrayList<Webtoon>();
        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Getting Webtoons");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();


        }

        @Override
        protected ArrayList<Webtoon> doInBackground(Void... params) {
            try {
                for (int i = 0; i < mWebtoons.size(); i++) {
                    String mWebtoonTitleCheck = mWebtoons.get(i).getmTitle();
                    if (mWebtoonTitleCheck == null) {
                        Document document = Jsoup.connect(mWebtoons.get(i).getmKoreanUrl()).userAgent("Mozilla").get();
                        title = document.title();

                        mWebtoons.get(i).setmTitle(title);
                    }//  String newTitle = title + Integer.toString(i);
                }

//                // Connect to the web site
//                Document document = Jsoup.connect(url).userAgent("Mozilla").get();
//                // Get the html document title

            } catch (IOException e) {
                e.printStackTrace();
            }


            return mTitle;
        }

        @Override
        protected void onPostExecute(ArrayList<Webtoon> result) {

            Context context = getApplicationContext();

            mRecyclerView = findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            mAdapter = new MainAdapter(context, mWebtoons);

            mRecyclerView.setAdapter(mAdapter);

            //     Toast toast = Toast.makeText(context, text, duration);



            mProgressDialog.dismiss();
        }
    }

}

