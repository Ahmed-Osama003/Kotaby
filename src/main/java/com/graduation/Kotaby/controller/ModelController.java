package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.service.ModelService;
import com.graduation.Kotaby.service.WordsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/memo")
public class ModelController {
    private final ModelService modelService;
    private final WordsService wordsService;

    public ModelController(ModelService modelService, WordsService wordsService) {
        this.modelService = modelService;
        this.wordsService = wordsService;
    }

    @PostMapping(path = "/transcribe",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> transcribeAudio(
            @RequestParam("file") MultipartFile audioFile,
            @RequestParam("page") int page) {
        try {
            String text = modelService.audioToText(audioFile);
            List<String> memoWords = Arrays.asList(text.split(" "));
            List<String> words = wordsService.getWordsByPage(page);
            words.forEach(System.out::println);
            System.out.println();
            System.out.println();
            memoWords.forEach(System.out::println);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "transcribedText", text,
                    "filename", audioFile.getOriginalFilename()
            ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "success", false,
                            "error", e.getMessage()
                    ));
        }
    }

}

