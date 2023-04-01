package com.example.eestech_challenge_begemol.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eestech_challenge_begemol.Model.Answer
import com.example.eestech_challenge_begemol.Model.Category
import com.example.eestech_challenge_begemol.R

class AnswerAdapter(
    private val onClickListener: TaskAdapter.OnClickListener,
    private var answerList: List<Answer>
) : RecyclerView.Adapter<AnswerAdapter.MyViewHolder>() {
    private lateinit var lastItem: RadioButton
    private var lastId: Int = -1

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header: TextView = itemView.findViewById(R.id.header)
        val button: RadioButton = itemView.findViewById(R.id.select_answer)
        fun bind(answer: Answer) {
            header.text = answer.text.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView: View = layoutInflater.inflate(R.layout.question_card, parent, false)
        return MyViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val answer = answerList[position]
        holder.bind(answer)
        holder.button.setOnClickListener {
            if(lastId != -1)
                lastItem.isChecked = false
            lastId = answer.id!!
            lastItem = holder.button
            onClickListener.onClick(answer.id!!)
        }
    }

    fun updateList(list: List<Answer>) {
        answerList = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = answerList.size

    class OnClickListener(val clickListener: (id: Int) -> Unit) {
        fun onClick(id: Int) =
            clickListener(id)
    }
}