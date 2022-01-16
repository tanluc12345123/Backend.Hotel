package com.tutorial.apidemo.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

//    public Hotel getHotel() {
//        return hotel;
//    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
