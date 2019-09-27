package com.lalit.synupapp.Controller;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lalit.synupapp.Controller.Adapter.Category_Adapter;
import com.lalit.synupapp.Dto.VariantsDto;
import com.lalit.synupapp.R;
import com.lalit.synupapp.Retrofit.Retrofit_Config;
import com.lalit.synupapp.Retrofit.Retrofit_Services;
import com.lalit.synupapp.Util.Common;

import am.appwise.components.ni.NoInternetDialog;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SpotsDialog spotsDialog;
    RecyclerView recyclerView_list;
    Category_Adapter category_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView_list = findViewById(R.id.recyclerView_list);

        if (Common.isConnectedCodeToInternet(getBaseContext())) {
            getSynupData();
        } else {
            NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(MainActivity.this).build();
            noInternetDialog.show();
            noInternetDialog.setCancelable(true);
        }


    }

    private void getSynupData() {

        spotsDialog = new SpotsDialog(MainActivity.this);
        spotsDialog.show();


        Retrofit_Services services = Retrofit_Config.getURLFunc().create(Retrofit_Services.class);
        Call<VariantsDto> call = services.getSynupData();
        call.enqueue(new Callback<VariantsDto>() {
            @Override
            public void onResponse(Call<VariantsDto> call, Response<VariantsDto> response) {

                spotsDialog.dismiss();
                VariantsDto dto = response.body();
                category_adapter = new Category_Adapter(dto, MainActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView_list.setLayoutManager(linearLayoutManager);
                recyclerView_list.setAdapter(category_adapter
                );
            }

            @Override
            public void onFailure(Call<VariantsDto> call, Throwable t) {
                spotsDialog.dismiss();
                Toast.makeText(MainActivity.this, "Call failed", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
