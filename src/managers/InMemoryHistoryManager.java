package managers;

import tasks.Task;

import java.util.*;

/**
 * Этот класс имплементирует класс HistoryManager, и является его реализацией.
 * В этой реализации учитываются повторяющиеся вызовы, и удаление TASKов.
 */
public class InMemoryHistoryManager implements HistoryManager {
    private final HashMap<Integer, Node> customLinked = new HashMap<>();
    private Node lastNode;
    private Node firstNode;

    /**
     * Метод принимает объект Task и добавляет его в историю просмотра.
     * В случае если такой объект уже вызывался, он помещается на верх истории, а прошлый
     * вызов удаляется.
     * @param task
     */
    public void add(Task task) {
        if (task != null) {
            int taskId = task.getTaskId();
            if(customLinked.containsKey(taskId)){
                remove(taskId);
            }
            linkLast(task);
        }
    }

    /**
     * Метод принимает ID таски, и делает его ключем.
     * А так же в методе записываются связи между тасками
     * @param task
     */
    private void linkLast(Task task) {

        final Node node = new Node<>(task, null, null);
        Node l = lastNode;
        if (firstNode == null) firstNode = node;
        if (l == null)
        {
            lastNode = node;
        }
        else {
            l.next = node;
            node.prev = l;
            lastNode = node;
        }
        customLinked.put(task.getTaskId(),node);
    }

    /**
     * Метод получает данные из ноды в порядке их вызова
     * @return
     */
    private ArrayList<Task> getTasks() {
        ArrayList<Task> tasksInCorrect = new ArrayList<>();
        Node node = firstNode;

        while (node != null){
            tasksInCorrect.add((Task) node.data);
            node = node.next;
        }

        return tasksInCorrect;
    }

    /**
     * Метод возвращает лист тасок
     * @return
     */
    @Override
    public ArrayList<Task> getHistory() {
        return getTasks();
    }

    /**
     * Метод принимает id таски которую нужно удалить из истории, и удалет ее из списка тасков и из связанного списка
     * @param taskId
     */
    @Override
    public void remove(int taskId) {
        removeNode(customLinked.get(taskId));
        customLinked.remove(taskId);
    }

    /**
     * Мето переопределяющий связи между нодами связанного списка
     * @param node
     */
    private void removeNode(Node node) {
        if (node == null) return;
        Node next = node.next;
        Node prev = node.prev;
        if(prev == next) return;
        if (prev == null){
            next.prev = null;
            firstNode = next;
        }else {
            prev.next = next;
        }

        if(next == null){
            prev.next = null;
            lastNode = prev;
        }else {
            next.prev = prev;
        }
    }
    class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> prev;

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}


