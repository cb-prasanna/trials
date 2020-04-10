package trials;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author cb-prasanna
 */
public class Runner {
    public static void main(String[] args) throws IOException, ParseException {
        String configJSON = "/Users/cb-prasanna/intelliJ_Projects/Antlr_Test/src/trials/config.json";
        String systemConfigJSON = "/Users/cb-prasanna/intelliJ_Projects/Antlr_Test/src/trials/systemConfig.json";
        JSONObject config = (JSONObject) new JSONParser().parse(new FileReader(configJSON));
        JSONObject systemConfig = (JSONObject) new JSONParser().parse(new FileReader(systemConfigJSON));

        Factory.createSource(config, systemConfig)

    }
}
