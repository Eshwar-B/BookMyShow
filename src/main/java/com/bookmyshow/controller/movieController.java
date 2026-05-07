package com.bookmyshow.controller;

import com.bookmyshow.dto.movieCreateRequestDto;
import com.bookmyshow.dto.movieResponseDto;
import com.bookmyshow.service.movieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class movieController {

    private final movieService movieService;

    public movieController(movieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public movieResponseDto create(@Valid @RequestBody movieCreateRequestDto request) {
        return movieService.create(request);
    }

    @GetMapping
    public List<movieResponseDto> getAll() {
        return movieService.getAllActive();
    }

    @GetMapping("/{id}")
    public movieResponseDto getById(@PathVariable Long id) {
        return movieService.getById(id);
    }
}
