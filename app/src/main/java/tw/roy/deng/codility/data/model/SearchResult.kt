package tw.roy.deng.codility.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<User>
) : Parcelable
