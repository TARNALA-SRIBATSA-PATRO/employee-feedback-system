package com.rumango.feedback.controller;

import com.rumango.feedback.entity.EmployeeFeedback;
import com.rumango.feedback.entity.EmployeeRating;
import com.rumango.feedback.repository.EmployeeFeedbackRepository;
import com.rumango.feedback.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/feedback")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeFeedbackController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeFeedbackRepository feedbackRepository;

    @PostMapping("/{employeeId}")
    public String saveFeedback(@PathVariable int employeeId, @RequestBody EmployeeFeedback feedback) {
        EmployeeRating employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            return "Employee not found!";
        }

        feedback.setEmployee(employee);
        feedbackRepository.save(feedback);
        return "Feedback saved successfully!";
    }
}
