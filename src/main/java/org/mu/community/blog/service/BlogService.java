package org.mu.community.blog.service;

import org.mu.community.blog.repository.BlogModifyRepository;
import org.mu.community.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Muu on 2014/9/25.
 */
@Service("blogService")
public class BlogService {

    private BlogRepository blogRepository;

    private BlogModifyRepository blogModifyRepository;

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Autowired
    public void setBlogModifyRepository(BlogModifyRepository blogModifyRepository) {
        this.blogModifyRepository = blogModifyRepository;
    }
}
