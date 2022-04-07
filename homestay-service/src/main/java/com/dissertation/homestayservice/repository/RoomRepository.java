package com.dissertation.homestayservice.repository;

import com.dissertation.common.entities.homestay_service.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

    @Query("{homestay_id: ?0}")
    List<Room> findByHomestayId(String homestayId);
}
