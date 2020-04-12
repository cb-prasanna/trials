package trails;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Mapper implements MapperInterface {
    JSONArray array;
    JSONObject systemConfig;
    final Logger log = LoggerFactory.getLogger(Mapper.class);

    public Mapper(JSONObject config, JSONObject systemConfig) {
        this.array = config.getJSONArray("mappings");
        this.systemConfig = systemConfig;
        validate(this.array);

    }

    private void validate(JSONArray array) {
        String[] mappings = new String[]{"src_name", "dest_name", "src_type", "dest_type"};
        for (int i = 0; i < array.length(); i++) {
            JSONObject cfg = array.getJSONObject(i);
            for (String field : mappings) {
                assert cfg.getString(field) != null;
            }
        }
    }

    @Override
    public SyncDestinationEntity map(SyncSourceEntity src, SyncDestinationEntity dest) throws MapperException {
        String src_name, dest_name;
        JSONObject src_obj, dest_obj;
        for (int i = 0; i < array.length(); i++) {
            JSONObject cfg = array.getJSONObject(i);
            map_default(cfg, src, dest);
        }
        return dest;
    }

    private void map_default(JSONObject cfg, SyncSourceEntity src, SyncDestinationEntity dest) throws MapperException {
        String src_type = cfg.getString("src_type");
        String dest_type = cfg.getString("dest_type");
        String src_name = cfg.getString("src_name");
        String dest_name = cfg.getString("dest_name");
        switch (src_type) {
            case "string": {
                String srcValue = src.getString(src_name);
                switch (dest_type) {
                    case "string":
                        dest.setString(dest_name, srcValue);
                        break;
                    default:
                        throw new MapperException("Unsupported mapping:" + dest_type);
                }
                break;
            }
            case "integer": {
                int srcValue = src.getInt(src_name);
                switch (dest_type) {
                    case "integer":
                        dest.setInt(dest_name, srcValue);
                        break;
                    case "string":
                        dest.setString(dest_name, Integer.toString(srcValue));
                        break;
                    case "double":
                        dest.setDouble(dest_name, (double) srcValue);
                    default:
                        throw new MapperException("Unsupported mapping:" + dest_type);
                }
                break;
            }
            case "double": {
                double srcValue = src.getDouble(src_name);
                switch (dest_type) {
                    case "string":
                        dest.setString(dest_name, Double.toString(srcValue));
                        break;
                    case "double":
                        dest.setDouble(dest_name, srcValue);
                    default:
                        throw new MapperException("Unsupported mapping:" + dest_type);
                }
                break;
            }
            case "boolean": {
                boolean srcValue = src.getBoolean(src_name);
                switch (dest_type) {
                    case "boolean":
                        dest.setBoolean(dest_name, srcValue);
                        break;
                    default:
                        throw new MapperException("Unsupported mapping:" + dest_type);
                }
                break;
            }
        }
    }
}
