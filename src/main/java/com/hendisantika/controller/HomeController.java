package com.hendisantika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.15
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping({"/", "/home"})
public class HomeController {

    private static final String HOME_PAGE_NAME = "index";

    @GetMapping
    public String showHomePage() {
        return HOME_PAGE_NAME;
    }
}
