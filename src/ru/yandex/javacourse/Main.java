package ru.yandex.javacourse;

import ru.yandex.javacourse.manager.TaskManager;
import ru.yandex.javacourse.tasks.Epic;
import ru.yandex.javacourse.tasks.Subtask;
import ru.yandex.javacourse.tasks.Task;
import ru.yandex.javacourse.tasks.TaskStatus;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Tests:
        Task task1 = new Task("task1", "task1Description1");
        Task task2 = new Task("task2", "task2Description2");

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        System.out.println("Списки задач:");
        System.out.println(taskManager.getTasks());
        System.out.println();

        task1.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task1);
        System.out.println("Списки задач, после изменения статуса task1 IN_PROGRESS:");
        System.out.println(taskManager.getTasks());
        System.out.println();

        Subtask subtask1 = new Subtask("subtask1", "subtask1Description1");
        Subtask subtask2 = new Subtask("subtask2", "subtask2Description2");

        Epic epic1 = new Epic("epic1", "epic1Description1");
        taskManager.addEpic(epic1);

        subtask1.setEpicId(epic1.getId());
        taskManager.addSubtask(subtask1);

        subtask2.setEpicId(epic1.getId());
        taskManager.addSubtask(subtask2);

        Epic epic2 = new Epic("epic2", "epic1Description2");
        taskManager.addEpic(epic2);

        System.out.println("Списки эпиков:");
        System.out.println(taskManager.getEpics());
        System.out.println();

        epic1.setName("newEpic1");
        epic1.setDescription("newDescriptionEpic1");

        taskManager.updateEpic(epic1);

        System.out.println("Списки эпиков после обновления данных эпиков:");
        System.out.println(taskManager.getEpics());
        System.out.println();

        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(subtask2);

        System.out.println("Списки эпиков после изменения статуса подзадачи 2 IN_PROGRESS:");
        System.out.println(taskManager.getEpics());
        System.out.println();

        subtask1.setStatus(TaskStatus.DONE);
        subtask2.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);

        System.out.println("Списки эпиков после изменения статуса двух подзадач DONE:");
        System.out.println(taskManager.getEpics());
        System.out.println();

        taskManager.removeTask(task2.getId());
        System.out.println("Списки задач после удаления одной задачи:");
        System.out.println(taskManager.getTasks());
        System.out.println();

        System.out.println("Задача epic1:");
        System.out.println(taskManager.getEpicById(epic1.getId()));
        System.out.println();

        System.out.println("Подзадачи epic1:");
        System.out.println(taskManager.getSubtasksByEpicId(epic1.getId()));
        System.out.println();

        System.out.println("Списки эпиков после удаления одного эпика:");
        taskManager.removeEpic(epic1.getId());
        System.out.println(taskManager.getEpics());
        System.out.println();

        System.out.println("Задача task1:");
        System.out.println(taskManager.getTaskById(task1.getId()));
        System.out.println();

        taskManager.clearTasks();
        System.out.println("Списки задач после удаления всех задач:");
        System.out.println(taskManager.getTasks());
        System.out.println();

        taskManager.clearEpics();
        System.out.println("Списки эпиков после удаления всех эпиков:");
        System.out.println(taskManager.getEpics());
        System.out.println();
    }
}