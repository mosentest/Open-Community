package org.mu.community.blog.controller;

import org.mu.community.blog.entity.Blog;
import org.mu.community.blog.service.BlogService;
import org.mu.community.common.dbutil.Page;
import org.mu.community.common.entity.User;
import org.mu.community.common.exception.InfoException;
import org.mu.community.common.exception.NoUserException;
import org.mu.community.common.service.UserService;
import org.mu.community.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Muu on 2014/9/25.
 */
@Controller
@RequestMapping("/")
public class BlogController {

    private static final int LIST_SIZE = 10;

    private static final int MONTH_DATA_SIZE = 12;

    private static final int LIST_MODE_SIZE = 30;

    private BlogService blogService;

    private UserService userService;

    @RequestMapping(value = { "blog/home.html", "blog/", "blog" }, method = RequestMethod.GET)
    public ModelAndView home(ModelMap model) {
        return new ModelAndView("blog/index", model);
    }

    @RequestMapping(value = "u/{path}/blogs.html", method = RequestMethod.GET)
    public ModelAndView user(@PathVariable("path") String path,
                             @RequestParam(value = "category", required = false, defaultValue = "0") Long category,
                             @RequestParam(value = "month", required = false) String month,
                             @RequestParam(value = "pn", required = false, defaultValue = "0") Integer pn,
                             @RequestParam(value = "listMode", required = false) boolean listMode,
                             ModelMap model) throws InfoException {
        Page<Blog> blogList = null;
        int size = listMode ? LIST_MODE_SIZE : LIST_SIZE;
        User user = userService.getUserByPath(path);
        if (user == null) {
            throw new NoUserException(path);
        }
        if (category != 0l) {
            blogList = blogService.getCategoyBlogs(user.getId(), category, listMode, pn, size);
            model.put("category", category);
        } else if (!StringUtil.isEmpty(month)) {
            blogList = blogService.getMonthBlogs(user.getId(), month, listMode, 0, size);
            model.put("month", month);
        } else {
            blogList = blogService.getRecentBlogs(user.getId(), listMode, pn, size);
        }
        model.put("blogStat", blogService.getBlogStat(user.getId()));
        model.put("monthDataList", blogService.getMonthData(user.getId(), 0, MONTH_DATA_SIZE));
        model.put("typeDataList", blogService.getTypeData(user.getId()));
        model.put("categoryDataList", blogService.getCategories(user.getId()));
        model.put("blogList", blogList);
        model.put("recentCommentList", blogService.getRecentComments(user.getId()));
        if (pn != 0) {
            model.put("pn", pn);
        }
        if (listMode) {
            model.put("listMode", true);
        }
        model.put("user", user);
        return new ModelAndView("blog/user", model);
    }

    @RequestMapping(value = "u/{path}/blog/{id}.html", method = RequestMethod.GET)
    public ModelAndView blog(@PathVariable("path") String path, @PathVariable("id") long id,
                             ModelMap model) {
        return new ModelAndView("blog/blog", model);
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
