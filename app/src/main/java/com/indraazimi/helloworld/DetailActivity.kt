package com.indraazimi.helloworld

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.indraazimi.helloworld.database.Diary
import com.indraazimi.helloworld.database.DiaryDb
import com.indraazimi.helloworld.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by lazy {
        val db = DiaryDb.getInstance(this)
        val factory = DetailViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuSimpan) {
            insertDiary()
            return true
        }
        return false
    }

    private fun insertDiary() {
        val judul = binding.judulEditText.text.toString()
        if (TextUtils.isEmpty(judul)) {
            Toast.makeText(this, R.string.judul_harus_diisi, Toast.LENGTH_LONG).show()
            return
        }
        
        val diary = binding.diaryEditText.text.toString()
        if (TextUtils.isEmpty(diary)) {
            Toast.makeText(this, R.string.diary_harus_diisi, Toast.LENGTH_LONG).show()
            return
        }

        val data = Diary(judul = judul, diary = diary)
        viewModel.insertDiary(data)
        finish()
    }
}