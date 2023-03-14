package com.example.server.realestate;

import com.example.server.exceptions.InvalidInputException;
import com.example.server.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstateServices {

    private final EstateRepository estateRepository;
    @Autowired
    public EstateServices(EstateRepository estateRepository) {
        this.estateRepository = estateRepository;
    }

    public List<Estate> findAllEstates() {
        return estateRepository.findAll();
    }

    public Optional<Estate> findEstateById(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidInputException("ID must not be null or empty");
        }
        return estateRepository.findById(id);
    }

    public List<Estate> findEstatesByCriteria(String name, Double minPrice, Double maxPrice,
                                              Integer minRoomNum, Integer maxRoomNum, Integer minArea, Integer maxArea) {
        return estateRepository.findByNameContainingIgnoreCaseAndPriceBetweenAndRoomNumBetweenAndAreaBetween(
                name, minPrice, maxPrice, minRoomNum, maxRoomNum, minArea, maxArea);
    }

    public Estate addEstate(Estate estate) {
        return estateRepository.save(estate);
    }

    public Estate updateEstate(String id, Estate estate) {
        try {
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException("ID must not be null or empty");
            }

            Estate existingEstate = estateRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Estate", "id", id));

            existingEstate.setName(estate.getName());
            existingEstate.setPrice(estate.getPrice());
            existingEstate.setRoomNum(estate.getRoomNum());
            existingEstate.setBathroomNum(estate.getBathroomNum());
            existingEstate.setArea(estate.getArea());
            existingEstate.setDescription(estate.getDescription());
            existingEstate.setNegotiable(estate.isNegotiable());
            existingEstate.setPhoneNumber(estate.getPhoneNumber());
            existingEstate.setEmail(estate.getEmail());
            existingEstate.setImageUrl(estate.getImageUrl());

            return estateRepository.save(existingEstate);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    public void deleteEstateById(String id) {
        try {
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException("ID must not be null or empty");
            }

            Estate estateToDelete = estateRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Estate", "id", id));

            estateRepository.delete(estateToDelete);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

}
