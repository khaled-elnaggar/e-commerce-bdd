package acceptance.helper.mapper;

import io.cucumber.java.DataTableType;
import org.example.infrastructure.httpclients.inventory.ProductInfo;
import org.example.presentation.rest.dto.RequestedProduct;

import java.util.Map;

public class DataTypesMapper {
  @DataTableType
  public ProductInfo toProductInfo(Map<String, String> entry) {
    return new ProductInfo(
            entry.get("Item"),
            parseInt(entry.get("Quantity")),
            parseInt(entry.get("Price")));
  }

  @DataTableType
  public RequestedProduct toRequestedProduct(Map<String, String> entry) {
    return new RequestedProduct(
            entry.get("Item"),
            parseInt(entry.get("Quantity")));
  }

  private int parseInt(String n) {
    return Integer.parseInt(n);
  }
}
