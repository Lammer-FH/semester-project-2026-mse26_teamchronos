package com.teamchronos.backend.repository;

import com.teamchronos.backend.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, PagingAndSortingRepository<Room, Long> {

    /**
     * Find page of room entries ordered by price per night.
     *
     * @param pageable for pagination
     * @return ordered page of room entries
     */
    //Page<Room> findAllByPricePerNight(Pageable pageable);
}
