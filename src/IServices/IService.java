package IServices;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    void Ajouter(T t) throws SQLException;
    void Supprimer(int t) throws SQLException;
    void Modifier(T t , int id) throws SQLException;
    List<T> Affichertout() throws SQLException;
}

