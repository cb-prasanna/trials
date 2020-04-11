package trials;

public class ApiDataFetchLayer implements DataFetchLayer {

    @Override
    public SyncEntities getData(String type) {
        System.out.println(" Giving Data of Type"+type);
        return null;
    }
}
