package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CandidateDbStoreTest {

    @BeforeEach
    public void before() {

    }

    @Test
    public void whenAddCandidate() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Java Developer", "программист");
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
    }

    @Test
    public void whenFindAll() {
    }

    @Test
    public void whenFindById() {
    }
}
