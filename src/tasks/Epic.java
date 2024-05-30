package tasks;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private final ArrayList<Integer> subtasksId = new ArrayList<>();
    public Epic(int taskId, TaskStatus taskStatus, String taskName, String taskText) {
        super(taskId, taskStatus, taskName, taskText);
    }
    public Epic(int taskId, String taskName, String taskText) {
        super(taskId, TaskStatus.NEW, taskName, taskText);
    }

    public Epic( String taskName, String taskText) {
        super(TaskStatus.NEW, taskName, taskText);
    }

    public void addSubtaskId(int subtaskId) {
        subtasksId.add(subtaskId);
    }

    public ArrayList<Integer> getSubtasksID() {
        return subtasksId;
    }
    public void clearAllSubtask(){
        subtasksId.clear();
    }

    public void deleteSubtask(int subtaskId){
       subtasksId.remove(Integer.valueOf(subtaskId)) ;
    }
    @Override
    public String toString() {

        return "taskTypes.Epic{" +
                "subtasksId=" + subtasksId +
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
        Epic epic = (Epic) o;
        return Objects.equals(subtasksId, epic.subtasksId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtasksId);
    }
}

