package com.teamchronos.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import com.teamchronos.backend.entity.Room;

@Service
public class RoomService {

    public Page<Room> findPaginated(int page, int size) {
        return Page.empty();
    }
}