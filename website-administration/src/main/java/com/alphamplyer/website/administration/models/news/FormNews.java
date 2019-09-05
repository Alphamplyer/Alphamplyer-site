package com.alphamplyer.website.administration.models.news;

import utils.validation.date.ValidTimestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FormNews {

    private long id;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    @ValidTimestamp(message = "{timestamp.publication_date}")
    private String publicationTime;

    private String description;

    @NotNull
    @NotEmpty
    private String content;

    public FormNews() {
    }

    /**
     * Get ID of the news
     * @return ID of the news
     */
    public long getId() {
        return id;
    }

    /**
     * Get publication time of the news
     * @return publication time of the news
     */
    public String getPublicationTime() {
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
     * Define ID of the news
     * @param id ID of the news
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Define publication time of the news
     * @param publicationTime publication time of the news
     */
    public void setPublicationTime(String publicationTime) {
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


    @Override
    public String toString() {
        return "FormNews{" +
            "id=" + id +
            ", publicationTime=" + publicationTime +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
