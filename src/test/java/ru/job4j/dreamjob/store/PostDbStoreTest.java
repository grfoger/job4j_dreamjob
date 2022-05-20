package ru.job4j.dreamjob.store;



import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;

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
    }

    @Test
    public void whenFindById() {
    }
}
