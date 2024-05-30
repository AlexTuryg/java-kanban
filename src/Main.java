import managers.FileBackedTaskManager;
import managers.Managers;
import managers.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        FileBackedTaskManager manager = new FileBackedTaskManager();

        //Загрузка истории
//        try {
//            FileBackedTaskManager.fileBackedTaskManager();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // Добавляет несколько Таскок
        makeSomeTasks(manager);
        // Добавлет эпик и сабтаски
        makeSomeEpicsWithSubtasks(manager);
        //
        // Удаляет все эпики и сабтаски
        deleteAllEpic(manager);
        // Удаляет все таски
        deleteAllSubtasks(manager);

        //Старый тест на проверку работы истории
        //oldTest();

    }
    public static void makeSomeTasks(FileBackedTaskManager manager){
        manager.addTask(new Task(TaskStatus.NEW, "First Task","First task text"));
        manager.addTask(new Task(TaskStatus.NEW, "Second Task","Second task text"));
        manager.addTask(new Task(TaskStatus.NEW, "Third Task","Third task text"));
    }
    public static void makeSomeEpicsWithSubtasks(FileBackedTaskManager manager){
        int firstEpicId = manager.addEpic(new Epic("First epic", "First epic text"));

        int feFirstSubtask = manager.addSubtask(new Subtask(TaskStatus.NEW, "First epic, First subtask",
                "First epic, first subtask text", firstEpicId));
        int feSecondSubtask = manager.addSubtask(new Subtask(TaskStatus.NEW, "First epic, Second subtask",
                "First epic, Second subtask text", firstEpicId));
        int feThirdSubtask = manager.addSubtask(new Subtask(TaskStatus.NEW, "First epic, Third subtask",
                "First epic, Third subtask text", firstEpicId));

        int secondEpicId = manager.addEpic(new Epic("Secind epic", "Second epic text"));
    }
    public static void deleteAllTasks(FileBackedTaskManager manager){
        manager.clearAllTasks();
    }
    public static void deleteAllEpic(FileBackedTaskManager manager){
        manager.clearAllEpics();
    }
    public static void deleteAllSubtasks(FileBackedTaskManager manager){
     manager.clearAllSubtasks();
    }

    public static void oldTest() {

        TaskManager manager = Managers.getDefault();

        int firstEpicId = manager.addEpic(new Epic("First epic", "First epic text"));

        int feFirstSubtask = manager.addSubtask(new Subtask(TaskStatus.NEW, "First epic, First subtask",
                "First epic, first subtask text", firstEpicId));
        int feSecondSubtask = manager.addSubtask(new Subtask(TaskStatus.NEW, "First epic, Second subtask",
                "First epic, Second subtask text", firstEpicId));
        int feThirdSubtask = manager.addSubtask(new Subtask(TaskStatus.NEW, "First epic, Third subtask",
                "First epic, Third subtask text", firstEpicId));

        int secondEpicId = manager.addEpic(new Epic("Secind epic", "Second epic text"));


        manager.getEpic(firstEpicId);
        manager.getSubtask(feFirstSubtask);
        manager.getSubtask(feSecondSubtask);
        manager.getSubtask(feThirdSubtask);
        manager.getEpic(secondEpicId);

        //manager.clearAllSubtasks(); Проверка удаления всех сабтасок

        List<Task> historyList = manager.getHistory();

        System.out.println("Тут задачи должны выйти в порядке создания\n");

        for (Task task : historyList) {
            System.out.println(task.toString());
        }

        System.out.println("Тут я удалил 2ую сабтаку, и вызвал 1й эпик из-за чего он должен стать последним в истории\n");
        manager.deleteSubtask(feSecondSubtask);
        manager.getEpic(firstEpicId);

        historyList = manager.getHistory();

        for (Task task : historyList) {
            System.out.println(task.toString());
        }

        System.out.println("А здесь я удалил 1й эпик с его сабтасками, и в истории остался только 2ой эпик\n");
        manager.deleteEpic(firstEpicId);

        historyList = manager.getHistory();

        for (Task task : historyList) {
            System.out.println(task.toString());
        }

        manager.deleteEpic(secondEpicId);

        System.out.println("Проверка удаления последнего элемента, списко должен быть пуст");

        historyList = manager.getHistory();

        for (Task task : historyList) {
            System.out.println(task.toString());
        }

    }
}
