package com.hendisantika.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.04
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
    @Size(min = 4, max = 50, message = "Title must be between {2} and {1}")
    private String title;

    @Size(min = 4, max = 200, message = "Description must be between {2} and {1}")
    private String description;

    @NotEmpty
    private List<String> tagsList;
}
