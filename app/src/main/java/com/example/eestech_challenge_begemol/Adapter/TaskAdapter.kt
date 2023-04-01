package com.example.eestech_challenge_begemol.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eestech_challenge_begemol.Model.Category
import com.example.eestech_challenge_begemol.Model.Task
import com.example.eestech_challenge_begemol.R

class TaskAdapter(
    private val onClickListener: OnClickListener,
    private var taskList: List<Task>
) : RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.task_number)
        val card: LinearLayout = itemView.findViewById(R.id.card)
        fun bind(task: Task) {
            id.text = task.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView: View = layoutInflater.inflate(R.layout.task_item, parent, false)
        return MyViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
        holder.card.setOnClickListener { onClickListener.onClick(task.id!!) }
    }

    fun updateList(list: List<Task>) {
        taskList = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = taskList.size

    class OnClickListener(val clickListener: (id: Int) -> Unit) {
        fun onClick(id: Int) =
            clickListener(id)
    }
}