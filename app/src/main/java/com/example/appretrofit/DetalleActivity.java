package com.example.appretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DialogTitle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetalleActivity extends AppCompatActivity {

    String idrecibe;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        idrecibe=getIntent().getStringExtra("id");
    //    keyalumno=getIntent().getStringExtra("keyalu");
        mostrar(idrecibe);

    }

    private void mostrar(String id){

        if (TextUtils.isEmpty(id)){
            Toast.makeText(DetalleActivity.this, "idvacio", Toast.LENGTH_SHORT).show();
            return;
        }
        Retrofit retrofit = API.getRetrofitClient();
        TodoAPI api = retrofit.create(TodoAPI.class);
        Call<List<Todo>> listCall = api.getTodo(id);

        listCall.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                TodoList list = new TodoList(activity,response.body());
              Log.e("lita",String.valueOf( list.getCount()));

            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"algo salrio mal we"
                        ,Toast.LENGTH_SHORT).show();
                Log.d("RetrofitError",t.toString());
            }
        });
    }
}