package com.events.dacapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.dacapi.models.Volume;

public interface VolumeRepository extends JpaRepository<Volume, Long> {
	public List<Volume> findAllByOrderByInitialsAscStartDateAsc();
}
