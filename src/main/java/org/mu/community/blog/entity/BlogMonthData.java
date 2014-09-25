package org.mu.community.blog.entity;

import java.util.Date;

/**
 * Created by Muu on 2014/9/25.
 */
public class BlogMonthData {

    private Date month;

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
}
