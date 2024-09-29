package com.majid.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import java.util.concurrent.TimeUnit

@Entity
data class User (
    @PrimaryKey @SerializedName("id") var id: Int,
    @SerializedName("login") var login: String?,
    @SerializedName("avatar_url") var avatarUrl: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("company") var company: String?,
    @SerializedName("blog") var blog: String?,
    var lastRefreshed: Date?
): Serializable {
    /**
     * We consider that a [UserEntity] is outdated when the last time
     * we fetched it was more than 10 minutes ago.
     */
    fun haveToRefreshFromNetwork(): Boolean {
        val currentTime = Date().time
        val lastRefreshedTime = lastRefreshed?.time ?: 0
        return TimeUnit.MILLISECONDS.toMinutes(currentTime - lastRefreshedTime) >= 10
    }
}