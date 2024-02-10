package org.example.infrastructure.httpclients.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "InventoryClient", url = "${external-services.inventory}")
public interface InventoryClient {
  @GetMapping("/inventory/products/{id}")
  ProductInfo getProductDetails(@PathVariable String id);
}
