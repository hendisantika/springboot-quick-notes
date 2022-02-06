package com.hendisantika.controller;

import com.hendisantika.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.23
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/tag")
public class TagController {

    private static final String TAG_PAGE_NAME = "add_tag";

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }
}
