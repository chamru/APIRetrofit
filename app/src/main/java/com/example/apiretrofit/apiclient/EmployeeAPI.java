package com.example.apiretrofit.apiclient;

import com.example.apiretrofit.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeAPI {
    @GET("employees")
    Call<List<Employee>>getEmployee();
}
