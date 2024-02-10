package acceptance.helper;

import io.cucumber.java.DataTableType;
import org.example.infrastructure.httpclients.inventory.ProductInfo;
import org.example.presentation.rest.dto.RequestedProduct;

import java.util.Map;

public class DataTypesMapper {
  @DataTableType
  public RequestedProduct toReturnedProduct(Map<String, String> entry) {
    return new RequestedProduct(
            entry.get("Item"),
            parseInt(entry.get("Quantity")));
  }

  @DataTableType
  public ProductInfo toRequestedProduct(Map<String, String> entry) {
    return new ProductInfo(
            entry.get("Item"),
            parseInt(entry.get("Price")),
            parseInt(entry.get("Quantity")));
  }

  private int parseInt(String n) {
    return Integer.parseInt(n);
  }
}
