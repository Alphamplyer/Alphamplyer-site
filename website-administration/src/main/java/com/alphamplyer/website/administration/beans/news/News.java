package com.alphamplyer.website.administration.beans.news;

import java.sql.Timestamp;
import java.util.List;

/**
 * Data of news
 */
public class News {

    private Integer id;
    private Integer categoryId;
    private Integer mainImageId;
    private Timestamp publicationTime;
    private String title;
    private String description;
    private String content;
    private List<Integer> authors;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public News() {
    }

    /**
     * Get ID of the news
     * @return ID of the news
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get category ID of the news
     * @return category ID of the news
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Get main image ID of the news
     * @return main image ID of the news
     */
    public Integer getMainImageId() {
        return mainImageId;
    }

    /**
     * Get publication time of the news
     * @return publication time of the news
     */
    public Timestamp getPublicationTime() {
        return publicationTime;
    }

    /**
     * Get title of the news
     * @return title of the news
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get description of the news
     * @return description of the news
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get content of the news
     * @return get content of the news
     */
    public String getContent() {
        return content;
    }

    /**
     * Get list of author ID of the news
     * @return list of author ID of the news
     */
    public List<Integer> getAuthors() {
        return authors;
    }

    /**
     * Get the creation date and time of the news
     * @return the creation date and time of the news
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the last update date and time of the news
     * @return the last update date and time of the news
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Define ID of the news
     * @param id ID of the news
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Define category ID of the news
     * @param categoryId category ID of the news
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Define main image ID of the news
     * @param mainImageId main image ID of the news
     */
    public void setMainImageId(Integer mainImageId) {
        this.mainImageId = mainImageId;
    }

    /**
     * Define publication time of the news
     * @param publicationTime publication time of the news
     */
    public void setPublicationTime(Timestamp publicationTime) {
        this.publicationTime = publicationTime;
    }

    /**
     * Define title of the news
     * @param title title of the news
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Define description of the news
     * @param description description of the news
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Define content of the news
     * @param content content of the news
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Define list of authors ID of the news
     * @param authors list of authors ID of the news
     */
    public void setAuthors(List<Integer> authors) {
        this.authors = authors;
    }

    /**
     * Define creation date and time of the news
     * @param createdAt creation date and time of the news
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Define last update date and time of the news
     * @param updatedAt last update date and time of the news
     */
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