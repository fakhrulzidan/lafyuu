package com.belajar.lafyuu.Models

import android.os.Parcel
import android.os.Parcelable

data class Items(
    var categoryId:Int=0,
    var title:String?=null,
    var description:String?=null,
    var picUrl:String?=null,
    var price:Double=0.0,
    var rating:Double=0.0,
    var showRecommended:Boolean = false,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(categoryId)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(picUrl)
        parcel.writeDouble(price)
        parcel.writeDouble(rating)
        parcel.writeByte(if (showRecommended) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Items> {
        override fun createFromParcel(parcel: Parcel): Items {
            return Items(parcel)
        }

        override fun newArray(size: Int): Array<Items?> {
            return arrayOfNulls(size)
        }
    }
}