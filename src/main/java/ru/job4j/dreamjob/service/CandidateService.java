package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

import java.util.Collection;

public class CandidateService {

    private static final CandidateService INST = new CandidateService();

    private final CandidateStore store = CandidateStore.instOf();

    public static CandidateService instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public void add(Candidate cand) {
        store.add(cand);
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public void update(Candidate cand) {
        store.update(cand);
    }

}
