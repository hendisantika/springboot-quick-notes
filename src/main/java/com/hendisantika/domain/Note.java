package com.hendisantika.domain;

import com.hendisantika.dto.NoteDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.03
 * To change this template use File | Settings | File Templates.
 */
@Document(collection = "notes")
@Data
@NoArgsConstructor
public class Note {

    @Id
    private String id;

    private String title;
    private String description;

    @CreatedDate
    private LocalDateTime createdDate;

    private List<String> tags;

    public Note(NoteDTO noteDTO) {
        this.title = noteDTO.getTitle();
        this.description = noteDTO.getDescription();
    }

}
