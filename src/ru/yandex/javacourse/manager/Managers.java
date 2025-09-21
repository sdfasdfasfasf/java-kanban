package ru.yandex.javacourse.manager;

public class Managers {
    private static HistoryManager historyManager = new InMemoryHistoryManager();

    public static HistoryManager getDefaultHistory() {
        return historyManager;
    }

    public TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}
