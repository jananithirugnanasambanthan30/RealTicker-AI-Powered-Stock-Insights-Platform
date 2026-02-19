package com.realticker.backend1.service;

import com.realticker.backend1.model.HistoricalPrice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;
import java.util.Map;

@Service
public class AiAnalysisService {

    @Value("${huggingface.api.key}")
    private String apiKey;

    // ✅ NEW ROUTER ENDPOINT (ONLY THIS WORKS NOW)
    private static final String API_URL =
            "https://router.huggingface.co/v1/chat/completions";

    private final RestTemplate restTemplate = new RestTemplate();

    public String analyzeStock(List<HistoricalPrice> history) {

        String prompt = """
                Analyze the following 6 months stock price data.
                Provide output in this format:

                Trend: (Upward / Downward / Sideways)
                Risk Level: (Low / Medium / High)
                Suggested Action: (Long-term investment / Short-term watch / Avoid)
                Reason: Explain simply for a beginner investor.

                Stock Data:
                """ + history;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "model", "mistralai/Mistral-7B-Instruct-v0.2",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                ),
                "max_tokens", 300
        );

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        API_URL,
                        HttpMethod.POST,
                        request,
                        String.class
                );
        String raw = response.getBody();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(raw);

            String aiText =
                    root.path("choices")
                            .get(0)
                            .path("message")
                            .path("content")
                            .asText();

            return aiText;

        } catch (Exception e) {
            e.printStackTrace();
            return "AI analysis failed";
        }




    }
}

