package com.example.mentos.LearningPage;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentos.Model.Task;
import com.example.mentos.R;
import java.util.ArrayList;
import java.util.List;

public class FragmentTask extends Fragment {
    private static final String ARG_HOBBY = "hobby";
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    public static FragmentTask newInstance(String hobby) {
        FragmentTask fragment = new FragmentTask();
        Bundle args = new Bundle();
        args.putString(ARG_HOBBY, hobby);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.l_fragment_task, container, false);

        recyclerView = view.findViewById(R.id.RecyclerViewTask);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(getContext(), taskList);
        recyclerView.setAdapter(taskAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),1));

        // Dummy data for testing
//        loadDummyTasks();


        String hobby = getArguments().getString(ARG_HOBBY);
        taskList.add(new Task(hobby + "<비행기> 완곡하기", true));
        taskList.add(new Task(hobby + "<비행기> 5회 연주", true));
        taskList.add(new Task(hobby + "<비행기> 10회 연주", true));
        taskList.add(new Task(hobby + "<캐논> 완곡하기", false));
        taskList.add(new Task(hobby + "<캐논> 5회 연주", false));
        taskList.add(new Task(hobby + "<캐논> 10회 연주", false));


        return view;

    }

    private void loadDummyTasks() {
        if (getArguments() != null) {
            String hobby = getArguments().getString(ARG_HOBBY);
            for (int i = 1; i <= 10; i++) {
                taskList.add(new Task(hobby + " 과제 내용 " + i, false));
            }
            taskAdapter.notifyDataSetChanged();
        }
    }
}
