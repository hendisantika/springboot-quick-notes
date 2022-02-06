package com.hendisantika.controller;

import com.hendisantika.domain.Tag;
import com.hendisantika.dto.TagDTO;
import com.hendisantika.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    @GetMapping("/add")
    public String prepareAddTagForm(Model model) {
        model.addAttribute("tag", new TagDTO());

        return TAG_PAGE_NAME;
    }

    @PostMapping("/add")
    public String processAddTagFrom(@Valid @ModelAttribute(name = "tag") TagDTO tagDTO,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return TAG_PAGE_NAME;
        }

        if (tagService.isTagInDatabase(tagDTO.getName())) {
            redirectAttributes.addFlashAttribute("tagError",
                    String.format("Tag %s already in database (letters size is ignored)", tagDTO.getName()));
            return "redirect:/tag/add";
        }

        tagService.insertTagIntoDatabase(new Tag(tagDTO));
        redirectAttributes.addFlashAttribute("tagCreated", true);

        return "redirect:/home";
    }
}
