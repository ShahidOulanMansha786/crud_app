package com.crudapp.service;

import com.crudapp.entity.Task;
import com.crudapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //create
    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    //read - all
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    // read- by id
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    //update
    public Optional<Task> updateTask(Long id, Task updatedTask){
        return taskRepository.findById(id).map(existingTask -> {
           existingTask.setTitle(updatedTask.getTitle());
           existingTask.setDescription(updatedTask.getDescription());
           existingTask.setStatus(updatedTask.getStatus());
           return taskRepository.save(existingTask);
        });
    }

    //delete
    public boolean deleteTaskById(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
