package io.pivotal.fe.demo.guestbook.domain;


import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sgupta
 * @since 9/22/15.
 */
@Entity
@Table(name = "Message")
public class Message implements Serializable {

  private static final long serialVersionUID = -4291753241578387042L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


  @Column(nullable = false)
  private String name;

  @Column(nullable = false, length = 1024)
  private String message;

  private Date created;
  
  private String fortune;

  protected Message(){
	    this.created = new Date();
  }

  public Message(String name, String message) {
    this.name = name;
    this.message = message;
    this.created = new Date();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  public String getFortune() {
	  return fortune;
  }

  public void setFortune(String fortune) {
	  this.fortune = fortune;
  }
  
  @JsonSerialize(using=JsonDateSerializer.class)
  public Date getCreated() {
    return created;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Message{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", message='").append(message).append('\'');
    sb.append(", created=").append(created);
    sb.append('}');
    return sb.toString();
  }
}
