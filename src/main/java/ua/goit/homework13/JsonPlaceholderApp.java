package ua.goit.homework13;

import ua.goit.homework13.dto.*;

import java.util.List;

import static ua.goit.homework13.Utils.generateUser;

public class JsonPlaceholderApp {
    public static void main(String[] args) {
        JsonPlaceholderService jsonPlaceholderClient = new JsonPlaceholderClient();
        //1. Create user
        User createdUser = jsonPlaceholderClient.createUser(generateUser());
        System.out.println("Creating user. createdUser = " + createdUser);
        //2. Get a user with id=2
        User userById2 = jsonPlaceholderClient.getUserById(2);
        System.out.println("Getting user by id=2. userById = " + userById2);
        //3. Update user with id=2 with createdUser
        createdUser.setId(2);
        User updatedUser = jsonPlaceholderClient.updateUser(createdUser);
        System.out.println("Updating of user with id=2. updatedUser = " + updatedUser);
        //4. Delete user with id=2 and print response HTTP status
        int responseStatus = jsonPlaceholderClient.deleteUser(userById2.getId());
        System.out.println("Deleting of user with id = 2. ResponseStatus = " + responseStatus);
        //5. Get a list of all users and print its size
        List<User> allUsers = jsonPlaceholderClient.getAllUsers();
        System.out.println("allUsers.size = " + allUsers.size());
        //6. Get first user by userName=Leopoldo_Corkery
        List<User> usersByUserName = jsonPlaceholderClient.getUsersByName("Leopoldo_Corkery");
        System.out.println("Getting users by userName. usersByUserName = " + usersByUserName);
        //7. Get comments to the post with max-id for the user with id=2 and write them to the file
        String comments = jsonPlaceholderClient.writeAndGetLastCommentsForUser(userById2.getId());
        System.out.println("Last comments = " + comments);
        //8. Get active tasks for user with id=2
        List<Task> activeTasks = jsonPlaceholderClient.getActiveTasks(userById2.getId());
        System.out.println("activeTasks = " + activeTasks);
    }

}
