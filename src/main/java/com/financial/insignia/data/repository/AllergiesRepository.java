package com.financial.insignia.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.insignia.data.entity.Allergies;

@Repository
public interface AllergiesRepository extends JpaRepository<Allergies, Long>{


	List<Allergies> findAllergiesByGuestId (Long id);
}
