package acceptance.helper;

import acceptance.helper.datatypes.HelperProduct;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTypesMapper {
  @DataTableType
  public HelperProduct authorEntryTransformer(Map<String, String> entry) {
    return new HelperProduct(
            entry.get("Item"),
            parseInt(entry.get("Price")),
            parseInt(entry.get("Quantity")),
            parseInt(entry.get("Instock")));
  }

  private int parseInt(String n){
    return Integer.parseInt(n);
  }
}
