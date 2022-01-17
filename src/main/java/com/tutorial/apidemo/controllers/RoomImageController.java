package com.tutorial.apidemo.controllers;


import com.tutorial.apidemo.models.Hotel;
import com.tutorial.apidemo.models.ResponseObject;
import com.tutorial.apidemo.models.Room;
import com.tutorial.apidemo.models.RoomImage;
import com.tutorial.apidemo.repositories.HotelRepository;
import com.tutorial.apidemo.repositories.RoomImageRepository;
import com.tutorial.apidemo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api")
public class RoomImageController {


    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomImageRepository roomImageRepository;

    @GetMapping("/rooms/{roomId}/roomImages")
    public List<RoomImage> getRoomImagesByRoom(@PathVariable(value = "roomId") int roomId) {
        return roomImageRepository.findByRoomId(roomId);
    }

    @PostMapping("/rooms/{roomId}/roomImages")
    ResponseEntity<ResponseObject> createRoomImage(@PathVariable(value = "roomId") int roomId, @RequestBody RoomImage newRoomImage){
        Optional<Room> foundRoomId = roomRepository.findById(roomId);
        if(foundRoomId.isPresent()){
            foundRoomId.map(room -> {
                newRoomImage.setRoom(room);
                return roomImageRepository.save(newRoomImage);
            });
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query room image successfully",foundRoomId)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","Cannot find room", "")
            );
        }
    }

    @PutMapping("/rooms/{roomId}/roomImages/{roomImageId}")
    ResponseEntity<ResponseObject> updateRoomImage(@PathVariable(value = "roomId") int roomId,
                                              @PathVariable(value = "roomImageId") int roomImageId,@RequestBody RoomImage roomImageRequest) {
        if (!roomRepository.existsById(roomId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","Cannot find room", "")
            );
        }else{
            RoomImage updateRoomImage = roomImageRepository.findById(roomImageId).map(roomImage -> {
                roomImage.setImage(roomImageRequest.getImage());
                return roomImageRepository.save(roomImage);
            }).orElseGet(()->{
                roomImageRequest.setId(roomImageId);
                return roomImageRepository.save(roomImageRequest);
            });
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Update Room Image Successfully", updateRoomImage)
            );
        }
    }
    @DeleteMapping("/rooms/{roomId}/roomImages/{roomImageId}")
    ResponseEntity<ResponseObject> deleterRoomImage(@PathVariable(value = "roomId") int roomId,
                                               @PathVariable(value = "roomImageId") int roomImageId){
        if (!roomRepository.findById(roomId).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","Cannot find room", "")
            );
        }else{
            boolean exists = roomImageRepository.existsById(roomImageId);
            if(exists){
                roomImageRepository.deleteById(roomImageId);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Delete Room Image Successfully", "")
                );
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed","Cannot find room image to delete", "")
                );
            }
        }
    }
}
