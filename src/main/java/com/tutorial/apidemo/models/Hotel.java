package com.tutorial.apidemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
public class Hotel extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    private String address;
    @Column(name = "hotel_name")
    private String hotel_name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "rate")
    private float rate;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "location_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Location location;

    @OneToMany(mappedBy = "hotel", cascade = {
            CascadeType.ALL
    })
    private List<Room> rooms = new ArrayList<>();

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Hotel(int id, String address, String hotel_name, String phone, float rate, String content, String image) {
        this.id = id;
        this.address = address;
        this.hotel_name = hotel_name;
        this.phone = phone;
        this.rate = rate;
        this.content = content;
        this.image = image;
    }

    public Hotel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public Location getLocation() {
//        return location;
//    }
//
    public void setLocation(Location location) {
        this.location = location;
    }


}
