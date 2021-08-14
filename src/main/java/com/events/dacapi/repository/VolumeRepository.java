package com.events.dacapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.dacapi.models.Volume;

public interface VolumeRepository extends JpaRepository<Volume, Long> {

}
