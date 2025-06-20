package com.graduation.Kotaby.service;

import com.graduation.Kotaby.DTO.HadithDTO;
import com.graduation.Kotaby.DTO.HadithResponseDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HadithService {private static final Logger logger = Logger.getLogger(HadithService.class.getName());

    private static final String DORAR_API_URL = "https://dorar.net/dorar_api.json";

    private final RestTemplate restTemplate;

    public HadithService() {
        this.restTemplate = new RestTemplate();
    }


    public List<HadithDTO> getRandomHadiths() {
        return getHadiths("");
    }

    public List<HadithDTO> searchHadiths(String searchKey) {
        return getHadiths(searchKey);
    }


    private List<HadithDTO> getHadiths(String searchKey) {
        try {
            String url = DORAR_API_URL + "?skey=" + (searchKey != null ? searchKey : "");
            logger.info("Calling Dorar API: " + url);

            HadithResponseDTO response = restTemplate.getForObject(url, HadithResponseDTO.class);

            if (response != null && response.getAhadith() != null && response.getAhadith().getResult() != null) {
                return parseHadithHtml(response.getAhadith().getResult());
            }

            return new ArrayList<>();

        } catch (RestClientException e) {
            logger.severe("Error calling Dorar API: " + e.getMessage());
            throw new RuntimeException("Failed to fetch hadiths from external API", e);
        }
    }

    private List<HadithDTO> parseHadithHtml(String htmlContent) {
        List<HadithDTO> hadiths = new ArrayList<>();

        try {
            Document doc = Jsoup.parse(htmlContent);
            Elements hadithElements = doc.select("div.hadith");
            Elements hadithInfoElements = doc.select("div.hadith-info");

            for (int i = 0; i < hadithElements.size() && i < hadithInfoElements.size(); i++) {
                Element hadithElement = hadithElements.get(i);
                Element infoElement = hadithInfoElements.get(i);

                String hadithText = cleanArabicText(hadithElement.text());

                String narrator = extractInfoValue(infoElement, "الراوي:");
                String scholar = extractInfoValue(infoElement, "المحدث:");
                String source = extractInfoValue(infoElement, "المصدر:");
                String pageOrNumber = extractInfoValue(infoElement, "الصفحة أو الرقم:");
                String authenticity = extractInfoValue(infoElement, "خلاصة حكم المحدث:");

                String rawHtml = hadithElement.outerHtml() + infoElement.outerHtml();

                HadithDTO hadith = new HadithDTO(hadithText, narrator, scholar, source,
                        pageOrNumber, authenticity, rawHtml);
                hadiths.add(hadith);
            }

        } catch (Exception e) {
            logger.severe("Error parsing hadith HTML: " + e.getMessage());
        }

        return hadiths;
    }

    private String extractInfoValue(Element infoElement, String label) {
        Elements spans = infoElement.select("span");
        for (int i = 0; i < spans.size() - 1; i++) {
            if (spans.get(i).text().equals(label)) {
                return spans.get(i + 1).text().trim();
            }
        }
        return "";
    }

    private String cleanArabicText(String text) {
        if (text == null) return "";
        return text.replaceAll("\\s+", " ").trim();
    }
}