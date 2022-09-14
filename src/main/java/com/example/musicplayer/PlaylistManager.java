package com.example.musicplayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlaylistManager {
    DBWorker worker;
    int maxAmountOfBands = 2;
    private ArrayList<String> bands;
    private static final String GET_BANDS = "SELECT * FROM bands ";

    public PlaylistManager(DBWorker worker) {
        this.worker = worker;
    }


    public String[] getPathFromBD() {
        String[] result = new String[maxAmountOfBands];
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        conn = worker.getConnection();
        if (conn != null) {
            try {
                st = conn.createStatement();

                for (int i = 0; i <= maxAmountOfBands; i++) {
                    rs = st.executeQuery(GET_BANDS);
                    while (rs.next()) {
                        result[i] = rs.getString("Directory");
                        i++;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("DBWorker returned null connection");
        }
        return result;
    }

    public String[] getBandFromBD() {
        String[] result = new String[maxAmountOfBands];
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        conn = worker.getConnection();
        if (conn != null) {
            try {
                st = conn.createStatement();

                for (int i = 0; i < maxAmountOfBands; i++) {
                    rs = st.executeQuery(GET_BANDS);
                    while (rs.next()) {
                        result[i] = rs.getString("BandName");
                       i++;
                    }
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("DBWorker returned null connection");
        }
        return null;
    }
}
