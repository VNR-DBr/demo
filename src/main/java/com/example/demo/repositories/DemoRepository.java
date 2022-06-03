package com.example.demo.repositories;

import com.example.demo.entities.Demo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<Demo, UUID> {

}
