package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;

import java.io.*;
import java.util.*;

/**
 * Класс наследуется от InMemoryTaskManager и в нем появляется возможность сохранять Таски и Загружать их из файла
 * "Tasks.csv"
 */
public class FileBackedTaskManager extends InMemoryTaskManager {

    /**
     * Метод перебирает все данные которые хранятся в полях класса родителя, и сохраняет их в файл.
     * Вызывает метод save(), для того что бы разбить экземпляр класса Task на строки.
     */
    private void save() {
        try (FileWriter fileWriter = new FileWriter("Tasks.csv")) {
            if (!tasks.isEmpty()) {
                for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                    fileWriter.append(toString(entry.getValue()));
                }
            }
            if (!epics.isEmpty()) {
                for (Map.Entry<Integer, Epic> entry : epics.entrySet()) {
                    fileWriter.append(toString(entry.getValue()));
                }
            }
            if (!subtasks.isEmpty()) {
                for (Map.Entry<Integer, Subtask> entry : subtasks.entrySet()) {
                    fileWriter.append(toString(entry.getValue()));
                }
            }
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Принимает экземпляр класса Task, после чего разибвает его на строки в зафисимости от типа экзампляра
     * (Task,Subtask,Epic)
     * @param task
     * @return
     */
    private String toString(Task task) {
        String taskText = "";
        if (task instanceof Epic) {
            taskText = task.getTaskId() + "," + TaskType.EPIC.name() + "," + task.getTaskName() + "," +
                    task.getTaskStatus() + "," + task.getTaskText() + ",";

        } else if (task instanceof Subtask) {
            taskText = task.getTaskId() + "," + TaskType.SUBTASK.name() + "," + task.getTaskName() + "," +
                    task.getTaskStatus() + "," + task.getTaskText() + ",";

        } else {
            taskText = task.getTaskId() + "," + TaskType.TASK.name() + "," + task.getTaskName() + "," +
                    task.getTaskStatus() + "," + task.getTaskText() + ",";

        }
        return taskText;
    }

    /**
     * Статический метод считывает данные из файла "Tasks.csv", и полученные строковые данные отправляет
     * в метод fromString().
     * @throws IOException
     */
    public static void fileBackedTaskManager() throws IOException {
        ArrayList<String> linesOfTasks = loadFromFile(new File("Tasks.csv"));
        id = linesOfTasks.size();
        for (String taskInString : linesOfTasks) {
            fromString(taskInString);
        }
    }

    /**
     * Статический метод принимает строки и создает из них экземпляры класса Task и его наследников
     * @param value
     */
    private static void fromString(String value) {
        String[] taskValues = value.split(",");
        if (Objects.equals(taskValues[1], TaskType.TASK.name())) {
            tasks.put(Integer.parseInt(taskValues[0]), new Task(Integer.parseInt(taskValues[0]),
                    TaskStatus.valueOf(taskValues[3]), taskValues[2], taskValues[4]));
        } else if (Objects.equals(taskValues[1], TaskType.EPIC.name())) {
            epics.put(Integer.parseInt(taskValues[0]), new Epic(Integer.parseInt(taskValues[0]),
                    TaskStatus.valueOf(taskValues[3]), taskValues[2], taskValues[4]));
        } else {
            subtasks.put(Integer.parseInt(taskValues[0]), new Subtask(Integer.parseInt(taskValues[0]), TaskStatus.valueOf(taskValues[3]),
                    taskValues[2], taskValues[4], Integer.parseInt(taskValues[5])));
        }
    }

    /**
     * Метод считывает строки из передоваемого в него файла, и возвращает лист строк
     * @param file
     * @return
     * @throws IOException
     */

    static ArrayList<String> loadFromFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        ArrayList<String> lines = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            lines.add(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return lines;
    }

    @Override
    public void clearAllTasks() {
        super.clearAllTasks();
        save();
    }

    @Override
    public void clearAllEpics() {
        super.clearAllEpics();
        save();
    }

    @Override
    public void clearAllSubtasks() {
        super.clearAllSubtasks();
        save();
    }

    @Override
    public int addTask(Task newTask) {
        int taskId = super.addTask(newTask);
        save();
        return taskId;
    }

    @Override
    public int addEpic(Epic newEpic) {
        int taskId = super.addEpic(newEpic);
        save();
        return taskId;
    }

    @Override
    public int addSubtask(Subtask newSubtask) {
        int taskId = super.addSubtask(newSubtask);
        save();
        return taskId;
    }

    @Override
    public void changeTask(int taskId, Task task) {
        super.changeTask(taskId,task);
        save();
    }

    @Override
    public void changeSubtask(int subtaskId, Subtask subtask) {
        super.changeSubtask(subtaskId,subtask);
        save();
    }

    @Override
    public void changeEpic(int epicID, Epic epic) {
        super.changeEpic(epicID,epic);
        save();
    }

    @Override
    public void deleteTask(int taskId) {
        super.deleteTask(taskId);
        save();
    }

    @Override
    public void deleteEpic(int epicId) {
        super.deleteEpic(epicId);
        save();
    }

    @Override
    public void deleteSubtask(int subtaskId) {
        super.deleteSubtask(subtaskId);
        save();
    }

}
