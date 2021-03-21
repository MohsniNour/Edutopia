/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;
import Entities.Commentt;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public interface IComment {
    public String getId(Commentt comment);
    public ObservableList<Commentt> getCommentsByIdForum(String id_forum);
    public ObservableList<Commentt> getComment();
    public void add(Commentt comment);
    public void remove(String id);
    public void activate(String id);
    public void update(String id, Commentt new_comment);
    public void addLike(String id, int like);
    public void addDisLike(String id, int disLike);
    public Commentt details(String id);
    public List<Commentt> listAvailable();
    public List<Commentt> listArchived();
    public String display(List<Commentt> cmts);
    public int countComment(String id_forum);
    
}
