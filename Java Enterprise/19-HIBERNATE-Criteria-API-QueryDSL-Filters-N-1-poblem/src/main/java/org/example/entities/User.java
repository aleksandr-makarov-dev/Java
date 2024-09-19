package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "findUserByNameAndCompany",query = """
                select u from User u
                where u.personalInfo.firstname = :firstname
                and u.company.name = :company
                """)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = { "company", "profile" })
@Table(name = "users", schema = "public")
public class User implements EntityBase<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String username;

    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    @Embedded
    @Valid
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

//    @Builder.Default
//    @ManyToMany
//    @JoinTable(
//            name = "users_chats",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "chat_id")
//    )
//    private List<Chat> chats = new ArrayList<>();
//
//    public void addChat(Chat chat){
//        chats.add(chat);
//        chat.getUsers().add(this);
//    }

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();
}
