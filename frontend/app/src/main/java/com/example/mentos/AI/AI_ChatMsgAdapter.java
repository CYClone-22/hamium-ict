package com.example.mentos.AI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.AI.Route.ApiClient;
import com.example.mentos.AI.Route.ChatGPTApi;
import com.example.mentos.AI.Route.GoalResponse;
import com.example.mentos.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AI_ChatMsgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements LevelTest_CustomDialog.OnLevelTestCompleteListener {

    public List<AI_ChatMsg> dataList;
    private boolean userHasResponded = false;
    private LevelTest_CustomDialog.DialogManager dialogManager;
    private BotChatViewHolder botChatViewHolder;

    public AI_ChatMsgAdapter(List<AI_ChatMsg> AIchatMsgList, Context context) {
        this.dataList = AIchatMsgList;
        this.dialogManager = new LevelTest_CustomDialog.DialogManager(context, this);
    }

    public void setDataList(List<AI_ChatMsg> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void addChatMsg(AI_ChatMsg ai_chatMsg) {
        dataList.add(ai_chatMsg);
        notifyItemInserted(dataList.size() - 1);

        if (AI_ChatMsg.ROLE_USER.equals(ai_chatMsg.getRole())) {
            userHasResponded = true;
        }
    }

    public void onAiResponseReceived(String responseText) {
        int loadingPosition = findLoadingPosition();
        if (loadingPosition != -1) {
            AI_ChatMsg updatedMsg = new AI_ChatMsg(AI_ChatMsg.ROLE_ASSISTANT, responseText);
            dataList.set(loadingPosition, updatedMsg);
            notifyItemChanged(loadingPosition);
        }
    }

    private int findLoadingPosition() {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getRole().equals(AI_ChatMsg.ROLE_LOADING)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onLevelTestComplete() {
        if (botChatViewHolder != null) {
            botChatViewHolder.challengeNum();
        }
    }

    @Override
    public int getItemViewType(int position) {
        String role = dataList.get(position).getRole();
        if (AI_ChatMsg.ROLE_USER.equals(role)) return 0;
        if (AI_ChatMsg.ROLE_LOADING.equals(role)) return 2;
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 0) {
            return new MyChatViewHolder(inflater.inflate(R.layout.item_ai_my_chat, parent, false));
        } else {
            return new BotChatViewHolder(inflater.inflate(R.layout.item_ai_partner_chat, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AI_ChatMsg ai_chatMsg = dataList.get(position);
        if (AI_ChatMsg.ROLE_USER.equals(ai_chatMsg.getRole())) {
            ((MyChatViewHolder) holder).setMsg(ai_chatMsg);
        } else if (AI_ChatMsg.ROLE_ASSISTANT.equals(ai_chatMsg.getRole())) {
            botChatViewHolder = (BotChatViewHolder) holder;
            botChatViewHolder.setMsg(ai_chatMsg);

            if (userHasResponded) {
                botChatViewHolder.leveltest_layout.setVisibility(View.VISIBLE);
            } else {
                botChatViewHolder.leveltest_layout.setVisibility(View.GONE);
            }
        } else if (AI_ChatMsg.ROLE_LOADING.equals(ai_chatMsg.getRole())) {
            ((BotChatViewHolder) holder).showLoading();
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyChatViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMsg;

        public MyChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.tv_msg);
        }

        public void setMsg(AI_ChatMsg ai_chatMsg) {
            tvMsg.setText(ai_chatMsg.getContent());
        }
    }

    class BotChatViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMsg;
        private ImageView loadingImageView;
        private LinearLayout last_btns;
        private Button move_btn, restart_btn;
        private Button levelTestStartButton;
        private LinearLayout leveltest_layout;
        private RelativeLayout challenge_nums;
        private NumberPicker numberPicker;
        private Button submit_btn;
        private int challenge_number;

        private boolean isExpanded = false;  // '재료'와 '방법'을 동시에 확장할지 여부를 제어하는 플래그

        public BotChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.tv_msg);
            loadingImageView = itemView.findViewById(R.id.loading);
            last_btns = itemView.findViewById(R.id.last_btns);
            move_btn = itemView.findViewById(R.id.move_btn);
            restart_btn = itemView.findViewById(R.id.restart_btn);
            levelTestStartButton = itemView.findViewById(R.id.leveltest_start_btn);
            leveltest_layout = itemView.findViewById(R.id.leveltest_layout);
            challenge_nums = itemView.findViewById(R.id.challenge_nums);
            numberPicker = itemView.findViewById(R.id.challenge_npker);
            submit_btn = itemView.findViewById(R.id.button_submit);

            submit_btn.setOnClickListener(v -> {
                // 메시지 생성
                String title = "챌린지 1. <된장찌개>\n";
                String ingredientsTitle = "재료 및 방법 (클릭하여 보기)";
                String ingredients = "된장(2~3 큰술), 두부(1/2모), 애호박(1/2개), 감자(1개), 양파(1/2개), 대파(1대), 다진 마늘(1 큰술), 고춧가루(1 작은술), 멸치(10마리), 다시마(5cm 크기), 물(3컵)\n\n";
                String instructions = "1. 냄비에 물 3컵을 붓고, 멸치와 다시마를 넣어 중불에서 10분 정도 끓여 육수를 만든다.\n2. 10분 후, 멸치와 다시마를 건져낸다.\n3. 된장 2~3 큰술을 육수에 풀어 넣고, 감자와 양파를 작게 썰어 넣는다.\n4. 감자가 익기 시작할 때까지 약 5분간 끓인다.\n5. 애호박과 두부를 먹기 좋게 썰어 넣고, 중불에서 5분 정도 더 끓인다.\n6. 다진 마늘 1 큰술과 고춧가루 1 작은술을 넣어 간을 맞춘다.\n7. 모든 재료가 익으면 대파를 썰어 넣고, 2분 정도 더 끓인다.\n8. 완성된 된장찌개를 그릇에 담아낸다.\n";

                // 이미지 리소스 가져오기
                Drawable drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.challengepng1); // drawable에 추가한 이미지 파일명

                // 이미지 크기 조정
                int imageWidth = (int) (itemView.getContext().getResources().getDisplayMetrics().density * 210);
                int imageHeight = (int) (itemView.getContext().getResources().getDisplayMetrics().density * 210);

                if (drawable != null) {
                    drawable.setBounds(0, 0, imageWidth, imageHeight);

                    SpannableString spannableMessage = new SpannableString(title + "\n" + ingredientsTitle);

                    ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
                    spannableMessage.setSpan(imageSpan, spannableMessage.length() - 1, spannableMessage.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    // 스타일 설정
                    spannableMessage.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableMessage.setSpan(new UnderlineSpan(), title.length() + 1, title.length() + 1 + ingredientsTitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableMessage.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            toggleExpanded();
                        }
                    }, title.length() + 1, title.length() + 1 + ingredientsTitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    // Text 설정
                    tvMsg.setText(spannableMessage);
                    tvMsg.setMovementMethod(LinkMovementMethod.getInstance()); // ClickableSpan 활성화
                    tvMsg.setVisibility(View.VISIBLE); // 메시지를 화면에 보이도록 설정

                    tvMsg.setPadding(0, 0, 0, drawable.getIntrinsicHeight() / 5);

                    // 레이아웃 업데이트
                    leveltest_layout.setVisibility(View.GONE);
                    challenge_nums.setVisibility(View.GONE);

                    // 디버그용 로그 추가
                    Log.d("BotChatViewHolder", "Submit button clicked, message displayed.");
                } else {
                    Log.e("BotChatViewHolder", "Drawable resource not found.");
                }
            });


            restart_btn.setOnClickListener(v -> restartActivity(itemView.getContext()));

            levelTestStartButton.setOnClickListener(v -> {
                LevelTest_CustomDialog dialog = new LevelTest_CustomDialog(itemView.getContext(), 0, dialogManager, AI_ChatMsgAdapter.this);
                dialog.show();
            });

//            submit_btn.setOnClickListener(v -> {
////                int challenge_number = numberPicker.getValue();
////                Log.d("BotChatViewHolder", "Challenge Number: " + challenge_number);
////
////                SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences("MentosPrefs", Context.MODE_PRIVATE);
////                int chatRoomId = sharedPreferences.getInt("chatroomId", -1);
////                int level = LevelTest_CustomDialog.DialogManager.level;
////
////                Log.d("BotChatViewHolder", "Sending Goal Data:");
////                Log.d("BotChatViewHolder", "Chat Room ID: " + chatRoomId);
////                Log.d("BotChatViewHolder", "Challenge Number: " + challenge_number);
////                Log.d("BotChatViewHolder", "Level: " + level);
////
////                ChatGPTApi chatGPTApi = ApiClient.getChatGPTApi();
////                Call<GoalResponse> call = chatGPTApi.sendGoal(chatRoomId, challenge_number, level);
////
////                Log.d("BotChatViewHolder", "Request Body: Chat Room ID=" + chatRoomId + ", Challenge Number=" + challenge_number + ", Level=" + level);
//
//
//
//
//
////                // 개발자가 지정한 고정된 메시지
////                String fixedMessage = "<1번째 챌린지 시작합니다~!>\n\n요리 이름: 야채 오믈렛\n재료: 계란, 양파, 당근, 대파, 피망, 소금, 후추, 식용유\n도구: 프라이팬, 젓가락, 그릇\n\n<방법>\n1. 양파, 당근, 대파, 피망 등 야채를 잘게 다져 준비한다.\n2. 계란을 그릇에 깨고 소금, 후추를 넣고 풀어준다.\n3. 프라이팬에 식용유를 두르고 야채를 볶는다.\n4. 야채가 익으면 계란을 부어 주변으로 퍼지도록 한다.\n5. 약불에서 오믈렛이 익을 때까지 조리한다.\n6. 젓가락으로 접시에 담아 내용물을 흔들어 퍼지지 않도록 한다.\n\n챌린지 중 궁금한 점이 생기면 채팅 주세요! :)";
////
////                // 메시지 추가
//////                addChatMsg(new AI_ChatMsg(AI_ChatMsg.ROLE_ASSISTANT, fixedMessage));
////
////                // tv_msg에 메시지 설정
////                tvMsg.setText(fixedMessage);
////                tvMsg.setVisibility(View.VISIBLE);
//
//
//
//
//
//                // leveltest_layout 숨기기
//                leveltest_layout.setVisibility(View.GONE);
//                challenge_nums.setVisibility(View.GONE);
//
////                call.enqueue(new Callback<GoalResponse>() {
////                    @Override
////                    public void onResponse(Call<GoalResponse> call, Response<GoalResponse> response) {
////                        if (response.isSuccessful()) {
////                            GoalResponse goalResponse = response.body();
////                            if (goalResponse != null) {
////                                // 로그에서 GoalResponse의 내용 출력
////                                Log.d("APIResponse", "GoalResponse: " + goalResponse.toString());
////
////                                // 메시지 추출 (GoalResponse 클래스에서 적절한 메서드 사용)
////                                String assistantMessage = goalResponse.getMessage(); // 예시: getMessage() 사용, 실제 메서드명은 GoalResponse 클래스에 맞게 수정 필요
////
////                                if (assistantMessage != null) {
////                                    // Adapter에 Assistant 메시지 추가
////                                    addChatMsg(new AI_ChatMsg(AI_ChatMsg.ROLE_ASSISTANT, assistantMessage));
////                                }
////
//////                                // 계획 데이터 처리
//////                                List<GoalResponse.Plan> plans = parsePlansFromResponse(goalResponse);
//////                                PlanRepository repository = new PlanRepository(itemView.getContext());
//////                                repository.savePlans(plans);
//////
//////                                // UI 업데이트
//////                                updateUIWithSavedPlans();
////                            }
////                        } else {
////                            Log.e("APIResponse", "Response error: " + response.errorBody().toString());
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<GoalResponse> call, Throwable t) {
////                        Log.e("BotChatViewHolder", "Error: " + t.getMessage());
////                    }
////                });
//            });
        }

        // 서버 응답에서 계획 데이터를 추출하는 메서드
        private List<GoalResponse.Plan> parsePlansFromResponse(GoalResponse response) {
            List<GoalResponse.Plan> plans = new ArrayList<>();
            if (response != null && response.getPlans() != null) {
                plans = response.getPlans(); // GoalResponse의 getPlans() 메서드 사용
            }
            return plans;
        }

//        // UI 업데이트 메서드
//        private void updateUIWithSavedPlans() {
//            PlanRepository repository = new PlanRepository(itemView.getContext());
//            LiveData<List<GoalResponse.Plan>> plansLiveData = repository.getPlans();
//
//            plansLiveData.observe((LifecycleOwner) itemView.getContext(), plans -> {
//                if (plans != null) {
//                    RecyclerView recyclerView = ((Activity) itemView.getContext()).findViewById(R.id.assignment_recycler_view);
//                    PlanAdapter planAdapter = (PlanAdapter) recyclerView.getAdapter();
//                    if (planAdapter == null) {
//                        planAdapter = new PlanAdapter(plans);
//                        recyclerView.setAdapter(planAdapter);
//                    } else {
//                        planAdapter.setPlans(plans);
//                    }
//                } else {
//                    Log.d("BotChatViewHolder", "No plans found in repository.");
//                }
//            });
//        }

        public void setMsg(AI_ChatMsg ai_chatMsg) {
            loadingImageView.setVisibility(View.GONE);
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(ai_chatMsg.getContent());

            if (userHasResponded) {
                leveltest_layout.setVisibility(View.VISIBLE);
            } else {
                leveltest_layout.setVisibility(View.GONE);
            }
        }

        public void challengeNum() {
            loadingImageView.setVisibility(View.GONE);
            tvMsg.setVisibility(View.GONE);
            last_btns.setVisibility(View.GONE);
            leveltest_layout.setVisibility(View.GONE);

            challenge_nums.setVisibility(View.VISIBLE);
            numberPicker.setMinValue(3);
            numberPicker.setMaxValue(10);
            numberPicker.setValue(3);
            numberPicker.setWrapSelectorWheel(false);
        }

        public void showLoading() {
            loadingImageView.setVisibility(View.VISIBLE);
            tvMsg.setVisibility(View.GONE);
            last_btns.setVisibility(View.GONE);
            leveltest_layout.setVisibility(View.GONE);
        }

        private void restartActivity(Context context) {
            if (context instanceof AIMainChatActivity) {
                Intent intent = ((AIMainChatActivity) context).getIntent();
                ((AIMainChatActivity) context).finish();
                context.startActivity(intent);
            } else {
                Log.e("BotChatViewHolder", "Context is not instance of AIMainChatActivity");
            }
        }







        private void toggleExpanded() {
            SpannableString updatedMessage;
            if (isExpanded) {
                updatedMessage = new SpannableString("챌린지 1. <된장찌개>\n\n재료 및 방법 (클릭하여 보기)");
            } else {
                updatedMessage = new SpannableString("챌린지 1. <된장찌개>\n\n된장(2~3 큰술), 두부(1/2모), 애호박(1/2개), 감자(1개), 양파(1/2개), 대파(1대), 다진 마늘(1 큰술), 고춧가루(1 작은술), 멸치(10마리), 다시마(5cm 크기), 물(3컵)\n\n\n1. 냄비에 물 3컵을 붓고, 멸치와 다시마를 넣어 중불에서 10분 정도 끓여 육수를 만든다.\n\n2. 10분 후, 멸치와 다시마를 건져낸다.\n\n3. 된장 2~3 큰술을 육수에 풀어 넣고, 감자와 양파를 작게 썰어 넣는다.\n\n4. 감자가 익기 시작할 때까지 약 5분간 끓인다.\n\n5. 애호박과 두부를 먹기 좋게 썰어 넣고, 중불에서 5분 정도 더 끓인다.\n\n6. 다진 마늘 1 큰술과 고춧가루 1 작은술을 넣어 간을 맞춘다.\n\n7. 모든 재료가 익으면 대파를 썰어 넣고, 2분 정도 더 끓인다.\n\n8. 완성된 된장찌개를 그릇에 담아낸다.");
            }
            isExpanded = !isExpanded;

            // Styling for expanded/collapsed state
            int startTitle = 0;
            int endTitle = "챌린지 1. <된장찌개>".length();
            int startIngredientsTitle = endTitle + 2;  // "\n\n" 문자 2개 추가
            int endIngredientsTitle = startIngredientsTitle + "재료 및 방법 (클릭하여 보기)".length();

            int startIngredients = endIngredientsTitle + 2;  // "\n\n" 문자 2개 추가
            int endIngredients = startIngredients + "된장(2~3 큰술), 두부(1/2모), 애호박(1/2개), 감자(1개), 양파(1/2개), 대파(1대), 다진 마늘(1 큰술), 고춧가루(1 작은술), 멸치(10마리), 다시마(5cm 크기), 물(3컵)".length();

            int startInstructions = endIngredients + 1;  // "\n\n" 문자 2개 추가
            int endInstructions = startInstructions + "1. 냄비에 물 3컵을 붓고, 멸치와 다시마를 넣어 중불에서 10분 정도 끓여 육수를 만든다.\n\n2. 10분 후, 멸치와 다시마를 건져낸다.\n\n3. 된장 2~3 큰술을 육수에 풀어 넣고, 감자와 양파를 작게 썰어 넣는다.\n\n4. 감자가 익기 시작할 때까지 약 5분간 끓인다.\n\n5. 애호박과 두부를 먹기 좋게 썰어 넣고, 중불에서 5분 정도 더 끓인다.\n\n6. 다진 마늘 1 큰술과 고춧가루 1 작은술을 넣어 간을 맞춘다.\n\n7. 모든 재료가 익으면 대파를 썰어 넣고, 2분 정도 더 끓인다.\n\n8. 완성된 된장찌개를 그릇에 담아낸다.".length();

            updatedMessage.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    toggleExpanded();
                }
            }, startTitle, endTitle, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            updatedMessage.setSpan(new UnderlineSpan(), startIngredientsTitle, endIngredientsTitle, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            tvMsg.setText(updatedMessage);
            tvMsg.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
