import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;


public class OutputThread extends Thread {
    private ThreadSafeQueue resultsQueue;

    OutputThread(ThreadSafeQueue results){
        this.resultsQueue = results;
    }

    public void run(){
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;
        try{
            terminal = defaultTerminalFactory.createTerminal();
            terminal.clearScreen();
            TerminalPosition pos = terminal.getCursorPosition();
            terminal.setCursorPosition(pos);
            int x = pos.getColumn();
            int y = pos.getRow();

            terminal.putCharacter('3');
            terminal.setCursorPosition(x+1, y);
            terminal.putCharacter('.');
            x += 2;

            for(int i = 0; i < 1024; i ++){
                Integer[] task = resultsQueue.deleteTask();
                int row = task[0] / 64;
                int col = task[0] % 64;
                terminal.setCursorPosition(x + col, y + row);
                terminal.putCharacter((char)(task[1] + (int)'0'));
                terminal.flush();
                Thread.sleep(10);



            }

        }
        catch(IOException | InterruptedException e){
            e.printStackTrace();
        }


    }


}
