package org.mu.community.blog.entity;

/**
 * Created by Muu on 2014/9/26.
 */
public class BlogStat {

    private long user;

    private long nView;

    private long nViewDay;

    private long nViewWeek;

    private long nViewMonth;

    private long nBlog;

    private long nLike;

    private String title;

    private String description;

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getnView() {
        return nView;
    }

    public void setnView(long nView) {
        this.nView = nView;
    }

    public long getnBlog() {
        return nBlog;
    }

    public void setnBlog(long nBlog) {
        this.nBlog = nBlog;
    }

    public long getnLike() {
        return nLike;
    }

    public void setnLike(long nLike) {
        this.nLike = nLike;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getnViewDay() {
        return nViewDay;
    }

    public void setnViewDay(long nViewDay) {
        this.nViewDay = nViewDay;
    }

    public long getnViewWeek() {
        return nViewWeek;
    }

    public void setnViewWeek(long nViewWeek) {
        this.nViewWeek = nViewWeek;
    }

    public long getnViewMonth() {
        return nViewMonth;
    }

    public void setnViewMonth(long nViewMonth) {
        this.nViewMonth = nViewMonth;
    }
}
