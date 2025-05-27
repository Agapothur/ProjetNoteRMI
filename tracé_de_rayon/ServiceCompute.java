import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import raytracer.Image;
import raytracer.Scene;

public interface ServiceCompute extends Remote{
    
    public Image calculer_image(String fichier, int taille_x, int taille_y,int pose_x , int pose_y, Scene s)throws RemoteException, ServerNotActiveException;
    
    public void setsd(ServiceRaytracer s) throws RemoteException, ServerNotActiveException;
}
