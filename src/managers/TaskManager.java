package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    //Удалил ненужные поля, и убрал модификаторы доступа

    //Методы возвращающие все виды задач
    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtasks();

    //Методы очищающие все виды задач
    void clearAllTasks();

    void clearAllEpics();

    void clearAllSubtasks();

    //Методы для получения всех видов задач
    Task getTask(int taskId);

    Epic getEpic(int epicId);

    Subtask getSubtask(int subtaskId);

    //Методы создающие все виды задач и записывающие в них Id
    int addTask(Task newTask);

    int addEpic(Epic newEpic);

    //Метод при добавлении в мапу проверят и изменяет статус эпика
    int addSubtask(Subtask newSubtask);

    //Методы изменяющие все виды задач
    void changeTask(int id, Task task);

    //При изменении сабтаска проверяется статус эпика
    void changeSubtask(int id, Subtask subtask);

    void changeEpic(int id, Epic epic);

    //Методы удаляющие все виды задач
    void deleteTask(int taskId);

    //При удалении эпика удаляются и его сабтаски
    void deleteEpic(int epicId);

    //Метод при удалении сабтаска, получает ID его эпика удаляет
    // из него сабтаску и проверят не изменился ли его статус
    void deleteSubtask(int subtaskId);

    //Метод возвращающий все подзадачи эпика
    ArrayList<Subtask> getEpicsSubtasks(int epicId);

    List<Task> getHistory();
}
