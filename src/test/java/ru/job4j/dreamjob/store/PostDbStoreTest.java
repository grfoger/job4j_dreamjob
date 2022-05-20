package ru.job4j.dreamjob.store;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDbStoreTest {


    @Test
    public void whenAddPost() {
        CityService cityService = new CityService();
        PostDbStore store = new PostDbStore(new Main().loadPool(), cityService);
        Post post = new Post(0, "Java Job", cityService.findById(1));
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        CityService cityService = new CityService();
        PostDbStore store = new PostDbStore(new Main().loadPool(), cityService);
        Post post = new Post(0, "Java Job", cityService.findById(1));
        store.add(post);
        Post postNew = new Post(post.getId(), "Java New Job", cityService.findById(1));
        store.update(postNew);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(postNew.getName()));
    }

    @Test
    public void whenFindAll() {
        CityService cityService = new CityService();
        PostDbStore store = new PostDbStore(new Main().loadPool(), cityService);
        store.baseClean();
        Post post1 = new Post(0, "Java Job", cityService.findById(1));
        Post post2 = new Post(0, "Another Java Job", cityService.findById(1));
        store.add(post1);
        store.add(post2);
        List<Post> list = store.findAll();
        assertThat(list, is(List.of(post1, post2)));
    }

    @Test
    public void whenFindById() {
        CityService cityService = new CityService();
        PostDbStore store = new PostDbStore(new Main().loadPool(), cityService);
        store.baseClean();
        Post post = new Post(0, "Java Job", cityService.findById(1));
        store.add(post);
        assertThat(store.findById(post.getId()), is(post));
    }
}
