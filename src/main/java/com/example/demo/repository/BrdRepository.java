package com.example.demo.repository;

import com.example.demo.entity.BRD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrdRepository extends JpaRepository<BRD, Long> {

}
