package org.example.common;

import org.example.presentation.rest.dto.ApiError;

public class ServiceException extends RuntimeException {
  private final ApiError apiError;

  public ServiceException(ApiError apiError) {
    super(apiError.getMessage());
    this.apiError = apiError;
  }

  public ApiError getApiError() {
    return apiError;
  }

}
