package com.example.apiretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apiretrofit.apiclient.EmployeeAPI;
import com.example.apiretrofit.model.Employee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployeeActivity extends AppCompatActivity {
    private final static String BASEURL = "http://dummy.restapiexample.com/api/v1/";
    private TextView tvData;
    private EditText etEmpNo;
    private Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        etEmpNo = findViewById(R.id.etEmpID);
        tvData = findViewById(R.id.tvData1);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    loadData();
            }

            private void loadData(){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

                Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNo.getText().toString()));

                listCall.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                                       String content = "";

                        content +="ID: "+ response.body().getId() + "\n";
                        content +="Employee Name: "+response.body().getEmployee_name() + "\n";
                        content +="Employee Age: "+response.body().getEmployee_age() + "\n";
                        content +="Employee Salary: "+response.body().getEmployee_salary() + "\n";

                        tvData.append(content);
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {

                        Toast.makeText(SearchEmployeeActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}
