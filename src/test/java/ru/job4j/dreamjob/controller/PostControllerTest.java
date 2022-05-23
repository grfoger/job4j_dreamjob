package ru.job4j.dreamjob.controller;


import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;
import ru.job4j.dreamjob.model.City;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class PostControllerTest {
    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post", new City(0, "Город")),
                new Post(2, "New post", new City(0, "Город"))
        );
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostControl postControl = new PostControl(
                postService,
                cityService
        );
        String page = postControl.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenAdd() {
        Post input = new Post(1, "New post", new City(0, "Город"));
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postControl = new PostControl(
                postService,
                cityService
        );
        String page = postControl.addPost(input);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }


    @Test
    public void whenUpdate() {
        Post input = new Post(2, "New post", new City(0, "Город"));
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postControl = new PostControl(
                postService,
                cityService
        );
        String page = postControl.updatePost(input);
        verify(postService).update(input);
        assertThat(page, is("redirect:/posts"));
    }
}