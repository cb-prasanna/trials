package trials;

import com.chargebee.Environment;
import com.chargebee.org.json.JSONObject;
import com.google.gson.Gson;
import trials.config.IntegrationConfig;
import trials.matcher.Matcher;
import trials.sync.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author cb-prasanna
 */
public class Runner {
    public static void main(String[] args) throws Exception, HTTPPostException, AccessTokenExpired {
        Gson GSON = new Gson();
        String localDir = System.getProperty("user.dir");
        String configJSON = localDir + "/src/trials/integConfig.json";
        JSONObject config = new IntegrationConfig().getJSONFromFile(Paths.get(configJSON));
        IntegrationConfig integrationConfig = GSON.fromJson(config.toString(),
                IntegrationConfig.class);

        Iterator<SyncSourceEntity> syncSource = CBIntegrationFactory.getSource(config,
                integrationConfig.getIntegration(), integrationConfig.getSyncItems().get(0).getSyncSource());
        SyncDestination syncDestination = CBIntegrationFactory.getDestination(config, integrationConfig.getIntegration()
                , integrationConfig.getSyncItems().get(0).getSyncDestination());
        Mapper mapper =
                CBIntegrationFactory.getMapper(integrationConfig.getSyncItems().get(0),
                        new JSONObject(), Mapper.Type.DEFAULT);
        List<String> ids = new ArrayList<>();
        List<SyncSourceEntity> syncEntites = new ArrayList<SyncSourceEntity>();
        while (syncSource.hasNext()) {
            SyncSourceEntity currentItem = syncSource.next();
            ids.add(currentItem.getString("email"));
            syncEntites.add(currentItem);
        }
        Matcher matcher = CBIntegrationFactory.getMatcher(config, new JSONObject());

        Map<SyncSourceEntity, SyncDestinationEntity> match = matcher.findMatch(syncEntites, syncDestination);

//        String configJSON = "/Users/cb-prasanna/intelliJ_Projects/Antlr_Test/src/trials
//        /integConfig.json";
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
