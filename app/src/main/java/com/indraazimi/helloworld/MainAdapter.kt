package com.indraazimi.helloworld

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.indraazimi.helloworld.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = ArrayList<Hewan>()

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hewan: Hewan) = with(binding) {
            Picasso.get().load(HewanApi.getHewanUrl(hewan.imageId)).into(imageView)
            textView.text = hewan.nama
            textView2.text = hewan.namaLatin

            root.setOnClickListener {
                val message = root.context.getString(R.string.x_diklik, hewan.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun updateData(newData: List<Hewan>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}