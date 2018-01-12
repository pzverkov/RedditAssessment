package com.zve.redditassessment.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.zve.redditassessment.models.RedditObject
import com.zve.redditassessment.models.RedditObjectWrapper

import java.lang.reflect.Type

/**
 * Created by Peter on 11.01.2018.
 */

class RedditObjectDeserializer : JsonDeserializer<RedditObject> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, type: Type, context: JsonDeserializationContext): RedditObject? {
        if (!json.isJsonObject) {
            return null
        }
        try {
            val wrapper = Gson().fromJson(json, RedditObjectWrapper::class.java)
            return context.deserialize<RedditObject>(wrapper.data, wrapper.kind!!.derivedClass)
        } catch (e: JsonParseException) {
            Log.e(TAG, "Failed to deserialize", e)
            return null
        }

    }

    companion object {
        val TAG = RedditObjectDeserializer::class.java.simpleName
    }
}
