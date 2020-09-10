package com.project.socialmedia.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.project.socialmedia.Account_Profile;
import com.project.socialmedia.R;
import com.project.socialmedia.Update;
import com.project.socialmedia.login;

public class NotificationsFragment extends Fragment implements View.OnClickListener {
    private TextView profile,update_profile,About,Logout;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        profile = root.findViewById(R.id.profile);
        update_profile = root.findViewById(R.id.update_profile);
        About = root.findViewById(R.id.about);
        Logout = root.findViewById(R.id.logout);

        profile.setOnClickListener(this);
        update_profile.setOnClickListener(this);
        About.setOnClickListener(this);
        Logout.setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.update_profile:

                startActivity(new Intent(view.getContext(), Update.class));
                break;
            case R.id.profile:

                startActivity(new Intent(view.getContext(), Account_Profile.class));
                break;
            case R.id.about:

                Toast.makeText(view.getContext(),"About",Toast.LENGTH_SHORT).show();

                break;
            case R.id.logout:

                startActivity(new Intent(view.getContext(), login.class));
                getActivity().finish();


                break;

        }
    }
}