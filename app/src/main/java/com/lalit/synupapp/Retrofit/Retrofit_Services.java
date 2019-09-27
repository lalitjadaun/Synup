package com.lalit.synupapp.Retrofit;


import com.lalit.synupapp.Dto.VariantsDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Retrofit_Services {


    @Headers({"Content-Type: application/json"})
    @GET("bins/19u0sf")
    Call<VariantsDto> getSynupData();


}
