package com.example.blog.controller;

import com.example.blog.model.Todo;
import com.example.blog.service.TodoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j

public class TodoController {

    private final TodoService todoService;

    // Todo 등록
    @PostMapping("/todos/save")
    public String createJsonTodo(@RequestBody @Valid TodoForm form, BindingResult bindingResult) {
        log.info("Post : Todo save");

        return validation(form, bindingResult);
    }

    // Todo 목록
    @GetMapping("/todos/{orderState}")
    public List<Todo> list(@PathVariable("orderState") Boolean orderState) {
        log.info("Get : Todos List");

        return todoService.findTodos(orderState);
    }

    // Todo 환료 상태 업데이트
    @PutMapping("/todos/{id}")
    public String updateTodo(
            @PathVariable("id") Long id,
            @RequestBody UpdateTodoRequest request
    ) {
        log.info("Put : Todo update");

        todoService.updateTodoCompleted(id, request.isCompleted());

        Todo findTodo = todoService.findOne(id);

        if (request.isCompleted() == findTodo.isCompleted()) {
            return "ok";
        } else {
            return "fail";
        }
    }

    // Todo 삭제(DB 업데이트)
    @PutMapping("/todos/delete/{id}")
    public String deleteTodo(
            @PathVariable("id") Long id
    ) {
        log.info("Delete : Todo Delete");

        todoService.updateTodoUseYn(id);

        Todo findTodo = todoService.findOne(id);

        if (findTodo.getUseYn().equals("N")) {
            return "ok";
        } else {
            return "fail";
        }
    }

    @PutMapping("/todos/clear")
    public String clearAllTodo() {
        log.info("Clear : Todo All Clear");

        int result = todoService.updateTodoAllClear();

        if(result > 0) {
            return "ok";
        } else {
            return "fail";
        }
    }

    // 요청 파라미터 validation 체크
    private String validation(@Valid @RequestBody TodoForm form, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "todo error";
        }

        Todo todo = new Todo();
        todo.setItem(form.getItem());
        todo.setCompleted(form.isCompleted());
        todo.setDate(form.getDate());
        todo.setTime(form.getTime());
        todo.setWriteDate(LocalDateTime.now());
        todo.setUpdateDate(LocalDateTime.now());

        todoService.save(todo);

        return "ok";
    }

    @Data
    static class UpdateTodoRequest {

        private Long id;
        @NotEmpty
        private boolean completed;
    }
}
