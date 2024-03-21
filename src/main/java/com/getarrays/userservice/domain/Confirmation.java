package com.getarrays.userservice.domain;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;





@Entity
@Data
@NoArgsConstructor
@Table(name = "confirmations",schema = "")
public class Confirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdDate;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private User user;


    public Confirmation(User user) {
        this.user=user;
        this.createdDate=LocalDateTime.now();
        this.token= UUID.randomUUID().toString();
    }
}
