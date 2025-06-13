package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.DTO.AyaResponse;
import com.graduation.Kotaby.service.WordsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quran")
public class QuranController {
    private final WordsService wordsService;

    public QuranController(WordsService wordsService) {
        this.wordsService = wordsService;
    }
    @GetMapping("/page/{pageNumber}")
    public List<AyaResponse> getAyatByPage(@PathVariable int pageNumber) {
        return  wordsService.getAyatByPage(pageNumber);
    }

}
