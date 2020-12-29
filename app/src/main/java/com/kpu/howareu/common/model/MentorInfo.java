package com.kpu.howareu.common.model;

import com.kpu.howareu.R;

import java.util.HashMap;
import java.util.Map;

public class MentorInfo {

    public String mentorName;
    public String mentorType;
    public int mentorStar;
    public String mentorUniverse;
    public int mentorPeople;

    public static Map<String, Integer> mentorImgMap = new HashMap<>();

    static {
        mentorImgMap.put("전유미", R.drawable.img_yumi);
        mentorImgMap.put("정종희", R.drawable.img_jonghee);
        mentorImgMap.put("김지호", R.drawable.img_jiho);
        mentorImgMap.put("조원준", R.drawable.img_wonjun);
        mentorImgMap.put("전유진", R.drawable.img_yujin);
    }
}
