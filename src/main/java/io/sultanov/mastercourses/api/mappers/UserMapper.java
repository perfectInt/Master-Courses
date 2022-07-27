package io.sultanov.mastercourses.api.mappers;

import io.sultanov.mastercourses.api.views.UserView;
import io.sultanov.mastercourses.domain.users.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserView toView(User user) {
        UserView userView = new UserView();
        userView.setName(user.getName());
        userView.setEmail(user.getEmail());
        return userView;
    }
}
