package ApiTests.KP0001;

import Business.Setup;
import Common.CommonVar;
import Common.ResourceFns;
import Common.RestUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleTest {
    @Before
    public void setUp() throws Exception {
        // Code setup here
        Setup.login();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() throws Exception {
        Map<String, Object> params = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();
        List<Map<String, Object>> purchase_units = new ArrayList<>();

        items.add(ResourceFns.baseMap(
                new String[]{"name", "description", "quantity", "unit_amount"},
                new Object[]{"T-Shirt", "Green XL", "1", ResourceFns.baseMap(new String[]{"currency_code", "value"}, new Object[]{"USD", "100.00"})}
        ));

        Map<String, Object> amount = ResourceFns.baseMap(new String[]{"currency_code", "value", "breakdown"}, new Object[]{"USD", "100.00", ResourceFns.baseMap(new String[]{"item_total"}, new Object[]{ResourceFns.baseMap(new String[]{"currency_code", "value"}, new Object[]{"USD", "100.00"})})});
        purchase_units.add(ResourceFns.baseMap(new String[]{"amount", "items"}, new Object[]{amount, items}));

        params.put("purchase_units", purchase_units);
        params.put("intent", "CAPTURE");
        params.put("application_context", ResourceFns.baseMap(new String[]{"return_url", "cancel_url"}, new Object[]{"https://example.com/return", "https://example.com/cancel"}));
        RestAssured.baseURI = CommonVar.BASE_URI;

        JsonPath jp = RestUtil.doPost("/v2/checkout/orders", params, 201);
        jp.prettyPrint();

        List<Map<String, Object>> purchase_unitsResult = jp.get("purchase_units");
        for (int i = 0; i < purchase_unitsResult.size(); i++) {
            Map<String, Object> itemResult = purchase_unitsResult.get(i);
            Map<String, Object> amountResult = (Map<String, Object>) itemResult.get("amount");

            // Kiểm tra giá trị amount
            Assert.assertEquals("USD", amountResult.get("currency_code"));
            Assert.assertEquals("100.00", amountResult.get("value"));

            // Kiểm tra breakdown -> item_total
            Map<String, Object> breakdown = (Map<String, Object>) amountResult.get("breakdown");
            Map<String, Object> itemTotal = (Map<String, Object>) breakdown.get("item_total");
            Assert.assertEquals("USD", itemTotal.get("currency_code"));
            Assert.assertEquals("100.00", itemTotal.get("value"));

            // Kiểm tra items
            List<Map<String, Object>> itemsResult = (List<Map<String, Object>>) itemResult.get("items");
            Map<String, Object> firstItem = itemsResult.get(0);
            Assert.assertEquals("T-Shirt", firstItem.get("name"));
            Assert.assertEquals("Green XL", firstItem.get("description"));
            Assert.assertEquals("1", firstItem.get("quantity"));

            // Kiểm tra unit_amount của item
            Map<String, Object> unitAmount = (Map<String, Object>) firstItem.get("unit_amount");
            Assert.assertEquals("100.00", unitAmount.get("value"));
            Assert.assertEquals("USD", unitAmount.get("currency_code"));
        }
        Assert.assertEquals("CREATED", jp.get("status"));
        Assert.assertEquals("CAPTURE", jp.get("intent"));
    }

    @After
    public void tearDown() throws Exception {
        RestAssured.reset();
    }
}
