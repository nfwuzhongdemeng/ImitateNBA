package com.dyy.nba.http.gson;

import android.util.Log;

import com.dyy.nba.model.MatchStat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/13.
 */

public class ListDefaultAdapter implements JsonDeserializer<List<?>> {
    @Override
    public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            Gson newGson = new GsonBuilder().serializeNulls()
                    .registerTypeAdapter(MatchStat.MaxPlayers.MatchPlayerInfo.class, new MatchPlayerInfoDefaultAdapter())
                    .create();

            String jsonStr = json.toString();
            if (jsonStr.contains("\"playerStats\":{") && jsonStr.contains("\"type\":\"16\"")) {
                // 不太优雅的方式，如果是16，改为160和场上球员
                jsonStr = jsonStr.replace("\"type\":\"16\"", "\"type\":\"160\"")
                        .replace("\"playerStats\":{", "\"groundStats\":{");
            }

            if (json.isJsonArray()) {
                return newGson.fromJson(jsonStr, typeOfT);
            } else if (json.getAsString() != null && !json.getAsString().equals("")) {
                String newjson = "[" + json.getAsString() + "]";
                return newGson.fromJson(newjson, typeOfT);
            }
        } catch (Exception ignore) {
            Log.e("---", ignore.toString());
        }

        return null;
    }
}
