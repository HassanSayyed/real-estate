package com.example.server.realestate;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstateRepository extends MongoRepository<Estate, String> {

    List<Estate> findByNameContainingIgnoreCaseAndPriceBetweenAndRoomNumBetweenAndAreaBetween(
            String name, double minPrice, double maxPrice,
            int minRoomNum, int maxRoomNum, int minArea, int maxArea
    );

}
