package trials.config;

import com.chargebee.org.json.JSONException;
import com.chargebee.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import trials.sync.DestinationEntityTypes;
import trials.sync.SourceEntityTypes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author cb-prasanna
 */
public class IntegrationConfig {

    @SerializedName("sync_items")
    private List<SyncItem> syncItems;

    @SerializedName("integration")
    private Integration integration;


    public static void main() throws IOException, JSONException {
        Gson GSON = new Gson();
        String localDir = System.getProperty("user.dir");
        String configJSON = localDir + "/src/trials/integConfig.json";
//        String systemConfigJSON = "/Users/cb-prasanna/github/trials/src/trial/systemConfig.json";
        JSONObject config = getJSONFromFile(Paths.get(configJSON));
//        JSONObject sysConfig = getJSONFromFile(Paths.get(systemConfigJSON));
        IntegrationConfig integrationConfig = GSON.fromJson(config.toString(),
                IntegrationConfig.class);
        System.out.println(integrationConfig);
    }


    static public JSONObject getJSONFromFile(Path path) throws IOException, JSONException {
        return new JSONObject(
                new String(Files.readAllBytes(path))
        );
    }

    public Integration getIntegration() {
        return integration;
    }

    public List<SyncItem> getSyncItems() {
        return syncItems;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    boolean validate() {
        //TODO : Config JSON Validation
//        this.getSyncItems().forEach(
//                syncItem -> {
//                    syncItem.getMappings().forEach(
//                            syncItemMapping -> {
//                                syncItemMapping.
//                            }
//                    );
//                }
//        );
        return true;
    }

    public enum DataType {
        @SerializedName("string")
        STRING,

        @SerializedName("integer")
        INTEGER,

        @SerializedName("double")
        DOUBLE,

        @SerializedName("boolean")
        BOOLEAN
    }

    public enum Integration {
        @SerializedName("hubspot")
        HUBSPOT
    }

    public class SyncItem {
        @SerializedName("mappings")
        private List<Mapping> mappings;

        @SerializedName("sync_source")
        private SourceEntityTypes syncSource;

        @SerializedName("sync_destination")
        private DestinationEntityTypes syncDestination;

        public List<Mapping> getMappings() {
            return mappings;
        }

        public SourceEntityTypes getSyncSource() {
            return syncSource;
        }

        public DestinationEntityTypes getSyncDestination() {
            return syncDestination;
        }
    }

    public class Mapping {
        @SerializedName("source")
        MappingEntity source;

        @SerializedName("destination")
        MappingEntity destination;

        public MappingEntity getSource() {
            return source;
        }

        public MappingEntity getDestination() {
            return destination;
        }
    }

    public class MappingEntity {
        @SerializedName("name")
        String name;

        @SerializedName("type")
        DataType type;

        public String getName() {
            return name;
        }

        public DataType getType() {
            return type;
        }
    }
}
