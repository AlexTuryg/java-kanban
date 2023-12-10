package manage;

import taskTypes.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    ArrayList<Task> tasksHisory = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (tasksHisory.size() < 10) {
            tasksHisory.add(task);
        } else {
            for (int i = 0; i < 10; i++) {
                if (i==9) {
                    tasksHisory.set(i, task);
                    break;
                }
                tasksHisory.set(i, tasksHisory.get(i + 1));
            }
        }
    }

    @Override
    public ArrayList<Task> getHistory() {

        System.out.println(tasksHisory.size());
        return tasksHisory;
    }
}
