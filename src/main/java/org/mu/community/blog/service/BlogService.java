package org.mu.community.blog.service;

import org.mu.community.blog.entity.*;
import org.mu.community.blog.repository.BlogModifyRepository;
import org.mu.community.blog.repository.BlogRedisRepository;
import org.mu.community.blog.repository.BlogRepository;
import org.mu.community.common.dbutil.Page;
import org.mu.community.common.exception.InfoException;
import org.mu.community.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Tuple;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Muu on 2014/9/25.
 */
@Service("blogService")
public class BlogService {

    private BlogRepository blogRepository;

    private BlogModifyRepository blogModifyRepository;

    private BlogRedisRepository blogRedisRepository;

    public Page<Blog> getRecentBlogs(int page, int size) {
        Page<Blog> blogList = new Page<>();
        blogList.setTotalElement(blogRepository.countAll(), size);
        blogList.setCurrentPage(page);
        blogList.setContent(blogRepository.getRecentBlogs(page * size, size));
        return blogList;
    }

    public Page<Blog> getFeaturedBlogs(int page, int size) {
        Page<Blog> blogList = new Page<>();
        blogList.setTotalElement(blogRedisRepository.countFeatured(), size);
        blogList.setCurrentPage(page);
        List<Long> idList = blogRedisRepository.getFeatured(page * size, size);
        blogList.setContent(blogRepository.getBlogsById(false, idList));
        return blogList;
    }

    public List<Blog> getDailyMostViewed(boolean listMode, int page, int size) {
        return blogRepository.getDailyMostViewed(listMode, page * size, size);
    }

    public List<Blog> getWeeklyMostViewed(boolean listMode, int page, int size) {
        return blogRepository.getWeeklyMostViewed(listMode, page * size, size);
    }

    public List<Blog> getMonthlyMostViewed(boolean listMode, int page, int size) {
        return blogRepository.getMonthlyMostViewed(listMode, page * size, size);
    }

    public List<Blog> getTopBloggers(int page, int size) {
        return null;
    }

    public Blog getBlog(long user, long auth, long id) throws InfoException {
        Blog blog = blogRepository.getBlog(user, id, auth);
        if (blog == null) {
            return null;
        }
        blogModifyRepository.view(user);
        return blog;
    }

    public List<BlogComment> getRecentComments(long user, long auth, int size) {
        return blogRepository.getRecentComments(user, auth, size);
    }

    @Transactional
    public Page<Blog> getRecentBlogs(long user, boolean listMode, int page, int size) throws InfoException {
        Page<Blog> blogList = new Page<>();
        blogList.setTotalElement(blogRepository.getBlogStat(user).getnBlog(), size);
        blogList.setContent(blogRepository.getBlogsByUser(user, listMode, page * size, size));
        blogList.setCurrentPage(page);
        return blogList;
    }

    @Transactional
    public Page<Blog> getMonthBlogs(long user, String month, boolean listMode, int page, int size) throws InfoException {
        Page<Blog> blogList = new Page<>();
        Date from = DateUtil.parseMonth(month);
        if (from == null) {
            return blogList;
        }
        Date to = DateUtil.add(from, Calendar.MONTH, 1);
        blogList.setTotalElement(blogRepository.countBlogByMonth(user, from, to), size);
        blogList.setContent(blogRepository.getBlogByMonth(user, from, to, listMode, page * size, size));
        blogList.setCurrentPage(page);
        return blogList;
    }

    @Transactional
    public Page<Blog> getCategoyBlogs(long user, long category, boolean listMode, int page, int size) throws InfoException {
        Page<Blog> blogList = new Page<>();
        blogList.setTotalElement(blogRepository.getBlogCategory(category).getnBlog(), size);
        blogList.setContent(blogRepository.getBlogByCategory(user, category, listMode, page * size, size));
        blogList.setCurrentPage(page);
        return blogList;
    }

    public BlogStat getBlogStat(long user) {
       return blogRepository.getBlogStat(user);
    }

    public List<BlogData> getMonthData(long user, int page, int size) {
        return blogRepository.getMonthData(user, page * size, size);
    }

    public List<BlogCategory> getCategories(long user) {
        return blogRepository.getCategories(user);
    }

    public List<BlogData> getTypeData(long user) {
        return blogRepository.getTypeData(user);
    }

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Autowired
    public void setBlogModifyRepository(BlogModifyRepository blogModifyRepository) {
        this.blogModifyRepository = blogModifyRepository;
    }

    @Autowired
    public void setBlogRedisRepository(BlogRedisRepository blogRedisRepository) {
        this.blogRedisRepository = blogRedisRepository;
    }
}
