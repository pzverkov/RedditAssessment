package com.zve.redditassessment.ui.comments

interface CommentsPresenter {
    fun setView(view: CommentsView)
    fun stop()
    fun loadComments(subreddit: String, id: String)
}
