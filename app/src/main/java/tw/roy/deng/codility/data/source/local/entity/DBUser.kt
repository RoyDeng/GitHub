package tw.roy.deng.codility.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class DBUser(
    @ColumnInfo(name = "login")
    @PrimaryKey(autoGenerate = false)
    val login: String = "",

    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "node_id")
    val nodeId: String = "",

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String = "",

    @ColumnInfo(name = "gravatar_id")
    val gravatarId: String = "",

    @ColumnInfo(name = "url")
    val url: String = "",

    @ColumnInfo(name = "html_url")
    val htmlUrl: String = "",

    @ColumnInfo(name = "followers_url")
    val followersUrl: String = "",

    @ColumnInfo(name = "following_url")
    val followingUrl: String = "",

    @ColumnInfo(name = "gists_url")
    val gistsUrl: String = "",

    @ColumnInfo(name = "starred_url")
    val starredUrl: String = "",

    @ColumnInfo(name = "subscriptions_url")
    val subscriptionsUrl: String = "",

    @ColumnInfo(name = "organizations_url")
    val organizationsUrl: String = "",

    @ColumnInfo(name = "repos_url")
    val reposUrl: String = "",

    @ColumnInfo(name = "events_url")
    val eventsUrl: String = "",

    @ColumnInfo(name = "received_events_url")
    val receivedEventsUrl: String = "",

    @ColumnInfo(name = "type")
    val type: String = "",

    @ColumnInfo(name = "site_admin")
    val siteAdmin: Boolean = false,

    @ColumnInfo(name = "score")
    val score: Double = 0.0
)
