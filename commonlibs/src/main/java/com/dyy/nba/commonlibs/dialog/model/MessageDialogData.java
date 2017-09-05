package com.dyy.nba.commonlibs.dialog.model;

/**
 * Created by 段钰莹 on 2017/9/4.
 * 消息框，确认框，形式title+message+多个按钮，其中message是必须的，其他可选
 */

public class MessageDialogData extends BaseModel {
    private String message;
    private int messageId;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
