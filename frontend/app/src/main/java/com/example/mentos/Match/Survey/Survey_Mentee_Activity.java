package com.example.mentos.Match.Survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentos.Match.MatchFragment;
import com.example.mentos.Match.Popular.PopularActivity;
import com.example.mentos.R;
import java.util.ArrayList;
import java.util.List;

public class Survey_Mentee_Activity extends AppCompatActivity {

    private Spinner spinnerHobbies;
    private CheckBox maleCheckBox, femaleCheckBox;
    private CheckBox age10sCheckBox, age20sCheckBox, age30sCheckBox, age40sCheckBox, age50sCheckBox, age60sCheckBox;
    private CheckBox seoulCheckBox, busanCheckBox, daeguCheckBox, incheonCheckBox, gwangjuCheckBox, daejeonCheckBox, ulsanCheckBox, sejongCheckBox, gyunggiCheckBox, gangwonCheckBox, chungbukCheckBox, chungnamCheckBox, jeonbukCheckBox, jeonnamCheckBox, gyungbukCheckBox, gyungnamCheckBox, jejuCheckBox;

    private RadioButton easyRadioButton, mediumRadioButton, hardRadioButton;
    private Button btnSubmit, TOP3btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_survey_mentee);

        // UI 요소 초기화
        spinnerHobbies = findViewById(R.id.spinnerHobbies);
        maleCheckBox = findViewById(R.id.maleCheckBox);
        femaleCheckBox = findViewById(R.id.femaleCheckBox);

        age10sCheckBox = findViewById(R.id.age10sCheckBox);
        age20sCheckBox = findViewById(R.id.age20sCheckBox);
        age30sCheckBox = findViewById(R.id.age30sCheckBox);
        age40sCheckBox = findViewById(R.id.age40sCheckBox);
        age50sCheckBox = findViewById(R.id.age50sCheckBox);
        age60sCheckBox = findViewById(R.id.age60sCheckBox);

        seoulCheckBox = findViewById(R.id.seoulCheckBox);
        busanCheckBox = findViewById(R.id.busanCheckBox);
        daeguCheckBox = findViewById(R.id.daeguCheckBox);
        incheonCheckBox = findViewById(R.id.incheonCheckBox);
        gwangjuCheckBox = findViewById(R.id.gwangjuCheckBox);
        daejeonCheckBox = findViewById(R.id.daejeonCheckBox);
        ulsanCheckBox = findViewById(R.id.ulsanCheckBox);
        sejongCheckBox = findViewById(R.id.sejongCheckBox);
        gyunggiCheckBox = findViewById(R.id.gyunggiCheckBox);
        gangwonCheckBox = findViewById(R.id.gangwonCheckBox);
        chungbukCheckBox = findViewById(R.id.chungbukCheckBox);
        chungnamCheckBox = findViewById(R.id.chungnamCheckBox);
        jeonbukCheckBox = findViewById(R.id.jeonbukCheckBox);
        jeonnamCheckBox = findViewById(R.id.jeonnamCheckBox);
        gyungbukCheckBox = findViewById(R.id.gyungbukCheckBox);
        gyungnamCheckBox = findViewById(R.id.gyungnamCheckBox);
        jejuCheckBox = findViewById(R.id.jejuCheckBox);

        easyRadioButton = findViewById(R.id.easyRadioButton);
        mediumRadioButton = findViewById(R.id.mediumRadioButton);
        hardRadioButton = findViewById(R.id.hardRadioButton);

        btnSubmit = findViewById(R.id.submitButton);
        TOP3btn = findViewById(R.id.TOP3btn);

        // Spinner 초기화
        initializeSpinner();

        // 초기 버튼 상태 설정
        updateSubmitButtonState();

        // 리스너 설정
        setListeners();
    }

    private void initializeSpinner() {
        // Spinner 데이터 설정
        String[] hobbies = getResources().getStringArray(R.array.hobby);

        // ArrayAdapter 설정
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, hobbies);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Spinner에 Adapter 설정
        spinnerHobbies.setAdapter(spinnerAdapter);

        // Spinner 선택 리스너 설정
        spinnerHobbies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 취미 항목 처리
                String selectedHobby = parent.getItemAtPosition(position).toString();
                // 취미 항목 변경 시 제출 버튼 상태 업데이트
                updateSubmitButtonState();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 아무것도 선택되지 않았을 때 처리
            }
        });
    }

    private void setListeners() {
        // 나이 체크박스에 대한 리스너 추가
        CheckBox[] ageCheckBoxes = {
                age10sCheckBox, age20sCheckBox, age30sCheckBox, age40sCheckBox, age50sCheckBox, age60sCheckBox
        };
        for (CheckBox checkBox : ageCheckBoxes) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSubmitButtonState());
        }

        // 성별 체크박스에 대한 리스너 추가
        CheckBox[] genderCheckBoxes = {
                age10sCheckBox, age20sCheckBox, age30sCheckBox, age40sCheckBox, age50sCheckBox, age60sCheckBox
        };
        for (CheckBox checkBox : genderCheckBoxes) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSubmitButtonState());
        }

        // 지역 체크박스에 대한 리스너 추가
        CheckBox[] locationCheckBoxes = {
                seoulCheckBox, busanCheckBox, daeguCheckBox, incheonCheckBox, gwangjuCheckBox, daejeonCheckBox,
                ulsanCheckBox, sejongCheckBox, gyunggiCheckBox, gangwonCheckBox, chungbukCheckBox, chungnamCheckBox,
                jeonbukCheckBox, jeonnamCheckBox, gyungbukCheckBox, gyungnamCheckBox, jejuCheckBox
        };
        for (CheckBox checkBox : locationCheckBoxes) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSubmitButtonState());
        }

        // 난이도 라디오 그룹에 대한 리스너 추가
        RadioGroup radioGroupLevel = findViewById(R.id.radioGroupLevel);
        radioGroupLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateSubmitButtonState();
            }
        });

        // 제출 버튼 클릭 리스너 추가
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSurveyData();
                Intent intent = new Intent(Survey_Mentee_Activity.this, MatchFragment.class);
                startActivity(intent);
            }
        });

        // Top3 버튼 클릭 리스너 추가
        TOP3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Survey_Mentee_Activity.this, PopularActivity.class);
                startActivity(intent);
            }
        });


    }

    private void updateSubmitButtonState() {
        boolean isAgeProvided = age10sCheckBox.isChecked() || age20sCheckBox.isChecked() || age30sCheckBox.isChecked() || age30sCheckBox.isChecked() ||
                age40sCheckBox.isChecked() || age50sCheckBox.isChecked() || age60sCheckBox.isChecked();
        boolean isHobbySelected = spinnerHobbies.getSelectedItem() != null && !spinnerHobbies.getSelectedItem().toString().isEmpty();
        boolean isGenderSelected = maleCheckBox.isChecked() || femaleCheckBox.isChecked();
        boolean isLocationSelected = seoulCheckBox.isChecked() || busanCheckBox.isChecked() || daeguCheckBox.isChecked() || incheonCheckBox.isChecked() ||
                gwangjuCheckBox.isChecked() || daejeonCheckBox.isChecked() || ulsanCheckBox.isChecked() || sejongCheckBox.isChecked() ||
                gyunggiCheckBox.isChecked() || gangwonCheckBox.isChecked() || chungbukCheckBox.isChecked() || chungnamCheckBox.isChecked() ||
                jeonbukCheckBox.isChecked() || jeonnamCheckBox.isChecked() || gyungbukCheckBox.isChecked() || gyungnamCheckBox.isChecked() ||
                jejuCheckBox.isChecked();
        boolean isLevelSelected = easyRadioButton.isChecked() || mediumRadioButton.isChecked() || hardRadioButton.isChecked();

        // 조건이 모두 충족되면 버튼 활성화
        if (isAgeProvided && isHobbySelected && isGenderSelected && isLocationSelected && isLevelSelected) {
            btnSubmit.setBackgroundResource(R.drawable.rounded_button_purple); // 버튼 활성화 색상
            btnSubmit.setEnabled(true);
        } else {
            btnSubmit.setBackgroundResource(R.drawable.rounded_button_gray); // 버튼 비활성화 색상
            btnSubmit.setEnabled(false);
        }
    }

    private void saveSurveyData() {
        String selectedHobby = spinnerHobbies.getSelectedItem().toString();

        List<String> selectedGenders = new ArrayList<>();
        if (maleCheckBox.isChecked()) selectedGenders.add("남자");
        if (femaleCheckBox.isChecked()) selectedGenders.add("여자");

        List<String> selectedAges = new ArrayList<>();
        if (age10sCheckBox.isChecked()) selectedAges.add("10대");
        if (age20sCheckBox.isChecked()) selectedAges.add("20대");
        if (age30sCheckBox.isChecked()) selectedAges.add("30대");
        if (age40sCheckBox.isChecked()) selectedAges.add("40대");
        if (age50sCheckBox.isChecked()) selectedAges.add("50대");
        if (age60sCheckBox.isChecked()) selectedAges.add("60대 이상");

        List<String> selectedLocations = new ArrayList<>();
        if (seoulCheckBox.isChecked()) selectedLocations.add("서울");
        if (busanCheckBox.isChecked()) selectedLocations.add("부산");
        if (daeguCheckBox.isChecked()) selectedLocations.add("대구");
        if (incheonCheckBox.isChecked()) selectedLocations.add("인천");
        if (gwangjuCheckBox.isChecked()) selectedLocations.add("광주");
        if (daejeonCheckBox.isChecked()) selectedLocations.add("대전");
        if (ulsanCheckBox.isChecked()) selectedLocations.add("울산");
        if (sejongCheckBox.isChecked()) selectedLocations.add("세종");
        if (gyunggiCheckBox.isChecked()) selectedLocations.add("경기");
        if (gangwonCheckBox.isChecked()) selectedLocations.add("강원");
        if (chungbukCheckBox.isChecked()) selectedLocations.add("충북");
        if (chungnamCheckBox.isChecked()) selectedLocations.add("충남");
        if (jeonbukCheckBox.isChecked()) selectedLocations.add("전북");
        if (jeonnamCheckBox.isChecked()) selectedLocations.add("전남");
        if (gyungbukCheckBox.isChecked()) selectedLocations.add("경북");
        if (gyungnamCheckBox.isChecked()) selectedLocations.add("경남");
        if (jejuCheckBox.isChecked()) selectedLocations.add("제주");

        List<String> selectedLevel = new ArrayList<>();
        if (easyRadioButton.isChecked()) selectedLevel.add("초급");
        if (mediumRadioButton.isChecked()) selectedLevel.add("중급");
        if (hardRadioButton.isChecked()) selectedLevel.add("고급");

        // 데이터 저장 또는 처리 로직 구현

        // 예시로 출력:
        System.out.println("Hobby: " + selectedHobby);
        System.out.println("Gender: " + selectedGenders);
        System.out.println("Age: " + selectedAges);
        System.out.println("Locations: " + selectedLocations);
        System.out.println("Level: " + selectedLevel);
    }
}
