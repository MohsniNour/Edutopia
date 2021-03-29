package Services;

import Entities.Exam;
import Entities.LigneExamen;
import IServices.IService;
import Utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Exam_Service implements IService<Exam>{
    Connection c;
    ObservableList<Exam> oL = FXCollections.observableArrayList();
    ArrayList<Exam> la = new ArrayList();

    public Exam_Service() {
        c = DataBaseConnection.getInstance().getConnection();

    }       
    @Override
    public void Ajouter(Exam t) throws SQLException {
       try {
            String requete = "INSERT INTO `exam`( `type`, `start_date`, `finish_date`, `id_subject`, `created_by`, `created_Date`, `archived_Date`) VALUES (?,?,?,?,?,?,null)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, t.getType());
            pst.setDate(2, t.getStart_date());
            pst.setDate(3, t.getFinish_date());
            pst.setInt(4, t.getId_subject());
            pst.setString(5, "amine");
            pst.setDate(6,new java.sql.Date(new Date().getTime()));
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      
    }

     public void AjouterNoteExamen(float note, int iduser,int idexam)  {
       try {
            String requete = "INSERT INTO ligne_exam(iduser,idexam,note) VALUES (?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, iduser);
            pst.setInt(2, idexam);
            pst.setFloat(3, note);
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      
    }
    
    
    @Override
    public void Supprimer(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modifier(Exam t, int id) throws SQLException {
 
        PreparedStatement ps;
        String query = "UPDATE `exam` SET `type`=?,`start_date`=?,`finish_date`=? WHERE `id_Exam`=?";
        ps = c.prepareStatement(query);
        ps.setString(1, t.getType());
        ps.setDate(2, t.getStart_date());
        ps.setDate(3, t.getFinish_date());
        ps.setInt(4, id);
        ps.execute();
    }
    
    public void Archiver( int id) throws SQLException {
 
        PreparedStatement ps;
        String query = "UPDATE `exam` SET `archived_Date`=? WHERE `id_Exam`=?";
        ps = c.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(new Date().getTime()));
        ps.setInt(2, id);
        ps.execute();
    }
    
    public ObservableList<Integer> get_List_exam (){
    
        ObservableList<Integer> list = FXCollections.observableArrayList();
        String requete = "select * from exam ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id_Exam"));         
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 
    }
    
    
    
      public int getIdExamenByTypeSubject (String type,String subject,java.sql.Date startdate){
    
        ObservableList<String> list = FXCollections.observableArrayList();
        String requete = "select id_Exam from exam where type='"+type +"' and id_subject='"+subject+"' and start_date='"+startdate+"'";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id_Exam");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return 0; 
    }
    
    public ObservableList<Exam> Affichertout() throws SQLException {
       ObservableList<Exam> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `exam` ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {  
                list.add(new Exam(rs.getInt("id_Exam"), rs.getString("type"), rs.getDate("start_date"),rs.getDate("finish_date"),rs.getInt("id_subject"), rs.getString("created_by"), rs.getDate("created_Date"),rs.getDate("archived_Date")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;   
    }
    
        public List<Exam> Affichertous()  {
       List<Exam> list = new ArrayList<Exam>();
        String requete = "SELECT e.id_Exam,e.type,e.start_date,e.finish_date,e.created_by,e.archived_Date,e.created_Date,sub.id_subject FROM exam e inner join subject sub on sub.id=e.id_subject ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {  
                Exam ee=new Exam(rs.getInt("id_Exam"), rs.getString("type"), rs.getDate("start_date"),rs.getDate("finish_date"),0, rs.getString("created_by"), rs.getDate("created_Date"),rs.getDate("archived_Date"));
                ee.setStartdate(rs.getDate("start_date"));
                ee.setSubject(rs.getString("id_subject"));
                list.add(ee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;   
    }
    

    public ObservableList<Exam> AfficherListExamMatiere(int id_subject) throws SQLException {
        ObservableList<Exam> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `exam` where id_Subject=? ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ps.setInt(1, id_subject);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Exam(rs.getInt("id_Exam"), rs.getString("type"), rs.getDate("start_date"),rs.getDate("finish_date"),rs.getInt("id_subject"), rs.getString("created_by"), rs.getDate("created_Date"),rs.getDate("archived_Date")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list; 
    }

    public ObservableList<String> get_List_exam_Concat (){
    
        ObservableList<String> list = FXCollections.observableArrayList();
        String requete = "select * from exam ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String concattype_subject=rs.getString("type")+"--"+rs.getString("id_subject")+"--"+rs.getDate("start_date").toString();
                list.add(concattype_subject);         
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 
    }
    
     public ObservableList<Exam> serach(String cas) throws SQLException {
        ObservableList<Exam> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `exam` where type LIKE '%" + cas + "%'or start_date LIKE '%" + cas + "%' or  finish_date LIKE '%" + cas + "%' or  created_Date LIKE '%" + cas + "%' or  created_by LIKE '%" + cas + "%' or  archived_Date LIKE '%" + cas+ "%' ";  
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               list.add(new Exam(rs.getInt("id_Exam"), rs.getString("type"), rs.getDate("start_date"),rs.getDate("finish_date"),rs.getInt("id_subject"), rs.getString("created_by"), rs.getDate("created_Date"),rs.getDate("archived_Date")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
     
   public List<LigneExamen> getExamByUser(int idUser)  {
       List<LigneExamen> list = new ArrayList<>();
        String requete = "SELECT e.id_Exam,e.start_date,e.type,sub.id_subject,le.note FROM exam e inner join ligne_exam le on e.id_Exam=le.idexam inner join subject sub on sub.id=e.id_subject  where le.iduser="+idUser;
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {  
            int id_Exam= rs.getInt(1);
            Date startdate=rs.getDate(2);
            String type=rs.getString(3);
            String sujet=rs.getString(4);
            float note=rs.getFloat(5);
            
            Exam ex=new Exam(id_Exam, type, startdate, sujet);
            
            LigneExamen ln=new LigneExamen();
            ln.setNote(note);
            ln.setExam(ex);
            list.add(ln);
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;   
    }
   
   public boolean VerfierPassageExamen(int idExam) {
      
       
        try {
            Statement ste = c.createStatement();
            String requete = "";
       
                requete = "select idexam from ligne_exam where idexam="+idExam;
            
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
              return true;
            
            }
       
        } catch (SQLException ex) {
            System.out.println("probleme d'affichage");
        }
        return false;
    }
   
   
   public List<Stat> afficherStatPassagExamen(int idsubject) {
      
        List<Stat> arr = new ArrayList<>();
        try {
            Statement ste = c.createStatement();
            String requete = "";
       
                requete = "select concat(type,' ',id_subject) as examen,COUNT(idligne) from ligne_exam le inner join exam e on le.idexam=e.id_Exam where id_subject="+idsubject+"  group BY examen";
            
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                
                int number = rs.getInt(2);
               String group=rs.getString(1);
               Stat stat=new Stat(group, number);
               arr.add(stat);
                
                
            
            }
           
            
            
        } catch (SQLException ex) {
            System.out.println("probleme d'affichage");
        }
        return arr;
    }

   
   public List<Stat> afficherStatExamenNote(int idexam) {
      int superieur=0;
      int inferieur=0;
        List<Stat> arr = new ArrayList<>();
        try {
            Statement ste = c.createStatement();
            String requete = "";
       
                requete = "select note from ligne_exam l inner join exam e on l.idexam=e.id_Exam where id_subject="+idexam;
            
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                
                float number = rs.getFloat(1);
               
                if(number>=10){
                    superieur++;
                }else{
                    inferieur++;
                }
                
            
            }
            if(superieur>0){
                arr.add(new Stat(">=10",superieur));
            }
            if(inferieur>0){
                arr.add(new Stat("<10",inferieur));
            }
            
            
        } catch (SQLException ex) {
            System.out.println("probleme d'affichage");
        }
        return arr;
    }

    public class Stat {

        private String group;
        private int nombre;

        public Stat(String group, int nombre) {
            this.group = group;
            this.nombre = nombre;
        }

        public Stat(){
            
        }
        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public int getNombre() {
            return nombre;
        }

        public void setNombre(int nombre) {
            this.nombre = nombre;
        }

    }
   
    
}
