package com.cmoncrieffe.satdungeongeneration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/dungeon-generation/api")
public class UrlRequestController {
    @GetMapping("/")
    public ResponseEntity<Boolean> getLive(){
        return ResponseEntity.ok(true);
    }
}
