package de.bitgrip.codingchallenge.repository;

import de.bitgrip.codingchallenge.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer>{
}