package com.monsun.suiicao.views.base;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    private BaseActivity activity;
    private View rootview;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.activity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        activity = null;
        super.onDetach();
    }
    public BaseActivity getBaseActivity() {
        return activity;
    }
    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
