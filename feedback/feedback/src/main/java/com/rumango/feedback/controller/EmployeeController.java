package com.rumango.feedback.controller;

import com.rumango.feedback.entity.EmployeeRating;
import com.rumango.feedback.entity.EmployeeFeedback;
import com.rumango.feedback.repository.EmployeeRepository;
import com.rumango.feedback.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private FeedbackRepository feedbackRepo;

    // Save employee (with or without feedback)
    @PostMapping("/save")
    public String saveEmployee(@RequestBody EmployeeRating emp) {
        if (emp.getFeedbackList() != null) {
            for (EmployeeFeedback feedback : emp.getFeedbackList()) {
                feedback.setEmployee(emp);
            }
        }
        repo.save(emp);
        return "Employee data saved successfully!";
    }

    // Get all employees (with feedbacks)
    @GetMapping("/all")
    public List<EmployeeRating> getAllEmployees() {
        return repo.findAll();
    }

    // Get one employee by ID
    @GetMapping("/{id}")
    public EmployeeRating getEmployeeById(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    // Update employee
    @PutMapping("/update")
    public String updateEmployee(@RequestBody EmployeeRating emp) {
        if (emp.getFeedbackList() != null) {
            for (EmployeeFeedback feedback : emp.getFeedbackList()) {
                feedback.setEmployee(emp);
            }
        }
        repo.save(emp);
        return "Employee data updated successfully!";
    }

    // Delete employee
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        repo.deleteById(id);
        return "Employee deleted successfully!";
    }

    // Submit a feedback (rating + optional comment)
    @PutMapping("/rate/{id}")
    public String rateEmployee(@PathVariable int id,
                               @RequestParam int rating,
                               @RequestParam(required = false) String comment) {

        EmployeeRating emp = repo.findById(id).orElse(null);
        if (emp == null) return "Employee not found";

        EmployeeFeedback feedback = new EmployeeFeedback();
        feedback.setRating(rating);
        feedback.setComment(comment);
        feedback.setRatedOn(new Date());
        feedback.setEmployee(emp);

        feedbackRepo.save(feedback);
        return "Feedback saved for " + emp.getName();
    }

    // Get all feedbacks for an employee
    @GetMapping("/feedback/{id}")
    public List<EmployeeFeedback> getAllFeedbacksForEmployee(@PathVariable int id) {
        EmployeeRating emp = repo.findById(id).orElse(null);
        return (emp != null) ? emp.getFeedbackList() : List.of();
    }
}
