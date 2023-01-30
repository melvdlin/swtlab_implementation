package org.somevand.swt.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UDEKApplicationTest {

    private static class SQLStatements {
        private static final String createTables = """
            create table halls
            (
                hallNumber int auto_increment
                    primary key,
                `rows`       int not null,
                `columns`    int not null
            );

            create table showings
            (
                id            int auto_increment
                    primary key,
                title         varchar(255)     not null,
                startDateTime int              not null,
                duration      int              not null,
                hallNumber    int              not null,
                isArchived    bit default b'0' not null,
                constraint fk_hallNumber
                    foreign key (hallNumber) references halls (hallNumber)
            );

            create index hallNumber_idx
                on showings (hallNumber);

            create table customeraccounts
            (
                email    varchar(255) not null
                    primary key,
                password varchar(255) not null
            );

            """;

        public static final String dropTables = "DROP TABLE IF EXISTS customeraccounts, halls, showings";
        public static final String insertCustomerAccount = "INSERT INTO customeraccounts (email, password) VALUES (?, ?)";
        public static final String insertHall = "INSERT INTO halls (hallNumber, `rows`, `seats`) VALUES (?, ?, ?)";
        public static final String insertShowing = "INSERT INTO showings (id, title, startDateTime, duration, hallNumber, isArchived) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void forwardNSUBrowse() {
    }

    @Test
    void forwardSubmitRegistration() {
    }
}