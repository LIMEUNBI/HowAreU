package com.kpu.howareu.activity.video;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kpu.howareu.R;
import com.kpu.howareu.common.config.Config;
import com.kpu.howareu.common.config.YouTubeAPI;

public class VideoPlayActivity extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle saveInstanceBundle) {
        super.onCreate(saveInstanceBundle);
        setContentView(R.layout.activity_video_play);
        youTubePlayerView = findViewById(R.id.youtubeView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                int index = getIntent().getIntExtra(Config.VIDEO_INDEX, 0);
                if (index == 0) {
                    youTubePlayer.loadVideo("DhWVqLZ60mk");
                } else if (index == 1) {
                    youTubePlayer.loadVideo("wzJS6BjYPLY");
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize(YouTubeAPI.getApiKey(), listener);
    }
}
