package org.example.infrastructure.httpclients.authorization;

import org.example.application.gateways.AuthGateway;
import org.example.common.ServiceException;
import org.example.presentation.rest.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthAdapter implements AuthGateway {
  private final AuthorizationClient authorizationClient;

  public AuthAdapter(AuthorizationClient authorizationClient) {
    this.authorizationClient = authorizationClient;
  }

  @Override
  public void authorizeUser(String authorization) {
    ResponseEntity<String> authorizationResponse = authorizationClient.authorizeUser(authorization);

    if (authorizationResponse.getStatusCode() != HttpStatus.ACCEPTED) {
      ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, 99, authorizationResponse.getBody());
      throw new ServiceException(apiError);
    }
  }

}

