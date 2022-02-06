package com.hendisantika.service;

import com.hendisantika.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.13
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public boolean isTagInDatabase(String tagName) {
        return !tagRepository.findByNameIgnoreCase(tagName).isEmpty();
    }
}
