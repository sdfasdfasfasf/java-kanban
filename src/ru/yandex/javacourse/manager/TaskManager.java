package ru.yandex.javacourse.manager;

import ru.yandex.javacourse.tasks.Epic;
import ru.yandex.javacourse.tasks.Subtask;
import ru.yandex.javacourse.tasks.Task;

import java.util.ArrayList;;

public interface TaskManager {
    // Обновить статус эпика
    void updateStatusEpic(Integer epicId);

    // Получение списка всех подзадач.
    ArrayList<Subtask> getSubtasks();

    // Получение списка всех задач.
    ArrayList<Task> getTasks();

    // Получение списка всех эпиков.
    ArrayList<Epic> getEpics();

    // Удаление всех задач.
    void clearTasks();

    // Удаление всех эпиков.
    void clearEpics();

    // Удаление всех подзадач.
    void clearSubtasks();

    // Получение подзадачи по идентификатору.
    Subtask getSubtaskById(Integer subtaskId);

    // Создание подзадачи.
    void addSubtask(Subtask subtask);

    // Обновление подзадачи.
    void updateSubtask(Subtask subtask);

    // Удаление подзадачи по идентификатору.
    void removeSubtask(Integer subtaskId);

    // Получение задачи по идентификатору.
    Task getTaskById(Integer taskId);

    // Получение эпика по идентификатору.
    Epic getEpicById(Integer epicId);

    // Создание задачи.
    void addTask(Task task);

    // Создание эпика.
    void addEpic(Epic epic);

    // Обновление задачи.
    void updateTask(Task task);

    // Обновление эпика.
    void updateEpic(Epic epic);

    // Удаление задачи по идентификатору.
    void removeTask(Integer taskId);

    // Удаление эпика по идентификатору.
    void removeEpic(Integer epicId);

    // Получение списка всех подзадач определённого эпика.
    ArrayList<Subtask> getSubtasksByEpicId(Integer epicId);
}
