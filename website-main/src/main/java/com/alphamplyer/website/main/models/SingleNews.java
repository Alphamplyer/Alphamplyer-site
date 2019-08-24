package com.alphamplyer.website.main.models;

import com.alphamplyer.website.main.beans.News;
import com.alphamplyer.website.main.beans.User;

import java.util.List;

public class SingleNews {

    private News news;
    private List<User> authors;

    public SingleNews() { }

    public News getNews() {
        return news;
    }
    public List<User> getAuthors() {
        return authors;
    }

    public void setNews(News news) {
        this.news = news;
    }
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
