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

        ArrayList<Subtask> subtuskHashMap = manager.getAllSubtasks();
        for (Subtask subtaskTst : subtuskHashMap)
            System.out.println(subtaskTst.toString());

        ArrayList<Epic> epicHashMap = manager.getAllEpics();

        System.out.println(epicHashMap.toString());

        Subtask subtusk2 = new Subtask("DONE","First epics","First epics txt",epicId);
        manager.changeSubtask(subtaskId,subtusk2);
        System.out.println(manager.getEpic(epicId).toString());
        manager.deleteSubtask(subtaskId);

        System.out.println(manager.getAllSubtasks().size());
        System.out.println(manager.getAllEpics().size());

        manager.clearAllEpics();

        System.out.println(manager.getAllSubtasks().size());
        System.out.println(manager.getAllEpics().size());

    }
}
