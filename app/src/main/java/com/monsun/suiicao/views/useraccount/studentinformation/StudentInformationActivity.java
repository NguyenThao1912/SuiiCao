package com.monsun.suiicao.views.useraccount.studentinformation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityStudentInformationBinding;
import com.monsun.suiicao.views.base.BaseActivity;

import kotlin.jvm.internal.Intrinsics;

public class StudentInformationActivity extends BaseActivity implements IStuInformation{
    ActivityStudentInformationBinding binding;

    @Override
    public void onBackPressed() {
        finish();
    }

    StuInforViewModel viewModel ;
    private ImageView ivUserAvatar;
    private float EXPAND_AVATAR_SIZE;
    private float COLLAPSE_IMAGE_SIZE;
    private float horizontalToolbarAvatarMargin;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private Pair<Integer, Integer> cashCollapseState;
    private AppCompatTextView titleToolbarText;
    private AppCompatTextView titleToolbarTextSingle;
    private AppCompatTextView invisibleTextViewWorkAround;
    private FrameLayout background;
    private float avatarAnimateStartPointY;
    private float avatarCollapseAnimationChangeWeight;
    private boolean isCalculated;
    private float verticalToolbarAvatarMargin;

    public static Intent newIntent(Context context) {
        return new Intent(context, StudentInformationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
        //set binding data and viewmodel

        binding = DataBindingUtil.setContentView(this,R.layout.activity_student_information);
        viewModel = new ViewModelProvider(this).get(StuInforViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        //==================================

        //turn on back button
        Toolbar mToolbar= (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left_s_line);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // perform whatever you want on back arrow click
            }
        });
        //==================================

        EXPAND_AVATAR_SIZE = this.getResources().getDimension(R.dimen.default_expanded_image_size);
        COLLAPSE_IMAGE_SIZE = this.getResources().getDimension(R.dimen.default_collapsed_image_size);
        horizontalToolbarAvatarMargin = this.getResources().getDimension(R.dimen.activity_margin);
        appBarLayout = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.anim_toolbar);
        ivUserAvatar = findViewById(R.id.imgb_avatar_wrap);
        titleToolbarText = findViewById(R.id.tv_profile_name);
        titleToolbarTextSingle = findViewById(R.id.tv_profile_name_single);
        background = findViewById(R.id.fl_background);
        invisibleTextViewWorkAround = findViewById(R.id.tv_workaround);
        StorageReference storageReference  = FirebaseStorage.getInstance().getReference().child(AppVar.mStudent.getStudentId().toString());
        // Load the image using Glide
        Glide.with(this)
                .load(storageReference)
                .into((de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.imgb_avatar_wrap));

        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            StudentInformationActivity var10000;
            if (!isCalculated) {
                StudentInformationActivity.this.avatarAnimateStartPointY = Math.abs(((float)appBarLayout.getHeight() - (StudentInformationActivity.this.EXPAND_AVATAR_SIZE + StudentInformationActivity.this.horizontalToolbarAvatarMargin)) / (float)appBarLayout.getTotalScrollRange());
                StudentInformationActivity.this.avatarCollapseAnimationChangeWeight = (float)1 / ((float)1 - avatarAnimateStartPointY);
                StudentInformationActivity.this.verticalToolbarAvatarMargin = ((float)StudentInformationActivity.this.toolbar.getHeight() - StudentInformationActivity.this.COLLAPSE_IMAGE_SIZE) * (float)2;
                StudentInformationActivity.this.isCalculated = true;
            }

            //StudentInformationActivity.updateViews(Math.abs(verticalOffset / (float)appBarLayout.getTotalScrollRange()));
            var10000 = StudentInformationActivity.this;
            Intrinsics.checkExpressionValueIsNotNull(appBarLayout, "appBarLayout");
            var10000.updateViews(Math.abs(verticalOffset / (float)appBarLayout.getTotalScrollRange()));
        });
    }

    private void updateViews(float offset) {
        AppCompatTextView var10000;
        ImageView var18;
        if (offset >= 0.15F && offset <= 1.0F) {
            var10000 = this.titleToolbarText;
            if (var10000 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("titleToolbarText");
            }

            AppCompatTextView var3 = var10000;
            if (var3.getVisibility() != View.VISIBLE) {
                var3.setVisibility(View.VISIBLE);
            }

            var3.setAlpha(((float)1 - offset) * 0.35F);
        } else if (offset >= 0.0F && offset <= 0.15F) {
            var10000 = this.titleToolbarText;
            if (var10000 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("titleToolbarText");
            }

            var10000.setAlpha(1.0F);
            var18 = this.ivUserAvatar;
            if (var18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ivUserAvatar");
            }

            var18.setAlpha(1.0F);
        }

        int var10002;
        Pair<Integer, Integer> var19;
        Pair<Integer, Integer> var10003;
        Integer var22;
        int var23;
        if (offset < 0.8F) {
            label109: {
                var10002 = 0;
                var10003 = this.cashCollapseState;
                if (var10003 != null) {
                    var22 = var10003.second;
                    if (var22 != null) {
                        var23 = var22;
                        break label109;
                    }
                }

                var23 = 0;
            }

        } else {
            label104: {
                var10002 = 1;
                var10003 = this.cashCollapseState;
                if (var10003 != null) {
                    var22 = var10003.second;
                    if (var22 != null) {
                        var23 = var22;
                        break label104;
                    }
                }

                var23 = 0;
            }

        }
        var19 = new Pair<>(var10002, var23);

        Pair var2 = var19;
        if (this.cashCollapseState != null && !Intrinsics.areEqual(this.cashCollapseState, var2)) {
            FrameLayout var21;
            switch(((Number)var2.first).intValue()) {
                case 0:
                    var18 = this.ivUserAvatar;
                    if (var18 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("ivUserAvatar");
                    }

                    var18.setTranslationX(0.0F);
                    var21 = this.background;
                    if (var21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("background");
                    }

                    var21.setBackgroundColor(ContextCompat.getColor(this, R.color.color_transparent));
                    var10000 = this.titleToolbarTextSingle;
                    if (var10000 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("titleToolbarTextSingle");
                    }

                    var10000.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    var21 = this.background;
                    if (var21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("background");
                    }

                    FrameLayout var13 = var21;
                    var13.setAlpha(0.0F);
                    var13.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    var13.animate().setDuration(250L).alpha(1.0F);
                    var10000 = this.titleToolbarTextSingle;
                    if (var10000 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("titleToolbarTextSingle");
                    }

                    AppCompatTextView var8 = var10000;
                    var8.setVisibility(View.VISIBLE);
                    var8.setAlpha(0.0F);
                    var8.animate().setDuration(500L).alpha(1.0F);
            }

            this.cashCollapseState = new Pair(var2.first, 1);
        } else {
            this.cashCollapseState = new Pair(var2.first, 0);
        }

        var18 = this.ivUserAvatar;
        if (var18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivUserAvatar");
        }

        ImageView var14 = var18;
        if (offset > avatarAnimateStartPointY) {
            float avatarCollapseAnimateOffset = (offset - avatarAnimateStartPointY) * this.avatarCollapseAnimationChangeWeight;
            float avatarSize = this.EXPAND_AVATAR_SIZE - (this.EXPAND_AVATAR_SIZE - this.COLLAPSE_IMAGE_SIZE) * avatarCollapseAnimateOffset;
            ViewGroup.LayoutParams var17 = var14.getLayoutParams();
            var17.height = Math.round(avatarSize);
            var17.width = Math.round(avatarSize);
            var10000 = this.invisibleTextViewWorkAround;
            if (var10000 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("invisibleTextViewWorkAround");
            }

            var10000.setTextSize(0, offset);
            AppBarLayout var10001 = this.appBarLayout;
            if (var10001 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("appBarLayout");
            }

            var14.setTranslationX(((float)var10001.getWidth() - this.horizontalToolbarAvatarMargin - avatarSize) / (float)2 * avatarCollapseAnimateOffset);
            Toolbar var20 = this.toolbar;
            if (var20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            }

            var14.setTranslationY(((float)var20.getHeight() - this.verticalToolbarAvatarMargin - avatarSize) / (float)2 * avatarCollapseAnimateOffset);
        } else {
            ViewGroup.LayoutParams var16 = var14.getLayoutParams();
            if (var16.height != (int)this.EXPAND_AVATAR_SIZE) {
                var16.height = (int)this.EXPAND_AVATAR_SIZE;
                var16.width = (int)this.EXPAND_AVATAR_SIZE;
                var14.setLayoutParams(var16);
            }

            var14.setTranslationX(0.0F);
        }

    }


}