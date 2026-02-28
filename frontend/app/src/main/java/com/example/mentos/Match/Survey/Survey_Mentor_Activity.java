package com.example.mentos.Match.Survey;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentos.Match.MatchFragment;
import com.example.mentos.Match.Popular.PopularActivity;
import com.example.mentos.R;
import java.util.ArrayList;
import java.util.List;

public class Survey_Mentor_Activity extends AppCompatActivity {

    private Spinner spinnerHobbies;
    private RadioButton maleRadioButton, femaleRadioButton;
    private EditText ageText;
    private CheckBox seoulCheckBox, busanCheckBox, daeguCheckBox, incheonCheckBox, gwangjuCheckBox, daejeonCheckBox, ulsanCheckBox, sejongCheckBox, gyunggiCheckBox, gangwonCheckBox, chungbukCheckBox, chungnamCheckBox, jeonbukCheckBox, jeonnamCheckBox, gyungbukCheckBox, gyungnamCheckBox, jejuCheckBox;
    private CheckBox easyCheckBox, mediumCheckBox, hardCheckBox;
    private Button btnSubmit, TOP3btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_survey_mentor);

        // UI 요소 초기화
        spinnerHobbies = findViewById(R.id.spinnerHobbies);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);
        ageText = findViewById(R.id.ageEditText);

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

        easyCheckBox = findViewById(R.id.easyCheckBox);
        mediumCheckBox = findViewById(R.id.mediumCheckBox);
        hardCheckBox = findViewById(R.id.hardCheckBox);

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
        // 나이 입력 필드에 대한 텍스트 변경 리스너 추가
        ageText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                updateSubmitButtonState();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        // 성별 라디오 그룹에 대한 리스너 추가
        RadioGroup radioGroupGender = findViewById(R.id.radioGroupGender);
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateSubmitButtonState();
            }
        });

        // 지역 체크박스에 대한 리스너 추가
        CheckBox[] locationCheckBoxes = {
                seoulCheckBox, busanCheckBox, daeguCheckBox, incheonCheckBox, gwangjuCheckBox, daejeonCheckBox,
                ulsanCheckBox, sejongCheckBox, gyunggiCheckBox, gangwonCheckBox, chungbukCheckBox, chungnamCheckBox,
                jeonbukCheckBox, jeonnamCheckBox, gyungbukCheckBox, gyungnamCheckBox, jejuCheckBox
        };
        for (CheckBox checkBox : locationCheckBoxes) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSubmitButtonState());
        }

        // 난이도 체크박스에 대한 리스너 추가
        CheckBox[] levelCheckBoxes = {easyCheckBox, mediumCheckBox, hardCheckBox};
        for (CheckBox checkBox : levelCheckBoxes) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSubmitButtonState());
        }

        // 제출 버튼 클릭 리스너 추가
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSurveyData();
                Intent intent = new Intent(Survey_Mentor_Activity.this, MatchFragment.class);
                startActivity(intent);
            }
        });

        // Top3 버튼 클릭 리스너 추가
        TOP3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Survey_Mentor_Activity.this, PopularActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateSubmitButtonState() {
        boolean isAgeProvided = !ageText.getText().toString().trim().isEmpty();
        boolean isHobbySelected = spinnerHobbies.getSelectedItem() != null && !spinnerHobbies.getSelectedItem().toString().isEmpty();
        boolean isGenderSelected = maleRadioButton.isChecked() || femaleRadioButton.isChecked();
        boolean isLocationSelected = seoulCheckBox.isChecked() || busanCheckBox.isChecked() || daeguCheckBox.isChecked() || incheonCheckBox.isChecked() ||
                gwangjuCheckBox.isChecked() || daejeonCheckBox.isChecked() || ulsanCheckBox.isChecked() || sejongCheckBox.isChecked() ||
                gyunggiCheckBox.isChecked() || gangwonCheckBox.isChecked() || chungbukCheckBox.isChecked() || chungnamCheckBox.isChecked() ||
                jeonbukCheckBox.isChecked() || jeonnamCheckBox.isChecked() || gyungbukCheckBox.isChecked() || gyungnamCheckBox.isChecked() ||
                jejuCheckBox.isChecked();
        boolean isLevelSelected = easyCheckBox.isChecked() || mediumCheckBox.isChecked() || hardCheckBox.isChecked();

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
        if (maleRadioButton.isChecked()) selectedGenders.add("남자");
        if (femaleRadioButton.isChecked()) selectedGenders.add("여자");

        String ageInput = ageText.getText().toString();

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

        List<String> selectedLevels = new ArrayList<>();
        if (easyCheckBox.isChecked()) selectedLevels.add("초급");
        if (mediumCheckBox.isChecked()) selectedLevels.add("중급");
        if (hardCheckBox.isChecked()) selectedLevels.add("고급");

        // 데이터 저장 또는 처리 로직 구현

        // 예시로 출력:
        System.out.println("Hobby: " + selectedHobby);
        System.out.println("Gender: " + selectedGenders);
        System.out.println("Age: " + ageInput);
        System.out.println("Locations: " + selectedLocations);
        System.out.println("Level: " + selectedLevels);
    }
}
