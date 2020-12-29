package com.kpu.howareu.common.model;

import com.kpu.howareu.R;

import java.util.HashMap;
import java.util.Map;

public class SelfInfo {

    public String universeName;
    public String course;
    public String type;
    public int view;

    public static Map<String, Integer> universeLogoMap = new HashMap<>();

    static {
        universeLogoMap.put("서울예술대학교", R.drawable.icon_seoul_art_uni);
        universeLogoMap.put("한국산업기술대학교", R.drawable.icon_kpu_uni);
        universeLogoMap.put("연세대학교", R.drawable.icon_yunsei_uni);
        universeLogoMap.put("숭실대학교", R.drawable.icon_sungsil_uni);
        universeLogoMap.put("건국대학교", R.drawable.icon_konkuk_uni);
        universeLogoMap.put("한양대학교", R.drawable.icon_hanyang_uni);
    }
}
