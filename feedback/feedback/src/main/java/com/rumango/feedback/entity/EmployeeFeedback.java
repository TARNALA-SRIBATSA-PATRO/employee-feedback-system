package com.rumango.feedback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee_feedback")
public class EmployeeFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rating;

    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rated_on", nullable = false, updatable = false)
    private Date ratedOn;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeRating employee;

    // Automatically set the ratedOn timestamp before saving
    @PrePersist
    protected void onCreate() {
        if (this.ratedOn == null) {
            this.ratedOn = new Date();
        }
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getRatedOn() {
        return ratedOn;
    }

    public void setRatedOn(Date ratedOn) {
        this.ratedOn = ratedOn;
    }

    public EmployeeRating getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeRating employee) {
        this.employee = employee;
    }
}
