package command;

import exception.EmptyException;
import basic.TaskList;
import basic.Ui;
import task.Deadline;

/**
 * Adds a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {

    private String input;

    /**
     * @param input A String inputted by the user.
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * @param tasks   The tasks stored in an ArrayList.
     * @param ui      The User Interface (UI).
     * @throws EmptyException If an empty description is inputted.
     */
    @Override
    public void execute(TaskList tasks, Ui ui ) throws EmptyException {
        int position;
        input = input.toLowerCase();
        if (input.contains("deadline")) {
            input = input.replace("deadline", "");
        }
        else{
            input = input.replaceFirst("d", "");
        }
        if (input.contains("/by")) {
            position = input.indexOf("/by");
        }
        else {
            position = -1;
        }
        boolean isInputEmpty = input.substring(0, position - 1).equals("");
        boolean isInputEmptySpace = input.substring(0, position - 2).equals(" ");
        // Add task if command contains "/by" and descriptions
        if (position != -1 && (!isInputEmpty || !isInputEmptySpace )) {
            String date = input.substring(position + 4);
            input = input.substring(0, position - 1);
            Deadline deadline = new Deadline(input, date);
            tasks.addTask(deadline);
            ui.showAdded();
            ui.printTaskNum(tasks, deadline);
        }
        else {
            throw new EmptyException("a deadline");
        }
    }

}