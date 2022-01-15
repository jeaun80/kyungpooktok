package com.example.kyungpooktok.domain.mail;

import com.example.kyungpooktok.domain.BaseTime;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

@Entity
@EntityListeners(value= AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Mail extends BaseTime {
    @Id
    private String email;

    private String num;
    private String title;
    private String message;
}
