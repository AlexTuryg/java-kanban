import managers.Managers;
import managers.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskTypes;

import java.util.ArrayList;

public class  Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager manager = Managers.getDefault();


        Task task0 = new Task(TaskTypes.NEW,"First","First task");
        Task task1 = new Task(TaskTypes.NEW,"Second","Second task");
        Task task2 = new Task(TaskTypes.NEW,"Third","Third task");
        Task task3 = new Task(TaskTypes.NEW,"Fourth","Fourth task");
        Task task4 = new Task(TaskTypes.NEW,"Fifth","Fifth task");


        int taskId0 = manager.addTask(task0);
        int taskId1 = manager.addTask(task1);
        int taskId2 = manager.addTask(task2);
        int taskId3 = manager.addTask(task3);
        int taskId4 = manager.addTask(task4);

        System.out.println("Тут должны вывестись все таски в количестве 5шт");

        ArrayList<Task> taskHashMap = manager.getAllTasks();
        for (Task taskTst : taskHashMap)
            System.out.println(taskTst.toString());


        Epic epic = new Epic("First epic", "First epic text" );
        int epicId = manager.addEpic(epic);
        Subtask subtusk0
                = new Subtask(TaskTypes.NEW,"First epics 0",  "First epicst text"  , epicId);
        Subtask subtusk1
                = new Subtask(TaskTypes.DONE,"First epics 2", "First epicst text 2", epicId);
        int subtask0Id = manager.addSubtask(subtusk0);
        int subtask1Id = manager.addSubtask(subtusk1);

        Epic epic2 = new Epic("Second epic", "Second epic text" );
        int epic2Id = manager.addEpic(epic2);
        Subtask subtuskSecond
                = new Subtask(TaskTypes.DONE,"Second epics 0", "Second epicst text"   , epic2Id);
        Subtask subtuskSecond1
                = new Subtask(TaskTypes.DONE, "Second epics 2", "Second epicst text 2", epic2Id);
        int secondId = manager.addSubtask(subtuskSecond);
        int second1Id = manager.addSubtask(subtuskSecond1);

        System.out.println("Тут должны выйти все сабтаски в количестве 4шт");

        ArrayList<Subtask> subtuskHashMap = manager.getAllSubtasks();
        for (Subtask subtaskTst : subtuskHashMap)
            System.out.println(subtaskTst.toString());

        System.out.println("Тут должны пойти все Епики 2шт");
        ArrayList<Epic> epicHashMap = manager.getAllEpics();
        System.out.println(epicHashMap.toString());

        System.out.println("Тут проверяется метод getEpicsSubtasks, который выводит сабтаски первого эпика");
        ArrayList<Subtask> subtasks = manager.getEpicsSubtasks(epicId);
        System.out.println("subtasks to GET");
        System.out.println(subtasks.toString());

        System.out.println("Тут проверяется заполняемость getHistory");
        for (int i = 0; i < 15; i++) {
           manager.getTask(taskId0);
        }
        ArrayList<Task> tasksHistory = (ArrayList<Task>) manager.getHistory();
        int iteration = 1;
        for (Task task : tasksHistory) {
            System.out.println(iteration++);
            System.out.println(task.toString());
        }

        System.out.println("Тут проверяется что getHistory нормально работает с другими типами задач");

        manager.getEpic(epicId);
        manager.getEpic(epicId);
        manager.getEpic(epicId);

        int lastTask = manager.addTask(new Task(TaskTypes.IN_PROGRESS,"Последний такск в истори просмотра","ТЕКСТ ПОСЛЕДНЕГО ТАКСА"));

        manager.getTask(lastTask);
        tasksHistory = (ArrayList<Task>) manager.getHistory();

        iteration = 1;
        for (Task task : tasksHistory) {
            System.out.println(iteration++);
            System.out.println(task.toString());
        }


    }
}
