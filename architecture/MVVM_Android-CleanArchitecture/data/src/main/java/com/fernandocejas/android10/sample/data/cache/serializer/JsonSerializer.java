/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.sample.data.cache.serializer;

import com.fernandocejas.android10.sample.data.entity.User;
import com.google.gson.Gson;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
public class JsonSerializer {

    private final Gson gson = new Gson();

    public JsonSerializer() {
    }

    /**
     * Serialize an object to Json.
     *
     * @param userEntity {@link User} to serialize.
     */
    public String serialize(User userEntity) {
        return gson.toJson(userEntity, User.class);
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param jsonString A json string to deserialize.
     * @return {@link User}
     */
    public User deserialize(String jsonString) {
        return gson.fromJson(jsonString, User.class);
    }
}
