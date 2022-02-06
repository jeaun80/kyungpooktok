package com.example.kyungpooktok.controller.api;


import com.example.kyungpooktok.domain.chat.ChatMessage;
import com.example.kyungpooktok.domain.chat.ChatRequest;
import com.example.kyungpooktok.domain.chat.ChatResponse;
import com.example.kyungpooktok.domain.chat.MessageType;
import com.example.kyungpooktok.service.ChatService;
import com.example.kyungpooktok.service.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@ServerEndpoint("/random")
public class WebSocketChat extends Socket {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);

    @Autowired
    private ChatService chatService;

    // tag :: async
    @GetMapping("/join")
    @ResponseBody
    public DeferredResult<ChatResponse> joinRequest() {
        String sessionId = ServletUtil.getSession().getId();
        logger.info(">> Join request. session id : {}", sessionId);

        final ChatRequest user = new ChatRequest(sessionId);
        final DeferredResult<ChatResponse> deferredResult = new DeferredResult<>(null);
        chatService.joinChatRoom(user, deferredResult);

        deferredResult.onCompletion(() -> chatService.cancelChatRoom(user));
        deferredResult.onError((throwable) -> chatService.cancelChatRoom(user));
        deferredResult.onTimeout(() -> chatService.timeout(user));

        return deferredResult;
    }

    @GetMapping("/cancel")
    @ResponseBody
    public ResponseEntity<Void> cancelRequest() {
        String sessionId = ServletUtil.getSession().getId();
        logger.info(">> Cancel request. session id : {}", sessionId);

        final ChatRequest user = new ChatRequest(sessionId);
        chatService.cancelChatRoom(user);

        return ResponseEntity.ok().build();
    }

    // -- tag :: async

    // tag :: websocket stomp
    @MessageMapping("/chat.message/{chatRoomId}")
    public void sendMessage(@DestinationVariable("chatRoomId") String chatRoomId, @Payload ChatMessage chatMessage) {
        logger.info("Request message. roomd id : {} | chat message : {} | principal : {}", chatRoomId, chatMessage);
        if (!StringUtils.hasText(chatRoomId) || chatMessage == null) {
            return;
        }

        if (chatMessage.getMessageType() == MessageType.CHAT) {
            chatService.sendMessage(chatRoomId, chatMessage);
        }
    }
    // -- tag :: websocket stomp

}
