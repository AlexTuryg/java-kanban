package managers;

import tasks.Task;

import java.util.*;

/**
 * Этот класс имплементирует класс HistoryManager, и является его реализацией.
 * В этой реализации учитываются повторяющиеся вызовы, и удаление TASKов.
 */
public class InMemoryHistoryManager implements HistoryManager {
    private final ArrayList<Task> tasksHistory = new ArrayList<>();
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
            if(tasksHistory.contains(task)){
                remove(task.getTaskId());
            }
            tasksHistory.add(task);
            linkLast(task.getTaskId());
        }
    }

    /**
     * Метод принимает ID таски, и делает его ключем.
     * А так же в методе записываются связи между тасками
     * @param taskId
     */
    private void linkLast(Integer taskId) {
        Node node = new Node<>(taskId, null, null);
        Node l = lastNode;
        if (firstNode == null && customLinked.isEmpty()) firstNode = node;
        if (l == null)
        {
            lastNode = node;
        }
        else {
            l.next = node;
            node.prev = l;
            lastNode = node;
        }
        customLinked.put(taskId,node);
    }

    /**
     * В мтеоде создается два списка, первый является порядком вызовов Тасок, второй является копией истори.
     * Во время выполения алгоритма из копии customLinked стираются отсортированные в taskInCorrect элементы,
     * цикл выполняется пока copyOfCustomLinked не опустеет.
     * @return
     */
    private ArrayList<Integer> getTasks() {
        ArrayList<Integer> tasksInCorrect = new ArrayList<>();
        HashMap<Integer,Node> copyOfCustomLinked = new HashMap<>();
        copyOfCustomLinked.putAll(customLinked);
        Node node = firstNode;

        while (!copyOfCustomLinked.isEmpty()){
            for (Map.Entry<Integer,Node> entry : customLinked.entrySet()){
                if (Objects.equals(node,entry.getValue())){
                    tasksInCorrect.add(entry.getKey());
                    copyOfCustomLinked.remove(entry.getKey());
                    break;
                }
            }
            node = node.next;
        }

        return tasksInCorrect;
    }

    /**
     * Метод получает отсоритрованную историю из метода getTasks(), и сортирует уже лист тасок
     * которые в последствии в осторированном виде передает вызывающему его объекту.
     * @return
     */
    @Override
    public ArrayList<Task> getHistory() {
        ArrayList<Integer> tasksInCorrect = getTasks();
        ArrayList<Task> history = new ArrayList<>();
        for (Integer i : tasksInCorrect)
        {
            for (Task task : tasksHistory){
                if(task.getTaskId() == i) history.add(task);
            }
        }
        return history;
    }

    /**
     * Метод принимает id таски которую нужно удалить из истории, и удалет ее из списка тасков и из связанного списка
     * @param taskId
     */
    @Override
    public void remove(int taskId) {
        tasksHistory.removeIf(task -> task.getTaskId() == taskId);
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

        if (prev == null){
            firstNode = next;
        }else {
            prev.next = next;
            node.prev = null;
        }

        if(next == null){
            lastNode = prev;
        }else {
            next.prev = prev;
            node.next = null;
        }
        node.data = null;
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
    }
}


