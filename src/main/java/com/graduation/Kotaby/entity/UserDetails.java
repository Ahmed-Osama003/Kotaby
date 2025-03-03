package com.graduation.Kotaby.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userDetails")
public class UserDetails {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "image")
    private String image;

    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public UserDetails() {}

    public UserDetails(String image, Date dateOfBirth, String nationality) {
        this.image = image;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
