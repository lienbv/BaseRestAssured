package Business;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

class PurchaseUnit {
    @JsonProperty("items")
    private List<Item> items;

    @JsonProperty("amount")
    private Amount amount;

    // Constructors, getters and setters
}

class Item {
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("unit_amount")
    private UnitAmount unitAmount;

    // Constructors, getters and setters
}

class UnitAmount {
    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("value")
    private String value;

    // Constructors, getters and setters
}

class Amount {
    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("value")
    private String value;

    @JsonProperty("breakdown")
    private Breakdown breakdown;

    // Constructors, getters and setters
}

class Breakdown {
    @JsonProperty("item_total")
    private ItemTotal itemTotal;

    // Constructors, getters and setters
}

class ItemTotal {
    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("value")
    private String value;

    // Constructors, getters and setters
}

class ApplicationContext {
    @JsonProperty("return_url")
    private String returnUrl;

    @JsonProperty("cancel_url")
    private String cancelUrl;

    // Constructors, getters and setters
}

class PaymentRequest {
    @JsonProperty("intent")
    private String intent;

    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;

    @JsonProperty("application_context")
    private ApplicationContext applicationContext;

    // Constructors, getters and setters
}
