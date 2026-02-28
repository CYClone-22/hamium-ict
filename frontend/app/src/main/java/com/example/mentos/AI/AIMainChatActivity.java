package com.example.mentos.AI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.AI.Route.ApiClient;
import com.example.mentos.AI.Route.ChatGPTApi;
import com.example.mentos.AI.Route.ChatGPTRequest;
import com.example.mentos.AI.Route.ChatGPTResponse;
import com.example.mentos.AI.Route.CreateAIChatRoomRequest;
import com.example.mentos.AI.Route.CreateAIChatRoomResponse;
import com.example.mentos.AI.Route.Question;
import com.example.mentos.AI.Route.StartChatRequest;
import com.example.mentos.AI.Route.StartChatResponse;
import com.example.mentos.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AIMainChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AI_ChatMsgAdapter adapter;
    ImageButton btnSend;
    ImageButton btnGoBackList;
    EditText etMsg;
    RelativeLayout progressBarLayout;
    Button move_btn, restart_btn;



    List<AI_ChatMsg> AIchatMsgList;

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "MentosPrefs";
    private static final String KEY_GUEST_ID = "guest_id";
    private int guestId;
    public int chatRoomId;

    private List<String> questions = new ArrayList<>();

    private boolean ignoreNextResponse = false; // 한 번만 응답을 무시할 플래그

    private DrawerLayout drawerLayout;
    private RecyclerView assignment_recyclerView;
    private ImageButton btn_AssignmentList;

    public AI_ChatMsg lastMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_mainchat);

        // 뷰 객체 연결
        recyclerView = findViewById(R.id.AI_chat_recyclerview);
        btnSend = findViewById(R.id.btn_send);
        btnGoBackList = findViewById(R.id.btn_goback_list);
        etMsg = findViewById(R.id.edit_text_message);
        progressBarLayout = findViewById(R.id.input_layout);

        // RecyclerView와 Adapter 초기화
        AIchatMsgList = new ArrayList<>();
        adapter = new AI_ChatMsgAdapter(AIchatMsgList, this); // Context를 전달하여 초기화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        drawerLayout = findViewById(R.id.drawer_layout);
        btn_AssignmentList = findViewById(R.id.btn_assignment_list);
        assignment_recyclerView = findViewById(R.id.assignment_recycler_view);

        // 뒤로가기 버튼 클릭 리스너 설정
        btnGoBackList.setOnClickListener(v -> finish());

        // 메시지 전송 버튼 클릭 리스너 설정
        btnSend.setOnClickListener(v -> {
            String msg = etMsg.getText().toString();
            etMsg.setText(null);

            if (adapter != null) {
                adapter.addChatMsg(new AI_ChatMsg(AI_ChatMsg.ROLE_USER, msg));
                scrollToBottom();
            } else {
                Log.e("AIMainChatActivity", "Adapter is null in btnSend click listener.");
            }

            InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) {
                manager.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }

            sendMsgToChatGPT(msg);
        });

        // SharedPreferences에서 guest_id 가져오기
        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        guestId = sharedPreferences.getInt(KEY_GUEST_ID, -1);

        if (guestId != -1) {
            createChatRoom(guestId);
        } else {
            Log.e("AIMainChatActivity", "Guest ID not found in SharedPreferences");
        }

        // RecyclerView 설정 (예시)
        assignment_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Adapter 설정 등 추가 작업 필요

        // 메뉴 버튼 클릭 시 DrawerLayout 열기
        btn_AssignmentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    private void setLoadingState(boolean isLoading) {
        if (isLoading) {
            progressBarLayout.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            btnSend.setEnabled(false);
        } else {
            progressBarLayout.setVisibility(View.VISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            etMsg.setClickable(false);
            etMsg.setFocusable(false);
            btnSend.setEnabled(false);
        }
    }

    private void createChatRoom(int guestId) {
        ChatGPTApi api = ApiClient.getChatGPTApi();
        CreateAIChatRoomRequest request = new CreateAIChatRoomRequest(guestId, true);
        api.createChatRoom(request).enqueue(new Callback<CreateAIChatRoomResponse>() {
            @Override
            public void onResponse(Call<CreateAIChatRoomResponse> call, Response<CreateAIChatRoomResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    chatRoomId = response.body().getChatRoomId();

                    // SharedPreferences에 chatRoomId 저장
                    // AIMainChatActivity에서 chatRoomId를 저장할 때
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("chatroomId", chatRoomId);
                    editor.apply();


                    // 로그를 통해 저장된 값 확인
                    Log.d("AIMainChatActivity", "ChatRoomId saved: " + chatRoomId);

                    startChat(chatRoomId);
                } else {
                    Log.e("createChatRoom", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CreateAIChatRoomResponse> call, Throwable t) {
                Log.e("createChatRoom", "onFailure: ", t);
            }
        });
    }

    private void startChat(int chatRoomId) {
        ChatGPTApi api = ApiClient.getChatGPTApi();
        StartChatRequest request = new StartChatRequest(chatRoomId);
        api.startChat(request).enqueue(new Callback<StartChatResponse>() {
            @Override
            public void onResponse(Call<StartChatResponse> call, Response<StartChatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Question> messages = response.body().getQuestions();
                    if (messages != null && !messages.isEmpty()) {
                        for (Question message : messages) {
                            if (message.getContent() != null) {
                                adapter.addChatMsg(new AI_ChatMsg(AI_ChatMsg.ROLE_ASSISTANT, message.getContent()));
                                scrollToBottom();
                                progressBarLayout.setVisibility(View.VISIBLE); // 이 상태에서는 항상 보이도록 설정
                            }
                        }
                    }
                } else {
                    Log.e("startChat", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<StartChatResponse> call, Throwable t) {
                Log.e("startChat", "onFailure: ", t);
            }
        });
    }

    private void sendMsgToChatGPT(String userMessage) {
        if (chatRoomId <= 0) {
            Log.e("AIMainChatActivity", "Invalid ChatRoomID: " + chatRoomId);
            return;
        }

        ChatGPTApi api = ApiClient.getChatGPTApi();
        ChatGPTRequest request = new ChatGPTRequest(chatRoomId, userMessage);

        adapter.addChatMsg(new AI_ChatMsg(AI_ChatMsg.ROLE_LOADING, ""));
        scrollToBottom();

        setLoadingState(true);

        api.getChatResponse(request).enqueue(new Callback<ChatGPTResponse>() {
            @Override
            public void onResponse(Call<ChatGPTResponse> call, Response<ChatGPTResponse> response) {
                setLoadingState(false);
                if (response.isSuccessful() && response.body() != null) {
                    String responseText = response.body().getResponse();
                    adapter.onAiResponseReceived(responseText);
                    addChatMessage(new AI_ChatMsg(AI_ChatMsg.ROLE_ASSISTANT, responseText));
                    scrollToBottom();
                } else {
                    Log.e("sendMsgToChatGPT", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ChatGPTResponse> call, Throwable t) {
                setLoadingState(false);
                Log.e("sendMsgToChatGPT", "onFailure: ", t);
            }
        });
    }


//    private void showLevelTestDialog() {
//        // LevelTest 다이얼로그 표시
//        LevelTest_CustomDialog.DialogManager dialogManager = new LevelTest_CustomDialog.DialogManager(this);
//        LevelTest_CustomDialog dialog = new LevelTest_CustomDialog(this, 0, dialogManager); // 다이얼로그 인덱스는 필요에 따라 설정
//        dialog.show();
//
//        // 애니메이션 로드 및 적용
//        Animation scaleUpAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_scale_up);
//        progressBarLayout.startAnimation(scaleUpAnimation);
//
//        // 다이얼로그가 표시될 때 progressBarLayout의 터치 기능만 비활성화
//        progressBarLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//        // 레벨 테스트 완료 리스너 설정
//        dialog.setOnLevelTestCompleteListener(() -> {
//            // 레벨 테스트 완료 시 challenge_nums 항목 visible로 설정
//            showChallengeNums();
//        });
//
//        dialog.show();
//    }

    private void showChallengeNums() {
        // challenge_nums 항목을 찾고 visible로 설정
        View challengeNumsView = findViewById(R.id.challenge_nums); // challenge_nums의 ID에 맞게 수정 필요
        if (challengeNumsView != null) {
            challengeNumsView.setVisibility(View.VISIBLE);
        }
    }

    public void onLevelTestComplete() {
        // 레벨 테스트 완료 시 호출되는 메서드
        Log.d("AIMainChatActivity", "Level test completed. Handling post-completion actions.");

        // 여기서 레벨 테스트 완료 후 필요한 작업을 수행합니다.
        // 예를 들어, UI 업데이트, 데이터 저장, 추가 처리 등을 할 수 있습니다.
        showChallengeNums(); // 예시: challenge_nums 항목을 보이도록 설정
    }


    private void scrollToBottom() {
        if (layoutManager != null && adapter != null) {
            recyclerView.post(() -> recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1));
        }
    }

    private void addChatMessage(AI_ChatMsg chatMsg) {
        if (adapter != null) {
            lastMessage = chatMsg;
            scrollToBottom();
        } else {
            Log.e("AIMainChatActivity", "Adapter is null when adding chat messages.");
        }
    }
}
