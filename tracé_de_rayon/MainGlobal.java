import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class MainGlobal {    
    public static void main (String[] args) throws RemoteException, ServerNotActiveException {
        Scanner sc = new Scanner(System.in);//pour communiquer
        Raytracer sdi;
        //TableauBlanc[]
        sdi = new Raytracer();
        ServiceRaytracer rd = (ServiceRaytracer) UnicastRemoteObject.exportObject(sdi, 0); 
        Registry reg = null;
        try {
            //reg = LocateRegistry.getRegistry("100.64.80.224", 1099);
            reg = LocateRegistry.getRegistry(1099);
        } catch (Exception e) {
        }

        reg.rebind("RayTracer", rd);/* Enregistrement de la référence sous le nom voulut */

	    Boolean exit = true;
	    while (exit){
            int oui = sc.nextInt();
            if(oui == 1){
                sdi.construireImage(100,100);
            }
            if(oui == 2){
                System.out.println("div x ?");
                int x = sc.nextInt();
                System.out.println("div y ?");
                int y = sc.nextInt();
                sdi.construireImage(x,y);
            }
	    }
    }
}