package trial;

import com.chargebee.Environment;

/**
 * @author cb-prasanna
 */
public class Runner {
    public static void main(String[] args) {
        String configJSON = "/Users/cb-prasanna/intelliJ_Projects/Antlr_Test/src/trials/config.json";
        String systemConfigJSON = "/Users/cb-prasanna/intelliJ_Projects/Antlr_Test/src/trials/systemConfig.json";
//        JSONObject config = (JSONObject) new JSONParser().parse(new FileReader(configJSON));
//        JSONObject systemConfig = (JSONObject) new JSONParser().parse(new FileReader(systemConfigJSON));

//        Factory.createSource(config, systemConfig);

        System.setProperty("com.chargebee.api.protocol", "http");
        System.setProperty("com.chargebee.api.domain.suffix", "localcb.in:8080");

        Environment.configure("mannar-test","test___dev__DYg3J6z0RwcdXbzpxa3smzNDqjQswCVts");

        ChargebeeAPIIterator customer = new ChargebeeAPIIterator("customer");
        int i = 0;
        while ( customer.hasNext() ){
            System.out.println(i++);
            SyncSourceEntity next = customer.next();
            System.out.println(next.getString("email"));
            System.out.println(next.getString("billing_address.line1"));
        }

    }
}
