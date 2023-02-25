package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.User;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.rowmapper.UserRowMapper;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUser(final String username) {
        return jdbcTemplate.queryForObject(
                "select u.user_name user_name, u.user_pass user_pass, user_email, ur.user_role user_role from my_user u, my_user_role ur where u.user_name = ? and u.user_name = ur.user_name",
                new String[]{username}, new UserRowMapper());
    }

    public void saveUser(final User u) {
        String sql = "INSERT INTO my_user VALUES ('"
                + u.getUsername() + "','"
                + u.getPassword() + "','"
                + u.getEmail() + "',0)";   // 0 == not enabled
        jdbcTemplate.execute(sql);
        jdbcTemplate.execute("INSERT INTO my_user_role VALUES ('" + u.getUsername() + "','" + u.getRole() + "')");
        System.out.println("UserDao - saved\n" + sql + "\n");
    }

    public List<String> getUsernameList() {
        return jdbcTemplate.queryForList("select user_name FROM my_user", String.class);
    }

}
