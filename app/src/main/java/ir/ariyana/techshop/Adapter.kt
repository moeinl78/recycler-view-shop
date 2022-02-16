package ir.ariyana.techshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ariyana.techshop.databinding.ItemCardBinding

class Adapter(private val data : ArrayList<Item>) : RecyclerView.Adapter<Adapter.ViewHolder>(){

    inner class ViewHolder(private val binding : ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(position : Int) {
            binding.itemName.text = data[position].itemName
            binding.itemWeight.text = data[position].itemWeigh
            binding.itemOs.text = data[position].itemSoftware
            binding.itemReleaseDate.text = data[position].itemReleaseDate
            Glide
                .with(binding.root.context)
                .load(data[position].itemImage)
                .into(binding.itemImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem(item : Item) {
        data.add(data.size - 1, item)
        notifyItemInserted(data.size - 1)
    }
}