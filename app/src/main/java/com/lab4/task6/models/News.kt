package com.lab4.task6.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class News(val title: String, val url: String) : Serializable, Parcelable
{
    var rating: Float = 0f
    var isSelected: Boolean = false

    constructor(parcel: Parcel) : this(parcel.readString()!!, parcel.readString()!!)
    {
        rating = parcel.readFloat()
        isSelected = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeFloat(rating)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News>
    {
        override fun createFromParcel(parcel: Parcel): News
        {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?>
        {
            return arrayOfNulls(size)
        }
    }
}