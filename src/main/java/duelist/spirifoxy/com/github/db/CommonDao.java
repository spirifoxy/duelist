package duelist.spirifoxy.com.github.db;

import java.util.List;

public interface CommonDao<T> {

    T getById(int id);

    void insert(T object);

    void update(T object);

    List<T> getAll();
}
