package com.example.blog.repository;

import com.example.blog.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {
    private final EntityManager em;

    public void save(Todo todo) {
        em.persist(todo);
    }

    public Todo findOne(Long id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> findAll(boolean orderState) {
        List<Todo> todoList = null;
        if (orderState) {
            todoList = em.createQuery(
                    "select t from Todo t" +
                                "where t.useYn = 'Y' " +
                                "order by t.writeDate DESC", Todo.class)
                            .getResultList();
        } else {
            todoList = em.createQuery(
                    "select t from Todo t" +
                                "where t.useYn = 'Y' " +
                                "order by t.writeDate ASC", Todo.class)
                            .getResultList();
        }
         return todoList;

    }

    public int updateTodoAllClear() {
        return em.createQuery(
                "update Todo t" +
                            "set t.useYn = 'N' " +
                            "where t.useYn = 'Y'")
                        .executeUpdate();
    }
}
