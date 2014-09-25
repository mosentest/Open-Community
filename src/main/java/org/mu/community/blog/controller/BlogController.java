package org.mu.community.blog.controller;

import org.mu.community.blog.service.BlogService;
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

    private BlogService blogService;

    @RequestMapping(value = { "blog/home.html", "blog/", "blog" }, method = RequestMethod.GET)
    public ModelAndView home(ModelMap model) {
        return new ModelAndView("blog/index", model);
    }

    @RequestMapping(value = "u/{path}/blogs.html", method = RequestMethod.GET)
    public ModelAndView user(@PathVariable("path") String path,
                             @RequestParam(value = "category", required = false) String category,
                             @RequestParam(value = "month", required = false) String month,
                             @RequestParam(value = "pn", required = false) int pn,
                             ModelMap model) {
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
}
