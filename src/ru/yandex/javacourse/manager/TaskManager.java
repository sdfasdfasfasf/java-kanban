package ru.yandex.javacourse.manager;

import ru.yandex.javacourse.tasks.Epic;
import ru.yandex.javacourse.tasks.Subtask;
import ru.yandex.javacourse.tasks.Task;
import ru.yandex.javacourse.tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

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
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    // Получение списка всех задач.
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    // Получение списка всех эпиков.
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    // Удаление всех задач.
    public void clearTasks() {
        tasks.clear();
    }

    // Удаление всех эпиков.
    public void clearEpics() {
        epics.clear();
    }

    // Удаление всех подзадач.
    public void clearSubtasks() {
        subtasks.clear();

        for(Epic epic : epics.values()) {
            updateStatusEpic(epic.getId());
        }
    }

    // Получение подзадачи по идентификатору.
    public Subtask getSubtaskById(Integer subtaskId) {
        return subtasks.get(subtaskId);
    }

    // Создание подзадачи.
    public void addSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);

        updateStatusEpic(subtask.getEpicId());
    }

    // Обновление подзадачи.
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);

        updateStatusEpic(subtask.getEpicId());
    }

    // Удаление подзадачи по идентификатору.
    public void removeSubtask(Integer subtaskId) {
        Integer epicId = getSubtaskById(subtaskId).getEpicId();
        subtasks.remove(subtaskId);

        updateStatusEpic(epicId);
    }

    // Получение задачи по идентификатору.
    public Task getTaskById(Integer taskId) {
        return tasks.get(taskId);
    }

    // Получение эпика по идентификатору.
    public Epic getEpicById(Integer epicId) {
        return epics.get(epicId);
    }

    // Создание задачи.
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    // Создание эпика.
    public void addEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    // Обновление задачи.
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    // Обновление эпика.
    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    // Удаление задачи по идентификатору.
    public void removeTask(Integer taskId) {
        tasks.remove(taskId);
    }

    // Удаление эпика по идентификатору.
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