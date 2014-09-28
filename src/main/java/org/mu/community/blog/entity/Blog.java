package org.mu.community.blog.entity;

import org.mu.community.common.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Muu on 2014/9/25.
 */
public class Blog {

    private long id;

    private User user;

    private BlogType type;

    private BlogCategory category;

    private boolean promoted;

    private boolean top;

    private Date create;

    private Date update;

    private String title;

    private String summary;

    private String content;

    private double score;

    private int nLike;

    private int nView;

    private int nReply;

    private boolean liked;

    private List<String> tags;

    private List<BlogComment> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BlogCategory getCategory() {
        return category;
    }

    public void setCategory(BlogCategory category) {
        this.category = category;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public int getnView() {
        return nView;
    }

    public void setnView(int nView) {
        this.nView = nView;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<BlogComment> getComments() {
        return comments;
    }

    public int getnReply() {
        return nReply;
    }

    public void setnReply(int nReply) {
        this.nReply = nReply;
    }

    public void setComments(List<BlogComment> comments) {
        this.comments = comments;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public BlogType getType() {
        return type;
    }

    public void setType(BlogType type) {
        this.type = type;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
