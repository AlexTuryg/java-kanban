package managers;

import tasks.Task;

import java.util.ArrayList;

/**
 * Этот класс имплементирует класс HistoryManager, и является его реализацией.
 * В этой реализации не учитываются повторяющиеся вызовы, и буфер всего на 10 элементов
 */
public class InMemoryHistoryManager implements HistoryManager {
    ArrayList<Task> tasksHistory = new ArrayList<>();

    @Override
    public void add(Task task) {
        //Добавил проверку на Null, и исправил опечатку
        if (task == null) return;
        if (tasksHistory.size() < 10) {
            tasksHistory.add(task);
        } else {
            for (int i = 0; i < 10; i++) {
                if (i==9) {
                    tasksHistory.set(i, task);
                    break;
                }
                /*
                "Можно было проще сделать удаление
                    tasksHisory.remove(0); "

                Не понял как это использовать, в моей реализации нулевой
                объект в листе просто перезаписывается первым, и так далее.
                 */

                tasksHistory.set(i, tasksHistory.get(i + 1));
            }
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        //Исправил возвращение и удалил забытый тут проверочный код
        return new ArrayList<>(tasksHistory);
    }
}
