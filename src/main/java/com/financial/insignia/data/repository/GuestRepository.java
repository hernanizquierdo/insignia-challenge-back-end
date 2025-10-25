package com.financial.insignia.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.insignia.data.entity.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long>{

}
