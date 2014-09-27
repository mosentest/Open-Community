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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Muu on 2014/9/25.
 */
@Service("blogService")
public class BlogService {

    private BlogRepository blogRepository;

    private BlogModifyRepository blogModifyRepository;

    private BlogRedisRepository blogRedisRepository;

    public List<BlogComment> getRecentComments(long user) {
        List<Long> idList = blogRedisRepository.getRecentComment(user);
        return blogRepository.getCommentsById(user, idList);
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
