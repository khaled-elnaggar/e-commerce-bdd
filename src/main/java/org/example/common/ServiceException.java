package org.example.common;

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
