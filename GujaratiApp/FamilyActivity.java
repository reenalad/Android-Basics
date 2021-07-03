package com.example.reena.gujaratiapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    //mediaplayer to play the sound
    MediaPlayer mediaPlayer;

    //handles audio focus when playing a sound file
    private AudioManager audioManager;

    //listener if audio changes focus
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (mediaPlayer != null) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                    //pause playback and play the word from the beginning when we resume
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);

                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    //resume playback
                    mediaPlayer.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    //stop playback and clean up resources
                    releaseMediaPlayer();
                }
            }
        }
    };

    //on completion listener to release mediaplayer once it has finished playing the audio file
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the {@link AudioManager} to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Mother", "માતા", "mātā", R.drawable.family_mother, R.raw.mother));
        words.add(new Word("Father", "પિતા", "pitā", R.drawable.family_father, R.raw.father));
        words.add(new Word("Son", "પુત્ર", "putra", R.drawable.family_son, R.raw.son));
        words.add(new Word("Daughter", "પુત્રી", "putrī", R.drawable.family_daughter, R.raw.daughter));
        words.add(new Word("Brother", "ભાઈ", "bhai", R.drawable.family_brother, R.raw.brother));
        words.add(new Word("Sister", "બહેન", "bahēn", R.drawable.family_sister, R.raw.sister));;
        words.add(new Word("Grandmother", "દાદી", "dādī", R.drawable.family_grandmother, R.raw.grandmother));
        words.add(new Word("Grandfather", "દાદા", "dādā", R.drawable.family_grandfather, R.raw.grandfather));


        WordAdapter adapter = new WordAdapter(this, words,R.color.colorWhite);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        //play audio file when the row is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();

                // Request audio focus in order to play the audio file
                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceID());
                    mediaPlayer.start();

                    //set on completion listener to release the media player
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        //when the activity is stopped, release the media player
        releaseMediaPlayer();
    }

    //Clean up the media player by releasing its resources
    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {

            //release the mediaplayer resources
            mediaPlayer.release();

            //set the mediaplayer back to null
            mediaPlayer = null;

            //abandon audiofocus
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }

    }
}
