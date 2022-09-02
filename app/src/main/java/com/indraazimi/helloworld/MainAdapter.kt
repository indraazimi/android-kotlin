package com.indraazimi.helloworld

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.indraazimi.helloworld.database.Diary
import com.indraazimi.helloworld.databinding.ListItemBinding

class MainAdapter : ListAdapter<Diary, MainAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Diary>() {
            override fun areItemsTheSame(oldItem: Diary, newItem: Diary): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Diary, newItem: Diary): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(diary: Diary) = with(binding) {
            judulTextView.text = diary.judul
            diaryTextView.text = diary.diary
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}