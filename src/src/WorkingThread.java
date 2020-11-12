public class WorkingThread extends Thread{
    private ThreadSafeQueue taskList;
    private ThreadSafeQueue resultList;
    WorkingThread(ThreadSafeQueue tasks, ThreadSafeQueue results){
        this.taskList = tasks;
        this.resultList = results;
    }

    public void run(){
        System.out.println("Running the thread");
        Bpp bpp = new Bpp();
        while(!taskList.isEmpty()){
            int currTask = taskList.deleteTask()[0];
            int currResult = getFirstDigit(bpp.getDecimal(currTask));
            resultList.addTask(currTask, currResult);
            //System.out.print(currResult);
        }

    }

    private int getFirstDigit(int num){
        if(String.valueOf(num).length() < 9){
            return 0;
        }
        else{
            int newNum = Math.abs(num);
            return(int)Math.floor(newNum/Math.pow(10, Math.floor(Math.log10(newNum))));

        }
    }
}
