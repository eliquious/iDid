/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idid.controller;

import java.io.File;
import idid.model.iDidModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Max.Franks
 */
public class iDidController {

    public iDidModel model = new iDidModel();
//    private String database_name = "settings.db";
    private final File config_dir = new File(".");

    public iDidController() {
        initialize();
        model = getCurrentSettings();
    }

    /*
     * Verifies database existance
     */
    private boolean does_database_exist() {
        if (!config_dir.exists()) {
            config_dir.mkdir();
        }
        return (new File(config_dir.getPath() + "\\idid.db")).exists();
    }

    private boolean do_tables_exist() {
        try {
            ResultSet rs = model.query("select name from sqlite_master where type='table';");
            outer:
            while (rs.next()) {
                if (rs.getString(0).matches("settings")) {
                    break outer;
                }
            }
            rs.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(iDidController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /*
     * Creates the config database
     */
    public void initialize() {
        if (!does_database_exist()) {
            model.create();
        }
    }

    /*
     * returns last saved configuration from sqlite database
     */
    public iDidModel getCurrentSettings() {
        return model.getLast();
    }

    public void save() {
        model.save();
    }

    public void create(String action){
        model.setAction(action);
        model.save();
    }

    /*
     * returns actions from sqlite database
     */
    public ArrayList<iDidModel> getAll() {
        return model.getAll();
    }

    /*
     * returns actions from sqlite database
     */
    public iDidModel getLast() {
        return model.getLast();
    }
}
