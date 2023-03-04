package smsviewer.app.crud_example

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import smsviewer.app.R


class ExamplesActivity : AppCompatActivity() {
    var exampleRepository: ExampleRepository? = null
    var exampleRVA: ExampleRVA? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        if (exampleRepository == null) {
            exampleRepository = ExampleRepository(this)
            exampleRVA = ExampleRVA(exampleRepository, this)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = exampleRVA
    }

    fun addExample(view: View) {
        exampleRepository?.addNext()
        notifyDs()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notifyDs() {
        exampleRVA?.notifyDataSetChanged()
    }
}
