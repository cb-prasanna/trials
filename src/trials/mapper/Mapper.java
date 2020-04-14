package trials.mapper;

import trials.syncDestination.SyncDestinationEntity;
import trials.model.entity.SyncSourceEntity;

public interface Mapper {
    SyncDestinationEntity map(SyncSourceEntity src, SyncDestinationEntity dest) throws MapperException;

}
