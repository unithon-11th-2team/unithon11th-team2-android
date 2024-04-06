package com.team2.data.model.response

import android.provider.ContactsContract.CommonDataKinds.Nickname
import com.team2.domain.entity.Item
import com.team2.domain.entity.ItemType
import kotlinx.serialization.Serializable

@Serializable
data class CommentDto(
    val message: String,
    val uid: Int,
    val nickname: String,
    val likeCount: Int
)