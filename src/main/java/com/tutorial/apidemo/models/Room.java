package com.tutorial.apidemo.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;


import java.util.List;



@Entity
@Table(name = "rooms")
public class Room extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "room_name")
    private String room_name;
    @Column(name = "price")
    private float price;
    @Column(name = "status")
    private boolean status;
    @Column(name = "content")
    private String content;

    @Column(name = "service")
    private String service = null;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "hotel_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotel_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = {
            CascadeType.ALL
    })
    private List<RoomImage> roomImages = new ArrayList<>();


    @Transient
    private String hotel_name;

    @Transient
    private String location_name;

//    @OneToMany(mappedBy = "room", cascade = {
//            CascadeType.ALL
//    })
//    private List<RoomOrder> room_orders = new ArrayList<>();


    public String getHotel_name() {
        return hotel.getHotel_name();
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getLocation_name() {
        return hotel.getLocation_name();
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Room(){

    }

    public Room(int id, String room_name, float price, boolean status, String content) {
        this.id = id;
        this.room_name = room_name;
        this.price = price;
        this.status = status;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    //    public Hotel getHotel() {
//        return hotel;
//    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<RoomImage> getRoomImages() {
        return roomImages;
    }

    public void setRoomImages(List<RoomImage> roomImages) {
        this.roomImages = roomImages;
    }
}
