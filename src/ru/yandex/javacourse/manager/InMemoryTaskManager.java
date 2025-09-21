package ru.yandex.javacourse.manager;

import ru.yandex.javacourse.tasks.Epic;
import ru.yandex.javacourse.tasks.Subtask;
import ru.yandex.javacourse.tasks.Task;
import ru.yandex.javacourse.tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    // Обновить статус эпика
    @Override
    public void updateStatusEpic(Integer epicId) {
        boolean isSubtasksStatusNew = false;
        boolean isSubtasksStatusInProgress = false;
        boolean isSubtasksStatusDone = false;

        for (Subtask subtask : subtasks.values()) {
            if(subtask.getEpicId() == epicId)
            switch (subtask.getStatus()) {
                case TaskStatus.NEW:
                    isSubtasksStatusNew = true;
                    break;
                case TaskStatus.IN_PROGRESS:
                    isSubtasksStatusInProgress = true;
                    break;
                case TaskStatus.DONE:
                    isSubtasksStatusDone = true;
                    break;
            }
        }

        if (isSubtasksStatusInProgress == false && isSubtasksStatusDone == false) {
            getEpicById(epicId).setStatus(TaskStatus.NEW);
        } else if (isSubtasksStatusNew == false && isSubtasksStatusInProgress == false && isSubtasksStatusDone == true) {
            getEpicById(epicId).setStatus(TaskStatus.DONE);
        } else {
            getEpicById(epicId).setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    // Получение списка всех подзадач.
    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    // Получение списка всех задач.
    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    // Получение списка всех эпиков.
    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    // Удаление всех задач.
    @Override
    public void clearTasks() {
        tasks.clear();
    }

    // Удаление всех эпиков.
    @Override
    public void clearEpics() {
        epics.clear();
    }

    // Удаление всех подзадач.
    @Override
    public void clearSubtasks() {
        subtasks.clear();

        for(Epic epic : epics.values()) {
            updateStatusEpic(epic.getId());
        }
    }

    // Получение подзадачи по идентификатору.
    @Override
    public Subtask getSubtaskById(Integer subtaskId) {
        Subtask subtask = subtasks.get(subtaskId);
        Managers.getDefaultHistory().add(subtask);

        return subtask;
    }

    // Создание подзадачи.
    @Override
    public void addSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);

        updateStatusEpic(subtask.getEpicId());
    }

    // Обновление подзадачи.
    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);

        updateStatusEpic(subtask.getEpicId());
    }

    // Удаление подзадачи по идентификатору.
    @Override
    public void removeSubtask(Integer subtaskId) {
        Integer epicId = getSubtaskById(subtaskId).getEpicId();
        subtasks.remove(subtaskId);

        updateStatusEpic(epicId);
    }

    // Получение задачи по идентификатору.
    @Override
    public Task getTaskById(Integer taskId) {
        Task task = tasks.get(taskId);
        Managers.getDefaultHistory().add(task);

        return task;
    }

    // Получение эпика по идентификатору.
    @Override
    public Epic getEpicById(Integer epicId) {
        Epic epic = epics.get(epicId);
        Managers.getDefaultHistory().add(epic);

        return epic;
    }

    // Создание задачи.
    @Override
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    // Создание эпика.
    @Override
    public void addEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    // Обновление задачи.
    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    // Обновление эпика.
    @Override
    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    // Удаление задачи по идентификатору.
    @Override
    public void removeTask(Integer taskId) {
        tasks.remove(taskId);
    }

    // Удаление эпика по идентификатору.
    @Override
    public void removeEpic(Integer epicId) {
        ArrayList<Integer> subtaskIds = new ArrayList<Integer>();

        for(Subtask subtask : subtasks.values()) {
            if(subtask.getEpicId() == epicId) {
                subtaskIds.add(subtask.getId());
            }
        }

        for(Integer subtaskId : subtaskIds) {
            removeSubtask(subtaskId);
        }

        epics.remove(epicId);
    }

    // Получение списка всех подзадач определённого эпика.
    @Override
    public ArrayList<Subtask> getSubtasksByEpicId(Integer epicId) {
        ArrayList<Subtask> subtasksByEpicId = new ArrayList<>();

        for(Subtask subtask : subtasks.values()) {
            if(subtask.getEpicId() == epicId) {
                subtasksByEpicId.add(subtask);
            }
        }

        return subtasksByEpicId;
    }
}