package com.example.mentos.LearningPage;

import android.net.Uri;
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

import com.example.mentos.Model.Lecture;
import com.example.mentos.R;
import java.util.ArrayList;
import java.util.List;

public class FragmentLecture extends Fragment {
    private static final String ARG_HOBBY = "hobby";
    private RecyclerView recyclerView;
    private LectureAdapter lectureAdapter;
    private List<Lecture> lectureList;

    public static FragmentLecture newInstance(String hobby) {
        FragmentLecture fragment = new FragmentLecture();
        Bundle args = new Bundle();
        args.putString(ARG_HOBBY, hobby);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.l_fragment_lecture, container, false);

        recyclerView = view.findViewById(R.id.RecyclerViewLecture);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lectureList = new ArrayList<>();
        lectureAdapter = new LectureAdapter(getContext(), lectureList);
        recyclerView.setAdapter(lectureAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),1));


////        // Dummy data for testing
//        loadDummyLectures();

        String hobby = getArguments().getString(ARG_HOBBY);
        lectureList.add(new Lecture( hobby + " 강의 " + 1 +"      *AI 추천", hobby, "난이도 " + 1, "https://www.youtube.com/watch?v=Mou4X0QQKoI"));
        lectureList.add(new Lecture( hobby + " 강의 " + 2, hobby, "난이도 " + 2, "https://www.youtube.com/watch?v=7OsIsSwbSaI"));
        lectureList.add(new Lecture( hobby + " 강의 " + 3, hobby, "난이도 " + 3, "https://www.youtube.com/watch?v=Xqs1PbM3ZAg"));
        lectureList.add(new Lecture( hobby + " 강의 " + 4, hobby, "난이도 " + 4, "https://www.youtube.com/watch?v=1jqwbvFx0II"));
        lectureList.add(new Lecture( hobby + " 강의 " + 5, hobby, "난이도 " + 5, "https://www.youtube.com/watch?v=g6HHNtutNqk"));

        return view;
    }

//    private void loadDummyLectures() {
//        if (getArguments() != null) {
//            String hobby = getArguments().getString(ARG_HOBBY);
//            for (int i = 1; i <= 10; i++) {
//                lectureList.add(new Lecture(
//                        "https://via.placeholder.com/200", // Placeholder image URL
//                        hobby + " 강의 " + i,
//                        hobby,
//                        "난이도 " + i
//                ));
//            }
//            lectureAdapter.notifyDataSetChanged();
//        }
//    }
}
