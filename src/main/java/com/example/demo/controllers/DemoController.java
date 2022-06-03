package com.example.demo.controllers;

import com.example.demo.entities.Demo;
import com.example.demo.services.DemoService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

  final DemoService demoService;

  @GetMapping(path = "/demo/{demoId}")
  public ResponseEntity<Demo> getById(@PathVariable("demoId") UUID id) {
        return new ResponseEntity(demoService.getById(id), HttpStatus.OK);
  }

  @DeleteMapping(path = "/demo/{demoId}")
  public ResponseEntity<String> deleteById(@PathVariable("demoId") UUID id) {
    demoService.deleteByID(id);

    return new ResponseEntity("", HttpStatus.OK);
  }
}
