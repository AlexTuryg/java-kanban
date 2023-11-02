public class Task {
    protected int taskId;
    protected String taskStatus;
    protected String taskName;
    protected String taskText;

    public Task(int taskId, String taskStatus, String taskName, String taskText) {
        this.taskId = taskId;
        this.taskStatus = taskStatus;
        this.taskName = taskName;
        this.taskText = taskText;
    }

    public Task(String taskStatus, String taskName, String taskText) {
        this.taskStatus = taskStatus;
        this.taskName = taskName;
        this.taskText = taskText;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }


    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskText() {
        return taskText;
    }


    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskStatus='" + taskStatus + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskText='" + taskText + '\'' +
                '}';
    }
}

