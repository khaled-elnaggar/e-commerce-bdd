package org.example.presentation.rest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
  List<RequestedProduct> products = new ArrayList<>();
}
