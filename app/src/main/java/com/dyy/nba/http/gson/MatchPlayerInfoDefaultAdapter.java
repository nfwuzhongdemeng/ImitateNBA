package com.dyy.nba.http.gson;

import com.dyy.nba.model.MatchStat;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by 段钰莹 on 2017/9/13.
 */

public class MatchPlayerInfoDefaultAdapter implements JsonSerializer<MatchStat.MaxPlayers.MatchPlayerInfo>, JsonDeserializer<MatchStat.MaxPlayers.MatchPlayerInfo> {
    @Override
    public MatchStat.MaxPlayers.MatchPlayerInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.getAsString().equals("")) {
                return null;
                //return new MatchPlayerInfo();
            }
        } catch (Exception ignore) {
        }
        try {
            return new Gson().fromJson(json, MatchStat.MaxPlayers.MatchPlayerInfo.class);
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(MatchStat.MaxPlayers.MatchPlayerInfo src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        Class<?> clz = src.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                jsonObject.addProperty(field.getName(), (String) field.get(src));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}
