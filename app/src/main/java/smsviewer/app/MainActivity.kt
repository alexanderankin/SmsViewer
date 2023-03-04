package smsviewer.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import smsviewer.app.crud_example.ExamplesActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navigateToExamples(view: View) {
        startActivity(Intent(this, ExamplesActivity::class.java));
    }
}
