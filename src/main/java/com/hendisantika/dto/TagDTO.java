package com.hendisantika.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.06
 * To change this template use File | Settings | File Templates.
 */
@Data
public class TagDTO {
    @Size(min = 1, max = 10, message = "Tag name must be between {2} and {1}")
    private String name;
}
