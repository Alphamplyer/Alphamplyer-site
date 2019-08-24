package com.alphamplyer.website.main.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;

public class News {

    private long id;
    private int categoryId;
    private int mainImageId;
    private Timestamp publicationTime;
    private String title;
    private String description;
    private String content;
    private List<Integer> authors;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public News() {
    }


    public long getId() {
        return id;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public int getMainImageId() {
        return mainImageId;
    }
    public Timestamp getPublicationTime() {
        return publicationTime;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getContent() {
        return content;
    }
    public List<Integer> getAuthors() {
        return authors;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }


    public void setId(long id) {
        this.id = id;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public void setMainImageId(int mainImageId) {
        this.mainImageId = mainImageId;
    }
    public void setPublicationTime(Timestamp publicationTime) {
        this.publicationTime = publicationTime;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setAuthors(List<Integer> authors) {
        this.authors = authors;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "News{" +
            "id=" + id +
            ", categoryId=" + categoryId +
            ", mainImageId=" + mainImageId +
            ", publicationTime=" + publicationTime +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", content='" + content + '\'' +
            ", authors=" + authors +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
