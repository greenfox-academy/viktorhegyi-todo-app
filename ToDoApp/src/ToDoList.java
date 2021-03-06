import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hegyi on 2017-04-06.
 */
public class ToDoList {

  List<String> todolistFile = null;
  List<String> usageFile = null;
  Path todolistPath = Paths.get("todolist.txt");
  String [] args;

  public ToDoList(String[] args) {
    this.args = args;
  }

  void ToDoList() {
    try {
      todolistFile = Files.readAllLines(todolistPath);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Uh-oh, could not read the file!");
    }
    if ( todolistFile.size() == 0 ) {
      System.out.println("No todos for today! :)");
    } else
      for (int i = 0; i <todolistFile.size() ; i++) {
        System.out.println((i + 1) + " - " + todolistFile.get(i));
      }
  }

  void usage() {
    try {
      Path usagePath = Paths.get("usage.txt");
      usageFile = Files.readAllLines(usagePath);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Uh-oh, could not read the file!");
    }
    for (int i = 0; i < usageFile.size(); i++) {
      System.out.println(usageFile.get(i));
    }
  }

  void addTodolist() {
    todolistPath = Paths.get("todolist.txt");
    List<String> newTask;

    try {
      if (args.length == 1) {
        System.out.println("Unable to remove: no task provided");
      } else {
      newTask = Files.readAllLines(todolistPath);
      newTask.add(newTask.size(), "[ ] " + args[1]);
      Files.write(todolistPath,newTask);
        }
      } catch (Exception e) {
      System.out.println("Uh-oh, could not read the file!");
    }
  }

  void removeTodolist() {
    todolistPath = Paths.get("todolist.txt");
    List<String> removeTask;
    try {
      removeTask = Files.readAllLines(todolistPath);
      if (args.length == 1) {
        System.out.println("Unable to remove: no index provided");
      } else if ( Integer.parseInt(args[1]) > removeTask.size()) {
        System.out.println("Unable to remove: index is out of bound");
      } else {
      removeTask.remove(Integer.parseInt(args[1]) - 1);
      Files.write(todolistPath,removeTask);
        }
    } catch (Exception e) {
      System.out.println("Unable to remove: index is not a number");
      }
  }

  void checkTask() {
    todolistPath = Paths.get("todolist.txt");
    List<String> checkedTask;
    String doneTask;

    try {
      checkedTask = Files.readAllLines(todolistPath);
      if (args.length == 1) {
        System.out.println("Unable to check: no index provided");
        } else if ( Integer.parseInt(args[1]) > checkedTask.size()) {
        System.out.println("Unable to check: index is out of bound");
        } else {
      doneTask = checkedTask.get(Integer.parseInt(args[1]) - 1);
      String newString = doneTask.replaceFirst(" ", "x");
      checkedTask.set((Integer.parseInt(args[1]) - 1), newString);
      Files.write(todolistPath, checkedTask);
        }
    } catch (Exception e) {
      System.out.println("Uh-oh, could not read the file!");
    }
  }

}