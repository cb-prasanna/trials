package trials;

import com.chargebee.Environment;
import com.chargebee.org.json.JSONObject;
import trials.config.IntegrationConfig;
import trials.integ.chargebee.ChargebeeAPIIterator;
import trials.sync.SourceEntityTypes;
import trials.sync.SyncSourceEntity;

/**
 * @author cb-prasanna
 */
public class Runner {
    public static void main(String[] args) throws Exception{
//        String configJSON = "./config.json";
//        String systemConfigJSON = "./systemConfig.json";
//        JSONObject config = (JSONObject) new JSONParser().parse(new FileReader(configJSON));
//        JSONObject systemConfig = (JSONObject) new JSONParser().parse(new FileReader(systemConfigJSON));

//        Factory.createSource(config, systemConfig);

//        System.setProperty("com.chargebee.api.protocol", "http");
//        System.setProperty("com.chargebee.api.domain.suffix", "localcb.in:8080");
//
//        Environment.configure("mannar-test","test___dev__DYg3J6z0RwcdXbzpxa3smzNDqjQswCVts");

		CBIntegrationFactory.getSource(new JSONObject(), new JSONObject(), IntegrationConfig.Integration.HUBSPOT,
				SourceEntityTypes.Customer);
//        String configJSON = "/Users/cb-prasanna/intelliJ_Projects/Antlr_Test/src/trials/config.json";
//        String systemConfigJSON = "/Users/cb-prasanna/intelliJ_Projects/Antlr_Test/src/trials/systemConfig.json";
//        JSONObject config = (JSONObject) new JSONParser().parse(new FileReader(configJSON));
//        JSONObject systemConfig = (JSONObject) new JSONParser().parse(new FileReader(systemConfigJSON));
//        SyncSource syncSource = CBIntegrationFactory.getSource(config,systemConfig);
//        SyncDestination syncDestination = CBIntegrationFactory.getDestination(config,systemConfig, Integrations.HUBSPOT);
//        MapperInterface mapper = CBIntegrationFactory.getMapper(config, MappingTypes.DEFAULT);
//        Matcher matcher = CBIntegrationFactory.getMatcher(config, MatcherTypes.DEFAULT);
//
//        execute();

    }
}
