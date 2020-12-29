package com.kpu.howareu.activity.self;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;
import com.kpu.howareu.common.model.SelfInfo;

import java.util.ArrayList;
import java.util.List;

public class SelfListActivity extends BaseActivity {

    private ImageView mImgBefore;
    private ListView mListView;

    private List<SelfInfo> mListData = new ArrayList<>();
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_list);

        mImgBefore = findViewById(R.id.img_before);
        mListView = findViewById(R.id.list_view);

        mImgBefore.setOnClickListener(v -> finish());

        mListAdapter = new ListAdapter(getApplicationContext(), R.layout.item_knowledge_list, (ArrayList<SelfInfo>) mListData);
        mListView.setAdapter(mListAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        mListData.clear();

        SelfInfo selfInfo1 = new SelfInfo();
        selfInfo1.universeName = "서울예술대학교";
        selfInfo1.course = "연극영화과";
        selfInfo1.type = "2020년 서울예술대학교\n종합전형 자소서";
        selfInfo1.view = 17;
        mListData.add(selfInfo1);

        SelfInfo selfInfo2 = new SelfInfo();
        selfInfo2.universeName = "한국산업기술대학교";
        selfInfo2.course = "융합디자인학과";
        selfInfo2.type = "2020년 한국산업기술대학교\n종합전형 자소서";
        selfInfo2.view = 17;
        mListData.add(selfInfo2);

        SelfInfo selfInfo3 = new SelfInfo();
        selfInfo3.universeName = "연세대학교";
        selfInfo3.course = "화학교육과";
        selfInfo3.type = "2020년 연세대학교\n종합전형 자소서";
        selfInfo3.view = 19;
        mListData.add(selfInfo3);

        SelfInfo selfInfo4 = new SelfInfo();
        selfInfo4.universeName = "숭실대학교";
        selfInfo4.course = "심리학과";
        selfInfo4.type = "2020년 숭실대학교\n종합전형 자소서";
        selfInfo4.view = 25;
        mListData.add(selfInfo4);

        SelfInfo selfInfo5 = new SelfInfo();
        selfInfo5.universeName = "건국대학교";
        selfInfo5.course = "화학공학과";
        selfInfo5.type = "2020년 건국대학교\n종합전형 자소서";
        selfInfo5.view = 35;
        mListData.add(selfInfo5);

        SelfInfo selfInfo6 = new SelfInfo();
        selfInfo6.universeName = "한양대학교";
        selfInfo6.course = "건축토목학과";
        selfInfo6.type = "2020년 한양대학교\n종합전형 자소서";
        selfInfo6.view = 23;
        mListData.add(selfInfo6);

    }

    public class ListAdapter extends ArrayAdapter<SelfInfo> {
        private List<SelfInfo> items;
        private Context context;

        public ListAdapter(Context context, int textViewResourceId, ArrayList<SelfInfo> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.context = context;
        }

        @Override
        public SelfInfo getItem(int position) {
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
            final ListAdapter.ViewHolder holder;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_self_list, null);
                holder = new ListAdapter.ViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ListAdapter.ViewHolder) convertView.getTag();
            }

            final SelfInfo selfInfo = items.get(position);

            holder.selfImg.setImageResource(SelfInfo.universeLogoMap.get(selfInfo.universeName));

            holder.selfCourse.setText(selfInfo.course);
            holder.selfType.setText(selfInfo.type);
            holder.selfCount.setText(selfInfo.view + "");

            return convertView;
        }

        public class ViewHolder {
            ImageView selfImg;
            TextView selfCourse;
            TextView selfType;
            TextView selfCount;

            public ViewHolder(View itemView) {
                selfImg = itemView.findViewById(R.id.img_uni);
                selfCourse = itemView.findViewById(R.id.self_course);
                selfType = itemView.findViewById(R.id.self_content);
                selfCount = itemView.findViewById(R.id.view_count);
            }
        }
    }
}
