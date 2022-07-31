package io.sultanov.mastercourses.api.mappers;

import io.sultanov.mastercourses.api.views.UserView;
import io.sultanov.mastercourses.domain.users.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserView toView(User user) {
        UserView userView = new UserView();
        userView.setEmail(user.getEmail());
        userView.setName(user.getName());
        return userView;
    }

    public List<UserView> toViews(List<User> users) {
        return users.stream().map(this::toView).toList();
    }
}
