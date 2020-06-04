package web.com.utils;

/**
 * Created by Wan Yu on 2020/3/17
 */
public class DaoFactory {
    private static final DaoFactory factory = new DaoFactory();
    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return factory;
    }

    public <T> T createDao(String className, Class<T> myclass){
        try {
            T t = (T) Class.forName(className).newInstance();
            return t;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
}
