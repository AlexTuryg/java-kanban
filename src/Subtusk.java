public class Subtusk extends Task{
    private int epicID;

    public Subtusk(int taskId, String taskStatus, String taskName, String taskText, int epicID) {
        super(taskId,taskStatus,taskName,taskText);
        this.epicID = epicID;
    }
    public Subtusk(String taskStatus, String taskName, String taskText, int epicID) {
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
