package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.DButil;
import org.csu.mypetstore.persistence.SequenceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDAOImpl implements SequenceDAO {
    private static final String GET_SEQUENCE = "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";
    private static final String UPDATE_SEQUENCE = "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence result = null;
        try {
            Connection connection = DButil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SEQUENCE);
            preparedStatement.setString(1, sequence.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                result = new Sequence();
                result.setName(resultSet.getString(1));
                result.setNextId(resultSet.getInt(2));
            }
            DButil.closeResultSet(resultSet);
            DButil.closePreparedStatement(preparedStatement);
            DButil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try {
            Connection connection = DButil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SEQUENCE);
            preparedStatement.setString(1, String.valueOf(sequence.getNextId()));
            preparedStatement.setString(2, sequence.getName());
            preparedStatement.executeUpdate();
            DButil.closePreparedStatement(preparedStatement);
            DButil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
