/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.User;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Sabrina
 */
public interface IUser {

    public void addUser(User u);

    public User getUser(int id);

    public void DeleteUser(User deletedUser);

    public void UpdateUser(int id, User UpdatedUser);

    public ObservableList<User> getUsers();

    public List<User> getListUser();
}
