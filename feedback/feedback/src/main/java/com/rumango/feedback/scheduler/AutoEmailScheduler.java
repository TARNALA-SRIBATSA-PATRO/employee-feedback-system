package com.rumango.feedback.scheduler;

import com.rumango.feedback.entity.EmployeeRating;
import com.rumango.feedback.repository.EmployeeRepository;
import com.rumango.feedback.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoEmailScheduler {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private EmailSenderService mailService;

    // Run every 1 hour (3600000 ms) → Change to "86400000" for daily
    @Scheduled(fixedRate = 36)  // every 1 hour
    public void sendRatingReminderToManagers() {
        List<EmployeeRating> employees = repo.findAll();

        for (EmployeeRating emp : employees) {
            String subject = "Reminder to Rate: " + emp.getName();
            String body = "Dear " + emp.getManagerName() + ",\n\n"
                    + "Please provide a rating for your employee: " + emp.getName() + ".\n"
                    + "You can click a number below to rate:\n"
                    + "1: http://localhost:8080/employee/rate/" + emp.getId() + "?rating=1\n"
                    + "2: http://localhost:8080/employee/rate/" + emp.getId() + "?rating=2\n"
                    + "3: http://localhost:8080/employee/rate/" + emp.getId() + "?rating=3\n"
                    + "4: http://localhost:8080/employee/rate/" + emp.getId() + "?rating=4\n"
                    + "5: http://localhost:8080/employee/rate/" + emp.getId() + "?rating=5\n\n"
                    + "Thanks,\nFeedback System";

            mailService.sendEmail(emp.getManagerEmail(), subject, body);
        }

        System.out.println("Reminder emails sent to all managers.");
    }

}
