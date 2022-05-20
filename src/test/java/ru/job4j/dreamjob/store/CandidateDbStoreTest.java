package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CandidateDbStoreTest {


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
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Java Developer", "программист");
        store.add(candidate);
        Candidate candidateNew = new Candidate(candidate.getId(), "Middle Java Developer", "программист");
        store.update(candidateNew);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidateNew.getName()));
    }

    @Test
    public void whenFindAll() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        store.baseClean();
        Candidate candidate = new Candidate(0, "Java Developer", "программист");
        Candidate candidateNew = new Candidate(0, "Middle Java Developer", "программист");
        store.add(candidate);
        store.add(candidateNew);
        assertThat(store.findAll(), is(List.of(candidate, candidateNew)));
    }

    @Test
    public void whenFindById() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        store.baseClean();
        Candidate candidate = new Candidate(0, "Java Developer", "программист");
        store.add(candidate);
        assertThat(store.findById(candidate.getId()), is(candidate));
    }
}
