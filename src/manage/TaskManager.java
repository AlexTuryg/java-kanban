package manage;

import taskTypes.Epic;
import taskTypes.Subtask;
import taskTypes.Task;

import java.util.ArrayList;
import java.util.HashMap;

public interface TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    //Общий Id для задач
    int id = 1;

    //Методы возвращающие все виды задач
    public ArrayList<Task> getAllTasks();

    public ArrayList<Epic> getAllEpics();

    public ArrayList<Subtask> getAllSubtasks();

    //Методы очищающие все виды задач
    public void clearAllTasks();

    public void clearAllEpics();

    public void clearAllSubtasks();

    //Методы для получения всех видов задач
    public Task getTask(int taskId);

    public Epic getEpic(int epicId);

    public Subtask getSubtask(int subtaskId);

    //Методы создающие все виды задач и записывающие в них Id
    public int addTask(Task newTask);

    public int addEpic(Epic newEpic);

    //Метод при добавлении в мапу проверят и изменяет статус эпика
    public int addSubtask(Subtask newSubtask);

    //Методы изменяющие все виды задач
    public void changeTask(int id, Task task);

    //При изменении сабтаска проверяется статус эпика
    public void changeSubtask(int id, Subtask subtask);

    public void changeEpic(int id, Epic epic);

    //Методы удаляющие все виды задач
    public void deleteTask(int taskId);

    //При удалении эпика удаляются и его сабтаски
    public void deleteEpic(int epicId);

    //Метод при удалении сабтаска, получает ID его эпика удаляет
    // из него сабтаску и проверят не изменился ли его статус
    public void deleteSubtask(int subtaskId);

    //Метод возвращающий все подзадачи эпика
    public ArrayList<Subtask> getEpicsSubtasks(int epicId);

    public ArrayList<Task> getHistory();
}
