package com.example.eestech_challenge_begemol.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.eestech_challenge_begemol.Model.Category
import com.example.eestech_challenge_begemol.R

class CategoryAdapter(
    private val onClickListener: OnClickListener,
    private var categoryList: List<Category>
) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.identefication)
        val header: TextView = itemView.findViewById(R.id.header)
        val descr: TextView = itemView.findViewById(R.id.seconder_text)
        val card: ConstraintLayout = itemView.findViewById(R.id.card)
        fun bind(category: Category) {
            header.text = category.name
            descr.text = category.description
            id.text = category.count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView: View = layoutInflater.inflate(R.layout.task_card, parent, false)
        return MyViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
        holder.card.setOnClickListener { onClickListener.onClick(category.id!!) }
    }

    override fun getItemCount() = categoryList.size

    fun updateList(list: List<Category>) {
        categoryList = list
        notifyDataSetChanged()
    }

    class OnClickListener(val clickListener: (id: Int) -> Unit) {
        fun onClick(id: Int) =
            clickListener(id)
    }
}