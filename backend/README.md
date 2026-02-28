# Backend
**라우트 api 명세서 - app.py**

< 기본 >

- **회원가입 (`/signup`)**: 사용자 회원가입 처리 ⇒ 사용자의 5가지 정보(이름 추가)를 받아 DB에 저장 
- **로그인 (`/login`)**: 이메일과 비밀번호로 사용자 로그인 처리 ⇒ 성공 시 게스트 ID 반환
- **설문조사 (`/survey`)**:  사용자 정보 및 설문 응답 데이터를 받아 DB에 저장

<ai 멘토 관련>

- **AI 채팅방 생성 (`/create_ai_chat_room`)**: `guest_id`와 `is_ai_chatbot`을 기반으로 새로운 `ChatRoom`을 생성하고 저장
- **초기 질문 라우트 (`/start_chat`)**: AI 채팅이 시작될 때, 초기 질문(무엇을 배우고 싶은지)을 사용자에게 던짐. 
- **사용자 응답 처리 (`/chat`)**: 자연어 처리로 취미 대상 추출 → db 저장 
- **레벨 테스트 문제 반환 (`get_level_test` ) :** `chat_room_id` 를 받아 취미 대상에 맞는 레벨 테스트 문제를 반환
- **레벨 테스트 채점 + 유저 레벨 지정 라우트(`submit_level_test`) :** `chat_room_id` 와 `user_answers`를 받아 db에 저장되어있는 해당 취미에 정답과 비교해서 점수를 채점해주고 점수와 유저의 레벨을 반환함
- **목표 라우트(`/goal` )**: `chat_room_id` 의 해당하는 취미 대상과 난이도에 대한 챌린지들을 생성
- **목표 정보 라우트(`/Challenge` )**: `chat_room_id` 와 `goal_number` 를 받아 해당 챌린지에 대한 정보 반환
- **AI 채팅방 대화 기록 반환(`/ai_chat_history/<int:chat_room_id>`):** 해당 AI 채팅방의 대화 기록들을 DB에서 불러와서 JSON 형태로 반환함
- **취미 대상 라우트(`/get_hobby_target`** **)** : `chat_room_id`을 받아 해당 채팅룸의 취미 대상을 반환함 (채팅방 이름에 ‘OO AI 멘토’ 띄울 목적)
- **사용자 결과물 평가(`/test_score` ) :** ‘음성 → 악보’ 변환

< 1:1 멘토멘티 관련>

- **멘토 추천 라우트** (**`/recommend_match`**)**:** 프론트한테 멘티의 `guest_id`를 받아 설문 데이터에 가장 적합한 상위 3명의 멘토 정보를 반환
- **매칭 라우트** (**`/match`**)**:** 매칭이 성사된 `mentee_id`와 `mentor_id` 를 받아 매칭 DB에 저장
- **멘토-멘티 채팅방 생성 (`/create_chat_room`):** 매칭된  `mentee_id` , `mentor_id` 와 취미대상을 받아 채팅방 생성 → DB에 저장
- **멘토-멘티 채팅 메시지 전송  (`/send_message`):** 멘토-멘티 간 메시지 전송 및 저장
- **1:1 채팅방 대화 기록 반환(`/match_chat_history/<int:match_chat_room_id>`):** 해당 AI 채팅방의 대화 기록들을 DB에서 불러와서 JSON 형태로 반환함


< 강의 >

- **강의 추천 라우트 (`/recommend_course`) :** 사용자의 클릭 데이터를 분석하고 유사한 사용자들의 클릭 패턴을 참고하여 강의 추천

< 커뮤니티 >

- **질문 작성 라우트 (`/community_post`) :** `question`, `guest_id` 을 받아 DB에 저장하고 해당 게시글에 대한 `post_id`를 반환
- **AI 답변 라우트 (`/community_ai`):** `post_id` 를 받아 게시판 답변 스타일로 프롬포트 설정된 AI의 답변 내용을 반환, DB에 저장
- **유저 답변 라우트 (`/community_user`):** `post_id`, `guest_id`, `answer` 를 받아 `answer_id` 를 반환, DB에 저장
