package com.monsun.suiicao.views.chatting.message;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.views.base.BaseViewModel;

public class MessageViewModel extends BaseViewModel<IMessage> {
    public MutableLiveData<String> Message = new MutableLiveData<>();
    private static final String TAG = "MessageViewModel";
    public MessageViewModel()
    {

    }
    public void OnSendIconCLick()
    {
        Log.d(TAG, "OnSendIconCLick: send ne " + Message.getValue());
        String message = Message.getValue();
        Message.postValue("");

        getNavigator().SendMessage(message);
    }
    public void GoBack()
    {
        getNavigator().goback();
    }
}
