package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private AtomicInteger idCount = new AtomicInteger(1);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(idCount.getAndIncrement(), new Post(1, "Junior Java Job", "Вакансия:Младший Java разработчик", LocalDate.now()));
        posts.put(idCount.getAndIncrement(), new Post(2, "Middle Java Job", "Вакансия:Java разработчик", LocalDate.now()));
        posts.put(idCount.getAndIncrement(), new Post(3, "Senior Java Job", "Вакансия:Старший Java разработчик", LocalDate.now()));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        posts.put(idCount.getAndIncrement(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.replace(post.getId(), post);
    }
}