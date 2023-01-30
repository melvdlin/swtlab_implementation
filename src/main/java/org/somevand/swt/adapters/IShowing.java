package org.somevand.swt.adapters;

import org.somevand.swt.model.Showing;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;

public interface IShowing {
    Optional<Showing> getShowing(int showingID) throws SQLException;
    List<Showing> getShowings() throws SQLException;
    List<Showing> getNonArchivedShowings() throws SQLException;
    void archive() throws SQLException;
    ReadWriteLock getLock();
}
