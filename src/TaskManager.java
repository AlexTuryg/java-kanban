import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    //Изменил название класса
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    //Общий Id для задач
    private int id = 1;

    //Методы возвращающие все виды задач
    //Исправил сигнатуру
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<Task>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<Epic>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<Subtask>(subtasks.values());
    }

    //Методы очищающие все виды задач
    public void clearAllTasks() {
        tasks.clear();
    }

    public void clearAllEpics() {
        epics.clear();
        subtasks.clear();
        //Добавил удаление всех сабтасков
    }

    public void clearAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values())
        {
            epic.clearAllSubtask();
        }
    }

    //Методы для получения всех видов задач
    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public Epic getEpic(int epicId) {
        return epics.get(epicId);
    }

    public Subtask getSubtask(int subtaskId) {
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
    public int addSubtask(Subtask newSubtask) {
        //Добавил проверку на содержание эпика
        if (epics.containsKey(newSubtask.getEpicId())) {
            newSubtask.setTaskId(++id);
            subtasks.put(id, newSubtask);
            Epic epic = epics.get(newSubtask.getEpicId());
            epic.addSubtaskId(id);
            //Исправил ошибку checkEpicStatus(epics.get(newSubtask.getEpicId()));
            checkEpicStatus(epic);
            return id;
        }else {
            return 0;
        }
    }

    //Методы изменяющие все виды задач
    public void changeTask(int taskId, Task task) {
        if (tasks.containsKey(taskId)) {
            tasks.put(taskId, task);
        }
    }

    //При изменении сабтаска проверяется статус эпика
    public void changeSubtask(int subtaskId, Subtask subtask) {
        if (subtasks.containsKey(subtaskId)) {
            subtasks.put(subtaskId, subtask);
            Epic epic = epics.get(subtask.getEpicId());
            checkEpicStatus(epic);
        }
    }

    public void changeEpic(int epicId, String name, String text) {
        //Исправил получение целого эпика на получение его имени и текста
        if (epics.containsKey(epicId)){
            Epic epic = epics.get(epicId);
            epic.setTaskName(name);
            epic.setTaskText(text);
            checkEpicStatus(epic);
            //убрал epics.put(epicId, checkEpicStatus(epic));
        }
    }

    //Методы удаляющие все виды задач
    //Добавил проверку на наличие элемента в мапе
    public void deleteTask(int taskId) {
        if (tasks.containsKey(taskId)) tasks.remove(taskId);
    }

    //При удалении эпика удаляются и его сабтаски
    public void deleteEpic(int epicId) {
        if (epics.containsKey(epicId)) {
            Epic epic = epics.get(epicId);
            ArrayList<Integer> subtasksId = epic.getSubtasksID();
            for (int subId : subtasksId) {
                subtasks.remove(subId);
            }
            epics.remove(epicId);
        }
    }

    //Метод при удалении сабтаска, получает ID его эпика удаляет
    // из него сабтаску и проверят не изменился ли его статус
    public void deleteSubtask(int subtaskId) {
        if (subtasks.containsKey(subtaskId)) {
            Subtask subtask = subtasks.get(subtaskId);
            Epic epic = epics.get(subtask.getEpicId());
            epic.deleteSubtask(subtaskId);
            subtasks.remove(subtaskId);
            checkEpicStatus(epic);
            // Исправил epics.put(subtask.getEpicId(), checkEpicStatus(epic));
        }
    }

    //Метод возвращающий все подзадачи эпика

    public ArrayList<Subtask> getEpicsSubtasks(int epicId) {
        //Исправил ошибку return new ArrayList<Subtask>(subtasks.values());
        //к сожалению на другую реализацию меня не хватило((((
        ArrayList<Integer> subtasksId = epics.get(epicId).getSubtasksID();
        ArrayList<Subtask> returnedSubtasks = new ArrayList<>();
        for (int sId: subtasksId){
            returnedSubtasks.add(subtasks.get(sId));
        }
        return returnedSubtasks;
    }

    //Метод обновляющий статус Эпика

    public Epic checkEpicStatus(Epic epic) {
        //Добавил проверку на пустоту, а не на Null
        if (epic.getSubtasksID().isEmpty()) {
            epic.setTaskStatus("NEW");
            return epic;
        }

        ArrayList<Integer> subtasksInEpicId = epic.getSubtasksID();
        Subtask subtusk;
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
        //Исправил, убрал лишние return
        if (doneStasus == subtasksInEpicId.size()) {
            epic.setTaskStatus("DONE");
        } else if (newStatus == subtasksInEpicId.size()) {
            epic.setTaskStatus("NEW");
        }

        epic.setTaskStatus("IN_PROGRESS");
        return epic;
    }


}
