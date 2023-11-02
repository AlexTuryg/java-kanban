import java.util.Objects;

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
    //Переопределил методы
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId && Objects.equals(taskStatus, task.taskStatus) && Objects.equals(taskName, task.taskName) && Objects.equals(taskText, task.taskText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskStatus, taskName, taskText);
    }
}

