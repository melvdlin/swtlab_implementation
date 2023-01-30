package org.somevand.swt.adapters;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.somevand.swt.model.CustomerAccount;
import org.somevand.swt.model.Hall;
import org.somevand.swt.model.Showing;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowingAdapterTest {
    private static class SQLStatements {
        private static final String createHalls = """
            CREATE TABLE halls (
              hallNumber INTEGER NOT NULL AUTO_INCREMENT,
              `rows` INTEGER NOT NULL,
              `columns` INTEGER NOT NULL,
              PRIMARY KEY (`hallNumber`)
            ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
            """;

        private static final String createShowings = """
            CREATE TABLE showings (
              id INTEGER NOT NULL AUTO_INCREMENT,
              title VARCHAR(255) NOT NULL,
              startDateTime INTEGER NOT NULL,
              duration INTEGER NOT NULL,
              hallNumber INTEGER NOT NULL,
              isArchived BIT(1) NOT NULL DEFAULT b'0',
              PRIMARY KEY (`id`),
              KEY `hallNumber_idx` (`hallNumber`),
              CONSTRAINT `fk_hallNumber` FOREIGN KEY (`hallNumber`) REFERENCES `halls` (`hallNumber`)
            ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
            """;

        private static final String createCustomerAccounts = """
            CREATE TABLE `customeraccounts` (
              `email` VARCHAR(255) NOT NULL,
              `password` VARCHAR(255) NOT NULL,
              PRIMARY KEY (`email`)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

            """;

        public static final String dropTables = "DROP TABLE IF EXISTS customeraccounts, halls, showings";
        public static final String insertCustomerAccount = "INSERT INTO customeraccounts (email, password) VALUES (?, ?)";
        public static final String insertHall = "INSERT INTO halls (hallNumber, `rows`, `columns`) VALUES (?, ?, ?)";
        public static final String insertShowing = "INSERT INTO showings (id, title, startDateTime, duration, hallNumber, isArchived) VALUES (?, ?, ?, ?, ?, ?)";
    }

    private static final String dbUrl = "jdbc:mysql://localhost:3306/swt_test";
    private static final String user = "swt_operator";
    private static final String password = "";

    private final CustomerAccount customerAccount = new CustomerAccount("abc@def.gh", "1234");
    private final Hall hall = new Hall(3, 5, 10);
    private final Showing nonArchivedShowing = new Showing(
            11,
            "abc",
            1675100752,
            7200,
            hall.hallNumber(),
            false
    );
    private final Showing archivedShowing = new Showing(
            13,
            "def",
            1672100752,
            10800,
            hall.hallNumber(),
            true
    );

    private ConnectionHelper beforeConnectionHelper;

    private ShowingAdapter showingAdapter;

    @BeforeEach
    void setUp() {
        beforeConnectionHelper = ConnectionHelper.getInstance();
        ConnectionHelper.setInstance(new ConnectionHelper(dbUrl, user, password));
        try(var con = ConnectionHelper.getInstance().getConnection();
            var dropStmt = con.prepareStatement(SQLStatements.dropTables);
            var createHallsStmt = con.prepareStatement(SQLStatements.createHalls);
            var createShowingsStmt = con.prepareStatement(SQLStatements.createShowings);
            var createCustomerAccountsStmt = con.prepareStatement(SQLStatements.createCustomerAccounts);
            var insertCustomerAccountStmt = con.prepareStatement(SQLStatements.insertCustomerAccount);
            var insertHallStmt = con.prepareStatement(SQLStatements.insertHall);
            var insertShowingStmt = con.prepareStatement(SQLStatements.insertShowing)) {

            dropStmt.executeUpdate();
            createHallsStmt.executeUpdate();
            createShowingsStmt.executeUpdate();
            createCustomerAccountsStmt.executeUpdate();

            insertCustomerAccountStmt.setString(1, customerAccount.email());
            insertCustomerAccountStmt.setString(2, customerAccount.password());
            insertCustomerAccountStmt.executeUpdate();

            insertHallStmt.setInt(1, hall.hallNumber());
            insertHallStmt.setInt(2, hall.rows());
            insertHallStmt.setInt(3, hall.columns());
            insertHallStmt.executeUpdate();

            insertShowingStmt.setInt(1, nonArchivedShowing.id());
            insertShowingStmt.setString(2, nonArchivedShowing.title());
            insertShowingStmt.setInt(3, nonArchivedShowing.startDateTime());
            insertShowingStmt.setInt(4, nonArchivedShowing.duration());
            insertShowingStmt.setInt(5, nonArchivedShowing.hallNumber());
            insertShowingStmt.setBoolean(6, nonArchivedShowing.isArchived());
            insertShowingStmt.executeUpdate();


            insertShowingStmt.setInt(1, archivedShowing.id());
            insertShowingStmt.setString(2, archivedShowing.title());
            insertShowingStmt.setInt(3, archivedShowing.startDateTime());
            insertShowingStmt.setInt(4, archivedShowing.duration());
            insertShowingStmt.setInt(5, archivedShowing.hallNumber());
            insertShowingStmt.setBoolean(6, archivedShowing.isArchived());
            insertShowingStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        showingAdapter = ShowingAdapter.getInstance();
    }

    @AfterEach
    void tearDown() {
        try(var con = ConnectionHelper.getInstance().getConnection();

            var dropStmt = con.prepareStatement(SQLStatements.dropTables)) {
            dropStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConnectionHelper.setInstance(beforeConnectionHelper);
    }

    @Test
    void getNonArchivedShowings() {
        try {
            List<Showing> showings = showingAdapter.getNonArchivedShowings();
            assertTrue(showings.stream().anyMatch( showing ->
                    showing.id() == nonArchivedShowing.id() &&
                    showing.title().equals(nonArchivedShowing.title()) &&
                    showing.startDateTime() == nonArchivedShowing.startDateTime() &&
                    showing.duration() == nonArchivedShowing.duration() &&
                    showing.hallNumber() == nonArchivedShowing.hallNumber()
            ));
            assertFalse(showings.stream().anyMatch( showing -> showing.id() == archivedShowing.id()));
        } catch (SQLException e) {
            fail();
        }
    }
}