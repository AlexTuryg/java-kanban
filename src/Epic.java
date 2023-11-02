import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic(int taskId, String taskName, String taskText) {
        super(taskId, "NEW", taskName, taskText);
    }

    public Epic( String taskName, String taskText) {
        super("NEW", taskName, taskText);
    }

    public void addSubtaskId(int subtaskId) {
        subtasksId.add(subtaskId);
    }
    public void addTaskStatus(String taskStatus){

    }

    public ArrayList<Integer> getSubtasksID() {
        return subtasksId;
    }

    public void setSubtasksId(ArrayList<Integer> subtasksId) {
        this.subtasksId = subtasksId;
    }


    public void deleteSubtask(int subtaskId){
       subtasksId.remove(subtasksId.indexOf(subtaskId)) ;
    }
    @Override
    public String toString() {

        return "Epic{" +
                "subtasksId=" + subtasksId +
                ", taskId=" + taskId +
                ", taskStatus='" + taskStatus + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskText='" + taskText + '\'' +
                '}';
    }
}

