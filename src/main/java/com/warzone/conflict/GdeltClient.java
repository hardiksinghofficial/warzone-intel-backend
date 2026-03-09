package com.warzone.conflict;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class GdeltClient {

    private static final Logger log = LoggerFactory.getLogger(GdeltClient.class);
    private final WebClient webClient;
    private final ObjectMapper mapper;

    @Value("${app.gdelt.base-url}")
    private String baseUrl;

    public GdeltClient(WebClient webClient, ObjectMapper mapper) {
        this.webClient = webClient;
        this.mapper = mapper;
    }

    public List<Conflict> fetchLatestConflicts() {
        List<Conflict> results = new ArrayList<>();
        try {
            String query = "war OR conflict OR military OR bombing OR missile OR nuclear OR airstrike";
            String url = baseUrl + "?query=" + query.replace(" ", "%20")
                    + "&mode=artlist&maxrecords=50&format=json&timespan=15min";

            String body = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
            if (body == null || body.isBlank()) return results;

            JsonNode articles = mapper.readTree(body).path("articles");
            if (!articles.isArray()) return results;

            for (JsonNode art : articles) {
                try {
                    Conflict c = parse(art);
                    if (c != null) results.add(c);
                } catch (Exception ignored) {}
            }
            log.info("Fetched {} events from GDELT", results.size());
        } catch (Exception e) {
            log.error("GDELT fetch error: {}", e.getMessage());
        }
        return results;
    }

    private Conflict parse(JsonNode art) {
        String title = art.path("title").asText("");
        if (title.isBlank()) return null;

        String url = art.path("url").asText("");
        String country = art.path("sourcecountry").asText("XX");
        double lat = art.path("sourcelat").asDouble(30 + Math.random() * 20);
        double lng = art.path("sourcelon").asDouble(30 + Math.random() * 50);

        Conflict c = new Conflict();
        c.setTitle(title.length() > 295 ? title.substring(0, 295) + "..." : title);
        c.setDescription(title);
        c.setCountryA(country.length() >= 2 ? country.substring(0, 2).toUpperCase() : "XX");
        c.setLat(lat); c.setLng(lng);
        c.setSeverity(severity(title));
        c.setStatus("ACTIVE_WAR");
        c.setConflictType("NEWS_EVENT");
        c.setSource("GDELT");
        c.setSourceUrl(url);
        c.setExternalId("gdelt-" + url.hashCode());
        c.setFetchedAt(LocalDateTime.now());
        return c;
    }

    private String severity(String t) {
        String l = t.toLowerCase();
        if (l.contains("nuclear") || l.contains("invasion") || l.contains("massacre")) return "CRITICAL";
        if (l.contains("bombing") || l.contains("airstrike") || l.contains("killed") || l.contains("missile")) return "HIGH";
        if (l.contains("attack") || l.contains("military") || l.contains("clash")) return "MEDIUM";
        return "LOW";
    }
}
