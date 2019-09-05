package com.alphamplyer.website.administration.beans.news;

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

    /**
     * Get ID of the news category
     * @return ID of the news category
     */
    public int getId() {
        return id;
    }

    /**
     * Get parent ID of the news category
     * @return parent ID of the news category
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * Get creator ID of the news category
     * @return creator ID of the news category
     */
    public int getCreatorId() {
        return creatorId;
    }

    /**
     * Get name of the news category
     * @return name of the news category
     */
    public String getName() {
        return name;
    }

    /**
     * Get description of the news category
     * @return description of the news category
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the creation date time of the news category
     * @return creation date time of the news category
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the last update date time of the news category
     * @return last update date time of the news category
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }


    /**
     * Define ID of the news category
     * @param id ID of the news category
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Define Parent ID of the news category
     * @param parentId Parent Id of the news category
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * Define the creator ID of the news category
     * @param creatorId the creator ID of the news category
     */
    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * Define the name of the news category
     * @param name the name of the news category
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Define the description of the news category
     * @param description the description of the news category
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Define the creation date time of the news category
     * @param createdAt the creation date time of the news category
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Define the last update date time of the news category
     * @param updatedAt the last update date time of the news category
     */
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
