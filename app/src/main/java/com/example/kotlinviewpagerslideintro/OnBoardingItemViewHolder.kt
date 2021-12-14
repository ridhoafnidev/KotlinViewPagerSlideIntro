package com.example.kotlinviewpagerslideintro

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem

class OnBoardingItemViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view){
    private val title = view.findViewById<TextView>(R.id.tv_title)
    private val desc = view.findViewById<TextView>(R.id.tv_desc)
    private val rvItem = view.findViewById<RecyclerView>(R.id.rv_item)

    fun bind(onBoardingItem: OnBoardingData){
        title.text = onBoardingItem.title
        desc.text = onBoardingItem.desc
        rvItem.setup {
            withLayoutManager(LinearLayoutManager(context))
            withDataSource(dataSourceTypedOf(onBoardingItem.questions))
            withItem<Questions, QuestionViewHolder>(R.layout.layout_question){
                onBind(::QuestionViewHolder){ _, data ->
                    titleCheckBox.text = data.question
                }
            }
        }
    }
}