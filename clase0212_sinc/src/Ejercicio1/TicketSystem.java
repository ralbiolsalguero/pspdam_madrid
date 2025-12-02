package Ejercicio1;

public class TicketSystem {
    private int tickets = 20;
    private MiLock lock = new MiLock();

    public boolean comprarTicket(String cliente){
        boolean comprado = false;

        try {
            lock.lock();

            if(tickets > 0){
                System.out.println(cliente + " ha comprado un ticket. Quedan: "+(tickets-1));
                tickets--;
                comprado = true;
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }

        return comprado;


    }
}
