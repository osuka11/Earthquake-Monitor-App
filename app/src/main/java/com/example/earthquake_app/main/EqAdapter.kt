package com.example.earthquake_app.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquake_app.Earthquake
import com.example.earthquake_app.R
import com.example.earthquake_app.databinding.EqListItemBinding

class EqAdapter() :androidx.recyclerview.widget.ListAdapter<Earthquake,EqAdapter.ViewHolder>(DiffCallBack) {

    private val tag = EqAdapter::class.java.simpleName

    companion object DiffCallBack: DiffUtil.ItemCallback<Earthquake>() {
        override fun areItemsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem == newItem
        }

    }
    lateinit var onItemClickListener: (Earthquake) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EqAdapter.ViewHolder {
        val binding = EqListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding, parent.context)

    }

    override fun onBindViewHolder(holder: EqAdapter.ViewHolder, position: Int) {
        val earthquake = getItem(position)
        holder.bind(earthquake)

    }

    inner class ViewHolder(private val binding: EqListItemBinding, parent: Context):RecyclerView.ViewHolder(binding.root){
        private val resources = parent
        fun bind(earthquake: Earthquake){
            binding.magnitudeText.text = resources.getString(R.string.magnitude_format,earthquake.magnitude)
            binding.locationText.text = earthquake.place
            binding.root.setOnClickListener {
                if(::onItemClickListener.isInitialized){
                    onItemClickListener(earthquake)
                }else{
                    Log.e(tag,"onItemCLickListener is not initialized")
                }

            }
        }
    }

}