package com.alphamplyer.website.administration.models.news;

import com.alphamplyer.website.administration.beans.news.News;
import com.alphamplyer.website.administration.beans.users.User;

import java.util.List;

public class SingleNews {

    private News news;
    private List<User> authors;

    public SingleNews() { }

    /**
     * Get news
     * @return news
     */
    public News getNews() {
        return news;
    }

    /**
     * Get news authors
     * @return news authors
     */
    public List<User> getAuthors() {
        return authors;
    }

    /**
     * Define news
     * @param news news
     */
    public void setNews(News news) {
        this.news = news;
    }

    /**
     * Define authors
     * @param authors authors
     */
    public void setAuthors(List<User> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "SingleNews{" +
            "news=" + news +
            ", authors=" + authors +
            '}';
    }
}
