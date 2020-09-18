package next.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import next.model.User;

public class UserDaoTest {

    @Test
    public void crud() throws Exception {
        User expected = new User("idid", "password", "name", "javajigi@email.com");
        UserDao userDao = new UserDao();
        UserDao.getInstance().insert(expected);

        User actual = UserDao.getInstance().findByUserId(expected.getUserId());
        assertEquals(expected, actual);
    }

}
