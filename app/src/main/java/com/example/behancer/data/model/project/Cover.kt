package com.example.behancer.data.model.project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    foreignKeys = [ForeignKey(
        entity = Project::class,
        parentColumns = ["id"],
        childColumns = ["project_id"]
    )]
)
data class Cover(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "photo_url")
    @SerializedName("202")
    var photoUrl: String,

    @ColumnInfo(name = "project_id")
    var projectId: Int
) : Serializable