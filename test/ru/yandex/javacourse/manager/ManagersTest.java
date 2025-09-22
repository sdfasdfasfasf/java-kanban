package ru.yandex.javacourse.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManagersTest {
    private static Managers managers;

    @BeforeEach
    public void beforeEach() {
        managers = new Managers();
    }

    @Test
    void getDefaultHistory() {
        //5 убедитесь, что утилитарный класс всегда возвращает проинициализированные и готовые к работе экземпляры менеджеров;
        HistoryManager historyManager = Managers.getDefaultHistory();
        Assertions.assertNotNull(historyManager, "historyManager не инициализирован");
    }

    @Test
    void getDefault() {
        //5 убедитесь, что утилитарный класс всегда возвращает проинициализированные и готовые к работе экземпляры менеджеров;
        TaskManager taskManager = managers.getDefault();
        Assertions.assertNotNull(taskManager, "taskManager не инициализирован");
    }
}