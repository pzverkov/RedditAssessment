package com.zve.redditassessment.models

/**
 * Created by Peter on 11.01.2018.
 */

enum class RedditType private constructor(val derivedClass: Class<*>) {
    t1(RedditComment::class.java),
    t3(RedditLink::class.java),
    Listing(RedditListing::class.java),
    more(RedditMore::class.java)
}
