package ua.goit.homework13;

import ua.goit.homework13.dto.Task;
import ua.goit.homework13.dto.User;

import java.util.List;
import java.util.Optional;

public interface JsonPlaceholderService {
    Optional<User> getUserById(int id);
    Optional<User> createUser(User user);
    Optional<User> updateUser(User user);
    int deleteUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserByUserName(String userName);
    void printCommentsAndWriteToFile(User user);
    List<Task> getActiveTasks(User user);
}
