package com.monsun.suiicao.views.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.models.User;
import com.monsun.suiicao.views.base.BaseViewModel;


public class LoginViewModel extends BaseViewModel<ILoginHandler> {
    private static final String TAG = "t";
    //binding data in xml
    public String Username = "";
    public String Password = "";

    private MutableLiveData<User> userLiveData ;
    public LiveData<User> getUser() {
        if (userLiveData == null)
            userLiveData = new MutableLiveData<>();
        return userLiveData;
    }

    public LoginViewModel() {
        super();
    }

    public void onClickLogin()
    {
        if (getNavigator().login())
        {
            getNavigator().setIsLoading(true);
            FirebaseFirestore db =  FirebaseFirestore.getInstance();
            db.collection("users")
                    .whereEqualTo("username",Username)
                    .whereEqualTo("password",Password).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (!task.getResult().isEmpty())
                    {
                        getNavigator().setIsLoading(false);
                        DocumentSnapshot doc =  task.getResult().getDocuments().get(0);
                        AppVar.Currentuser = doc.toObject(User.class);
                        getNavigator().startMainActivity();
                    }
                    else
                    {
                        getNavigator().setIsLoading(false);
                        getNavigator().showToast("Sai tài khoản hoặc mật khẩu !");
                    }
                }
            });
        }

    }
    public void onClickForgetPassword()
    {
        getNavigator().showToast("Please contact to your mentor for your problem ! ");
    }

}

