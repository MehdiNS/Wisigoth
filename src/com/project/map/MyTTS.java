package com.project.map;

import java.util.HashMap;
import java.util.Locale;

import android.app.Service;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.widget.Toast;

public class MyTTS implements OnInitListener {

	private static final String TAG = null;
	private TextToSpeech mTts;
	private boolean mSpeak = false;
	private Context mContext;
	private String text;
	private boolean mSpeakingEngineAvailable = false;
	private HashMap<String, String> hashMap = new HashMap<String, String>();

	public MyTTS(Context c, String s) {
		mContext = c;
		text = s;
	}

	/********** SPEAKING **********/

	public void initTTS() {
		// Initialize text-to-speech. This is an asynchronous operation.
		// The OnInitListener (second argument) is called after initialization
		// completes.
		Log.i(TAG, "Initializing TextToSpeech...");
		mTts = new TextToSpeech(mContext, this // TextToSpeech.OnInitListener
		);

	}

	public void shutdownTTS() {
		Log.i(TAG, "Shutting Down TextToSpeech...");
		mTts.shutdown();
		Log.i(TAG, "TextToSpeech Shut Down.");

	}

	public void say(String s) {
		hashMap.put(TextToSpeech.Engine.KEY_FEATURE_NETWORK_SYNTHESIS, "true");
		mTts.speak(s, TextToSpeech.QUEUE_FLUSH, hashMap);

	}

	// Implements TextToSpeech.OnInitListener.
	public void onInit(int status) {

		// status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
		if (status == TextToSpeech.SUCCESS) {
			int result = mTts.setLanguage(Locale.FRANCE);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				// Language data is missing or the language is not supported.
				Log.e(TAG, "Language is not available.");
			} else {
				Log.i(TAG, "TextToSpeech Initialized.");
				mSpeakingEngineAvailable = true;
				say(text);
			}
		} else {
			// Initialization failed.
			Log.e(TAG, "Could not initialize TextToSpeech.");
		}
	}

	public void setText(String s) {
		text = s;
	}

}