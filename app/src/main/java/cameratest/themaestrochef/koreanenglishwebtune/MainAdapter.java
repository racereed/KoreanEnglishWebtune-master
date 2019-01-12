package cameratest.themaestrochef.koreanenglishwebtune;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Webtoon> mWebtoons;
    public MainAdapter(Context context, ArrayList<Webtoon> webtoons) {
            mWebtoons = webtoons;
            mContext = context;
    };



    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, final int position) {

        String title1 =(mWebtoons.get(position).getmTitle().replaceAll(":: 네이버 만화", ""));
        String title2 = title1.replaceAll(":: 네이버 웹툰", "");
        holder.mTitleTextView.setText(title2);
            Picasso.get().load
                    (mWebtoons.get(position).getmImgUrl()).resize(650,550)
                    .into(holder.webtoonCoverImage);
        holder.mParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, WebToonActivity.class);
                myIntent.putExtra("position", position); //Optional parameters
                mContext.startActivity(myIntent);
                Log.v("click", "works");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWebtoons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;
        public ImageView webtoonCoverImage;
        public RelativeLayout mParentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
            webtoonCoverImage = itemView.findViewById(R.id.webtoon_cover_image);
            mParentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }


}