package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();

    private AtomicInteger idCount = new AtomicInteger(1);

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(idCount.get(), new Candidate(idCount.getAndIncrement(), "Junior Java Job", "Кандидат:Младший Java разработчик", LocalDate.now()));
        candidates.put(idCount.get(), new Candidate(idCount.getAndIncrement(), "Middle Java Job", "Кандидат:Java разработчик", LocalDate.now()));
        candidates.put(idCount.get(), new Candidate(idCount.getAndIncrement(), "Senior Java Job", "Кандидат:Старший Java разработчик", LocalDate.now()));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate cand) {
        cand.setId(idCount.getAndIncrement());
        candidates.put(cand.getId(), cand);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate cand) {
        candidates.replace(cand.getId(), cand);
    }
}