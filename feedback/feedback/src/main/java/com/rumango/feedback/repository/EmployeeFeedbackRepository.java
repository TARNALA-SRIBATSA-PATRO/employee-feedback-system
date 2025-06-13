package com.rumango.feedback.repository;

import com.rumango.feedback.entity.EmployeeFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeFeedbackRepository extends JpaRepository<EmployeeFeedback, Integer> {
}
