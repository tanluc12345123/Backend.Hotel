package com.tutorial.apidemo.controllers;


import com.tutorial.apidemo.models.ResponseObject;
import com.tutorial.apidemo.models.Service;
import com.tutorial.apidemo.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/services")
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }
    @GetMapping("/services/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable int id){
        Optional<Service> foundService = serviceRepository.findById(id);
        return foundService.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query service successfully",foundService)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find service with id = " + id,"")
                );
    }

    @PostMapping("/services/insert")
    ResponseEntity<ResponseObject> insertService(@RequestBody Service newService){
        List<Service> foundServices = serviceRepository.findByService(newService.getService().trim());
        if(foundServices.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Service name already taken","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Insert Service Successfully",serviceRepository.save(newService))
        );
    }
    @PutMapping("/services/{id}")
    ResponseEntity<ResponseObject> updateService(@RequestBody Service newService, @PathVariable int id){
        Service updatedService = serviceRepository.findById(id).map(service -> {
            service.setService(newService.getService());
            return serviceRepository.save(service);
        }).orElseGet(()->{
            newService.setId(id);
            return serviceRepository.save(newService);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Update Service Successfully", updatedService)
        );
    }
    @DeleteMapping("/services/{id}")
    ResponseEntity<ResponseObject> deleteService(@PathVariable int id){
        boolean exists = serviceRepository.existsById(id);
        if(exists){
            serviceRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete Service Successfully", "")
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","Cannot find service to delete", "")
            );
        }
    }
}
