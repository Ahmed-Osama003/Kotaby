package com.graduation.Kotaby.entity;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "start_page")
    int startPage;

    @Column(name = "end_page")
    int endPage;

    @Column(name = "end_date")
    Date endDate;

    @Column (name = "completed")
    Boolean completed;

    public Task(boolean completed, int startPage, int endPage, Date endDate) {
        this.completed = completed;
        this.startPage = startPage;
        this.endPage = endPage;
        this.endDate = endDate;
    }

    public Task() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", startPage='" + startPage + '\'' +
                ", endPage='" + endPage + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
