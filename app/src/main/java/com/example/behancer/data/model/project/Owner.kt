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
data class Owner(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "username")
    @SerializedName("username")
    var username: String,

    @ColumnInfo(name = "project_id")
    var projectId: Int = 0
) : Serializable {


}