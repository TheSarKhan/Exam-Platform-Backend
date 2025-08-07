package com.exam.examapp.controller;

import com.exam.examapp.service.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {
    private final FileService fileService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @PostMapping(value = "/upload-file", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file){
        return ResponseEntity.ok(fileService.uploadFile("test", file));
    }
}
