package com.indraazimi.helloworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.indraazimi.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.recylerView.addItemDecoration(divider)
        binding.recylerView.adapter = MainAdapter(getData())
        binding.recylerView.setHasFixedSize(true)
    }

    private fun getData(): List<Hewan> {
        return listOf(
            Hewan("Angsa"  , "Cygnus olor"            , R.drawable.angsa),
            Hewan("Ayam"   , "Gallus gallus"          , R.drawable.ayam),
            Hewan("Bebek"  , "Cairina moschata"       , R.drawable.bebek),
            Hewan("Domba"  , "Ovis ammon"             , R.drawable.domba),
            Hewan("Kalkun" , "Meleagris gallopavo"    , R.drawable.kalkun),
            Hewan("Kambing", "Capricornis sumatrensis", R.drawable.kambing),
            Hewan("Kelinci", "Oryctolagus cuniculus"  , R.drawable.kelinci),
            Hewan("Kerbau" , "Bubalus bubalis"        , R.drawable.kerbau),
            Hewan("Kuda"   , "Equus caballus"         , R.drawable.kuda),
            Hewan("Sapi"   , "Bos taurus"             , R.drawable.sapi),
        )
    }
}