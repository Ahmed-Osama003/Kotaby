package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.entity.primary.Streak;
import com.graduation.Kotaby.service.StreakService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/streak")
public class StreakController {
    private final StreakService streakService;

    public StreakController(StreakService streakService) {
        this.streakService = streakService;
    }

    @PostMapping("/create/{userID}")
    public Streak createStreak(@PathVariable int userID) {
        streakService.create(userID);
        return streakService.getStreak(userID)
                .orElseThrow(() -> new RuntimeException("Streak not found for user ID: " + userID));
    }

    @PostMapping("/update/{userID}")
    public Streak updateStreak(@PathVariable int userID, @RequestBody(required = false) Date date) {
        if (date == null) {
            date = new Date();
        }
        streakService.update(userID, date);
        return streakService.getStreak(userID)
                .orElseThrow(() -> new RuntimeException("Streak not found for user ID: " + userID));
    }

    @GetMapping("/{userID}")
    public Streak getStreak(@PathVariable int userID) {
        return streakService.getStreak(userID)
                .orElseThrow(() -> new RuntimeException("Streak not found for user ID: " + userID));
    }
}
