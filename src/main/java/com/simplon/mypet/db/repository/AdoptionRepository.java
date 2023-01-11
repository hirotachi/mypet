package com.simplon.mypet.db.repository;

import com.simplon.mypet.db.entity.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}