package com.example.mentos.LearningPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mentos.R;

public class LearningPageFragment extends Fragment {

    private boolean isTaskView = true;
    private LinearLayout hobbyButtonsContainer;
    private String currentHobby = "피아노"; // 초기값 설정

    private TextView topTextView;
    private Switch switchTaskLecture;
    private Button selectedButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.h_fragment_learningpage, container, false);

        hobbyButtonsContainer = view.findViewById(R.id.hobby_buttons_container);
        topTextView = view.findViewById(R.id.top_text_view);
        switchTaskLecture = view.findViewById(R.id.switch_task_lecture);

        switchTaskLecture.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isTaskView = !isChecked;
            updateUI(isChecked);
            updateFragment();
        });

        String[] hobbies = getUserHobbies();
        addHobbyButtons(hobbies);

        if (hobbies.length > 0) {
            currentHobby = hobbies[0];
            showFragment(currentHobby);
            if (hobbyButtonsContainer.getChildCount() > 0) {
                Button initialButton = (Button) hobbyButtonsContainer.getChildAt(0);
                onHobbyButtonClick(initialButton);
            }
        }

        return view;
    }

    private String[] getUserHobbies() {
        return new String[]{"피아노", "뜨개질"};
    }

    private void addHobbyButtons(String[] hobbies) {
        for (String hobby : hobbies) {
            Button button = new Button(requireContext());
            button.setText(hobby);
            button.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent));
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray));
            button.setTextSize(16);

            button.setOnClickListener(view -> {
                currentHobby = hobby;
                showFragment(currentHobby);
                onHobbyButtonClick(button);
            });

            hobbyButtonsContainer.addView(button);
        }
    }

    private void showFragment(String hobby) {
        Fragment fragment = isTaskView ? FragmentTask.newInstance(hobby) : FragmentLecture.newInstance(hobby);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void updateFragment() {
        showFragment(currentHobby);
    }

    private void updateUI(boolean isChecked) {
        if (isChecked) {
            topTextView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPurple));
            topTextView.setText("강의");
        } else {
            topTextView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorLightPurple));
            topTextView.setText("과제");
        }
    }

    private void onHobbyButtonClick(Button button) {
        if (selectedButton != null) {
            selectedButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray));
            selectedButton.setTypeface(null, android.graphics.Typeface.NORMAL);
        }

        button.setTextColor(getColorForCurrentState());
        button.setTypeface(null, android.graphics.Typeface.BOLD);
        selectedButton = button;
    }

    private int getColorForCurrentState() {
        return isTaskView ? ContextCompat.getColor(requireContext(), R.color.colorPurple) : ContextCompat.getColor(requireContext(), R.color.colorPurple);
    }
}
