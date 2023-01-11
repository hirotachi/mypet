package com.simplon.mypet.db.repository;

import com.simplon.mypet.db.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}