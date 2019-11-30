package com.floleproto.mcapi.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MojangParser {
	
	public static UUID getUUIDByPlayerName(String name) {
		try {
            URL url = new URL( "https://api.mojang.com/users/profiles/minecraft/" + name);

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(br);

            String uuid = UUIDObject.get("id").toString();
            return UUID.fromString(uuid);
        } catch (Exception e) {
            return null;
        }
		
	}
}
