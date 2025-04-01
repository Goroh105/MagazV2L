package dao;

import java.util.List;
import exception.DAOException;

public interface RepositoryDAO<T> {
    // Добавление сущности в таблицу базы данных
    // возвращает ID добавленного должности
    Long insert(T o) throws DAOException;

    // Редактирование cущности
    void update(T o) throws DAOException;

    // Удаление сущности
    void delete(Long Id) throws DAOException;

    // Поиск сущности по Id
    T findById(Long Id) throws DAOException;

    // Получение списка сущностей
    List<T> findAll() throws DAOException;
}