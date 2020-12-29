package com.kpu.howareu.activity.dashboard;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kpu.howareu.R;
import com.kpu.howareu.common.model.DateManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScheduleFragment extends Fragment {

    private View mView = null;
    private static ScheduleFragment instance = null;

    private DateManager mDateManager = DateManager.getInstance();

    private TextView mFirstChoice;
    private TextView mSecondChoice;
    private TextView mThirdChoice;

    private TextView mTxtUniFirst;
    private TextView mTxtUniSecond;
    private TextView mTxtUniThird;
    private ImageView mImgAdd;

    private ImageView mImgMonthBefore;
    private ImageView mImgMonthAfter;
    private TextView mTxtMonth;

    private GridView mGridView;
    private GridAdapter mGridAdapter = null;
    private ArrayList<CalendarItemData> mGridDataList = new ArrayList<>();
    private ArrayList<MessageData> mDailyListItemData = new ArrayList<>();
    private int mRowCount = 5; // GridView 의 row 수를 계산하여 저장한다.(5 or 6)
    private int mCalendarViewSelectedPosition = 0;
    private Calendar calTodayNow = Calendar.getInstance();

    private SlidingUpPanelLayout mSlidingLayout;

    public static ScheduleFragment getInstance() {
        if (instance == null) {
            instance = new ScheduleFragment();
        }
        return instance;
    }

    private class MessageData {
        String groupKey;
        Object message = null;
        long dateTime;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_schedule, container, false);

        mFirstChoice = mView.findViewById(R.id.btn_first);
        mSecondChoice = mView.findViewById(R.id.btn_second);
        mThirdChoice = mView.findViewById(R.id.btn_third);

        mTxtUniFirst = mView.findViewById(R.id.txt_uni_first);
        mTxtUniSecond = mView.findViewById(R.id.txt_uni_second);
        mTxtUniThird = mView.findViewById(R.id.txt_uni_third);
        mImgAdd = mView.findViewById(R.id.img_add);

        mImgMonthBefore = mView.findViewById(R.id.month_before);
        mImgMonthAfter = mView.findViewById(R.id.month_after);
        mTxtMonth = mView.findViewById(R.id.month);

        mGridView = mView.findViewById(R.id.grid_view);
        mGridAdapter = new GridAdapter(getContext(), R.layout.item_calender_list, mGridDataList);
        mGridView.setAdapter(mGridAdapter);
        mGridView.setOnItemClickListener(mGridViewClickListener);
        mGridView.setSelector(new StateListDrawable());

        mFirstChoice.setOnClickListener(v -> {
            mTxtUniFirst.setTextColor(getResources().getColor(R.color.howareu_main));
            mTxtUniSecond.setTextColor(getResources().getColor(R.color.black20));
            mTxtUniThird.setTextColor(getResources().getColor(R.color.black20));
        });

        mSecondChoice.setOnClickListener(v -> {
            mTxtUniFirst.setTextColor(getResources().getColor(R.color.black20));
            mTxtUniSecond.setTextColor(getResources().getColor(R.color.howareu_main));
            mTxtUniThird.setTextColor(getResources().getColor(R.color.black20));
        });

        mThirdChoice.setOnClickListener(v -> {
            mTxtUniFirst.setTextColor(getResources().getColor(R.color.black20));
            mTxtUniSecond.setTextColor(getResources().getColor(R.color.black20));
            mTxtUniThird.setTextColor(getResources().getColor(R.color.howareu_main));
        });

        mImgMonthBefore.setOnClickListener(v -> {
            mDateManager.moveMonthAndSaveMonth(-1, DateManager.SAVE_ALL);
            refresh();
        });

        mImgMonthAfter.setOnClickListener(v -> {
            mDateManager.moveMonthAndSaveMonth(1, DateManager.SAVE_ALL);
            refresh();
        });

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        mTxtMonth.setText(mDateManager.getPeriodMonth() + "월");

        mGridDataList.clear();

        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        calStart.setTimeInMillis(mDateManager.getPeriodStartDt());
        calEnd.setTimeInMillis(mDateManager.getPeriodEndDt());

        CalendarItemData itemData;

        // 이달의 몇번째 요일(일요일:1 ~ 토요일:7)
        int sDayOfWeekCount = calStart.get(Calendar.DAY_OF_WEEK);
        for(int i = 1; i < sDayOfWeekCount; i++) {
            itemData = new CalendarItemData();
            mGridDataList.add(itemData);
        }

        int lastDate = calEnd.get(Calendar.DATE);
        for (int i = 1; i <= lastDate; i++) {
            itemData = new CalendarItemData();
            itemData.date = String.valueOf(i);
            itemData.dayOfWeek = calStart.get(Calendar.DAY_OF_WEEK);

            mGridDataList.add(itemData);

            calStart.add(Calendar.DATE, 1);
        }

        // 이달이 몇주차로 표시될지 판단(5~6row)
        if (mGridDataList.size() <= 35) {
            mRowCount = 5;
        } else {
            mRowCount = 6;
        }

        // 이달의 몇번째 요일(일요일:1 ~ 토요일:7)
        int eDayOfWeekCount = calEnd.get(Calendar.DAY_OF_WEEK);
        for(int i = eDayOfWeekCount+1; i <= 7; i++) {
            itemData = new CalendarItemData();
            mGridDataList.add(itemData);
        }

        mGridAdapter.notifyDataSetChanged();
    }

    public class CalendarItemData {
        public String date;
        public int count = 0;
        public int dayOfWeek = 1;
    }

    private void refreshDailyList(int position) {
        CalendarItemData calendarItemData = mGridDataList.get(position);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mDateManager.getLongPeriodDt());
        try {
            cal.set(Calendar.DATE, Integer.valueOf(calendarItemData.date));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar[] cals = DateManager.getDailyStartEndTime(cal.getTimeInMillis());

        // 날자별 정렬
        final Comparator<MessageData> comparator = new Comparator<MessageData>() {
            public int compare(MessageData obj1, MessageData obj2) {
                return obj1.dateTime > obj2.dateTime ?
                        -1 : obj1.dateTime < obj2.dateTime ? 1 : 0;
            }
        };
        Collections.sort(mDailyListItemData, comparator);

        String headerDate = new SimpleDateFormat("MM월 dd일 (EEE)", Locale.KOREAN).format(cal.getTimeInMillis());

        mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
    }
    AdapterView.OnItemClickListener mGridViewClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mCalendarViewSelectedPosition = position;

            CalendarItemData calendarItemData = mGridDataList.get(position);
            if (TextUtils.isEmpty(calendarItemData.date)) {
                // 빈날짜는 아무행동 없음.
            } else if (calendarItemData.count > 0) {
                refreshDailyList(position);
            } else {
                Calendar calNow = Calendar.getInstance();
                try {
                    calNow.set(Calendar.MONTH, mDateManager.getPeriodYear());
                    calNow.set(Calendar.MONTH, mDateManager.getPeriodMonth()-1);
                    calNow.set(Calendar.DATE, Integer.valueOf(calendarItemData.date));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public class GridAdapter extends ArrayAdapter<CalendarItemData> {
        private static final int LAYOUT_TOOLBAR_SIZE = 114;
        private static final int LAYOUT_AMOUNT_SIZE = 82;
        private static final int LAYOUT_DAY_OF_WEEK_SIZE = 24;
        private static final int LAYOUT_FOOTER_SIZE = 15;

        private List<CalendarItemData> items;
        private Context context;
        private int cellWidth;
        private int cellHeightOf5;
        private int cellHeightOf6;

        public GridAdapter(Context context, int textViewResourceId, ArrayList<CalendarItemData> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.context = context;

            // 셀 가로 사이즈
            int displayWidth = context.getResources().getDisplayMetrics().widthPixels;
            cellWidth =  displayWidth / 7;

            // 셀 세로 사이즈
            int displayHeight = context.getResources().getDisplayMetrics().heightPixels;

            // 다른 레이아웃의 사이즈
            int layoutDp = LAYOUT_TOOLBAR_SIZE + LAYOUT_AMOUNT_SIZE + LAYOUT_DAY_OF_WEEK_SIZE + LAYOUT_FOOTER_SIZE;
            int otherLayoutSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, layoutDp, getResources().getDisplayMetrics());
            displayHeight -= otherLayoutSize;

            // 상태바 사이즈
            int statusBarHeightResourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (statusBarHeightResourceId > 0) {
                displayHeight -= context.getResources().getDimensionPixelSize(statusBarHeightResourceId);
            }

            cellHeightOf5 =  displayHeight / 5;
            cellHeightOf6 =  displayHeight / 6;
        }

        @Override
        public CalendarItemData getItem(int position) {
            items.get(position);
            return super.getItem(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_calender_list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (mRowCount == 5) {
                convertView.setLayoutParams(new GridView.LayoutParams(cellWidth, cellHeightOf5));
            } else {
                convertView.setLayoutParams(new GridView.LayoutParams(cellWidth, cellHeightOf6));
            }

            CalendarItemData itemData = items.get(position);

            if (!TextUtils.isEmpty(itemData.date)) {
                holder.tvDate.setText(itemData.date);
            } else {
                holder.tvDate.setText("");
            }

            return convertView;
        }

        public class ViewHolder {
            TextView tvDate;
            TextView tvCount;

            public ViewHolder(View itemView) {
                tvDate = (TextView) itemView.findViewById(R.id.date);
                tvCount = (TextView) itemView.findViewById(R.id.count);
            }
        }
    }
}
