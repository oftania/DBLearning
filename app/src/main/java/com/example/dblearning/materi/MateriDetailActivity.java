package com.example.dblearning.materi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dblearning.R;
import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.model.Materi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MateriDetailActivity extends AppCompatActivity {
    private Context context;
    @BindView(R.id.video_player)
    VideoView videoPlayer;
    @BindView(R.id.tv_judul)
    TextView tvJudul;
    @BindView(R.id.tv_deskripsi)
    TextView tvDeskripsi;
    private Materi data;

    // posisi sekarang (in milliseconds).
    private int mCurrentPosition = 0;
    // Tag for the instance state bundle.
    private static final String PLAYBACK_TIME = "play_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_detail);
        ButterKnife.bind(this);
        context = this;
        initComponent(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Load the media each time onStart() is called.
        initializePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // In Android versions less than N (7.0, API 24), onPause() is the
        // end of the visual lifecycle of the app.  Pausing the video here
        // prevents the sound from continuing to play even after the app
        // disappears.
        //
        // This is not a problem for more recent versions of Android because
        // onStop() is now the end of the visual lifecycle, and that is where
        // most of the app teardown should take place.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoPlayer.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Media playback takes a lot of resources, so everything should be
        // stopped and released at this time.
        releasePlayer();
    }

    private void releasePlayer() {
        videoPlayer.stopPlayback();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current playback position (in milliseconds) to the
        // instance state bundle.
        outState.putInt(PLAYBACK_TIME, videoPlayer.getCurrentPosition());
    }

    private void initializePlayer() {
        // Buffer and decode the video sample.
        Uri videoUri = getMedia(AppData.URL_VIDEO+data.getUrlVideo());
        videoPlayer.setVideoURI(videoUri);

        // Listener for onPrepared() event (runs after the media is prepared).
        videoPlayer.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        // Hide buffering message.
                        Toast.makeText(context, "memproses video...", Toast.LENGTH_SHORT).show();
                        // Restore saved position, if available.
                        if (mCurrentPosition > 0) {
                            videoPlayer.seekTo(mCurrentPosition);
                        } else {
                            // Skipping to 1 shows the first frame of the video.
                            videoPlayer.seekTo(1);
                        }
                        // Start playing!
                        videoPlayer.start();
                    }
                });

        // Listener for onCompletion() event (runs after media has finished
        // playing).
        videoPlayer.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(context, "video selesai", Toast.LENGTH_SHORT).show();
                        // Return the video position to the start.
                        videoPlayer.seekTo(0);
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void initComponent(Bundle savedInstanceState) {
        Intent intentData = getIntent();
        data = (Materi) intentData.getSerializableExtra("data");
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }
        //set up title and desc
        assert data != null;
        tvJudul.setText(""+data.getJudul());
        tvDeskripsi.setText(""+data.getDeskripsi());
        // Set up the media controller widget and attach it to the video view.
        MediaController controller = new MediaController(context);
        controller.setMediaPlayer(videoPlayer);
        videoPlayer.setMediaController(controller);
    }

    private Uri getMedia(String mediaName) {
        if (URLUtil.isValidUrl(mediaName)) {
            // Media name is an external URL.
            return Uri.parse(mediaName);
        } else {
            finish();
            Toast.makeText(context, "video tidak ditemukan", Toast.LENGTH_SHORT).show();
            return Uri.parse(mediaName);
        }
    }
}