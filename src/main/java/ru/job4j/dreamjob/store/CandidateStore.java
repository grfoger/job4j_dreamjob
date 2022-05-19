package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class CandidateStore {


    private final AtomicInteger idCount = new AtomicInteger(1);

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(idCount.get(), new Candidate(idCount.getAndIncrement(), "Junior Java Job", "Кандидат:Младший Java разработчик", LocalDate.now(), null));
        candidates.put(idCount.get(), new Candidate(idCount.getAndIncrement(), "Middle Java Job", "Кандидат:Java разработчик", LocalDate.now(), null));
        candidates.put(idCount.get(), new Candidate(idCount.getAndIncrement(), "Senior Java Job", "Кандидат:Старший Java разработчик", LocalDate.now(), null));
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate cand) {
        cand.setId(idCount.getAndIncrement());
        cand.setCreated(LocalDate.now());
        candidates.put(cand.getId(), cand);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate cand) {
        candidates.replace(cand.getId(), cand);
    }
}