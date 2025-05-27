import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import raytracer.Disp;

//interface pour le service
public interface ServiceRaytracer extends Remote{
    public Disp construireImage(int div1, int div2) throws RemoteException, ServerNotActiveException;

    public void enregistrerClient(ServiceCompute var1) throws RemoteException, InterruptedException;
}
