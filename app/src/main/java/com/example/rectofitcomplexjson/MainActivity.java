package com.example.rectofitcomplexjson;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    private static final String TAG = "MainActivity";

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = ApiClient.getClient();

        textView = findViewById(R.id.textViewId);

        apiInterface = retrofit.create(ApiInterface.class);

        //getAllTodos();

        //postTodos();

        getALlEmployees();
    }

    private void getALlEmployees() {
        Call<Employees> employeesCall = apiInterface.getAllEmployee("http://dummy.restapiexample.com/api/v1/employees");

        employeesCall.enqueue(new Callback<Employees>() {
            @Override
            public void onResponse(Call<Employees> call, Response<Employees> response) {
                Log.i(TAG, "onResponse: "+response.code());

                Employees employees = response.body();

                List<Data> employeeData = employees.getData();

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("List Size : "+employeeData.size()+"\n");
                stringBuilder.append(response.code()+"\n");
                stringBuilder.append(employees.getStatus()+"\n"+"\n"+"\n");



                for (Data data : employeeData){
                    stringBuilder.append("Id: "+data.getId()+"\n");
                    stringBuilder.append("Employee name: "+data.getEmployee_name()+"\n");
                    stringBuilder.append("Employee age: "+data.getEmployee_age()+"\n");
                    stringBuilder.append("Employee salary: "+data.getEmployee_salary()+"\n\n");
                }

                textView.setText(stringBuilder.toString());

            }

            @Override
            public void onFailure(Call<Employees> call, Throwable t) {
                Log.i(TAG, "Error: "+t.getMessage());
                textView.setText(t.getMessage());
            }
        });
    }


    private void postTodos() {
        Todo inputTodo = new Todo(29, "Junaed", true);

        Call<Todo> postTodoCall = apiInterface.postTodo(inputTodo);

        postTodoCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Todo todo = response.body();
                Log.i(TAG, "onResponse: " + todo.toString());

            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {

            }
        });
    }

    public void getAllTodos() {
        Call<List<Todo>> allTodosCall = apiInterface.getTodos();

        allTodosCall.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                for (Todo todo : response.body()) {
                    Log.i(TAG, "Id: " + todo.getId());
                    Log.i(TAG, "userId: " + todo.getUserId());
                    Log.i(TAG, "title: " + todo.getTitle());
                    Log.i(TAG, "completed: " + todo.getCompleted());
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {

            }
        });
    }


}
