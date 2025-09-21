package ru.yandex.javacourse.manager;

import ru.yandex.javacourse.tasks.Epic;
import ru.yandex.javacourse.tasks.Subtask;
import ru.yandex.javacourse.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private ArrayList<Task> historyTasks = new ArrayList<>();
    private final Integer countHistoryTasks = 10;

    @Override
    public void add(Task task) {
        Task newTask;

        if(historyTasks.size() == countHistoryTasks) {
            Integer index = 0;
            historyTasks.remove(historyTasks.get(index));
        }

        if(task instanceof Subtask) {
            Subtask newSubtask = new Subtask(task.getName(), task.getDescription());
            newSubtask.setStatus(task.getStatus());
            newSubtask.setEpicId(((Subtask) task).getEpicId());
            newSubtask.setId(task.getId());

            newTask = newSubtask;
        } else if(task instanceof Epic) {
            Epic newEpic = new Epic(task.getName(), task.getDescription());
            newEpic.setStatus(task.getStatus());
            newEpic.setId(task.getId());

            newTask = newEpic;
        } else {
            newTask = new Task(task.getName(), task.getDescription());
            newTask.setStatus(task.getStatus());
            newTask.setId(task.getId());
        }

        historyTasks.add(newTask);
    }

    // Получить историю
    public List<Task> getHistory() {
        return historyTasks;
    }
}
