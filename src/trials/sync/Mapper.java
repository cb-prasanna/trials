package trials.sync;

public interface Mapper {

    public enum Type {
        DEFAULT
    }

    SyncDestinationEntity map(SyncSourceEntity src, SyncDestinationEntity dest) throws Exception;

}
