import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();

    // Получение списка всех задач.
    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    // Получение списка всех эпиков.
    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    // Удаление всех задач.
    public void clearTasks() {
        tasks.clear();
    }

    // Удаление всех эпиков.
    public void clearEpics() {
        epics.clear();
    }

    // Получение задачи по идентификатору.
    public Task getTaskById(Integer id) {
        return tasks.get(id);
    }

    // Получение эпика по идентификатору.
    public Epic getEpicById(Integer id) {
        return epics.get(id);
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
    public void removeTask(Integer id) {
        tasks.remove(id);
    }

    // Удаление эпика по идентификатору.
    public void removeEpic(Integer id) {
        epics.remove(id);
    }

    // Получение списка всех подзадач определённого эпика.
    public HashMap<Integer, Subtask> getSubtasksByEpicId(Integer id) {
        return epics.get(id).getSubtasks();
    }
}