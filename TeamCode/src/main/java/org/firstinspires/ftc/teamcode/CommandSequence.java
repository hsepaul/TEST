package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.commands.BasicCommand;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by David Austin on 11/22/2016.
 */

public class CommandSequence extends org.firstinspires.ftc.teamcode.commands.BasicCommand {
    ArrayList<org.firstinspires.ftc.teamcode.commands.BasicCommand> commands = new ArrayList<org.firstinspires.ftc.teamcode.commands.BasicCommand>();
    Iterator<org.firstinspires.ftc.teamcode.commands.BasicCommand> iterator;
    org.firstinspires.ftc.teamcode.commands.BasicCommand current;
    public void addCommand(BasicCommand cmd) {
        commands.add(cmd);
    }

    static final int INIT = 0;
    static final int EXEC = 1;
    static final int STOP = 2;
    int state = INIT;

    public void init() {
        iterator = commands.iterator();
        if (iterator.hasNext()) {
            current = iterator.next();
            current.init();
            state = EXEC;
        } else {
            state = STOP;
        }
    }
    public void execute() {
        if (state == STOP) return;
        if (state == INIT) {
            current.init();
            state = EXEC;
            return;
        }
        if (current.isFinished()) {
            current.stop();
            if (iterator.hasNext()) {
                current = iterator.next();
                state = INIT;
            } else state = STOP;
        } else {
            current.execute();
        }
    }

    public boolean isFinished() {
        return state == STOP;
    }
}
