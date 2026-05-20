package com.teamchronos.backend.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class BookingService {

    public boolean isRoomAvailable(int roomId, LocalDate startDate, LocalDate endDate) {
        return true;
    }
}