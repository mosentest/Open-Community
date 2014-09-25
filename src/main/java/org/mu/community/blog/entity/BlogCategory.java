package org.mu.community.blog.entity;

import org.mu.community.common.entity.User;

/**
 * Created by Muu on 2014/9/25.
 */
public class BlogCategory {

    private long id;

    private long user;

    private String name;

    private int nBlog;

    public BlogCategory() {}

    public BlogCategory(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getnBlog() {
        return nBlog;
    }

    public void setnBlog(int nBlog) {
        this.nBlog = nBlog;
    }
}
