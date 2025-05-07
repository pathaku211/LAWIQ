import android.os.Parcel
import android.os.Parcelable

data class Article(
    val title: String = "",
    var content: String = "",
    val author: String = "",
    var imageURL: String =""
) : Parcelable {

    // You can add a computed property if needed
    val description: String
        get() = content // If you want the description to be the same as content, for example

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(author)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}
