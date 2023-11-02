import java.util.ArrayList;
import java.util.HashMap;

public class ObjectManager {

    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtusk> subtasks = new HashMap<>();
    //Общий Id для задач
    private int id = 0;

    //Методы возвращающие все виды задач
    public HashMap<Integer, Task> getAllTasks() {
        return tasks;
    }

    public HashMap<Integer, Epic> getAllEpics() {
        return epics;
    }

    public HashMap<Integer, Subtusk> getAllSubtasks() {
        return subtasks;
    }

    //Методы очищающие все виды задач
    public void clearAllTasks() {
        tasks.clear();
    }

    public void clearAllEpics() {
        epics.clear();
    }

    public void clearAllSubtasks() {
        subtasks.clear();
    }

    //Методы для получения всех видов задач
    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public Epic getEpic(int epicId) {
        return epics.get(epicId);
    }

    public Subtusk getSubtask(int subtaskId) {
        return subtasks.get(subtaskId);
    }

    //Методы создающие все виды задач и записывающие в них Id
    public int addTask(Task newTask) {
        newTask.setTaskId(++id);
        tasks.put(id, newTask);
        return id;
    }

    public int addEpic(Epic newEpic) {
        newEpic.setTaskId(++id);
        epics.put(id, newEpic);
        return id;
    }

    //Метод при добавлении в мапу проверят и изменяет статус эпика
    public int addSubtask(Subtusk newSubtask) {
        newSubtask.setTaskId(++id);
        subtasks.put(id, newSubtask);
        Epic epic = epics.get(newSubtask.getEpicId());
        epic.addSubtaskId(id);
        epics.put(newSubtask.getEpicId(), checkEpicStatus(epics.get(newSubtask.getEpicId())));
        return id;
    }

    //Методы изменяющие все виды задач
    public void changeTask(int taskId, Task task) {
        tasks.put(taskId, task);
    }

    //При изменении сабтаска проверяется статус эпика
    public void changeSubtask(int subtaskId, Subtusk subtask) {
        subtasks.put(subtaskId, subtask);
        Epic epic = epics.get(subtask.getEpicId());
        epics.put(subtask.getEpicId(), checkEpicStatus(epic));
    }

    public void changeEpic(int epicId, Epic epic) {
        epics.put(epicId, checkEpicStatus(epics.get(epicId)));
    }

    //Методы удаляющие все виды задач
    public void deleteTask(int taskId) {
        tasks.remove(taskId);
    }

    //При удалении эпика удаляются и его сабтаски
    public void deleteEpic(int epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<Integer> subtasksId = epic.getSubtasksID();
        for (int subId : subtasksId) {
            subtasks.remove(subId);
        }
        epics.remove(epicId);
    }

    //Метод при удалении сабтаска, получает ID его эпика удаляет
    // из него сабтаску и проверят не изменился ли его статус
    public void deleteSubtask(int subtaskId) {
        Subtusk subtask = subtasks.get(subtaskId);
        Epic epic = epics.get(subtask.getEpicId());
        epic.deleteSubtask(subtaskId);
        subtasks.remove(subtaskId);
        epics.put(subtask.getEpicId(), checkEpicStatus(epic));
    }

    //Метод возвращающий все подзадачи эпика

    public ArrayList<Integer> getEpicsSubtasks(int epicId) {
        return epics.get(epicId).getSubtasksID();
    }

    //Метод обновляющий статус Эпика

    public Epic checkEpicStatus(Epic epic) {
        if (epic.getSubtasksID() == null) {
            epic.setTaskStatus("NEW");
            return epic;
        }

        ArrayList<Integer> subtasksInEpicId = epic.getSubtasksID();
        Subtusk subtusk;
        int doneStasus = 0;
        int newStatus = 0;
        for (int subtaskId : subtasksInEpicId) {
            subtusk = subtasks.get(subtaskId);
            if (subtusk.getTaskStatus().equals("DONE")) {
                doneStasus++;
            }
            if (subtusk.getTaskStatus().equals("NEW")) {
                newStatus++;
            }
        }

        if (doneStasus == subtasksInEpicId.size()) {
            epic.setTaskStatus("DONE");
            return epic;
        } else if (newStatus == subtasksInEpicId.size()) {
            epic.setTaskStatus("NEW");
            return epic;
        }

        epic.setTaskStatus("IN_PROGRESS");
        return epic;
    }


}
