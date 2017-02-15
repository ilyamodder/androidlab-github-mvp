package com.itis.androidlab.githubmvp.api;

import com.google.gson.JsonObject;
import com.itis.androidlab.githubmvp.content.Authorization;
import com.itis.androidlab.githubmvp.content.CommitResponse;
import com.itis.androidlab.githubmvp.content.Repository;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;


public interface GithubService {

    @POST("/authorizations")
    Observable<Authorization> authorize(@Header("Authorization") String authorization,
                                        @Body JsonObject params);

    @GET("/user/repos")
    Observable<List<Repository>> repositories();

    @GET("/repos/{user}/{repo}/commits")
    Observable<List<CommitResponse>> commits(@Path("user") String user, @Path("repo") String repo);

}
