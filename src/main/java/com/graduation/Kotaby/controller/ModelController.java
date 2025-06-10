package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.service.CheckerService;
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
    private final CheckerService checkerService;

    public ModelController(ModelService modelService, WordsService wordsService, CheckerService checkerService) {
        this.modelService = modelService;
        this.wordsService = wordsService;
        this.checkerService = checkerService;
    }

    @PostMapping(path = "/transcribe",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> transcribeAudio(
            @RequestParam("file") MultipartFile audioFile,
            @RequestParam("id") int userID,
            @RequestParam("page") int page) {
        try {
            String text = modelService.audioToText(audioFile);
            List<String> userWords = Arrays.asList(text.split(" "));
            List<String> pageWords = wordsService.getWordsByPage(page);
            userWords.forEach(System.out::println);
            System.out.println();
            System.out.println();
            pageWords.forEach(System.out::println);
            return checkerService.check(userWords, pageWords, userID, page);
        } catch (Exception e) {
            return List.of("Error processing audio file: " + e.getMessage());
        }
    }

}

