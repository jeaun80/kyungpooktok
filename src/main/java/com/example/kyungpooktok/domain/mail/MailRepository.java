package com.example.kyungpooktok.domain.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kyungpooktok.domain.mail.Mail;
public interface MailRepository extends JpaRepository<Mail,String> {
}
