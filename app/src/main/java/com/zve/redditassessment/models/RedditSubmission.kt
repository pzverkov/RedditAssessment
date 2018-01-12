package com.zve.redditassessment.models

import org.joda.time.DateTime

/**
 * Created by Peter on 11.01.2018.
 */

open class RedditSubmission : RedditObject() {
    val bannedBy: String? = null
    val subreddit: String? = null
    val isSaved: Boolean = false
    val id: String? = null
    val gilded: Int = 0
    val author: String? = null
    val score: Int = 0
    val name: String? = null
    val created: Long = 0
    val authorFlairText: String? = null
    val createdUtc: DateTime? = null
    val ups: Int = 0
}
