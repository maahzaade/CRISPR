package ca.mcgill.db;

import ca.mcgill.crispr.modeling.Assay;
import ca.mcgill.crispr.modeling.OffTarget;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Mahdiye on 4/10/2017.
 */
public class DBModification {


    Connection connection;
    ResultSet resultSet;
    Statement statement;

    private void initialize() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/bioinformatics", "root",
                    "Mn@12345678");
            statement = connection.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Assay> loadAssay() {
        initialize();
        ArrayList<Assay> assays = new ArrayList<Assay>();
        int i = 0;


        try {
            resultSet = statement.executeQuery("select * from cleavages order by genome");
            OffTarget offTarget;
            Assay assay = new Assay();
            ArrayList<OffTarget> offTargets = new ArrayList<OffTarget>();

            String oldGuideRna = "";
            String newGuideRna;
            while (resultSet.next()) {
                newGuideRna = resultSet.getString(2);
                //it is useful just for the first time
                assay.setGuideSeq(newGuideRna);
                if (!oldGuideRna.isEmpty() && !oldGuideRna.equals(newGuideRna)) {
                    i++;
                    assay.setGuideSeq(oldGuideRna);
                    assay.setOffTargets(offTargets);
                    assays.add(Handler.preprocessAssay(assay));
                    assay = new Assay(newGuideRna);
                    offTargets = new ArrayList<OffTarget>();
                }
                offTarget = new OffTarget();
                offTarget.setName(resultSet.getString(1));
                offTarget.setOftSeq(resultSet.getString(3));
                offTarget.setDiffLogo(resultSet.getString(4));
                offTarget.setMismatchNumber(resultSet.getInt(5));
                offTarget.setId(resultSet.getInt(6));
                offTargets.add(offTarget);
                oldGuideRna = newGuideRna;

            }
            assay.setGuideSeq(oldGuideRna);
            assay.setOffTargets(offTargets);
            assays.add(Handler.preprocessAssay(assay));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finalizeQuery();
        System.out.println("---------------" + i);
        return assays;
    }


    private void finalizeQuery() {

        try {
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
