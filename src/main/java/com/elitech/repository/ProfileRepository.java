package com.elitech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elitech.model.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
