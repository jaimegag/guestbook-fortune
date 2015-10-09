package io.pivotal.fe.demo.guestbook.service;

import io.pivotal.fe.demo.guestbook.domain.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * @author sgupta
 * @since 9/22/15.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {


}
