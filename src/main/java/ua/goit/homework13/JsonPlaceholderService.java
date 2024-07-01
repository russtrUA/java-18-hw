package ua.goit.homework13;

import ua.goit.homework13.dto.Task;
import ua.goit.homework13.dto.User;

import java.util.List;

public interface JsonPlaceholderService {
    User getUserById(int id);
    List<User> getUsersByName(String userName);
    User createUser(User user);
    User updateUser(User user);
    int deleteUser(int userId);
    List<User> getAllUsers();
    String writeAndGetLastCommentsForUser(int userId);
    List<Task> getActiveTasks(int userId);
}
