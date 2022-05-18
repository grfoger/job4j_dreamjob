package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class PostStore {


    private final AtomicInteger idCount = new AtomicInteger(1);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(idCount.get(), new Post(idCount.getAndIncrement(), "Junior Java Job", "Вакансия:Младший Java разработчик", LocalDate.now(), new City(0, "-")));
        posts.put(idCount.get(), new Post(idCount.getAndIncrement(), "Middle Java Job", "Вакансия:Java разработчик", LocalDate.now(), new City(0, "-")));
        posts.put(idCount.get(), new Post(idCount.getAndIncrement(), "Senior Java Job", "Вакансия:Старший Java разработчик", LocalDate.now(), new City(0, "-")));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {

        post.setId(idCount.getAndIncrement());
        post.setCreated(LocalDate.now());
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.replace(post.getId(), post);
    }
}