package com.jaycesub.github_api_tutorials.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("total_count") var total_count: Int,
    @SerializedName("incomplete_results") var incomplete_results: Boolean,
    @SerializedName("items") var items: List<Items>
)

data class Items(
    @SerializedName("login") val login: String,
    @SerializedName("id") var id: Int,
    @SerializedName("node_id") val node_id: String,
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("gravatar_id") val gravatar_id: String,
    @SerializedName("url") val url: String,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("followers_url") val followers_url: String,
    @SerializedName("subscriptions_url") val subscriptions_url: String,
    @SerializedName("organizations_url") val organizations_url: String,
    @SerializedName("repos_url") val repos_url: String,
    @SerializedName("received_events_url") val received_events_url: String,
    @SerializedName("type") val type: String,
    @SerializedName("score") val score: Double
)