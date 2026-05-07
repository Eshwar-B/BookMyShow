package com.bookmyshow.controller;

import com.bookmyshow.dto.theatreCreateRequestDto;
import com.bookmyshow.dto.theatreResponseDto;
import com.bookmyshow.service.theatreService;
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
@RequestMapping("/theatres")
public class theatreController {

    private final theatreService theatreService;

    public theatreController(theatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public theatreResponseDto create(@Valid @RequestBody theatreCreateRequestDto request) {
        return theatreService.create(request);
    }

    @GetMapping
    public List<theatreResponseDto> getByCity(@RequestParam String city) {
        return theatreService.getByCity(city);
    }

    @GetMapping("/{id}")
    public theatreResponseDto getById(@PathVariable Long id) {
        return theatreService.getById(id);
    }
}
