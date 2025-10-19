package com.dasa.keepinventory.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private T data;
    private String error;
    private boolean isSuccess;

    private ApiResponse(T data, String error, boolean isSuccess) {
        this.data = data;
        this.error = error;
        this.isSuccess = isSuccess;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, null, true);
    }

    public static <T> ApiResponse<T> error(String error) {
        return new ApiResponse<>(null, error, false);
    }

    // Getters
    public T getData() { return data; }
    public String getError() { return error; }
    public boolean isSuccess() { return isSuccess; }
}
