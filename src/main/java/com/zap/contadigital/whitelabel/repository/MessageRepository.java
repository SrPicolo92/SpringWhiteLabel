package com.zap.contadigital.whitelabel.repository;

import com.zap.contadigital.whitelabel.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
