package com.bookmyshow.service;

import com.bookmyshow.dto.theatreCreateRequestDto;
import com.bookmyshow.dto.theatreResponseDto;
import com.bookmyshow.entity.theatreEntity;
import com.bookmyshow.repository.theatreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class theatreService {

    private final theatreRepository theatreRepository;

    public theatreService(theatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public theatreResponseDto create(theatreCreateRequestDto request) {
        theatreEntity theatre = new theatreEntity();
        theatre.setName(request.getName());
        theatre.setCity(request.getCity());
        theatre.setAddress(request.getAddress());
        theatre.setActive(true);
        return toDto(theatreRepository.save(theatre));
    }

    public List<theatreResponseDto> getByCity(String city) {
        return theatreRepository.findByCityAndActiveTrue(city).stream().map(this::toDto).toList();
    }

    public theatreResponseDto getById(Long id) {
        theatreEntity theatre = theatreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Theatre not found"));
        return toDto(theatre);
    }

    private theatreResponseDto toDto(theatreEntity theatre) {
        theatreResponseDto dto = new theatreResponseDto();
        dto.setId(theatre.getId());
        dto.setName(theatre.getName());
        dto.setCity(theatre.getCity());
        dto.setAddress(theatre.getAddress());
        dto.setActive(theatre.getActive());
        return dto;
    }
}
