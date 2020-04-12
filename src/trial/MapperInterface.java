package trails;

public interface MapperInterface {
    SyncDestinationEntity map(SyncSourceEntity src, SyncDestinationEntity dest) throws MapperException;

}
