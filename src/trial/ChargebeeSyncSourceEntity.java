package trial;

import com.chargebee.org.json.JSONException;
import com.chargebee.org.json.JSONObject;

/**
 * @author cb-prasanna
 */
public class ChargebeeSyncSourceEntity implements SyncSourceEntity {

    private final JSONObject resourceJSON;
    private final String resource;

    public ChargebeeSyncSourceEntity(String resource, JSONObject resourceJSON) {
        this.resource = resource;
        this.resourceJSON = resourceJSON;
    }

    @Override
    public String getType() {
        return resource;
    }

    @Override
    public Integer getInt(String key) {
        String[] split = key.split("\\.");
        JSONObject resourceJSON = this.resourceJSON;
        int idx;
        for (idx = 0; idx < split.length - 1; idx++) {
            resourceJSON = resourceJSON.optJSONObject(split[idx]);
//            if ( resourceJSON == null ){
//                return null;
//            }
        }
        return resourceJSON.optInt(split[idx]);
    }

    @Override
    public String getString(String key) {
        String[] split = key.split("\\.");
        JSONObject resourceJSON = this.resourceJSON;
        int idx;
        for (idx = 0; idx < split.length - 1; idx++) {
            resourceJSON = resourceJSON.optJSONObject(split[idx]);
            if ( resourceJSON == null ){
                return null;
            }
        }
        return resourceJSON.optString(split[idx]);
    }

    @Override
    public Double getDouble(String key) {
        try {
            return resourceJSON.getDouble(key);
        } catch (JSONException ex) {
            throw new RuntimeException("",ex);
        }
    }

    @Override
    public String getResource() {
        try {
            return resourceJSON.toString(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean getBoolean(String src_name) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println("yoyo");
    }
}
