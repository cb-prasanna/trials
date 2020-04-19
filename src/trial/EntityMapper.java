package trial;

import com.chargebee.org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class EntityMapper implements Mapper {
    private static final Logger log = LoggerFactory.getLogger(EntityMapper.class);

    private final List<IntegrationConfig.Mapping> mappings;

    public EntityMapper(IntegrationConfig integrationConfig, SystemConfig systemConfig) throws JSONException {
        this.mappings = integrationConfig.getSyncItems().get(0).getMappings();
    }

    public SyncDestinationEntity map(SyncSourceEntity sourceEntity, SyncDestinationEntity destinationEntity) throws Exception {
        for (IntegrationConfig.Mapping mapping : mappings) {
            String sourceName = mapping.getSource().getName();
            String destinationName = mapping.getDestination().getName();
            IntegrationConfig.DataType sourceType = mapping.getSource().getType();
            IntegrationConfig.DataType destinationType = mapping.getSource().getType();
            switch (sourceType) {
                case STRING:
                    String srcString = sourceEntity.getString(sourceName);
                    switch (destinationType) {
                        case STRING:
                            destinationEntity.setString(destinationName, srcString);
                            break;
                        default:
                            throw new MapperException("Unsupported mapping:" + destinationType);
                    }
                    break;
                case INTEGER:
                    int srcInt = sourceEntity.getInt(sourceName);
                    switch (destinationType) {
                        case INTEGER:
                            destinationEntity.setInt(destinationName, srcInt);
                            break;
                        case STRING:
                            destinationEntity.setString(destinationName, Integer.toString(srcInt));
                            break;
                        case DOUBLE:
                            destinationEntity.setDouble(destinationName, (double) srcInt);
                        default:
                            throw new MapperException("Unsupported mapping:" + destinationType);
                    }
                    break;
                case DOUBLE:
                    double srcDouble = sourceEntity.getDouble(sourceName);
                    switch (destinationType) {
                        case STRING:
                            destinationEntity.setString(destinationName, Double.toString(srcDouble));
                            break;
                        case DOUBLE:
                            destinationEntity.setDouble(destinationName, srcDouble);
                        default:
                            throw new MapperException("Unsupported mapping:" + destinationType);
                    }
                    break;
                case BOOLEAN:
                    boolean srcBoolean = sourceEntity.getBoolean(sourceName);
                    switch (destinationType) {
                        case BOOLEAN:
                            destinationEntity.setBoolean(destinationName, srcBoolean);
                            break;
                        default:
                            throw new MapperException("Unsupported mapping:" + destinationType);
                    }
                    break;
            }
        }
        return destinationEntity;
    }
}
