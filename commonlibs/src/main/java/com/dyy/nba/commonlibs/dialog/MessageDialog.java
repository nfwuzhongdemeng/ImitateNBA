package com.dyy.nba.commonlibs.dialog;

import android.view.View;
import android.widget.TextView;

import com.dyy.nba.commonlibs.dialog.model.MessageDialogData;

/**
 * Created by 段钰莹 on 2017/9/4.
 */

public class MessageDialog  extends BaseDialog<MessageDialogData>{
    @Override
    protected void initData(MessageDialogData data,View parentView) {
        String message = data.getMessage();
        if(message == null || message.length() == 0)
            throw new NullPointerException("消息框的message不能为空！");
        TextView tvMessage = (TextView) parentView.findViewById(data.getMessageId());
        tvMessage.setText(message);
    }
}
