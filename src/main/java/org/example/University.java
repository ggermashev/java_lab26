package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class University {
    private Connection connection;
    private boolean isClosed;

    University(Connection connection) {
        this.connection = connection;
        isClosed = false;
    }

    void close() throws SQLException {
        connection.close();
    }

    LinkedList<String> getTeachersWork(String day, int room) throws SQLException {
        LinkedList<String> list = new LinkedList<String>();
        ResultSet res = connection.createStatement().executeQuery(String.format(
                        "select t.fio as fio from teachers as t " +
                        "join schedule_teachers as s_t on s_t.teacher_id = t.id and s_t.lessons_amount > 0" +
                                "join subjects as s on s.id = s_t.subject_id and s.sub_day = '%s' " +
                                "join schedule_subjects as s_s on s_s.subject_id = s.id " +
                                "join rooms as r on r.id = s_s.room_id and r.number = %d " +
                                "order by fio", day, room));

        while (res.next()) {
            list.add(res.getString("fio"));
        }
        return list;
    }

    LinkedList<String> getTeachersDontWork(String day) throws SQLException {
        LinkedList<String> list = new LinkedList<String>();
        ResultSet res = connection.createStatement().executeQuery(String.format(
                "select distinct fio from teachers except select t.fio as fio from teachers as t " +
                        "join schedule_teachers as s_t on s_t.teacher_id = t.id and s_t.lessons_amount > 0" +
                        "join subjects as s on s.id = s_t.subject_id and s.sub_day = '%s' " +
                        "order by fio", day));

        while (res.next()) {
            list.add(res.getString("fio"));
        }
        return list;
    }

    LinkedList<String> getDays(int lessons_count) throws SQLException {
        LinkedList<String> list = new LinkedList<String>();
        ResultSet res = connection.createStatement().executeQuery(String.format("with days as\n" +
                "         (select distinct sub_day, count(sub_day) over (partition by sub_day) as cnt\n" +
                "          from subjects)\n" +
                "select sub_day from days\n" +
                "where cnt = %d", lessons_count));

        while (res.next()) {
            list.add(res.getString("sub_day"));
        }
        return list;
    }

}
