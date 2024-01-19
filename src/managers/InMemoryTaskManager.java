package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskTypes;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int id = 1;

    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void clearAllTasks() {
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            historyManager.remove(entry.getValue().getTaskId());
        }
        tasks.clear();
    }

    @Override
    public void clearAllEpics() {

        for (Integer epicId : epics.keySet())
            historyManager.remove(epicId);
        for (Integer subtasksId : subtasks.keySet())
            historyManager.remove(subtasksId);

        epics.clear();
        subtasks.clear();
    }

    @Override
    public void clearAllSubtasks() {
        for (Map.Entry<Integer, Subtask> entry : subtasks.entrySet()) {
            historyManager.remove(entry.getValue().getTaskId());
        }
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearAllSubtask();
            checkEpicStatus(epic);
        }
    }

    @Override
    public Task getTask(int taskId) {
        Task task = tasks.get(taskId);
        historyManager.add(task);
        return task;
    }

    @Override
    public Epic getEpic(int epicId) {
        Epic epic = epics.get(epicId);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public Subtask getSubtask(int subtaskId) {
        Subtask subtask = subtasks.get(subtaskId);
        historyManager.add(subtask);
        return subtask;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public int addTask(Task newTask) {
        newTask.setTaskId(++id);
        tasks.put(id, newTask);
        return id;
    }

    @Override
    public int addEpic(Epic newEpic) {
        newEpic.setTaskId(++id);
        epics.put(id, newEpic);
        return id;
    }

    @Override
    public int addSubtask(Subtask newSubtask) {
        if (epics.containsKey(newSubtask.getEpicId())) {
            newSubtask.setTaskId(++id);
            subtasks.put(id, newSubtask);
            Epic epic = epics.get(newSubtask.getEpicId());
            epic.addSubtaskId(id);
            checkEpicStatus(epic);
            return id;
        } else {
            return 0;
        }
    }

    @Override
    public void changeTask(int taskId, Task task) {
        if (tasks.containsKey(taskId)) {
            tasks.put(taskId, task);
        }
    }

    @Override
    public void changeSubtask(int subtaskId, Subtask subtask) {
        if (subtasks.containsKey(subtaskId)) {
            subtasks.put(subtaskId, subtask);
            Epic epic = epics.get(subtask.getEpicId());
            checkEpicStatus(epic);
        }
    }

    @Override
    public void changeEpic(int epicID, Epic epic) {
        if (epics.containsKey(epicID)) {
            Epic epicToChange = epics.get(epicID);
            epicToChange.setTaskName(epic.getTaskName());
            epicToChange.setTaskText(epic.getTaskText());
        }
    }

    @Override
    public void deleteTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            historyManager.remove(taskId);
            tasks.remove(taskId);
        }
    }

    @Override
    public void deleteEpic(int epicId) {
        if (epics.containsKey(epicId)) {
            Epic epic = epics.get(epicId);
            ArrayList<Integer> subtasksId = epic.getSubtasksID();
            for (int subId : subtasksId) {
                historyManager.remove(subId);
                subtasks.remove(subId);
            }
            historyManager.remove(epicId);
            epics.remove(epicId);
        }
    }

    @Override
    public void deleteSubtask(int subtaskId) {
        if (subtasks.containsKey(subtaskId)) {
            Subtask subtask = subtasks.get(subtaskId);
            Epic epic = epics.get(subtask.getEpicId());
            epic.deleteSubtask(subtaskId);
            historyManager.remove(subtaskId);
            subtasks.remove(subtaskId);
            checkEpicStatus(epic);
        }
    }

    @Override
    public ArrayList<Subtask> getEpicsSubtasks(int epicId) {
        ArrayList<Integer> subtasksId = epics.get(epicId).getSubtasksID();
        ArrayList<Subtask> returnedSubtasks = new ArrayList<>();
        for (int sId : subtasksId) {
            returnedSubtasks.add(subtasks.get(sId));
        }
        return returnedSubtasks;
    }

    private void checkEpicStatus(Epic epic) {
        if (epic.getSubtasksID().isEmpty()) {
            epic.setTaskStatus(TaskTypes.NEW);
            return;
        }

        ArrayList<Integer> subtasksInEpicId = epic.getSubtasksID();
        Subtask subtusk;
        int doneStasus = 0;
        int newStatus = 0;
        for (int subtaskId : subtasksInEpicId) {
            subtusk = subtasks.get(subtaskId);
            if (subtusk.getTaskStatus().equals(TaskTypes.DONE)) {
                doneStasus++;
            }
            if (subtusk.getTaskStatus().equals(TaskTypes.NEW)) {
                newStatus++;
            }
        }
        if (doneStasus == subtasksInEpicId.size()) {
            epic.setTaskStatus(TaskTypes.DONE);
        } else if (newStatus == subtasksInEpicId.size()) {
            epic.setTaskStatus(TaskTypes.NEW);
        } else {
            epic.setTaskStatus(TaskTypes.IN_PROGRESS);
        }

    }

}
