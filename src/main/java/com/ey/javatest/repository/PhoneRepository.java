package com.ey.javatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.javatest.data.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Integer>{

}
