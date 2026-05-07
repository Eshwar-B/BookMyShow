package com.bookmyshow.controller;

import com.bookmyshow.dto.showCreateRequestDto;
import com.bookmyshow.dto.showResponseDto;
import com.bookmyshow.service.showService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class showController {

    private final showService showService;

    public showController(showService showService) {
        this.showService = showService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public showResponseDto create(@Valid @RequestBody showCreateRequestDto request) {
        return showService.create(request);
    }

    @GetMapping
    public List<showResponseDto> getUpcomingByMovie(@RequestParam Long movieId) {
        return showService.getUpcomingByMovie(movieId);
    }

    @GetMapping("/{id}")
    public showResponseDto getById(@PathVariable Long id) {
        return showService.getById(id);
    }
}
