public class Subtask extends Task {
    public Subtask(String name, String description) {
        super(name, description);
    }

    @Override
    public String toString(){
        String result = "Subtask{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", description=" + getDescription() +
                ", status=" + getStatus() +
                "}";
        return result;
    }
}
