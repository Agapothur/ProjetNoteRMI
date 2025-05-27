import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MainLocal {    
    public static void main (String[] args) throws RemoteException, InterruptedException {
        Compute compute = new Compute();
        ServiceCompute stb = (ServiceCompute) UnicastRemoteObject.exportObject(compute, 0);
        Registry reg = null;
        try {
            reg = LocateRegistry.getRegistry(args[0], 1099);
        } catch (Exception e) {
        }
        ServiceRaytracer sd = null;
        try {
            sd = (ServiceRaytracer) reg.lookup("RayTracer");
        } catch (Exception e) {
            // TODO: handle exception
        }
        sd.enregistrerClient(stb);
        compute.setsd(sd);
    }
}
