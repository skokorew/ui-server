package ru.scrumtrek.uiserver.time;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class WorldTimeGetter {
    private ObjectMapper mapper = new ObjectMapper();

    public String getTime(String timeType) throws IOException {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(timeUrl(timeType)).openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (200 != responseCode) throw new IOException();
            byte[] contents = IOUtils.toByteArray(conn.getInputStream());
            JsonNode timeContents = mapper.readTree(contents);
            String time = timeContents.get("currentDateTime").textValue();
            return time;
        } catch (MalformedURLException e) {
            return LocalDateTime.now().toString();
        }
    }

    private String timeUrl(String timeType) {
        switch (timeType) {
            case "est": return "http://worldclockapi.com/api/json/est/now";
            case "utc": return "http://worldclockapi.com/api/json/utc/now";
            default: return null;
        }
    }
}
