/*
 * SpotifyCharts
 */

import java.util.*;
import java.util.stream.*;
import com.google.common.collect.*;
import java.io.*;
import java.net.*;
import javax.json.*;

public class SpotifyCharts {

    private String url = "https://spotifycharts.com/api/";

    private static final Map<String,String> PARAMS = ImmutableMap.of(
        "type", "regional",
        "country", "US",
        "recurrence", "daily",
        "date", "latest"
    );

    private String country;
    private int rank;

    public SpotifyCharts(final String country, final int rank) {
        this.country = country;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String getCountry() {
        return country;
    }

    public String getParams() {
        String finalParams = PARAMS.entrySet().stream()
                .map(Object::toString)
                .collect(Collectors.joining("&"));
        return finalParams;
    }

    public String getStringUrl() {
        String params = getParams();
        System.out.println(url + "?" + params);
        return url + "?" + params;
    }

    public URL getUrl() {
        String sUrl = getStringUrl();
        System.out.println(sUrl);
        return new URL(getStringUrl());
    }

    public JsonArray getSongs() {
        JsonArray songs;
        URL url = getUrl();
        try (InputStream inputStream = url.openStream();
            JsonReader reader = Json.createReader(inputStream)) {
            JsonObject data = reader.readObject();
            JsonObject entries = data.getJsonObject("entries");
            songs = entries.getJsonArray("items");
        }
        return songs;
    }

    public String getName(song) {
        return song.get("track").get("name");
    }

    public String getTopTwo() {
        JsonArray songs = getSongs();
        String songOne = getName(songs.getJsonObject(0));
        String soneTwo = getName(songs.getJsonObject(1));
        return "1. " + songOne + " 2. " + soneTwo;
    }
}

