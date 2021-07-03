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

public class PhrasesActivity extends AppCompatActivity {

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

        words.add(new Word("Hello", "નમસ્તે", "namastē", R.raw.hello));
        words.add(new Word("How are you?", "તમે કેમ છો?", "tamē kēm chō", R.raw.how_are_you));
        words.add(new Word("I am fine", "હું મજા માં છું", "huṁ majā māṁ chuṁ", R.raw.i_am_fine));
        words.add(new Word("What is your name?", "તમારું નામ શું છે?", "tamāruṁ nām śuṁ chē", R.raw.what_is_your_name));
        words.add(new Word("My name is...", "મારું નામ...છે", "māruṁ nām...chē", R.raw.my_name_is));
        words.add(new Word("Where are you from?", "તમે ક્યાંથી છો?", "tamē kyānthī chō", R.raw.where_are_you_from));
        words.add(new Word("I am from...", "હું...થી", "huṁ... thī", R.raw.i_am_from));
        words.add(new Word("Nice to meet you", "તમને મળીને આનંદ થયો", "tamanē malīnē ānanda thayō", R.raw.nice_to_meet_you));
        words.add(new Word("Goodbye", "આવજો", "avajō", R.raw.goodbye));



        WordAdapter adapter = new WordAdapter(this, words, R.color.colorAccent);

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
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceID());
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
