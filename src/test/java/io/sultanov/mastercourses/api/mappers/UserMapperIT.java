package io.sultanov.mastercourses.api.mappers;

import io.sultanov.mastercourses.api.views.UserView;
import io.sultanov.mastercourses.domain.users.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserMapperIT {

    @Autowired
    protected UserMapper userMapper;

    @Test
    public void toViewTest() {
        User user = new User();
        user.setEmail("idinax@gmail.com");
        user.setName("Dima");
        UserView userView = userMapper.toView(user);
        assertEquals(user.getEmail(), userView.getEmail());
        assertEquals(user.getName(), userView.getName());
    }

    @Test
    public void toViewsTest() {
        User user1 = new User();
        user1.setEmail("idinax@gmail.com");
        user1.setName("Dima");

        User user2 = new User();
        user2.setEmail("suka@gmail.com");
        user2.setName("Vladion");

        List<UserView> userViewList = userMapper.toViews(List.of(user1, user2));

        assertEquals(2L, userViewList.size());

        assertEquals(user1.getName(), userViewList.get(0).getName());
        assertEquals(user1.getEmail(), userViewList.get(0).getEmail());

        assertEquals(user2.getName(), userViewList.get(1).getName());
        assertEquals(user2.getEmail(), userViewList.get(1).getEmail());
    }
}