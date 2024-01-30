package data;
import java.util.Collection;


public interface DataStore<T> {
    void addEntity(T entity);
    Collection<T> getAllEntities();
}


