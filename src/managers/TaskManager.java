package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtasks();

    void clearAllTasks();

    void clearAllEpics();

    void clearAllSubtasks();

    Task getTask(int taskId);

    Epic getEpic(int epicId);

    Subtask getSubtask(int subtaskId);

    int addTask(Task newTask);

    int addEpic(Epic newEpic);

    int addSubtask(Subtask newSubtask);

    void changeTask(int id, Task task);

    void changeSubtask(int id, Subtask subtask);

    void changeEpic(int id, Epic epic);

    void deleteTask(int taskId);

    void deleteEpic(int epicId);

    void deleteSubtask(int subtaskId);

    ArrayList<Subtask> getEpicsSubtasks(int epicId);

    List<Task> getHistory();
}
