package com.example.server.realestate;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "estates")
public class Estate {

    public Estate(String name, double price,
                  int roomNum, int bathroomNum,
                  int area, String description,
                  boolean isNegotiable, String phoneNumber,
                  String email, String imageUrl) {
        this.name = name;
        this.price = price;
        this.roomNum = roomNum;
        this.bathroomNum = bathroomNum;
        this.area = area;
        this.description = description;
        this.isNegotiable = isNegotiable;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    @Id
    private String id;

    private String name;

    private double price;

    @Field("room_num")
    private int roomNum;

    @Field("bathroom_num")
    private int bathroomNum;

    private int area;

    private String description;

    @Field("is_negotiable")
    private boolean isNegotiable;

    @Field("phone_number")
    private String phoneNumber;

    private String email;

    @Field("image_url")
    private String imageUrl;

}