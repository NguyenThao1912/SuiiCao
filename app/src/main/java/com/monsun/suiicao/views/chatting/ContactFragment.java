package com.monsun.suiicao.views.chatting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.monsun.suiicao.R;
import com.monsun.suiicao.views.chatting.frag.listcontact.ListContactFragment;
import com.monsun.suiicao.views.chatting.frag.ListChat.ChatFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private View v;
    public ContactFragment() {
        // Required empty public constructor
    }


    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_contact, container, false);
        setWidget();
        ViewPagerContactAdapter adapter = new ViewPagerContactAdapter(this);
        viewPager2.setAdapter(adapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position)
                {
                    case 0:
                        tab.setText("Recent Contact");
                        break;
                    case 1:
                        tab.setText("Users");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
        return v;
    }
    private void setWidget()
    {
        viewPager2 = v.findViewById(R.id.viewpager_chat);
        tabLayout = v.findViewById(R.id.tab_layout_chat);
    }

    public class ViewPagerContactAdapter  extends FragmentStateAdapter {
        private static final int NUM_PAGES = 2;

        public ViewPagerContactAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }
        
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position)
            {
                case 0:
                    return ChatFragment.newInstance();
                case 1:
                    return ListContactFragment.newInstance();
                default:
                    return ChatFragment.newInstance();
            }

        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}