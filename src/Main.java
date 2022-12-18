import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Chatting ob = new Chatting();
        new Th1(ob);
        new Th2(ob);
        new Th3(ob);
    }
}


class Chatting{
    Scanner sc = new Scanner(System.in);
    int flag = 0;

    public synchronized void user1talk() throws InterruptedException{
        while(flag == 1 || flag == 2){
            wait();
        }
        System.out.print("User1: ");
        String str1 = sc.nextLine().toLowerCase();

        if(str1.equals("bye")){
            System.out.println("User 1 has left the chat.");
            System.exit(0);
        }
        flag = 1;
        notifyAll();
    }
    public synchronized void user2talk() throws InterruptedException{
        while(flag == 0 || flag == 2){
            wait();
        }
        System.out.print("User2: ");
        String str2 = sc.nextLine().toLowerCase();

        if(str2.equals("bye")){
            System.out.println("User 2 has left the chat.");
            System.exit(0);
        }
        flag = 2;
        notifyAll();
    }
    public synchronized void user3talk() throws InterruptedException{
        while(flag == 0 || flag == 1){
            wait();
        }
        System.out.print("User3: ");
        String str3 = sc.nextLine().toLowerCase();


        if(str3.equals("bye")){
            System.out.println("User 3 has left the chat.");
            System.exit(0);
        }
        flag = 0;
        notifyAll();
    }
}


class Th1 implements Runnable{
    Chatting ch;

    Th1(Chatting ch){

        this.ch = ch;
        new Thread(this, "Th1").start();
    }

    public void run(){
        try{
            while(true){
                ch.user1talk();
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}


class Th2 implements Runnable{
    Chatting ch;

    Th2(Chatting ch){

        this.ch = ch;
        new Thread(this, "Th2").start();
    }

    public void run(){
        try{
            while(true){
                ch.user2talk();
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}


class Th3 implements Runnable{

    Chatting ch;


    Th3(Chatting ch){
        this.ch = ch;
        new Thread(this, "Th3").start();
    }

    public void run(){
        try{
            while(true){
                ch.user3talk();
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}







































































