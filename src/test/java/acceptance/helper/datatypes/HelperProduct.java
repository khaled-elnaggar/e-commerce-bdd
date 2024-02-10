package acceptance.helper.datatypes;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HelperProduct {
  String id;
  private int price;
  private int quantity;
  private int inStock;
}
