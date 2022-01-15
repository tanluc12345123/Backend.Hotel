package com.tutorial.apidemo.repositories;

import com.tutorial.apidemo.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByLocationId(int locationId);
    Optional<Hotel> findByIdAndLocationId(int id, int LocationId);
}
