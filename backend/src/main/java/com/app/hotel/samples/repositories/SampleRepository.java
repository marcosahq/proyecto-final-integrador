package com.app.hotel.samples.repositories;

import com.app.hotel.samples.models.entities.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
