package com.tutorial.apidemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "room_image")
public class RoomImage extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Room room;

    public RoomImage(){

    }
    public RoomImage(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public Room getRoom() {
//        return room;
//    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

