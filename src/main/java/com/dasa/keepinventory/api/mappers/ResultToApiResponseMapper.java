package com.dasa.keepinventory.api.mappers;

import com.dasa.keepinventory.api.dto.response.ApiResponse;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;

@Component
public class ResultToApiResponseMapper {
    
    public <T> ApiResponse<T> map(Result<T> result) {
        if (result.isSuccess()) {
            return ApiResponse.success(result.getData());
        } else {
            return ApiResponse.error(result.getError());
        }
    }
}
