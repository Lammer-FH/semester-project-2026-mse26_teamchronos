package com.teamchronos.backend.service;

import com.teamchronos.backend.entity.Room;
import com.teamchronos.backend.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Page<Room> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("pricePerNight").ascending());
        return roomRepository.findAll(pageable);
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }
}