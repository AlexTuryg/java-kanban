public class Subtask extends Task{
    //Изменил опечатку в названии класса
    private int epicID;

    public Subtask(int taskId, String taskStatus, String taskName, String taskText, int epicID) {
        super(taskId,taskStatus,taskName,taskText);
        this.epicID = epicID;
    }
    public Subtask(String taskStatus, String taskName, String taskText, int epicID) {
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
                '}';
    }
}
