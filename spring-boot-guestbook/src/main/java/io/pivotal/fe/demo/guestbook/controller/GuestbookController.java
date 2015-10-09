package io.pivotal.fe.demo.guestbook.controller;

import io.pivotal.fe.demo.guestbook.domain.Message;
import io.pivotal.fe.demo.guestbook.service.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author sgupta
 * @since 9/22/15.
 */
@Controller
@RequestMapping(value = "/message/**", produces = "application/json")
public class GuestbookController {
  public static final Logger LOGGER = Logger.getLogger(GuestbookController.class.getName());

  @Autowired
  private MessageRepository messageRepository;


  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public List<Message> getAllMessages() {
    LOGGER.info("message repo is: " + messageRepository);
    List<Message> messages = new LinkedList<>();
    Iterable<Message> messageIterable = messageRepository.findAll();
    for (Message message : messageIterable) {
      messages.add(message);
    }
    return messages;
  }


  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public Message putMessage(@RequestBody Message message) {
    LOGGER.info("message repo is: " + messageRepository);

    return messageRepository.save(message);
  }



}
