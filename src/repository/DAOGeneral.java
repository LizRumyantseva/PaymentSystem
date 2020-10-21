package repository;

import java.util.List;

public interface DAOGeneral<T,S> {
    void add(T obj);
    void delete(S i);
    List<T> getAll();
}
