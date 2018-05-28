package com.example.diu.bangladictionary;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {


    private MediaPlayer mediaPlayer;
    private ListView listView;
    private AudioManager mAudioManager;
    private View rootView;

    /**
     * show massage when mediplayer finish the playing the audio
     */

    private MediaPlayer.OnCompletionListener onCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
            //Toast.makeText(NumbersFragment.this, "I am done", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListner=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.word_list, container, false);
        colorTranslationEnglishToBangla();
        return rootView;
    }

    //getColor translation method
    public void colorTranslationEnglishToBangla(){
        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        //create a list of words
        final ArrayList<Word> words=new ArrayList<Word>();

        words.add(new Word("red", "লাল",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("mustard yellow", "গাড় হলুদ",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "মলিন হলুদ",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("green", "সবুজ",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("brown", "বাদামী",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray", "ধূসর",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black", "কালো",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white", "সাদা",R.drawable.color_white,R.raw.color_white));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter wordAdapter=new WordAdapter(getActivity(),words,R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        listView=(ListView)rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Release the media player if it currently exists because we are about to
                        // play a different sound file
                        releaseMediaPlayer();
                        Word word=words.get(position);
                        // Request audio focus so in order to play the audio file. The app needs to play a
                        // short audio file, so we will request audio focus with a short amount of time
                        // with AUDIOFOCUS_GAIN_TRANSIENT.

                        int result=mAudioManager.requestAudioFocus(mOnAudioFocusChangeListner,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                        if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                            // We have audio focus now.

                            // Create and setup the {@link MediaPlayer} for the audio resource associated
                            // with the current word
                            mediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioresourceId());

                            // Start the audio file
                            mediaPlayer.start();

                            // Setup a listener on the media player, so that we can stop and release the
                            // media player once the sound has finished playing.
                            mediaPlayer.setOnCompletionListener(onCompletionListener);

                        }
                    }
                }
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if(mediaPlayer!=null){
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer=null;
            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListner);
        }
    }


}
