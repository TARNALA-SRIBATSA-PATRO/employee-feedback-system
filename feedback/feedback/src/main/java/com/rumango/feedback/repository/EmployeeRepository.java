package com.rumango.feedback.repository;

import com.rumango.feedback.entity.EmployeeRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeRating, Integer> {
}
