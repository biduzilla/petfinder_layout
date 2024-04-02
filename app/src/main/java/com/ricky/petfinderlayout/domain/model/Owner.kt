package com.ricky.petfinderlayout.domain.model

import androidx.annotation.DrawableRes
import com.ricky.petfinderlayout.R

data class Owner(
    val name: String = "",
    val basicInfo: String = "",
    @DrawableRes val image: Int = R.drawable.blue_dog
)
