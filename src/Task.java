import java.util.Objects;

public class Task {
    // Текущий счетчик Id увеличивается на 1
    private static Integer currentId = 0;

    // Название
    private String name;

    // Описание
    private String description;

    // Уникальный идентификационный номер задачи
    private Integer id;

    // Статус
    private TaskStatus status;

    public Task(String name, String description) {
        currentId++;
        this.id = currentId;
        this.name = name;
        this.description = description;
        this.status = TaskStatus.NEW;
    }

    public Integer getId() {
        return  id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        String result = "Task{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", status=" + status +
                "}";
        return result;
    }

    // @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Task otherTask = (Task) obj;

        return (id == otherTask.id) &&
                Objects.equals(name, otherTask.name) &&
                Objects.equals(description, otherTask.description) &&
                Objects.equals(status, otherTask.status);
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash += id.hashCode();
        hash = hash * 31;

        if(name != null) {
            hash += name.hashCode();
        }

        if(description != null) {
            hash += description.hashCode();
        }

        if(status != null) {
            hash += status.hashCode();
        }

        return  hash;
    }
}
