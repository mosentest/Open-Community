package org.mu.community.blog.entity;

import org.mu.community.common.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Muu on 2014/9/25.
 */
public class BlogComment {

    private long id;

    private long blog;

    private User user;

    private Date create;

    private String content;

    private int nLike;

    private int nReply;

    private boolean liked;

    private List<BlogReply> replies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBlog() {
        return blog;
    }

    public void setBlog(long blog) {
        this.blog = blog;
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

    public int getnLike() {
        return nLike;
    }

    public void setnLike(int nLike) {
        this.nLike = nLike;
    }

    public List<BlogReply> getReplies() {
        return replies;
    }

    public void setReplies(List<BlogReply> replies) {
        this.replies = replies;
    }

    public int getnReply() {
        return nReply;
    }

    public void setnReply(int nReply) {
        this.nReply = nReply;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
