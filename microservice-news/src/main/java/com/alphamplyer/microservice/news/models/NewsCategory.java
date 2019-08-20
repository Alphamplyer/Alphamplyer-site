package com.alphamplyer.microservice.news.models;

import java.sql.Timestamp;

public class NewsCategory {

    private int id;
    private int parentId;
    private int creatorId;
    private String name;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public NewsCategory() {
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "NewsCategory{" +
            "id=" + id +
            ", parentId=" + parentId +
            ", creatorId=" + creatorId +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }

}
