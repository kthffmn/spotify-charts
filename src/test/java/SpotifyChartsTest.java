import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import com.google.common.collect.*;
import java.io.*;
import java.net.*;
import javax.json.*;

/*
 * SpotifyChartsTest
 */

public class SpotifyChartsTest {
    private SpotifyCharts charts;
    @Before public void initialize() {
        charts = new SpotifyCharts("US", 1);
    }
    @Test public void testGetCountry() {
        assertEquals("getCountry returns country", "US",  charts.getCountry());
    }
    @Test public void testGetRank() {
        assertEquals("getRank returns rank", 1,  charts.getRank());
    }
    @Test public void testGetParams() {
        String params = "type=regional&country=US&recurrence=daily&date=latest";
        assertEquals("getParams formats params", params, charts.getParams());
    }
    @Test public void testGetStringUrl() {
        String sUrl = "https://spotifycharts.com/api/?type=regional&country=US&recurrence=daily&date=latest";
        assertEquals("getUrl adds params to url", sUrl, charts.getStringUrl());
    }
    @Test public void testGetUrl() throws MalformedURLException {
        URL url = new URL("https://spotifycharts.com/api/?type=regional&country=US&recurrence=daily&date=latest");
        assertEquals("getUrl adds params to url", url, charts.getUrl());
    }
    @Test public void testGetSongs() {
        JsonArray songs = charts.getSongs();
        String songName = songs.getJsonObject(0).getJsonObject("track").getString("name");
        assertEquals("getSongs returns list of songs", "Panda", songName);
    }
    @Test public void testGetName() {
        JsonObject firstSong = charts.getSongs().getJsonObject(0);
        JsonObject secondSong = charts.getSongs().getJsonObject(1);
        assertEquals("getName returns track name", "Panda", charts.getName(firstSong));
        assertEquals("getName returns track name", "Work", charts.getName(secondSong));
    }
    @Test public void testGetTopTwo() {
        String tracks = "1. Panda 2. Work";
        assertEquals("getTopTwo returns string of first two songs", tracks, charts.getTopTwo());
    }
}
