/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idid.model;

import idid.controller.iDidController;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This iDidModel object should be the only iDidModel object
 * It holds all the settings from the config file when the program is loaded
 * These settings are usually changed by the user in the configurateion class.
 *
 * @author Max.Franks
 */
public class iDidModel {

    private int id;
    private String action;
    private String timestamp;

    public iDidModel() {
    }

    public iDidModel(String action) {
        this.setAction(action);
        this.setTimestamp();
    }

    public iDidModel(int id, String action, String time) {
        setID(id);
        setAction(action);
        setTimestamp(time);
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        
        this.timestamp = DateFormat.getDateTimeInstance().format(new Date());
//        new Timestamp(System.currentTimeMillis()).toString();
    }

    public void setTimestamp(String time) {
        this.timestamp = time;
    }

    /*
     * returns the connection to the config database
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + new File(".\\iDid.db").toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(iDidModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*
     * returns actions from sqlite database
     */
    public ArrayList<iDidModel> getAll() {
        ArrayList<iDidModel> actions = new ArrayList<iDidModel>();
        try {
            ResultSet rs = query("select * from actions order by id DESC");
            while (rs.next()) {
                actions.add(new iDidModel(rs.getInt(1), rs.getString("action"), rs.getString("created_at")));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(iDidModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actions;
    }

    /*
     * returns last saved configuration from sqlite database
     */
    public iDidModel getLast() {
        try {
            ResultSet rs = query("select * from actions order by id DESC limit 1 ");
            while (rs.next()) {
                setAction(rs.getString("action"));
                setTimestamp(rs.getString("created_at"));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(iDidModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    public ResultSet query(String sql) {
        try {
            return getConnection().createStatement().executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(iDidController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void save() {
        this.setID((getActionCount() + 1));
        this.setTimestamp();
        try {

            Connection conn = getConnection();
            PreparedStatement prep = conn.prepareStatement("insert into actions values ("
                    + this.getID() + ",?,?)");
            prep.setString(1, getAction());
            prep.setString(2, getTimestamp());
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(iDidController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create() {

        Connection conn = null;
        Statement stat = null;
        try {
            conn = getConnection();
            stat = conn.createStatement();
            stat.executeUpdate("create table if not exists actions (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "action TEXT, created_at TEXT);");
            conn.close();
            this.setAction("Start using iDid");
            this.setTimestamp();
            save();
        } catch (SQLException ex) {
            Logger.getLogger(iDidController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getActionCount() {
        int length = 0;
        ResultSet rs = query("select * from actions");
        try {
            while (rs.next()) {
                length++;
            }
            return length;
        } catch (SQLException ex) {
            Logger.getLogger(iDidController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
