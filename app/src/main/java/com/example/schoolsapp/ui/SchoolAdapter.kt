package com.example.schoolsapp.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolsapp.databinding.ListRowBinding
import com.example.schoolsapp.model.SchoolResponseItem

class SchoolAdapter(
    private val data: MutableList<SchoolResponseItem> = mutableListOf(),
    private val onItemClicked: (SchoolResponseItem) -> Unit
) : RecyclerView.Adapter<SchoolAdapter.ViewHolder>() {

    private val KEY_LAUNCH = ""

    fun updateData(newData: List<SchoolResponseItem>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], onItemClicked)

    inner class ViewHolder(
        private val binding: ListRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: SchoolResponseItem, onClickedItem: (SchoolResponseItem) -> Unit) {

            binding.schoolName.text = "Name:   ${data.schoolName}"
            binding.schoolAddress.text =
                "Address:   ${data.primaryAddressLine1} ${data.city} ${data.stateCode} ${data.zip}"

            itemView.setOnClickListener {
                onClickedItem(data)
            }
        }
    }
}