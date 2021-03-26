package com.monsun.suiicao.firebase;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.Utils.CommonUtils;
import com.monsun.suiicao.models.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseSer {
    public static FirebaseAuth mAuth =  FirebaseAuth.getInstance();;
    public static FirebaseUser FireAuth_User;
    private static final String TAG = "FirebaseSer";
    private static String DEFAULT_FIREBASE_PASSWORD = "#$!@123987";
    private static int LOGIN_FIREBASE_RESULT = 0;

    public static void TRY_LOGGING_IN(String email,Activity activity) {
        int k = SignUpUser_with_email_password(email, DEFAULT_FIREBASE_PASSWORD,activity);
        if (k == CommonUtils.FIREBASE_CREATE_SUCCESS)
        {
            // TODO do nothing
            Log.d(TAG, "TRY_LOGGING_IN: Create Success");
        }
        else if (k == CommonUtils.FIREBASE_EMAIL_EXIST)
        {
            Log.d(TAG, "TRY_LOGGING_IN: Email exist");
            SignInUser_with_email_password(email,DEFAULT_FIREBASE_PASSWORD,activity);
        }
        else
        {
            SignInUser_with_email_password(email,DEFAULT_FIREBASE_PASSWORD,activity);
        }
    }
    public static int SignUpUser_with_email_password(String email,String password , Activity activity)
    {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try
                        {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FireAuth_User = mAuth.getCurrentUser();
                                LOGIN_FIREBASE_RESULT = CommonUtils.FIREBASE_CREATE_SUCCESS;
                                String uid = FireAuth_User.getUid();
                                String type = "";
                                String username = "";
                                String name = "";
                                if (AppVar.mStudent != null) {
                                    type = "class_" + AppVar.mStudent.getClassId();
                                    username = AppVar.mStudent.getUsername();
                                    name = AppVar.mStudent.getFullName();
                                } else {
                                    type = "mentor_" + AppVar.mMentor.getClassId();
                                    username = AppVar.mMentor.getUsername();
                                    name = AppVar.mMentor.getMentorName();
                                }

                                // TODO Create User Information
                                Log.d(TAG, "Sign In FireBase: Success ");
                                Create_User_Information_FireBase(AppVar.mStudent.getUsername(), type, uid,name);
                            }
                            else
                                throw task.getException();
                        }
                        catch (FirebaseAuthWeakPasswordException weakPassword)
                        {
                            Log.d(TAG, "onComplete: weak_password");

                            LOGIN_FIREBASE_RESULT = CommonUtils.FIREBASE_PASSWORD_TOO_WEAK;
                        }
                        // if user enters wrong password.
                        catch (FirebaseAuthInvalidCredentialsException malformedEmail)
                        {
                            Log.d(TAG, "onComplete: malformed_email");

                            // TODO: Take your action
                            LOGIN_FIREBASE_RESULT = CommonUtils.FIREBASE_EMAIL_WRONG_FORMAT;
                        }
                        catch (FirebaseAuthUserCollisionException existEmail)
                        {
                            Log.d(TAG, "onComplete: exist_email");

                            LOGIN_FIREBASE_RESULT = CommonUtils.FIREBASE_EMAIL_EXIST;
                        }
                        catch (Exception e)
                        {
                            Log.d(TAG, "onComplete: dump shit" + e.getMessage());
                        }
                    }
                });
        return LOGIN_FIREBASE_RESULT;
    }
    public static void SignInUser_with_email_password(String email,String password,Activity activity)
    {
        try
        {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                FireAuth_User = mAuth.getCurrentUser();
                            }
                        }
                    });
        }
        catch (Exception e)
        {
            Log.d(TAG, "SignUpUser_with_email_password: " + e.getMessage());
        }
    }

    private static void Create_User_Information_FireBase(String username,String type,String userid,String name)
    {
        // Lấy ref của firebase realtime
        /* TODO Ghi Thông tin user lên realtime
            database : String type: classid -> 1 lớp sv , mentor+classid -> mentor của lớp
            String username hỗ trợ việc ghi nhớ tài khoản
            String userid : mã ng dùng trên firebase
         */
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(type).child(userid);
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("uid",userid);
        hashMap.put("contact_id",username);
        hashMap.put("contact_img","default");
        hashMap.put("contact_name",name);
        hashMap.put("contact_status","false");      // Trạng thái online - offline
        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Log.i(TAG, "onComplete: Create new user firebase");
                }
            }
        });
    }


}
