import java.util.HashMap;

public class  Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        ObjectManager manager = new ObjectManager();
        Task task = new Task("NEW","First","First task");
        Task task1 = new Task("NEW","Second","Second task");
        int ftaskId = manager.addTask(task);
        int staskId = manager.addTask(task1);

        HashMap<Integer,Task> taskHashMap = manager.getAllTasks();
        Task taskTst = taskHashMap.get(ftaskId);
        Task task1Tst = taskHashMap.get(staskId);
        System.out.println(taskTst.toString());
        System.out.println(task1Tst.toString());

        Epic epic = new Epic("First epic", "First epic text" );
        int epicId = manager.addEpic(epic);
        Subtusk subtusk
                = new Subtusk("NEW","First epics", "First epicst text", epicId);
        Subtusk subtusk1
                = new Subtusk("DONE", "First epics 2", "First epicst text 2", epicId);
        int subtaskId = manager.addSubtask(subtusk);
        int subtask2Id = manager.addSubtask(subtusk1);

        HashMap<Integer,Subtusk> subtuskHashMap = manager.getAllSubtasks();
        System.out.println(subtuskHashMap.get(subtaskId));
        System.out.println(subtuskHashMap.get(subtask2Id));

        HashMap<Integer,Epic> epicHashMap = manager.getAllEpics();
        System.out.println(epicHashMap.get(epicId).toString());

        Subtusk subtusk2 = new Subtusk("DONE","First epics","First epics txt",epicId);
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
