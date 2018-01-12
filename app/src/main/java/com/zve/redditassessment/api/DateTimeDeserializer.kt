package com.zve.redditassessment.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.zve.redditassessment.app.ApplicationConstants

import org.joda.time.DateTime

import java.lang.reflect.Type

/**
 * Created by Peter on 11.01.2018.
 */

class DateTimeDeserializer : JsonDeserializer<DateTime> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeoft: Type, context: JsonDeserializationContext): DateTime {
        return DateTime(json.asLong * ApplicationConstants.MS_IN_SECOND)
    }
}
