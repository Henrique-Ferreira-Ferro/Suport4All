package com.io.Suport4All.controller;

import com.io.Suport4All.dto.NetworkCommandRequest;
import com.io.Suport4All.service.NetworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/network")
@CrossOrigin(origins = "*")
public class NetworkController {

    private final NetworkService networkService;

    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @PostMapping("/execute")
    public ResponseEntity<String> execute(
            @RequestBody NetworkCommandRequest request) {

        return ResponseEntity.ok(
                networkService.execute(request)
        );
    }
}

