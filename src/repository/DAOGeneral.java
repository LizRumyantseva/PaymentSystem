package repository;

public interface DAOGeneral<T,S> {
    void add(T obj);
    void delete(S i);
}
