package com.kpu.howareu.activity.community;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;
import com.kpu.howareu.activity.dashboard.MainActivity;
import com.kpu.howareu.common.config.Config;
import com.kpu.howareu.common.model.PostInfo;

import java.util.ArrayList;
import java.util.List;

public class SchoolActivity extends BaseActivity {

    private TextView mToolbarTitle;

    private ImageView mImgBefore;
    private ImageView mImgSearch;
    private ImageView mImgWrite;

    private ListView mListView;
    private ListAdapter mListAdapter;

    private List<PostInfo> mListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_list);

        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbarTitle.setText("학교생활");

        mImgBefore = findViewById(R.id.img_before);
        mImgSearch = findViewById(R.id.img_search);
        mImgWrite = findViewById(R.id.img_write);

        mListView = findViewById(R.id.list_view);
        mListAdapter = new ListAdapter(getApplicationContext(), R.layout.item_knowledge_list, (ArrayList<PostInfo>) mListData);
        mListView.setAdapter(mListAdapter);

        mImgBefore.setOnClickListener(v -> {
            finish();
        });

        mListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(SchoolActivity.this, CommunityActivity.class);
            intent.putExtra(Config.POST_TYPE, mToolbarTitle.getText().toString());
            intent.putExtra(Config.POST_TITLE, mListData.get(i).postTitle);
            intent.putExtra(Config.POST_CONTENT, mListData.get(i).postContent);
            intent.putExtra(Config.POST_DATETIME, mListData.get(i).dateTime);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        mListData.clear();

        PostInfo postInfo1 = new PostInfo();
        postInfo1.postTitle = "생활관 멘토링";
        postInfo1.postContent = "기숙사 생활관 멘토링 모집합니다....";
        postInfo1.dateTime = "17:31";
        postInfo1.postWriter = "익명";
        postInfo1.postGoodCount = 24;
        postInfo1.postBadCount = 2;
        mListData.add(postInfo1);

        PostInfo postInfo2 = new PostInfo();
        postInfo2.postTitle = "학교근처 맛집 추천해주세요";
        postInfo2.postContent = "알면 나중에 좋을 것 같아서....";
        postInfo2.dateTime = "17:46";
        postInfo2.postWriter = "익명";
        postInfo2.postGoodCount = 3;
        postInfo2.postBadCount = 1;
        mListData.add(postInfo2);

        PostInfo postInfo3 = new PostInfo();
        postInfo3.postTitle = "마실 동아리 괜찮나요?";
        postInfo3.postContent = "마실 동아리 들어가고 싶은데....";
        postInfo3.dateTime = "17:55";
        postInfo3.postWriter = "익명";
        postInfo3.postGoodCount = 7;
        postInfo3.postBadCount = 5;
        mListData.add(postInfo3);

        PostInfo postInfo4 = new PostInfo();
        postInfo4.postTitle = "과방들은 어디있어요?";
        postInfo4.postContent = "과방에서 프린트좀 하고 싶은데....";
        postInfo4.dateTime = "18:01";
        postInfo4.postWriter = "익명";
        postInfo4.postGoodCount = 1;
        postInfo4.postBadCount = 1;
        mListData.add(postInfo4);

        PostInfo postInfo5 = new PostInfo();
        postInfo5.postTitle = "과잠 사고 싶어요";
        postInfo5.postContent = "신입생중에 맞춘 과 있는지....";
        postInfo5.dateTime = "18:14";
        postInfo5.postWriter = "익명";
        postInfo5.postGoodCount = 6;
        postInfo5.postBadCount = 2;
        mListData.add(postInfo5);

        PostInfo postInfo6 = new PostInfo();
        postInfo6.postTitle = "대학원 준비하는 사람 있나요?";
        postInfo6.postContent = "대학원진학에 고민이 있는데....";
        postInfo6.dateTime = "18:32";
        postInfo6.postWriter = "익명";
        postInfo6.postGoodCount = 5;
        postInfo6.postBadCount = 1;
        mListData.add(postInfo6);

        PostInfo postInfo7 = new PostInfo();
        postInfo7.postTitle = "괜찮은 정형외과 있나요ㅜㅜ";
        postInfo7.postContent = "허리가 너무 아파서ㅜㅜ....";
        postInfo7.dateTime = "19:03";
        postInfo7.postWriter = "익명";
        postInfo7.postGoodCount = 3;
        postInfo7.postBadCount = 0;
        mListData.add(postInfo7);

        PostInfo postInfo8 = new PostInfo();
        postInfo8.postTitle = "헬스장 다녀보신분";
        postInfo8.postContent = "학교 헬스장 시설 괜찮나요?...";
        postInfo8.dateTime = "19:32";
        postInfo8.postWriter = "익명";
        postInfo8.postGoodCount = 4;
        postInfo8.postBadCount = 0;
        mListData.add(postInfo8);

        PostInfo postInfo9 = new PostInfo();
        postInfo9.postTitle = "학교에 휴게공간 어디어디 있나요?";
        postInfo9.postContent = "새내기라 아직 모르는게 많은데....";
        postInfo9.dateTime = "19:47";
        postInfo9.postWriter = "익명";
        postInfo9.postGoodCount = 1;
        postInfo9.postBadCount = 0;
        mListData.add(postInfo9);

        mListAdapter.notifyDataSetChanged();
    }

    public class ListAdapter extends ArrayAdapter<PostInfo> {
        private List<PostInfo> items;
        private Context context;

        public ListAdapter(Context context, int textViewResourceId, ArrayList<PostInfo> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.context = context;
        }

        @Override
        public PostInfo getItem(int position) {
            items.get(position);
            return super.getItem(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_knowledge_list, null);
                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final PostInfo postInfo = items.get(position);

            holder.knowledgeTitle.setText(postInfo.postTitle);
            holder.knowledgeContent.setText(postInfo.postContent);
            holder.knowledgeDate.setText(postInfo.dateTime + "");
            holder.knowledgeWriter.setText(postInfo.postWriter);
            holder.knowledgeGood.setText("공감 " + postInfo.postGoodCount);
            holder.knowledgeBad.setText("비공감 " + postInfo.postBadCount);

            if (position == items.size() - 1) {
                holder.lineHolder.setVisibility(View.GONE);
            } else {
                holder.lineHolder.setVisibility(View.VISIBLE);
            }

            return convertView;
        }

        public class ViewHolder {
            TextView knowledgeTitle;
            TextView knowledgeContent;
            TextView knowledgeDate;
            TextView knowledgeWriter;
            TextView knowledgeGood;
            TextView knowledgeBad;
            View lineHolder;

            public ViewHolder(View itemView) {
                knowledgeTitle = itemView.findViewById(R.id.knowledge_title);
                knowledgeContent = itemView.findViewById(R.id.knowledge_content);
                knowledgeDate = itemView.findViewById(R.id.knowledge_datetime);
                knowledgeWriter = itemView.findViewById(R.id.knowledge_writer);
                knowledgeGood = itemView.findViewById(R.id.knowledge_good);
                knowledgeBad = itemView.findViewById(R.id.knowledge_bad);
                lineHolder = itemView.findViewById(R.id.line);
            }
        }
    }
}

