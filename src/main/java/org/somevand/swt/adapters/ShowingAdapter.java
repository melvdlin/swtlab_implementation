package org.somevand.swt.adapters;

import org.somevand.swt.model.Showing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum ShowingAdapter implements IShowing {
    INSTANCE;

    private static class SQLStrings {
        public static final String queryS = "SELECT * FROM swt.showings WHERE id = ?;";
        public static final String querySAll = "SELECT * FROM swt.showings";
        public static final String querySAllNA = "SELECT * FROM swt.showings WHERE isArchived = FALSE;";
        public static final String updateS = "UPDATE swt.showings SET isArchived = TRUE WHERE startDateTime <= UNIX_TIMESTAMP()";
        public static final String idCol = "id";
        public static final String titleCol = "title";
        public static final String startDateTimeCol = "startDateTime";
        public static final String durationCol = "duration";
        public static final String hallNumberCool = "hallNumber";
        public static final String isArchivedCol = "isArchived";
    }

    @Override
    public Optional<Showing> getShowing(int showingID) throws SQLException {

        ConnectionHelper.getLock().readLock().lock();
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStrings.queryS)
        ) {
            statement.setInt(1, showingID);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return Optional.of(
                        new Showing(
                                showingID,
                                res.getString(SQLStrings.titleCol),
                                res.getInt(SQLStrings.startDateTimeCol),
                                res.getInt(SQLStrings.durationCol),
                                res.getInt(SQLStrings.hallNumberCool),
                                res.getBoolean(SQLStrings.isArchivedCol)));
            }
            return Optional.empty();
        } finally {
            ConnectionHelper.getLock().readLock().unlock();
        }
    }

    @Override
    public List<Showing> getShowings() throws SQLException {

        ConnectionHelper.getLock().readLock().lock();
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStrings.querySAll)
        ) {
            ResultSet res = statement.executeQuery();
            List<Showing> showings = new ArrayList<>();
            while (res.next()) {
                showings.add(
                        new Showing(
                                res.getInt(SQLStrings.idCol),
                                res.getString(SQLStrings.titleCol),
                                res.getInt(SQLStrings.startDateTimeCol),
                                res.getInt(SQLStrings.durationCol),
                                res.getInt(SQLStrings.hallNumberCool),
                                res.getBoolean(SQLStrings.isArchivedCol)));
            }
            return showings;
        } finally {
            ConnectionHelper.getLock().readLock().unlock();
        }
    }

    @Override
    public List<Showing> getNonArchivedShowings() throws SQLException {

        ConnectionHelper.getLock().readLock().lock();
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStrings.querySAllNA)
        ) {
            ResultSet res = statement.executeQuery();
            List<Showing> showings = new ArrayList<>();
            while (res.next()) {
                showings.add(
                        new Showing(
                                res.getInt(SQLStrings.idCol),
                                res.getString(SQLStrings.titleCol),
                                res.getInt(SQLStrings.startDateTimeCol),
                                res.getInt(SQLStrings.durationCol),
                                res.getInt(SQLStrings.hallNumberCool),
                                res.getBoolean(SQLStrings.isArchivedCol)));
            }
            return showings;
        } finally {
            ConnectionHelper.getLock().readLock().unlock();
        }
    }

    @Override
    public void archive() throws SQLException {

        ConnectionHelper.getLock().readLock().lock();
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStrings.updateS)
        ) {
            statement.executeUpdate();
        } finally {
            ConnectionHelper.getLock().readLock().unlock();
        }
    }
}
