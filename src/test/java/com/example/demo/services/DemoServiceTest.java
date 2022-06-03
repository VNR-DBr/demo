package com.example.demo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.example.demo.entities.Demo;
import com.example.demo.repositories.DemoRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DemoServiceTest {

  @InjectMocks
  private DemoService demoService;

  @Mock
  private DemoRepository demoRepository;

  private UUID id;

  @BeforeEach
  void setUp() {
    id = UUID.randomUUID();
  }

  @Test
  void deleteByID() {
    demoService.deleteByID(id);

    verify(demoRepository).deleteById(id);
  }

  @Test
  void getById_successful() {
    Demo demo = new Demo(id, "test");
    Optional<Demo> demoOptional = Optional.of(demo);

    given(demoRepository.findById(id)).willReturn(demoOptional);

    Demo result = demoService.getById(id);

    assertThat(result).isEqualTo(demo);
  }

  @Test
  void getById_throwsNoSuchElementException() {
    Optional<Demo> optionalDemo = Optional.empty();

    given(demoRepository.findById(id)).willReturn(optionalDemo);

    assertThrows(NoSuchElementException.class, () -> demoService.getById(id));
  }
}