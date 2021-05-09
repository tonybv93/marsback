package com.mars.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.auth.entity.Segment;

@Repository
public interface ISegmentRepository extends JpaRepository<Segment, String> {

}
