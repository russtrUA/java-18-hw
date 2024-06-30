package ua.goit.homework13;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.goit.homework13.dto.Post;
import ua.goit.homework13.dto.Task;
import ua.goit.homework13.dto.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

public class JsonPlaceholderClient implements JsonPlaceholderService {
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().build();
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final Gson JSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Optional<User> getUserById(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(USERS_URL + "/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return Optional.of(JSON.fromJson(body, User.class));
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Optional<User> createOrUpdateUser(User user, String method) {
        try {
            String url = method.equals("PUT") ? (USERS_URL + "/" + user.getId()) : USERS_URL;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .method(method, HttpRequest.BodyPublishers.ofString(JSON.toJson(user)))
                    .header("Content-type", "application/json; charset=UTF-8")
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return Optional.of(JSON.fromJson(body, User.class));
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> createUser(User user) {
        return createOrUpdateUser(user, "POST");
    }

    @Override
    public Optional<User> updateUser(User user) {
        return createOrUpdateUser(user, "PUT");
    }

    @Override
    public int deleteUser(User user) {
        int responseCode = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(USERS_URL + "/" + user.getId()))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(USERS_URL))
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
    public Optional<User> getUserByUserName(String userName) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(USERS_URL + "?username=" + userName))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            Type listType = new TypeToken<List<User>>() {
            }.getType();
            List<User> list = JSON.fromJson(body, listType);
            return Optional.of(list.getFirst());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private List<Post> getUserPosts(User user) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(USERS_URL + "/" + user.getId() + "/posts"))
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

    private Optional<String> getCommentsByPostId(int postId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(POSTS_URL + "/" + postId + "/comments"))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return Optional.of(body);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void printCommentsAndWriteToFile(User user) {
        List<Post> userPosts = getUserPosts(user);
        Integer maxPostId = userPosts.stream()
                .map(Post::getId)
                .max(Integer::compareTo)
                .orElseThrow();
        String commentsByPostId = getCommentsByPostId(maxPostId).orElseThrow();
        String filePath = "./files/hw13_task2/user-" + user.getId() + "-post-" + maxPostId + "-comments.json";
        writeToFile(commentsByPostId, filePath);
        System.out.println("commentsByPostId = " + commentsByPostId);
    }

    @Override
    public List<Task> getActiveTasks(User user) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(USERS_URL + "/" + user.getId() + "/todos"))
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

    private void writeToFile(String commentsByPostId, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(commentsByPostId);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
