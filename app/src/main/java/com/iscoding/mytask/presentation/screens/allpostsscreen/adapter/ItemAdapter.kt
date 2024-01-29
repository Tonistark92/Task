package de.syntax_institut.telefonbuch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iscoding.mytask.data.remote.dto.Post
import com.iscoding.mytask.databinding.ListItemBinding
import com.iscoding.mytask.presentation.screens.allpostsscreen.AllPostsFragmentDirections
import com.iscoding.mytask.presentation.screens.detailsScreen.DetailsViewModel


class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var dataset: List<Post> = emptyList()

    inner class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.tvItemTitle.text = item.title
        holder.binding.tvItemBody.text = item.body

        holder.binding.contactCard.setOnClickListener {
//          it.findNavController().navigate(  AllPostsFragmentDirections.actionAllPostsFragmentToDetailsFragment(postId = yourPostId))
            it.findNavController().navigate(AllPostsFragmentDirections.actionAllPostsFragmentToDetailsFragment())
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    // Function to update the dataset using DiffUtil
    fun updateDataset(newDataset: List<Post>) {
        val diffCallback = PostDiffCallback(dataset, newDataset)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        dataset = newDataset
        diffResult.dispatchUpdatesTo(this)
    }

    // DiffUtil.Callback implementation
    private class PostDiffCallback(private val oldList: List<Post>, private val newList: List<Post>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}