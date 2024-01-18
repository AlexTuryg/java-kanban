package managers;

import tasks.Task;

import java.util.*;

/**
 * Этот класс имплементирует класс HistoryManager, и является его реализацией.
 * В этой реализации не учитываются повторяющиеся вызовы, и буфер всего на 10 элементов
 */
public class InMemoryHistoryManager implements HistoryManager {
    private final ArrayList<Task> tasksHistory = new ArrayList<>();
    private final HashMap<Integer, Node> customLinked = new HashMap<>();
    private Node lastNode;
    private Node firstNode;

    public void add(Task task) {
        if (task != null) {
            if(tasksHistory.contains(task)){
                remove(task.getTaskId());
            }
            tasksHistory.add(task);
            linkLast(task);
        }
    }

    private void linkLast(Task task) {
        int taskId = task.getTaskId();
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

    @Override
    public void remove(int taskId) {
        tasksHistory.removeIf(task -> task.getTaskId() == taskId);
        removeNode(customLinked.get(taskId));
        customLinked.remove(taskId);
    }

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


