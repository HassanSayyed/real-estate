package com.example.server.realestate;

import com.example.server.realestate.Requests.EstateSearchRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/estate")
public class EstateController {

    private final EstateServices estateServices;
    @Autowired
    public EstateController(EstateServices estateServices) {
        this.estateServices = estateServices;
    }

    @GetMapping
    public ResponseEntity<List<Estate>> getAllEstates() {
        return new ResponseEntity<List<Estate>>(estateServices.findAllEstates(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Estate>> getSingleEstate(@PathVariable String id){
        return new ResponseEntity<Optional<Estate>>(estateServices.findEstateById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Estate> addEstate(@RequestBody Estate estate) {
        System.out.println(estate.toString());
        Estate newEstate = estateServices.addEstate(estate);
        return new ResponseEntity<Estate>(newEstate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estate> updateEstate(@PathVariable String id, @RequestBody Estate estate) {
        Estate updatedEstate = estateServices.updateEstate(id, estate);
        return new ResponseEntity<Estate>(updatedEstate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstateById(@PathVariable("id") String id) {
        estateServices.deleteEstateById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search")
    public List<Estate> getEstatesByCriteria(@RequestBody EstateSearchRequest estateSearchRequest) {
        return estateServices.findEstatesByCriteria(
                estateSearchRequest.getName(),
                estateSearchRequest.getMinPrice(),
                estateSearchRequest.getMaxPrice(),
                estateSearchRequest.getMinRoomNum(),
                estateSearchRequest.getMaxRoomNum(),
                estateSearchRequest.getMinArea(),
                estateSearchRequest.getMaxArea()
        );
    }


}
