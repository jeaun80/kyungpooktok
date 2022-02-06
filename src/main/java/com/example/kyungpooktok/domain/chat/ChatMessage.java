package com.example.kyungpooktok.domain.chat;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChatMessage {


    private String senderSessionId;
    private String message;
    private MessageType messageType;



    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
    @Override
    public String toString() {
        return "ChatMessage{" + "senderSessionId='" + senderSessionId + '\'' + ", message='" + message + '\'' + ", messageType=" + messageType + '}';
    }
}
