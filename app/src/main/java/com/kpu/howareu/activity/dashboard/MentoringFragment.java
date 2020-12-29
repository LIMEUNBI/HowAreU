package com.kpu.howareu.activity.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.community.CommunityActivity;
import com.kpu.howareu.activity.community.EmploymentActivity;
import com.kpu.howareu.activity.community.SchoolActivity;
import com.kpu.howareu.activity.mentor.MentorInfoActivity;
import com.kpu.howareu.common.config.Config;
import com.kpu.howareu.common.model.MentorInfo;
import com.kpu.howareu.common.model.PostInfo;
import com.kpu.howareu.common.model.SelfInfo;
import com.kpu.howareu.common.utils.SharedPreferenceBase;

import java.util.ArrayList;
import java.util.List;

public class MentoringFragment extends Fragment {

    private View mView = null;
    private static MentoringFragment instance = null;

    private TextView mTxtMentorName;
    private TextView mTxtMentorUniverse;
    private Button mBtnProfile;
    private ListView mListView;
    private ListAdapter mListAdapter;

    private List<MentorInfo> mListData = new ArrayList<>();

    public static MentoringFragment getInstance() {
        if (instance == null) {
            instance = new MentoringFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_mentoring, container, false);

        mTxtMentorName = mView.findViewById(R.id.mentor_name);
        mTxtMentorUniverse = mView.findViewById(R.id.mentor_universe);
        mBtnProfile = mView.findViewById(R.id.btn_profile);

        mListView = mView.findViewById(R.id.list_view);
        mListAdapter = new ListAdapter(getContext(), R.layout.item_knowledge_list, (ArrayList<MentorInfo>) mListData);
        mListView.setAdapter(mListAdapter);

        String userName = SharedPreferenceBase.getPrefString(getContext(), Config.USER_NAME, "");
        if (TextUtils.isEmpty(userName)) {
            userName = "신영준";
        }
        mTxtMentorName.setText(userName);
        mTxtMentorUniverse.setText("한국산업기술대학교");

        mBtnProfile.setOnClickListener(v -> {

        });

        mListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getActivity(), MentorInfoActivity.class);
            startActivity(intent);
        });

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        mListData.clear();

        MentorInfo mentorInfo1 = new MentorInfo();
        mentorInfo1.mentorName = "전유미";
        mentorInfo1.mentorType = "수시합격";
        mentorInfo1.mentorStar = 5;
        mentorInfo1.mentorUniverse = "이화여자대학교 / 섬유패션학부 4학년";
        mentorInfo1.mentorPeople = 4;
        mListData.add(mentorInfo1);

        MentorInfo mentorInfo2 = new MentorInfo();
        mentorInfo2.mentorName = "정종희";
        mentorInfo2.mentorType = "수시합격";
        mentorInfo2.mentorStar = 5;
        mentorInfo2.mentorUniverse = "국민대학교 / 시각디자인과 3학년";
        mentorInfo2.mentorPeople = 5;
        mListData.add(mentorInfo2);

        MentorInfo mentorInfo3 = new MentorInfo();
        mentorInfo3.mentorName = "김지호";
        mentorInfo3.mentorType = "정시합격";
        mentorInfo3.mentorStar = 5;
        mentorInfo3.mentorUniverse = "건국대학교 / 영상디자인과 3학년";
        mentorInfo3.mentorPeople = 3;
        mListData.add(mentorInfo3);

        MentorInfo mentorInfo4 = new MentorInfo();
        mentorInfo4.mentorName = "조원준";
        mentorInfo4.mentorType = "정시합격";
        mentorInfo4.mentorStar = 4;
        mentorInfo4.mentorUniverse = "중앙대학교 / 실내환경디자인과 4학년";
        mentorInfo4.mentorPeople = 4;
        mListData.add(mentorInfo4);

        MentorInfo mentorInfo5 = new MentorInfo();
        mentorInfo5.mentorName = "전유진";
        mentorInfo5.mentorType = "수시합격";
        mentorInfo5.mentorStar = 5;
        mentorInfo5.mentorUniverse = "한양대학교 / 실내건축디자인과 4학년";
        mentorInfo5.mentorPeople = 5;
        mListData.add(mentorInfo5);

        mListAdapter.notifyDataSetChanged();
    }

    public class ListAdapter extends ArrayAdapter<MentorInfo> {
        private List<MentorInfo> items;
        private Context context;

        public ListAdapter(Context context, int textViewResourceId, ArrayList<MentorInfo> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.context = context;
        }

        @Override
        public MentorInfo getItem(int position) {
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
                convertView = inflater.inflate(R.layout.item_mentor_list, null);
                holder = new ListAdapter.ViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ListAdapter.ViewHolder) convertView.getTag();
            }

            final MentorInfo mentorInfo = items.get(position);

            holder.mentorImg.setImageResource(MentorInfo.mentorImgMap.get(mentorInfo.mentorName));
            holder.mentorName.setText(mentorInfo.mentorName);
            holder.mentorType.setText(mentorInfo.mentorType);

            if (mentorInfo.mentorStar == 0) {
                holder.mentorStar1.setImageResource(R.drawable.ic_star_empty);
                holder.mentorStar2.setImageResource(R.drawable.ic_star_empty);
                holder.mentorStar3.setImageResource(R.drawable.ic_star_empty);
                holder.mentorStar4.setImageResource(R.drawable.ic_star_empty);
                holder.mentorStar5.setImageResource(R.drawable.ic_star_empty);
            } else if (mentorInfo.mentorStar == 1) {
                holder.mentorStar1.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar2.setImageResource(R.drawable.ic_star_empty);
                holder.mentorStar3.setImageResource(R.drawable.ic_star_empty);
                holder.mentorStar4.setImageResource(R.drawable.ic_star_empty);
                holder.mentorStar5.setImageResource(R.drawable.ic_star_empty);
            } else if (mentorInfo.mentorStar == 2) {
                holder.mentorStar1.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar2.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar3.setImageResource(R.drawable.ic_star_empty);
                holder.mentorStar4.setImageResource(R.drawable.ic_star_empty);
                holder.mentorStar5.setImageResource(R.drawable.ic_star_empty);
            } else if (mentorInfo.mentorStar == 3) {
                holder.mentorStar1.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar2.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar3.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar4.setImageResource(R.drawable.ic_star_empty);
                holder.mentorStar5.setImageResource(R.drawable.ic_star_empty);
            } else if (mentorInfo.mentorStar == 4) {
                holder.mentorStar1.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar2.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar3.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar4.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar5.setImageResource(R.drawable.ic_star_empty);
            } else if (mentorInfo.mentorStar == 5) {
                holder.mentorStar1.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar2.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar3.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar4.setImageResource(R.drawable.ic_star_full);
                holder.mentorStar5.setImageResource(R.drawable.ic_star_full);
            }

            holder.mentorUniverse.setText(mentorInfo.mentorUniverse);
            holder.mentorPeople.setText("인원 " + mentorInfo.mentorPeople + "/5");
            if (mentorInfo.mentorPeople == 5) {
                holder.mentorPeople.setTextColor(getResources().getColor(R.color.text_e95e6c));
            } else {
                holder.mentorPeople.setTextColor(getResources().getColor(R.color.howareu_main));
            }

            return convertView;
        }

        public class ViewHolder {
            ImageView mentorImg;
            TextView mentorName;
            TextView mentorType;
            ImageView mentorStar1;
            ImageView mentorStar2;
            ImageView mentorStar3;
            ImageView mentorStar4;
            ImageView mentorStar5;
            TextView mentorUniverse;
            TextView mentorPeople;

            public ViewHolder(View itemView) {
                mentorImg = itemView.findViewById(R.id.img_profile);
                mentorName = itemView.findViewById(R.id.mentor_name);
                mentorType = itemView.findViewById(R.id.mentor_type);
                mentorStar1 = itemView.findViewById(R.id.mentor_star1);
                mentorStar2 = itemView.findViewById(R.id.mentor_star2);
                mentorStar3 = itemView.findViewById(R.id.mentor_star3);
                mentorStar4 = itemView.findViewById(R.id.mentor_star4);
                mentorStar5 = itemView.findViewById(R.id.mentor_star5);
                mentorUniverse = itemView.findViewById(R.id.mentor_universe);
                mentorPeople = itemView.findViewById(R.id.mentor_people);
            }
        }
    }
}
