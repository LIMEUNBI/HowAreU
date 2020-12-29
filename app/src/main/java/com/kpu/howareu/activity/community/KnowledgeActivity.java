package com.kpu.howareu.activity.community;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class KnowledgeActivity extends BaseActivity {

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
        mToolbarTitle.setText("전공지식");

        mImgBefore = findViewById(R.id.img_before);
        mImgSearch = findViewById(R.id.img_search);
        mImgWrite = findViewById(R.id.img_write);

        mListView = findViewById(R.id.list_view);
        mListAdapter = new ListAdapter(getApplicationContext(), R.layout.item_knowledge_list, (ArrayList<PostInfo>) mListData);
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(KnowledgeActivity.this, CommunityActivity.class);
            intent.putExtra(Config.POST_TYPE, mToolbarTitle.getText().toString());
            intent.putExtra(Config.POST_TITLE, mListData.get(i).postTitle);
            intent.putExtra(Config.POST_CONTENT, mListData.get(i).postContent);
            intent.putExtra(Config.POST_DATETIME, mListData.get(i).dateTime);
            startActivity(intent);
        });

        mImgBefore.setOnClickListener(v -> {
            finish();
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
        postInfo1.postTitle = "4학년때 많이 바쁜가요?";
        postInfo1.postContent = "취업준비도 하고 졸업작품 준비도 해야된다는데....";
        postInfo1.dateTime = "12:13";
        postInfo1.postWriter = "익명";
        postInfo1.postGoodCount = 5;
        postInfo1.postBadCount = 2;
        mListData.add(postInfo1);

        PostInfo postInfo2 = new PostInfo();
        postInfo2.postTitle = "디자인과는 학점 별로 안 중요하죠?";
        postInfo2.postContent = "선배들도 대부분 별로....";
        postInfo2.dateTime = "17:46";
        postInfo2.postWriter = "익명";
        postInfo2.postGoodCount = 3;
        postInfo2.postBadCount = 1;
        mListData.add(postInfo2);

        PostInfo postInfo3 = new PostInfo();
        postInfo3.postTitle = "2학년 수업 중 인터렉티브 디자인수업되게 좋습니다!";
        postInfo3.postContent = "특별히 사용하는 프로그램이라던가....";
        postInfo3.dateTime = "17:55";
        postInfo3.postWriter = "익명";
        postInfo3.postGoodCount = 7;
        postInfo3.postBadCount = 5;
        mListData.add(postInfo3);

        PostInfo postInfo4 = new PostInfo();
        postInfo4.postTitle = "캐릭터 만들때 주로 어떤 툴 많이쓰나요?";
        postInfo4.postContent = "종류가 워낙 많아서 헷갈리....";
        postInfo4.dateTime = "18:01";
        postInfo4.postWriter = "익명";
        postInfo4.postGoodCount = 1;
        postInfo4.postBadCount = 1;
        mListData.add(postInfo4);

        PostInfo postInfo5 = new PostInfo();
        postInfo5.postTitle = "웹퍼블리셔가 꿈인데 어떤 복수전공을 하면 좋나요?";
        postInfo5.postContent = "아직 관련 학과에 대해서는 잘몰라서....";
        postInfo5.dateTime = "18:14";
        postInfo5.postWriter = "익명";
        postInfo5.postGoodCount = 6;
        postInfo5.postBadCount = 2;
        mListData.add(postInfo5);

        PostInfo postInfo6 = new PostInfo();
        postInfo6.postTitle = "학교에서 밤 많이들 새나요? ";
        postInfo6.postContent = "저는 통학이라 집에....";
        postInfo6.dateTime = "18:32";
        postInfo6.postWriter = "익명";
        postInfo6.postGoodCount = 5;
        postInfo6.postBadCount = 1;
        mListData.add(postInfo6);

        PostInfo postInfo7 = new PostInfo();
        postInfo7.postTitle = "학년별로 어떤 활동들을 많이 하면 좋을까요?";
        postInfo7.postContent = "1,2학년때는 그냥 놀아...";
        postInfo7.dateTime = "19:03";
        postInfo7.postWriter = "익명";
        postInfo7.postGoodCount = 3;
        postInfo7.postBadCount = 0;
        mListData.add(postInfo7);

        PostInfo postInfo8 = new PostInfo();
        postInfo8.postTitle = "학원은 어떤 학원을 선택해야할까요?";
        postInfo8.postContent = "국비지원되는 학원도 많....";
        postInfo8.dateTime = "19:32";
        postInfo8.postWriter = "익명";
        postInfo8.postGoodCount = 4;
        postInfo8.postBadCount = 0;
        mListData.add(postInfo8);

        PostInfo postInfo9 = new PostInfo();
        postInfo9.postTitle = "졸업작품 언제쯤 시작하나요?";
        postInfo9.postContent = "겨울방학쯤에는....";
        postInfo9.dateTime = "19:47";
        postInfo9.postWriter = "익명";
        postInfo9.postGoodCount = 1;
        postInfo9.postBadCount = 0;
        mListData.add(postInfo9);

        PostInfo postInfo10 = new PostInfo();
        postInfo10.postTitle = "학원에 다녀야 할까요?";
        postInfo10.postContent = "제가 아는게 별로 없어서...";
        postInfo10.dateTime = "19:47";
        postInfo10.postWriter = "익명";
        postInfo10.postGoodCount = 1;
        postInfo10.postBadCount = 0;
        mListData.add(postInfo10);

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
            holder.knowledgeDate.setText(postInfo.dateTime);
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
