package net.gormelof.hackathontask.api;

import net.gormelof.hackathontask.request.searchrecords.SearchData;
import net.gormelof.hackathontask.response.searchrecords.SearchRecords;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HackathonService {
    @POST("searchRecords")
    Call<SearchRecords> searchRecords(@Body SearchData data);
}
