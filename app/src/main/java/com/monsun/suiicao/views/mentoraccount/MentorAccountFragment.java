package com.monsun.suiicao.views.mentoraccount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.Utils.CommonUtils;
import com.monsun.suiicao.databinding.FragmentMentorAccountBinding;
import com.monsun.suiicao.firebase.FirebaseSer;
import com.monsun.suiicao.views.base.BaseFragment;
import com.monsun.suiicao.views.imagepicker.ImagePickerActivity;
import com.monsun.suiicao.views.login.LoginActivity;
import com.monsun.suiicao.views.mentoraccount.IUserHandler;
import com.monsun.suiicao.views.mentoraccount.UserViewModel;
import com.monsun.suiicao.views.useraccount.Mentorinformation.MentorInformation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MentorAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MentorAccountFragment extends BaseFragment implements IUserHandler {

    private static final String TAG = "MentorAccountFragment";
    private FragmentMentorAccountBinding binding;
    private UserViewModel viewModel;

    private static final int REQUEST_IMAGE = 1;

    public MentorAccountFragment() {
        // Required empty public constructor
    }

    public static MentorAccountFragment newInstance() {
        MentorAccountFragment fragment = new MentorAccountFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mentor_account;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_mentor_account,container, false);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        viewModel.setNavigator(this);
        View v = binding.getRoot();
        StorageReference storageReference;
        storageReference  = FirebaseStorage.getInstance().getReference().child(AppVar.mMentor.getMentorId().toString());
        // Load the image using Glide
        Glide.with(getActivity())
                .load(storageReference)
                .into(binding.mentorPic);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void OpenLoginActivity() {
        Log.d(TAG, "OpenLoginActivity: ");
        Intent t = LoginActivity.newIntent(getActivity());
        //======================
        SharedPreferences preferences = getActivity().getSharedPreferences(CommonUtils.MY_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove(CommonUtils.MY_USER);
        edit.remove(CommonUtils.TYPE_USER);
        edit.commit();
        //======================
        FirebaseSer.mAuth.signOut();
        AppVar.mStudent = null;
        startActivity(t);
        getActivity().finish();
    }

    @Override
    public void OpenMentorInfor() {
        Intent t = MentorInformation.newIntent(getActivity());
        startActivity(t);
    }

    @Override
    public void OpenUploadImage() {
        //goij hamf cuar o owr day
        showImagePickerOptions();
    }

    public void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(getActivity(), new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onCameraSelected() {
                launchCamera();
            }

            @Override
            public void onGallerySelected() {
                launchGallery();
            }
        });
    }

    public void launchCamera() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.REQUEST_CODE_TYPE, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // Gán tỉ lệ khóa là 1x1
        intent.putExtra(ImagePickerActivity.EXTRA_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.EXTRA_ASPECT_RATIO_X, 1);
        intent.putExtra(ImagePickerActivity.EXTRA_ASPECT_RATIO_Y, 1);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    public void launchGallery() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.REQUEST_CODE_TYPE, ImagePickerActivity.REQUEST_IMAGE_GALLERY);

        // Gán kích thước tối đa cho ảnh
        intent.putExtra(ImagePickerActivity.EXTRA_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.EXTRA_BITMAP_MAX_WIDTH, 480);
        intent.putExtra(ImagePickerActivity.EXTRA_BITMAP_MAX_HEIGHT, 640);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    public void loadImageProfile(String url) {
        Glide.with(this).load(url)
                .into(binding.mentorPic);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == -1) {
                Uri uri = data.getParcelableExtra("path");
                FirebaseSer.UploadImage(uri);
                Log.e(TAG,uri.toString());
                loadImageProfile(uri.toString());
            }
        }
    }
}