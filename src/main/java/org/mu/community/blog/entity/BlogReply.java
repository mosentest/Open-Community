package org.mu.community.blog.entity;

import org.mu.community.common.entity.User;

import java.util.Date;

/**
 * Created by Muu on 2014/9/25.
 */
public class BlogReply {

    private long id;

    private long comment;

    private User user;

    private Date create;

    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getComment() {
        return comment;
    }

    public void setComment(long comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
