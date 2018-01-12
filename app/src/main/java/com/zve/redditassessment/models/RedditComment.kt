package com.zve.redditassessment.models

/**
 * Created by Peter on 11.01.2018.
 */

class RedditComment : RedditSubmission() {
    val replies: RedditObject? = null
    val subredditId: String? = null
    private val parent_id: String? = null
    val controversiality: Int = 0
    val body: String? = null
    val bodyHtml: String? = null
    val linkId: String? = null
    var depth: Int = 0

    fun getparentId(): String? {
        return parent_id
    }
}
