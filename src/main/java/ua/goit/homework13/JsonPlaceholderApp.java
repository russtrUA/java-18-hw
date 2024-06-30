package ua.goit.homework13;

import ua.goit.homework13.dto.*;

import java.util.List;

public class JsonPlaceholderApp {
    public static void main(String[] args) {
        JsonPlaceholderService jsonPlaceholderClient = new JsonPlaceholderClient();
        //1. Create user
        User createdUser = jsonPlaceholderClient.createUser(generateUser()).orElseThrow();
        System.out.println("Creating user. createdUser = " + createdUser);
        //2. Get a user with id=2
        User userById = jsonPlaceholderClient.getUserById(2).orElseThrow();
        System.out.println("Getting user by id=2. userById = " + userById);
        //3. Update user with id=2 that changes name
        userById.setName("Ivan Sirko");
        User updatedUser = jsonPlaceholderClient.updateUser(userById).orElseThrow();
        System.out.println("Updating of user with id=2. updatedUser = " + updatedUser);
        //4. Delete user with id=2 and print response HTTP status
        int responseStatus = jsonPlaceholderClient.deleteUser(userById);
        System.out.println("Deleting of user with id = 2. ResponseStatus = " + responseStatus);
        //5. Get a list of all users and print its size
        List<User> allUsers = jsonPlaceholderClient.getAllUsers();
        System.out.println("allUsers.size = " + allUsers.size());
        //6. Get first user by userName=Leopoldo_Corkery
        User userByUserName = jsonPlaceholderClient.getUserByUserName("Leopoldo_Corkery").orElseThrow();
        System.out.println("Getting user by userName. userByUserName = " + userByUserName);
        //7. Get comments to the post with max-id for the user with id=2 and write them to the file
        jsonPlaceholderClient.printCommentsAndWriteToFile(userById);
        //8. Get active tasks for user with id=2
        List<Task> activeTasks = jsonPlaceholderClient.getActiveTasks(userById);
        System.out.println("activeTasks = " + activeTasks);
    }

    private static User generateUser() {
        return User.builder()
                .name("Mykola Kryvonis")
                .username("m.kryv")
                .email("m.kryv@gmail.com")
                .address(Address.builder()
                        .city("Kyiv")
                        .street("Peremogy avenue")
                        .suite("Apt. 36")
                        .zipcode("04210")
                        .geo(new GeoLocation("50.4501", "30.5234"))
                        .build())
                .phone("+3801112345678")
                .website("google.com")
                .company(Company.builder()
                        .name("Ukraine-IKEA")
                        .catchPhrase("User-centric fault-tolerant solution")
                        .bs("revolutionize end-to-end systems")
                        .build())
                .build();
    }
}
