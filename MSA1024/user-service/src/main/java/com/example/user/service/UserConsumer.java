package com.example.user.service;

import com.example.user.config.RabbitConfig;
import com.example.user.model.User;
import com.example.user.model.UserMessage;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserConsumer {

    private final UserRepository repo;

    @RabbitListener(queues = RabbitConfig.USER_REQUEST_QUEUE)
    @SendTo  // convertSendAndReceive로 호출 시 응답을 자동으로 보냄
    public Object handleRequest(UserMessage message) {
        System.out.println("📩 Received: " + message);

        switch (message.getAction()) {
            case "CREATE" -> {
                User saved = repo.save(new User(null, message.getName(), message.getEmail()));
                return new UserMessage("CREATE", saved.getId(), saved.getName(), saved.getEmail());
            }
            case "READ" -> {
                if (message.getId() != null) {
                    var user = repo.findById(message.getId()).orElse(null);
                    if (user != null) {
                        return new UserMessage("READ", user.getId(), user.getName(), user.getEmail());
                    } else {
                        return null;
                    }
                } else {
                    List<User> users = repo.findAll();
                    return users.stream()
                            .map(u -> new UserMessage("READ", u.getId(), u.getName(), u.getEmail()))
                            .collect(Collectors.toList());
                }
            }
            case "UPDATE" -> {
                var u = repo.findById(message.getId()).orElseThrow();
                u.setName(message.getName());
                u.setEmail(message.getEmail());
                var updated = repo.save(u);
                return new UserMessage("UPDATE", updated.getId(), updated.getName(), updated.getEmail());
            }
            case "DELETE" -> {
                repo.deleteById(message.getId());
                return new UserMessage("DELETE", message.getId(), null, null);
            }
            default -> {
                return null;
            }
        }
    }
}
