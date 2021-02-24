package com.artistecomm.speechtotext

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val RECOGNIZER_RESULT=1
    private lateinit var speechBtn: ImageView
    private lateinit var speechText : EditText
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        speechBtn = findViewById(R.id.speakBtn)
        speechText = findViewById(R.id.SpeechtextView)

        speechBtn.setOnClickListener{

            try {

                var speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now")
                startActivityForResult(speechIntent, RECOGNIZER_RESULT)
            }
            catch (e: Exception) {
               var packageName:String= "com.google.android.googlequicksearchbox"
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://market.android.com/details?id="+packageName))
                startActivity(browserIntent)
            }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RECOGNIZER_RESULT && resultCode == RESULT_OK)
        {
            var list :ArrayList<String> = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
                speechText.setText(list.get(0).toString())
        }
    }


}