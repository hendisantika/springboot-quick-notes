package com.hendisantika.dto;

import com.hendisantika.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.05
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindNoteDTO {
    private String title;

    private String description;

    private String tag;

    public boolean findAllTags() {
        return tag.equals(Tag.FIND_ALL_TAGS);
    }

    public boolean noCriteria() {
        return findAllTags() && title.equals("") && description.equals("");
    }

    public boolean searchByTitleOnly() {
        return !title.equals("") && description.equals("");
    }

    public boolean searchByDescriptionOnly() {
        return !description.equals("") && title.equals("");
    }

    public boolean searchByTagOnly() {
        return description.equals("") && title.equals("");
    }
}
