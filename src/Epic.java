import java.util.HashMap;

public class Epic  extends Task {
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    // Получение списка всех подзадач.
    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public void setStatus(TaskStatus status) {
        System.out.println("Нельзя менять статус эпика. Статус эпика расчитывается автоматически.");
    }

    // Удаление всех подзадач.
    public void clearSubtasks() {
        subtasks.clear();
    }

    // Получение подзадачи по идентификатору.
    public Task getSubtaskById(Integer id) {
        return subtasks.get(id);
    }

    // Создание подзадачи.
    public void addSubtask(Subtask task) {
        subtasks.put(task.getId(), task);
    }

    // Обновление подзадачи.
    public void updateSubtask(Subtask task) {
        subtasks.put(task.getId(), task);

        boolean isSubtasksStatusNew = false;
        boolean isSubtasksStatusInProgress = false;
        boolean isSubtasksStatusDone = false;

        for (Subtask subtask : subtasks.values()) {
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

        if (isSubtasksStatusNew == true && isSubtasksStatusInProgress == false && isSubtasksStatusDone == false) {
            super.setStatus(TaskStatus.NEW);
        } else if (isSubtasksStatusNew == false && isSubtasksStatusInProgress == false && isSubtasksStatusDone == true) {
            super.setStatus(TaskStatus.DONE);
        } else {
            super.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    // Удаление подзадачи по идентификатору.
    public void removeSubtask(Integer id) {
        subtasks.remove(id);
    }

    @Override
    public String toString() {
        String result = "Epic:" + super.toString();

        result += ", Subtasks[";

        int indexSubtasks = 1;
        int countSubtasks = subtasks.size();

        for(Subtask subtask : subtasks.values()) {
            result += subtask.toString();

            if(indexSubtasks < countSubtasks) {
                result += ",";
            }

            indexSubtasks++;
        }

        result += "]";

        return result;
    }
}
