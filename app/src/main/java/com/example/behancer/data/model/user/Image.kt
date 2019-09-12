package com.example.behancer.data.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["user_id"]
    )]
)
data class Image(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "photo_url")
    @SerializedName("230")
    var photoUrl: String,

    @ColumnInfo(name = "user_id")
    var userId: Int
)