package com.hendisantika.domain;

import com.hendisantika.dto.TagDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.07
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
public class Tag {
    public static final String FIND_ALL_TAGS = "TAGS_ALL";

    @Id
    private String id;

    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag(TagDTO tagDTO) {
        this.name = tagDTO.getName();
    }
}
