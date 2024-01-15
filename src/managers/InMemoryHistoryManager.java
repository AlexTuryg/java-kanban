package managers;

import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Этот класс имплементирует класс HistoryManager, и является его реализацией.
 * В этой реализации не учитываются повторяющиеся вызовы, и буфер всего на 10 элементов
 */
public class InMemoryHistoryManager implements HistoryManager {
    private final ArrayList<Task> tasksHistory = new ArrayList<>();
    private final HashMap<Integer, Node> customLinked = new HashMap<>();
    int size = 0;
    private Node<Integer> lastNode;
    private Node<Integer> fitstNode;

    @Override
    public void add(Task task) {

        if (task == null) return;

        tasksHistory.add(task);
        linkLast(task.getTaskId());

    }

    private void linkLast(Integer taskId) {
        Node<Integer> l = lastNode;
        Node<Integer> newNode = new Node<>(taskId, null, l);

        if (l == null) {
            customLinked.put(taskId, newNode);
            fitstNode = new Node<>(taskId, null, null);
        } else {
            lastNode.next = newNode;

            if (customLinked.containsKey(taskId)) {
                remove(taskId);
            }
            customLinked.put(taskId, newNode);
        }
        lastNode = newNode;
        size++;
    }

    private ArrayList<Task> getTasks() {

        ArrayList<Task> tasks = new ArrayList<>();

        if (size == 0) return tasks;

        Node<Integer> curentNode = fitstNode;

        for (int i = 0; i < size; i++) {
            tasks.add(tasksHistory.get(curentNode.data));
            curentNode = curentNode.next;
        }

        return tasks;
    }

    @Override
    public ArrayList<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void remove(int taskId) {
        Node.removeNode(customLinked.get(taskId));
        customLinked.remove(taskId);
    }

}

class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> prev;

    public Node(T data, Node<T> next, Node<T> prev) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public static void removeNode(Node node) {
        Node prev = node.prev;
        prev.next = node.next;

        Node next = node.next;
        next.prev = node.prev;
    }
}
