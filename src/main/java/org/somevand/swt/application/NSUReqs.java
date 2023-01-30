package org.somevand.swt.application;

import org.somevand.swt.model.Showing;

import java.sql.SQLException;
import java.util.List;

public interface NSUReqs {
    List<Showing> forwardNSUBrowse() throws SQLException;
}
