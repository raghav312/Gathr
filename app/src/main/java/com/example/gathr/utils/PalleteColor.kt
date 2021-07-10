package com.example.gathr.utils

import com.example.gathr.R
import kotlin.collections.ArrayList

//provides random color from this pallete
class palleteColor {
    companion object{
         fun fillList(): ArrayList<Int> {
            val colorList = ArrayList<Int>()
            colorList.add(R.color.ColorGreenNote)
            colorList.add(R.color.ColorOrangeNote)
            colorList.add(R.color.ColorPurpleNote)
            colorList.add(R.color.ColorBlueNote)
             colorList.add(R.color.ColorBlue)
            return colorList
        }
    }




}