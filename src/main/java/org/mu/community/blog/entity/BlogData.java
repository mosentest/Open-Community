package org.mu.community.blog.entity;

import java.util.Date;

/**
 * Created by Muu on 2014/9/25.
 */
public class BlogData {

    private Date month;

    private BlogType type;

    private int nBlog;

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public int getnBlog() {
        return nBlog;
    }

    public void setnBlog(int nBlog) {
        this.nBlog = nBlog;
    }

    public BlogType getType() {
        return type;
    }

    public void setType(BlogType type) {
        this.type = type;
    }
}
