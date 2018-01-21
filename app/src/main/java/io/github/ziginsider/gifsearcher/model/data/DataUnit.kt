package io.github.ziginsider.gifsearcher.model.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by zigin on 21.01.2018.
 */

class DataUnit : Parcelable {
    var iconId: Int = 0
    var title: String = ""
    var urlImage: String = ""

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.iconId)
        dest.writeString(this.title)
        dest.writeString(this.urlImage)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.iconId = `in`.readInt()
        this.title = `in`.readString()
        this.urlImage = `in`.readString()
    }

    companion object {

        fun getDataUnit(name: String): DataUnit {
            val d = DataUnit()
            d.title = name
            return d
        }

        val CREATOR: Parcelable.Creator<DataUnit> = object : Parcelable.Creator<DataUnit> {
            override fun createFromParcel(source: Parcel): DataUnit {
                return DataUnit(source)
            }

            override fun newArray(size: Int): Array<DataUnit?> {
                return arrayOfNulls(size)
            }
        }
    }
}