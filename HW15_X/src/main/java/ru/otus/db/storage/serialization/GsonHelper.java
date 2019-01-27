package ru.otus.db.storage.serialization;

import com.google.gson.*;
import ru.otus.db.storage.dataSets.UserDataSet;

import java.lang.reflect.Type;

public class GsonHelper {
    public static String toJson(UserDataSet user) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(UserDataSet.class, new UserDataSetSerializer())
                .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .create();
        String json = gson.toJson(user);
        return json;
    }

    public static UserDataSet userFromJson(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(UserDataSet.class, new UserDataSetDeserializer())
                .create();
        UserDataSet user = gson.fromJson(json, UserDataSet.class);
        return user;
    }

    static class UserDataSetSerializer implements JsonSerializer<UserDataSet> {
        @Override
        public JsonElement serialize(UserDataSet src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject result = new JsonObject();

            result.addProperty("id", src.getID());
            result.addProperty("name", src.getName());
            result.addProperty("address", src.getStreet());

            return result;
        }
    }

    static class UserDataSetDeserializer implements JsonDeserializer<UserDataSet>
    {
        @Override
        public UserDataSet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            JsonObject jo = json.getAsJsonObject();

            UserDataSet user = new UserDataSet();
            if (jo.has("id"))
                user.setID(jo.get("id").getAsLong());
            user.setName(jo.get("name").getAsString());
            user.setStreet(jo.get("address").getAsString());

            return user;
        }
    }

}
