package org.mu.community.blog.repository;

import org.apache.ibatis.annotations.Param;
import org.mu.community.blog.entity.Blog;
import org.mu.community.blog.entity.BlogCategory;
import org.mu.community.blog.entity.BlogComment;
import org.mu.community.blog.entity.BlogReply;
import org.springframework.stereotype.Repository;

/**
 * Created by Muu on 2014/9/25.
 */
@Repository("blogModifyRepository")
public interface BlogModifyRepository {

    public long createBlog(Blog blog);

    public long createCategory(BlogCategory category);

    public long createComment(BlogComment comment);

    public long createReply(BlogReply reply);

    public long updateBlog(Blog blog);

    public long deleteCategory(@Param("user") long user, @Param("category") long category);

    public long deleteBlog(@Param("user") long user, @Param("blog") long blog);

    public long deleteComment(@Param("comment") long comment);

    public long deleteReply(@Param("reply") long reply);

    public int likeBlog(@Param("user") long user, @Param("blog") long blog);

    public int dislikeBlog(@Param("user") long user, @Param("blog") long blog);

    public int likeComment(@Param("user") long user, @Param("comment") long comment);

    public int dislikeComment(@Param("user") long user, @Param("comment") long comment);

}
