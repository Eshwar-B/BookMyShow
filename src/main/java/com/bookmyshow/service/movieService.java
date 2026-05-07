package com.bookmyshow.service;

import com.bookmyshow.dto.movieCreateRequestDto;
import com.bookmyshow.dto.movieResponseDto;
import com.bookmyshow.entity.movieEntity;
import com.bookmyshow.repository.movieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class movieService {

    private final movieRepository movieRepository;

    public movieService(movieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public movieResponseDto create(movieCreateRequestDto request) {
        movieEntity movie = new movieEntity();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setDurationMinutes(request.getDurationMinutes());
        movie.setLanguage(request.getLanguage());
        movie.setGenre(request.getGenre());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setActive(true);
        return toDto(movieRepository.save(movie));
    }

    public List<movieResponseDto> getAllActive() {
        return movieRepository.findByActiveTrue().stream().map(this::toDto).toList();
    }

    public movieResponseDto getById(Long id) {
        movieEntity movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        return toDto(movie);
    }

    private movieResponseDto toDto(movieEntity movie) {
        movieResponseDto dto = new movieResponseDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setDurationMinutes(movie.getDurationMinutes());
        dto.setLanguage(movie.getLanguage());
        dto.setGenre(movie.getGenre());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setActive(movie.getActive());
        return dto;
    }
}
