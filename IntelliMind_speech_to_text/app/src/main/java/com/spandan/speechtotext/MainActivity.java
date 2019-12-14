package com.sriyanksiddhartha.speechtotext;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

	private TextView txvResult;
	private ListView lv_speech;
	private final int REQ_CODE_SPEED_INPUT = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();
		txvResult = (TextView) findViewById(R.id.txvResult);
		lv_speech = (ListView) findViewById(R.id.lv_speech);

	}

	public void getSpeechInput(View view) {
		txvResult.setText("");
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");
		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(intent, REQ_CODE_SPEED_INPUT);
		} else {
			Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case REQ_CODE_SPEED_INPUT:
				if (resultCode == RESULT_OK && data != null) {
					final ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
					PackageManager packageManager = getPackageManager();
					final List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(
							new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
					if (resolveInfoList.size() > 0) {
						lv_speech.setVisibility(View.VISIBLE);
						lv_speech.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result));
					}
					lv_speech.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
							lv_speech.setVisibility(View.GONE);
							txvResult.setText(result.get(i));
						}
					});
					txvResult.setText(result.get(0));
				}
				break;
		}
	}
}
