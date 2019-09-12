package com.example.behancer.data.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.behancer.data.model.user.Image
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "username")
    @SerializedName("username")
    var username: String,

    @ColumnInfo(name = "location")
    @SerializedName("location")
    var location: String,

    @ColumnInfo(name = "created_on")
    @SerializedName("created_on")
    var createdOn: Long,

    @SerializedName("images")
    @Ignore
    var image: Image? = null,

    @ColumnInfo(name = "display_name")
    @SerializedName("display_name")
    var displayName: String
) {
    constructor() : this(0, "", "", 0, null, "")
}