package uk.ac.solent.sqllite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var helper: MyHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helper = MyHelper(this)

        addBtn.setOnClickListener{
            val title = etTitle.text.toString()
            val artist = etArtist.text.toString()
            val year = etYear.text.toString().toLong()

            val id = helper.addSong(title, artist, year)
            etID.setText("$id")

        }
    }
}
