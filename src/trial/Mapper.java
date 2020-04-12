package trial;

public interface Mapper {
    SyncDestinationEntity map(SyncSourceEntity src, SyncDestinationEntity dest) throws MapperException;

}
