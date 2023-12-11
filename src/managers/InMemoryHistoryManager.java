package managers;

import tasks.Task;

import java.util.ArrayList;

/**
 * Этот класс имплементирует класс HistoryManager, и является его реализацией.
 * В этой реализации не учитываются повторяющиеся вызовы, и буфер всего на 10 элементов
 */
public class InMemoryHistoryManager implements HistoryManager {
    private final ArrayList<Task> tasksHistory = new ArrayList<>();

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
                "В этом случае вы просто удаляет 0 элемента,
                 не нужно пробегаться по всем списку"

                Все, благодаря объяснению понял, я просто решил что
                элементы должны быть с 0 по 9 в ArrayList, и так будет
                проще в следующий раз с ними работать,
                в общем сам все усложнил
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
