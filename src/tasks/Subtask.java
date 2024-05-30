package tasks;

import java.util.Objects;

public class Subtask extends Task{
    private int epicID;

    public Subtask(int taskId, TaskStatus taskStatus, String taskName, String taskText, int epicID) {
        super(taskId,taskStatus,taskName,taskText);
        this.epicID = epicID;
    }
    public Subtask(TaskStatus taskStatus, String taskName, String taskText, int epicID) {
        super(taskStatus,taskName,taskText);
        this.epicID = epicID;
    }

    public int getEpicId() { return epicID;  }

    @Override
    public String toString() {
        return "Subtusk{" +
                "epicID=" + epicID +
                ", taskId=" + taskId +
                ", taskStatus='" + taskStatus + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskText='" + taskText + '\'' +
                '}'+ "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epicID == subtask.epicID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicID);
    }
}
