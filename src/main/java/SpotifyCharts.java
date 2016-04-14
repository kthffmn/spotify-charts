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

    public static void main (String... args) {
        SpotifyCharts charts = new SpotifyCharts("US", 1);
        System.out.println(charts.getTopNames(3));
    }

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
        return url + "?" + getParams();
    }

    public URL getUrl() {
        String sUrl = getStringUrl();
        try {
            return new URL(getStringUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonArray getSongs() {
        JsonArray songs;
        URL url = getUrl();
        try (InputStream inputStream = url.openStream();
            JsonReader reader = Json.createReader(inputStream)) {
            JsonObject data = reader.readObject();
            JsonObject entries = data.getJsonObject("entries");
            songs = entries.getJsonArray("items");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }

    public String getName(JsonObject song) {
        return song.getJsonObject("track").getString("name");
    }

    public List<String> getTopNames(int n) {
        JsonArray songs = getSongs();
        int counter = n > songs.size() ? songs.size() : n;
        List<String> names = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            String name = getName(songs.getJsonObject(i));
            names.add(name);
        }
        return names;
    }
}

