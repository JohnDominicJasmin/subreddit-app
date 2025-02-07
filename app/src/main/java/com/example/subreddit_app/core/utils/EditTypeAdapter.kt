package com.example.subreddit_app.core.utils

import com.google.gson.*
import java.lang.reflect.Type

class EditedTypeAdapter : JsonDeserializer<Boolean> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Boolean {
        return when {
            json.isJsonPrimitive && json.asJsonPrimitive.isBoolean -> json.asBoolean
            json.isJsonPrimitive && json.asJsonPrimitive.isNumber -> true  // Edited (timestamp)
            else -> false
        }
    }
}
