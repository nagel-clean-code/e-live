package com.example.data.storage.models

import android.os.Parcel
import android.os.Parcelable
import com.example.domain.models.SessionResultModel

data class SessionResultModelImpl(
    override val mail: String? = ""
): SessionResultModel, Parcelable{
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SessionResultModelImpl> {
        override fun createFromParcel(parcel: Parcel): SessionResultModelImpl {
            return SessionResultModelImpl(parcel)
        }

        override fun newArray(size: Int): Array<SessionResultModelImpl?> {
            return arrayOfNulls(size)
        }
    }

}
