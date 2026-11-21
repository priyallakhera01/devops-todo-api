package com.example.todo;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {

    private final ConcurrentMap<Long, Todo> todos = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Todo> findAll() {
        return todos.values().stream()
                .sorted(Comparator.comparingLong(Todo::id))
                .toList();
    }

    public Todo create(String title) {
        long id = nextId.getAndIncrement();
        Todo todo = new Todo(id, title.trim());
        todos.put(id, todo);
        return todo;
    }
}
