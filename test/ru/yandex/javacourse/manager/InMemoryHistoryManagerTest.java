package ru.yandex.javacourse.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.tasks.Task;
import ru.yandex.javacourse.tasks.TaskStatus;

import java.util.List;

class InMemoryHistoryManagerTest {
    private static HistoryManager historyManager;
    private static final String task1Name = "task1";
    private static final String task1Description = "description1";

    @BeforeEach
    public void beforeEach() {
        historyManager= new InMemoryHistoryManager();
    }

    @Test
    @DisplayName("Добавление задачи в историю. После добавления задачи, история не должна быть пустой.")
    void addShouldAddTaskInHisotryTest() {
        // given
        Task task = new Task(task1Name,task1Description);

        // when
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();

        // then
        Assertions.assertNotNull(history, "После добавления задачи, история не должна быть пустой");
        Assertions.assertEquals(1, history.size(), "После добавления, история не должна быть пустой.");
        Assertions.assertEquals(task, history.get(0), "Задачи не совпадают.");
    }

    @Test
    @DisplayName("Получить историю просмотра задач. Задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.")
    void getHistoryShouldCheckStateTaskInHistoryAfterChangeStateTaskTest() {
        // given
        Task task = new Task(task1Name,task1Description);

        // when
        historyManager.add(task);
        //9 убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.
        task.setStatus(TaskStatus.IN_PROGRESS);
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();

        // then
        Assertions.assertNotNull(history, "После добавления задачи, история не должна быть пустой");
        Assertions.assertEquals(2, history.size(), "После добавления, история не должна быть пустой.");
        Assertions.assertEquals(TaskStatus.NEW, history.get(0).getStatus(), "Статусы задач не совпадают 1.");
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, history.get(1).getStatus(), "Статусы задач не совпадают 2.");
    }
}