package org.mu.community.blog.repository;

import org.apache.ibatis.annotations.Param;
import org.mu.community.blog.entity.*;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Tuple;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Muu on 2014/9/25.
 */
@Repository("blogRepository")
public interface BlogRepository {

    public BlogStat getBlogStat(long user);

    public BlogCategory getBlogCategory(long category);

    public List<BlogCategory> getCategories(long user);

    public int countBlogByMonth(@Param("user") long user, @Param("from") Date from, @Param("to") Date to);

    public long countAll();

    public List<Blog> getPromotedBlogs();

    public List<Blog> getDailyMostViewed(@Param("listMode") boolean listMode, @Param("offset") int offset, @Param("size") int size);

    public List<Blog> getWeeklyMostViewed(@Param("listMode") boolean listMode, @Param("offset") int offset, @Param("size") int size);

    public List<Blog> getMonthlyMostViewed(@Param("listMode") boolean listMode, @Param("offset") int offset, @Param("size") int size);

    public List<Blog> getRecentBlogs(@Param("offset") int offset, @Param("size") int size);

    public List<Blog> getBlogsById(@Param("listMode") boolean listMode, @Param("idList") List<Long> idList);

    public List<Blog> getBlogsByUser(@Param("user") long user, @Param("listMode") boolean listMode, @Param("offset") int offset, @Param("size") int size);

    public List<Blog> getBlogByCategory(@Param("user") long user, @Param("category") long category, @Param("listMode") boolean listMode,
                                        @Param("offset") int offset, @Param("size") int size);

    public List<Blog> getBlogByMonth(@Param("user") long user, @Param("from") Date from, @Param("to") Date to, @Param("listMode") boolean listMode,
                                     @Param("offset") int offset, @Param("size") int size);

    public Blog getBlog(@Param("user") long user, @Param("blog") long blog, @Param("auth") long auth);

    public List<BlogComment> getBlogComments(@Param("blog") long blog, @Param("auth") Long auth,
                                             @Param("offset") int offset, @Param("size") int size);

    public List<BlogReply> getCommentReplies(@Param("comment") long comment, @Param("offset") int offset, @Param("size") int size);

    public List<BlogData> getMonthData(@Param("user") long user, @Param("offset") int offset, @Param("size") int size);

    public List<BlogData> getTypeData(@Param("user") long user);

    public List<BlogComment> getRecentComments(@Param("user") long user, @Param("auth") long auth, @Param("size") int size);

}
