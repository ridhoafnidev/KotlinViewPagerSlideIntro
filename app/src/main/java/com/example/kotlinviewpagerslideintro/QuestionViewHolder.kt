package com.example.kotlinviewpagerslideintro

import android.view.View
import android.widget.CheckBox
import com.afollestad.recyclical.ViewHolder

class QuestionViewHolder(view: View) : ViewHolder(view){
    val titleCheckBox: CheckBox = view.findViewById(R.id.cb_question)
}
