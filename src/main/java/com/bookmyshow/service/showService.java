package com.bookmyshow.service;

import com.bookmyshow.dto.showCreateRequestDto;
import com.bookmyshow.dto.showResponseDto;
import com.bookmyshow.entity.movieEntity;
import com.bookmyshow.entity.screenEntity;
import com.bookmyshow.entity.showEntity;
import com.bookmyshow.entity.showStatusEnum;
import com.bookmyshow.repository.movieRepository;
import com.bookmyshow.repository.screenRepository;
import com.bookmyshow.repository.showRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class showService {

    private final showRepository showRepository;
    private final movieRepository movieRepository;
    private final screenRepository screenRepository;

    public showService(showRepository showRepository, movieRepository movieRepository, screenRepository screenRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
    }

    public showResponseDto create(showCreateRequestDto request) {
        if (!request.getShowEndTime().isAfter(request.getShowStartTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show end time must be after start time");
        }

        movieEntity movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        screenEntity screen = screenRepository.findById(request.getScreenId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Screen not found"));

        showEntity show = new showEntity();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setShowStartTime(request.getShowStartTime());
        show.setShowEndTime(request.getShowEndTime());
        show.setBasePrice(request.getBasePrice());
        show.setStatus(request.getStatus() != null ? request.getStatus() : showStatusEnum.SCHEDULED);
        return toDto(showRepository.save(show));
    }

    public List<showResponseDto> getUpcomingByMovie(Long movieId) {
        return showRepository.findByMovieIdAndShowStartTimeAfterAndStatus(movieId, LocalDateTime.now(), showStatusEnum.SCHEDULED)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public showResponseDto getById(Long id) {
        showEntity show = showRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found"));
        return toDto(show);
    }

    private showResponseDto toDto(showEntity show) {
        showResponseDto dto = new showResponseDto();
        dto.setId(show.getId());
        dto.setMovieId(show.getMovie().getId());
        dto.setScreenId(show.getScreen().getId());
        dto.setShowStartTime(show.getShowStartTime());
        dto.setShowEndTime(show.getShowEndTime());
        dto.setBasePrice(show.getBasePrice());
        dto.setStatus(show.getStatus());
        return dto;
    }
}
