package com.example.kotlinviewpagerslideintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewParent
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var onBoardingItemsAdapter: OnBoardingItemAdapter
    private lateinit var indicatorContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()
        setupIndicators()
        setupCurrentIndicator(0)
    }

    private fun setupAdapter(){
        onBoardingItemsAdapter = OnBoardingItemAdapter(
            listOf(
                OnBoardingData(
                    "Title1",
                    "Desc1",
                    listOf(Questions("A"),Questions("B"),Questions("C"))
                ),
                OnBoardingData(
                    "Title2",
                    "Desc2",
                    listOf(Questions("D"),Questions("E"),Questions("F"))
                ),
                OnBoardingData(
                    "Title3",
                    "Desc3",
                    listOf(Questions("G"),Questions("H"),Questions("I"))
                ),
                OnBoardingData(
                    "Title4",
                    "Desc4",
                    listOf(Questions("H"),Questions("I"),Questions("J"))
                )
            )
        )
        val onBoardViewPager = findViewById<ViewPager2>(R.id.view_pager)
        onBoardViewPager.adapter = onBoardingItemsAdapter
        onBoardViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setupCurrentIndicator(position)
            }
        })
        (onBoardViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        findViewById<Button>(R.id.next).setOnClickListener {
            if (onBoardViewPager.currentItem + 1 < onBoardingItemsAdapter.itemCount){
                onBoardViewPager.currentItem += 1
            }
        }
        findViewById<Button>(R.id.preview).setOnClickListener {
            if (onBoardViewPager.currentItem - 1 < onBoardingItemsAdapter.itemCount){
                onBoardViewPager.currentItem -= 1
            }
        }
    }
    private fun setupIndicators(){
        indicatorContainer = findViewById(R.id.indicator_container)
        val indicator = arrayOfNulls<ImageView>(onBoardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicator.indices) {
            indicator[i] = ImageView(applicationContext)
            indicator[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setupCurrentIndicator(position: Int){

        val childCount = indicatorContainer.childCount

        if (position == childCount - 1){
            findViewById<Button>(R.id.next).text = "SELESAI"
        }
        else{
            findViewById<Button>(R.id.next).text = "NEXT"
            findViewById<Button>(R.id.next).visibility = View.VISIBLE
        }

        if (position == 0){
            findViewById<Button>(R.id.preview).visibility = View.INVISIBLE
        }
        else{
            findViewById<Button>(R.id.preview).visibility = View.VISIBLE
        }

        for (i in 0 until childCount){
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }
            else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}