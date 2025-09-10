package ru.yandex.javacourse.tasks;

public class Epic  extends Task {
    public Epic(String name, String description) {
        super(name, description);
    }

    @Override
    public String toString() {
        String result = "ru.yandex.javacourse.tasks.Epic:" + super.toString();

        return result;
    }
}
