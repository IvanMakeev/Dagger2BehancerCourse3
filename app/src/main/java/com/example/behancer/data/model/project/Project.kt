package com.example.behancer.data.model.project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Project(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String,

    @ColumnInfo(name = "published_on")
    @SerializedName("published_on")
    var publishedOn: Long,

    @SerializedName("covers")
    @Ignore
    var cover: Cover? = null,

    @SerializedName("owners")
    @Ignore
    var owners: List<Owner>? = null
) : Serializable{
    constructor(): this(0,"", 0, null, null)
}