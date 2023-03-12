package com.example.server.realestate.Requests;

import lombok.Getter;

@Getter
public class EstateSearchRequest {

    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Integer minRoomNum;
    private Integer maxRoomNum;
    private Integer minArea;
    private Integer maxArea;
}
