package ru.yandex.javacourse.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.tasks.Task;
import ru.yandex.javacourse.tasks.TaskStatus;

import java.util.List;

class InMemoryHistoryManagerTest {
    private static HistoryManager historyManager;

    @BeforeEach
    public void beforeEach() {
        historyManager= new InMemoryHistoryManager();
    }

    @Test
    void add() {
        Task task = new Task("task1","description1");
        historyManager.add(task);

        final List<Task> history = historyManager.getHistory();
        Assertions.assertNotNull(history, "После добавления задачи, история не должна быть пустой");
        Assertions.assertEquals(1, history.size(), "После добавления, история не должна быть пустой.");
        Assertions.assertEquals(task, history.get(0), "Задачи не совпадают.");
    }

    @Test
    void getHistory() {
        Task task = new Task("task1","description1");
        historyManager.add(task);

        //9 убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.
        task.setStatus(TaskStatus.IN_PROGRESS);
        historyManager.add(task);

        final List<Task> history = historyManager.getHistory();
        Assertions.assertNotNull(history, "После добавления задачи, история не должна быть пустой");
        Assertions.assertEquals(2, history.size(), "После добавления, история не должна быть пустой.");
        Assertions.assertEquals(TaskStatus.NEW, history.get(0).getStatus(), "Статусы задач не совпадают 1.");
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, history.get(1).getStatus(), "Статусы задач не совпадают 2.");
    }
}