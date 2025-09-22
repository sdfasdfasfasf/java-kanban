package ru.yandex.javacourse.manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.yandex.javacourse.tasks.Epic;
import ru.yandex.javacourse.tasks.Subtask;
import ru.yandex.javacourse.tasks.Task;

import java.util.List;

class InMemoryTaskManagerTest {
    private static TaskManager taskManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    void updateStatusEpic() {
    }

    @Test
    void getSubtasks() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description");
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description");
        subtask.setEpicId(epic.getId());
        taskManager.addSubtask(subtask);

        Epic epic2 = new Epic("Test addNewEpic2", "Test addNewEpic2 description");
        taskManager.addEpic(epic2);

        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description");
        subtask2.setEpicId(epic2.getId());
        taskManager.addSubtask(subtask2);

        //7 проверьте, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера;
        final List<Subtask> subtasks = taskManager.getSubtasks();

        Assertions.assertNotNull(subtasks, "Подзадачи не возвращаются");
        Assertions.assertEquals(2, subtasks.size(), "Неверное количество задач.");
    }

    @Test
    void getTasks() {
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        taskManager.addTask(task);

        Task task2 = new Task("Test addNewTask2", "Test addNewTask2 description");
        taskManager.addTask(task2);
        final int taskId2 = task2.getId();

        //7 проверьте, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера;
        final List<Task> tasks = taskManager.getTasks();

        Assertions.assertNotNull(tasks, "Задачи не возвращаются");
        Assertions.assertEquals(2, tasks.size(), "Неверное количество задач.");
    }

    @Test
    void getEpics() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description");
        taskManager.addEpic(epic);
        final int epicId = epic.getId();

        Epic epic2 = new Epic("Test addNewEpic2", "Test addNewEpic2 description");
        taskManager.addEpic(epic2);

        //7 проверьте, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера;
        final List<Epic> epics = taskManager.getEpics();

        Assertions.assertNotNull(epics, "Эпики не возвращаются");
        Assertions.assertEquals(2, epics.size(), "Неверное количество эпиков.");
    }

    @Test
    void clearTasks() {
    }

    @Test
    void clearEpics() {
    }

    @Test
    void clearSubtasks() {
    }

    @Test
    void getSubtaskById() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description");
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description");
        subtask.setEpicId(epic.getId());
        taskManager.addSubtask(subtask);
        final int subtaskId = subtask.getId();

        final Subtask savedsubtask = taskManager.getSubtaskById(subtaskId);

        //6 проверьте, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id;
        Epic epic2 = new Epic("Test addNewEpic2", "Test addNewEpic2 description");
        taskManager.addEpic(epic2);

        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description");
        subtask2.setEpicId(epic2.getId());
        taskManager.addSubtask(subtask2);

        final int subtaskId2 = subtask2.getId();
        final Subtask savedsubtask2 = taskManager.getSubtaskById(subtaskId2);

        Assertions.assertEquals(subtask.getId(), savedsubtask.getId(), "Id подзадач не совпадают.");
        Assertions.assertEquals(subtask2.getId(), savedsubtask2.getId(), "Id подзадач не совпадают.");
    }

    @Test
    void addSubtask() {
        //2 проверьте, что наследники класса Task равны друг другу, если равен их id;
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description");
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description");
        subtask.setEpicId(epic.getId());
        taskManager.addSubtask(subtask);
        final int subtaskId = subtask.getId();

        final Subtask savedsubtask = taskManager.getSubtaskById(subtaskId);

        Assertions.assertEquals(subtask.getId(), savedsubtask.getId(), "Id подзадач не совпадают.");
        Assertions.assertEquals(subtask, savedsubtask, "Подзадачи не совпадают.");

        //4 проверьте, что объект Subtask нельзя сделать своим же эпиком;
        //Epic newEpic = (Epic) subtask; // ошибка компиляции
        //taskManager.addEpic(newEpic);
    }

    @Test
    void updateSubtask() {
    }

    @Test
    void removeSubtask() {
    }

    @Test
    void getTaskById() {
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        taskManager.addTask(task);
        final int taskId = task.getId();

        final Task savedTask = taskManager.getTaskById(taskId);

        //6 проверьте, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id;
        Task task2 = new Task("Test addNewTask2", "Test addNewTask2 description");
        taskManager.addTask(task2);
        final int taskId2 = task2.getId();
        final Task savedTask2 = taskManager.getTaskById(taskId2);

        Assertions.assertEquals(task.getId(), savedTask.getId(), "Id задач не совпадают.");
        Assertions.assertEquals(task2.getId(), savedTask2.getId(), "Id задач не совпадают.");
    }

    @Test
    void getEpicById() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description");
        taskManager.addEpic(epic);
        final int epicId = epic.getId();

        final Epic savedEpic = taskManager.getEpicById(epicId);

        //6 проверьте, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id;
        Epic epic2 = new Epic("Test addNewEpic2", "Test addNewEpic2 description");
        taskManager.addEpic(epic2);
        final int epicId2 = epic2.getId();
        final Epic savedEpic2 = taskManager.getEpicById(epicId2);

        Assertions.assertEquals(epic.getId(), savedEpic.getId(), "Id эпиков не совпадают.");
        Assertions.assertEquals(epic2.getId(), savedEpic2.getId(), "Id эпиков не совпадают.");
    }

    @Test
    void addTask() {
        //1 проверьте, что экземпляры класса Task равны друг другу, если равен их id;
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        taskManager.addTask(task);
        final int taskId = task.getId();

        final Task savedTask = taskManager.getTaskById(taskId);

        Assertions.assertEquals(task.getId(), savedTask.getId(), "Id задач не совпадают.");
        Assertions.assertEquals(task, savedTask, "Задачи не совпадают.");
    }

    @Test
    void shouldCheckUnchangedTaskByAdd() {
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        taskManager.addTask(task);
        final int taskId = task.getId();

        final Task savedTask = taskManager.getTaskById(taskId);

        Task task2 = new Task("Test addNewTask2", "Test addNewTask2 description");
        taskManager.addTask(task2);
        final int taskId2 = task2.getId();

        final Task savedTask2 = taskManager.getTaskById(taskId2);

        //8 создайте тест, в котором проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер
        Assertions.assertEquals(task, savedTask, "Задачи не совпадают.");
        Assertions.assertEquals(task2, savedTask2, "Задачи не совпадают.");
    }

    @Test
    void shouldCheckUnchangedEpicByAdd() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description");
        taskManager.addEpic(epic);
        final int epicId = epic.getId();

        final Epic savedEpic = taskManager.getEpicById(epicId);

        Epic epic2 = new Epic("Test addNewEpic2", "Test addNewEpic2 description");
        taskManager.addEpic(epic2);
        final int epicId2 = epic2.getId();

        final Epic savedEpic2 = taskManager.getEpicById(epicId2);

        //8 создайте тест, в котором проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер
        Assertions.assertEquals(epic, savedEpic, "Эпики не совпадают.");
        Assertions.assertEquals(epic2, savedEpic2, "Эпики не совпадают.");
    }

    @Test
    void shouldCheckUnchangedSubtaskByAdd() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description");
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description");
        subtask.setEpicId(epic.getId());
        taskManager.addSubtask(subtask);
        final int subtaskId = subtask.getId();

        final Subtask savedsubtask = taskManager.getSubtaskById(subtaskId);

        Epic epic2 = new Epic("Test addNewEpic2", "Test addNewEpic2 description");
        taskManager.addEpic(epic2);

        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description");
        subtask2.setEpicId(epic2.getId());
        taskManager.addSubtask(subtask2);
        final int subtaskId2 = subtask2.getId();

        final Subtask savedsubtask2 = taskManager.getSubtaskById(subtaskId2);

        //8 создайте тест, в котором проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер
        Assertions.assertEquals(subtask, savedsubtask, "Подзадачи не совпадают.");
        Assertions.assertEquals(subtask2, savedsubtask2, "Подзадачи не совпадают.");
    }

    @Test
    void addEpic() {
        //2 проверьте, что наследники класса Task равны друг другу, если равен их id;
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description");
        taskManager.addEpic(epic);
        final int epicId = epic.getId();

        final Epic savedEpic = taskManager.getEpicById(epicId);

        Assertions.assertEquals(epic.getId(), savedEpic.getId(), "Id эпиков не совпадают.");
        Assertions.assertEquals(epic, savedEpic, "Эпики не совпадают.");

        //3 проверьте, что объект Epic нельзя добавить в самого себя в виде подзадачи;
        //Subtask subtask = (Subtask) epic; // ошибка компиляции
        //taskManager.addSubtask(subtask);
        //taskManager.addSubtask(epic); // ошибка компиляции
    }

    @Test
    void updateTask() {
    }

    @Test
    void updateEpic() {
    }

    @Test
    void removeTask() {
    }

    @Test
    void removeEpic() {
    }

    @Test
    void getSubtasksByEpicId() {
    }
}