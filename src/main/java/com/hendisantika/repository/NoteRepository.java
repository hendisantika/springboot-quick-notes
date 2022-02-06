package com.hendisantika.repository;

import com.hendisantika.domain.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.08
 * To change this template use File | Settings | File Templates.
 */
public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findAllByDescriptionContainingIgnoreCase(String description);

    List<Note> findAllByTitleContainingIgnoreCase(String title);

    List<Note> findAllByDescriptionContainingIgnoreCaseOrTitleContainingIgnoreCase(String description, String title);

}
