package com.example.mentos.Match;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MatchRequest extends JsonObjectRequest {
    private static final String MATCH_REQUEST_URL = "http://52.79.234.36:5001/match";
    public MatchRequest(int mentee_id, int mentor_id, Response.Listener<JSONObject> listener) throws JSONException {
        super(Method.POST, MATCH_REQUEST_URL,createRequestBody(mentee_id, mentor_id), listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 네트워크 오류 로그
                Log.e("MatchRequest", "Error: " + error.toString());
                error.printStackTrace();
            }
        });

        //파라미터 로그
        Log.d("MatchRequest", "Params: " + createRequestBody(mentee_id, mentor_id).toString());
    }

    private static JSONObject createRequestBody(int mentee_id, int mentor_id) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("mentee_id", mentee_id);
        requestBody.put("mentor_id", mentor_id);

        return requestBody;
    }
}
