package com.example.appretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    private ListView lvTodos;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        lvTodos = (ListView)findViewById(R.id.lvTodos);
        loadToDos();


    }
    protected void loadToDos(){
        Retrofit retrofit = API.getRetrofitClient();
        TodoAPI api = retrofit.create(TodoAPI.class);

        Call<List<Todo>> listCall = api.getAllTodos();


        listCall.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                TodoList list = new TodoList(activity,response.body());
                lvTodos.setAdapter(list);
                lvTodos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        Intent intent = new Intent(MainActivity.this,DetalleActivity.class);
                        Bundle bundle=new Bundle();
                       bundle.putString("id", String.valueOf( response.body().get(i).getId()));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Algo salió mal"
                        ,Toast.LENGTH_SHORT).show();
                Log.d("RetrofitError",t.toString());
            }
        });
    }

}