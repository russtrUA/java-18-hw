package ua.goit.homework13;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.goit.homework13.dto.Post;
import ua.goit.homework13.dto.Task;
import ua.goit.homework13.dto.User;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static ua.goit.homework13.Constants.*;
import static ua.goit.homework13.Utils.writeToFile;


public class JsonPlaceholderClient implements JsonPlaceholderService {
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().build();

    private static final Gson JSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public User getUserById(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + USERS + DELIMITER + id))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return JSON.fromJson(body, User.class);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private List<User> getUsers(String... userNames) {
        try {
            String url = userNames.length == 0 ? BASE_URL + USERS
                    : BASE_URL + USERS + QUESTION_MARK + USER_NAME + EQUALS_SIGN + userNames[0];
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            Type listType = new TypeToken<List<User>>() {
            }.getType();
            return JSON.fromJson(body, listType);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }
    @Override
    public List<User> getUsersByName(String userName) {
        return getUsers(userName);
    }

    @Override
    public List<User> getAllUsers() {
        return getUsers();
    }

    @Override
    public User createUser(User user) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + USERS))
                    .POST(HttpRequest.BodyPublishers.ofString(JSON.toJson(user)))
                    .header("Content-type", "application/json; charset=UTF-8")
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return JSON.fromJson(body, User.class);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + USERS + DELIMITER + user.getId()))
                    .PUT(HttpRequest.BodyPublishers.ofString(JSON.toJson(user)))
                    .header("Content-type", "application/json; charset=UTF-8")
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return JSON.fromJson(body, User.class);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteUser(int userId) {
        int responseCode = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + USERS + DELIMITER + userId))
                    .DELETE()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    private List<Post> getUserPosts(int userId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + USERS + DELIMITER + userId + DELIMITER + POSTS))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            Type listType = new TypeToken<List<Post>>() {
            }.getType();
            return JSON.fromJson(body, listType);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private String getCommentsByPostId(int postId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + POSTS + DELIMITER + postId + DELIMITER + COMMENTS))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return body;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String writeAndGetLastCommentsForUser(int userId) {
        List<Post> userPosts = getUserPosts(userId);
        Integer maxPostId = userPosts.stream()
                .map(Post::getId)
                .max(Integer::compareTo)
                .orElseThrow();
        String commentsByPostId = getCommentsByPostId(maxPostId);
        String filePath = FILE_TEMPLATE.replace("{USER_ID}", String.valueOf(userId))
                .replace("{POST_ID}", String.valueOf(maxPostId));
        writeToFile(commentsByPostId, filePath);
        return commentsByPostId;
    }

    @Override
    public List<Task> getActiveTasks(int userId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + USERS + DELIMITER + userId + DELIMITER + TODOS))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            Type listType = new TypeToken<List<Task>>() {
            }.getType();
            List<Task> allTasks = JSON.fromJson(body, listType);
            return allTasks.stream()
                    .filter(task -> !task.isCompleted())
                    .toList();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
