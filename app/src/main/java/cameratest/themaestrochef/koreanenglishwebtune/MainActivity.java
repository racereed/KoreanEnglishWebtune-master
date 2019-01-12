package cameratest.themaestrochef.koreanenglishwebtune;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    ProgressDialog mProgressDialog;

    public static ArrayList<Webtoon> getmWebtoons(){
        return mWebtoons;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mWebtoons = new ArrayList<Webtoon>();
        //        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=131385&no=&seq=", "https://www.webtoons.com/en/fantasy/kubera/list?title_no=83",

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

        //The secret of angel
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=703846", null,
                "https://www.webtoons.com/id/drama/goddess/list?title_no=1392",
                "https://www.webtoons.com/id/drama/goddess/ep32/viewer?title_no=1392&episode_no=",
                "https://shared-comic.pstatic.net/thumb/webtoon/703846/thumbnail/thumbnail_IMAG06_aa715a18-fe51-4adf-b21b-5fc253ed3f32.jpg",
                "The Secret of Angel"));

        // 8 days of hannah
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=694805", null, "https://www.webtoons.com/en/romance/days-of-hana/list?title_no=1246&page=1",
                null,
                "https://shared-comic.pstatic.net/thumb/webtoon/694805/thumbnail/thumbnail_IMAG09_eba71d8b-fa7a-4350-aa6a-748687bdaf80.jpg\n",
                null));
        //Noblesse
        mWebtoons.add(new Webtoon("https://m.comic.naver.com/webtoon/list.nhn?titleId=25455", null, "https://www.webtoons.com/en/fantasy/noblesse/list?title_no=87&page=1",

                null,"https://shared-comic.pstatic.net/thumb/webtoon/25455/thumbnail/title_thumbnail_20100614120245_t125x101.jpg",
                null
        ));

        for (int i = 0; i <mWebtoons.size() ; i++) {
            String koreanEpisodeGuide = mWebtoons.get(i).getmKoreanUrl().replace("list", "detail");
            Log.v("MainActivity100", koreanEpisodeGuide);
            mWebtoons.get(i).setmKoreanEposodeURL(koreanEpisodeGuide);

        }

        new DowloadAsyncTask().execute();

    }

    // DowloadAsyncTask AsyncTask
    private class DowloadAsyncTask extends AsyncTask<Void, Void, ArrayList<Webtoon>> {

        final ArrayList<Webtoon> mTitle = new ArrayList<Webtoon>();

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

