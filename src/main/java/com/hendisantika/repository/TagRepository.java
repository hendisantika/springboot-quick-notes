package com.hendisantika.repository;

import com.hendisantika.domain.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.09
 * To change this template use File | Settings | File Templates.
 */
public interface TagRepository extends MongoRepository<Tag, String> {

    List<Tag> findByNameIgnoreCase(String name);
}
