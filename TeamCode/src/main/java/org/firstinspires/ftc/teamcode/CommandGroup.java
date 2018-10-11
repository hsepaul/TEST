package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.commands.BasicCommand;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by David Austin on 11/22/2016.
 */

public class CommandGroup extends org.firstinspires.ftc.teamcode.commands.BasicCommand {
    ArrayList<org.firstinspires.ftc.teamcode.commands.BasicCommand> commands = new ArrayList<org.firstinspires.ftc.teamcode.commands.BasicCommand>();
    Iterator<org.firstinspires.ftc.teamcode.commands.BasicCommand> iterator;
    public void addCommand(org.firstinspires.ftc.teamcode.commands.BasicCommand cmd) {
        commands.add(cmd);
    }

    public void init() {
        iterator = commands.iterator();
        while (iterator.hasNext()) {
            iterator.next().init();
        }
    }

    public void execute() {
        iterator = commands.iterator();
        ArrayList<org.firstinspires.ftc.teamcode.commands.BasicCommand> toRemove = new ArrayList<org.firstinspires.ftc.teamcode.commands.BasicCommand>();
        while (iterator.hasNext()) {
            BasicCommand cmd = iterator.next();
            if (cmd.isFinished()) {
                cmd.stop();
                toRemove.add(cmd);
            } else {
                cmd.execute();
            }
        }
        Iterator removeIterator = toRemove.iterator();
        while (removeIterator.hasNext()) {
            commands.remove(removeIterator.next());
        }
    }

    public boolean isFinished() {
        return commands.isEmpty();
    }
}
