package com.example.foodorderapp.data.user

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String,
    val phoneNumber: String,
    val email: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val image_byte_array: ByteArray
):Parcelable
