package com.calmscient.data.remote

import androidx.annotation.DrawableRes

data class CardItemDataClass(val availableContentTypes: List<ItemType>,
                             val audioResourceId: String?, // Nullable Int for audio resource ID
                             val videoResourceId: String?,
                             @DrawableRes val contentIcons: List<Int>,
                             val description: String?,
                             val heading: String?,
                             var summary: String?,
                             val isCompleted: Boolean
)

enum class ItemType {
    VIDEO, AUDIO, QUIZ, LESSON
}
