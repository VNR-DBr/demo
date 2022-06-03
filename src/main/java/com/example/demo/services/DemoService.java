package com.example.demo.services;

import com.example.demo.entities.Demo;
import com.example.demo.repositories.DemoRepository;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {

  final DemoRepository demoRepository;

  public void deleteByID(UUID id) {
    demoRepository.deleteById(id);
  }

  public Demo getById(UUID id) {
    return demoRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }
}
