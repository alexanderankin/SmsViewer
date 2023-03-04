package smsviewer.app.crud_example

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import smsviewer.app.R

private val exampleRepository = ExampleRepository().addSamples()
private val exampleRVA = ExampleRVA(exampleRepository)

class ExamplesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = exampleRVA
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addExample(view: View) {
        exampleRepository.addNext()
        exampleRVA.notifyDataSetChanged()
    }
}
