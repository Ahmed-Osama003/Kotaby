package com.graduation.Kotaby.entity.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompletionId implements Serializable {
    private int taskId;
    private int userId;

    public CompletionId() {
    }

    public CompletionId(int taskId, int userId) {
        this.taskId = taskId;
        this.userId = userId;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CompletionId that = (CompletionId) object;
        return Objects.equals(userId, that.userId) && Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, taskId);
    }

}
