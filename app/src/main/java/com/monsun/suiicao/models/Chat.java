package com.monsun.suiicao.models;

public class Chat {
    private String sender;
    private String receiver;
    private String message;
    private String createAt;
    private String chatID;
    private boolean isseen;

    public Chat(String sender, String receiver, String message, String createAt, String chatID, boolean isseen) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.createAt = createAt;
        this.chatID = chatID;
        this.isseen = isseen;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public boolean isIsseen() {
        return isseen;
    }

    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }

    public Chat() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
