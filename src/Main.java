import java.util.ArrayList;
import java.util.HashMap;

public class  Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager manager = new TaskManager();
        Task task = new Task("NEW","First","First task");
        Task task1 = new Task("NEW","Second","Second task");
        int ftaskId = manager.addTask(task);
        int staskId = manager.addTask(task1);

        ArrayList<Task> taskHashMap = manager.getAllTasks();
        for (Task taskTst : taskHashMap)
            System.out.println(taskTst.toString());

        Epic epic = new Epic("First epic", "First epic text" );
        int epicId = manager.addEpic(epic);
        Subtask subtusk
                = new Subtask("NEW","First epics", "First epicst text", epicId);
        Subtask subtusk1
                = new Subtask("DONE", "First epics 2", "First epicst text 2", epicId);
        int subtaskId = manager.addSubtask(subtusk);
        int subtask2Id = manager.addSubtask(subtusk1);

        Epic epic2 = new Epic("Second epic", "Second epic text" );
        int epic2Id = manager.addEpic(epic2);
        Subtask subtuskSecond
                = new Subtask("NEW","Second epics", "Second epicst text", epic2Id);
        Subtask subtuskSecond1
                = new Subtask("DONE", "Second epics 2", "Second epicst text 2", epic2Id);
        int SecondId = manager.addSubtask(subtuskSecond);
        int Second1Id = manager.addSubtask(subtuskSecond1);



        ArrayList<Subtask> subtuskHashMap = manager.getAllSubtasks();
        for (Subtask subtaskTst : subtuskHashMap)
            System.out.println(subtaskTst.toString());

        ArrayList<Epic> epicHashMap = manager.getAllEpics();

        System.out.println(epicHashMap.toString());

        ArrayList<Subtask> subtasks = manager.getEpicsSubtasks(epicId);
        System.out.println("subtasks to GET");
        System.out.println(subtasks.toString());

    }
}
