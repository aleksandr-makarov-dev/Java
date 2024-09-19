package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = {"user", "chat"})
@Table(name = "users_chats", schema = "public")
public class UserChat extends AuditableEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public void setUser(User user){
        this.user = user;
        user.getUserChats().add(this);
    }

    public void setChat(Chat chat){
        this.chat = chat;
        chat.getUserChats().add(this);
    }
}
