package com.example.mentos.AI.Route;

import com.example.mentos.AI.ProcessingStatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ChatGPTApi {
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer sk-mentos-qHLdPTiXoYsq42J13McDT3BlbkFJZvXpl4ImtXbSi1eUzFEb"
    })
    @POST("/chat")
    Call<ChatGPTResponse> getChatResponse(@Body ChatGPTRequest request);

    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer sk-mentos-qHLdPTiXoYsq42J13McDT3BlbkFJZvXpl4ImtXbSi1eUzFEb"
    })

    @POST("/create_ai_chat_room")  // 서버의 실제 엔드포인트 경로를 설정하세요.
    Call<CreateAIChatRoomResponse> createChatRoom(@Body CreateAIChatRoomRequest request);

    @POST("/start_chat")
    Call<StartChatResponse> startChat(@Body StartChatRequest request);

    @POST("generate_detailed_description")
    Call<GenerateDescriptionResponse> generateDetailedDescription(@Body GenerateDescriptionRequest request);


    @GET("/processing/status/{chatRoomId}")
    Call<ProcessingStatusResponse> getProcessingStatus(@Path("chatRoomId") Integer chatRoomId);

    @GET("/goal")  // goal 엔드포인트를 설정하세요.
    Call<GoalResponse> sendGoal(@Query("chat_room_id") int chatRoomId,
                                @Query("challenge_number") int challengeNumber,
                                @Query("level") int level);
}
