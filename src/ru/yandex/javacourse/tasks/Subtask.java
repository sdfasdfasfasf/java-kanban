package ru.yandex.javacourse.tasks;

public class Subtask extends Task {
    private Integer epicId;

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    public Subtask(String name, String description) {
        super(name, description);
    }

    @Override
    public String toString(){
        String result = "ru.yandex.javacourse.tasks.Subtask{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", description=" + getDescription() +
                ", status=" + getStatus() +
                ", epicId=" + getEpicId() +
                "}";
        return result;
    }
}