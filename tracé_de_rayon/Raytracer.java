import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import raytracer.Disp;
import raytracer.Scene;
import raytracer.Image;

public class Raytracer implements ServiceRaytracer{
    public static List<ServiceCompute> lsc;

    String fichier = "simple.txt";

    int taille_x = 500;

    int taille_y = 500;

    static Disp disp;

    static ArrayList<Position> positions;

    public Raytracer(String s, int x, int y){
        lsc = new ArrayList<>();
        fichier = s;
        taille_x = x;
        taille_y = y;
    }

    public Raytracer(){
        lsc = new ArrayList<>();
    }

    public synchronized Disp construireImage(int div1,int div2) throws RemoteException, ServerNotActiveException{
        positions = new ArrayList<>();
        disp = new Disp("Raytracer", taille_x, taille_y);

        Scene scene = new Scene(fichier, taille_x, taille_y);


        int tailledecoupex = (taille_x) / div1;
        int tailledecoupey = (taille_y) / div2;

        System.out.println(tailledecoupex);

                System.out.println(tailledecoupey);

        for (int i = 0; i < taille_x; i += tailledecoupex) {
            for (int j = 0; j < taille_y; j+= tailledecoupey ){
                positions.add(new Position(i, j));
            }
        }
        
        //for (int i = 0; i<lsc.size(); i++){
         //   int xdecoupe = taille_x/lsc.size();
          //  Image bout = lsc.get(i).calculer_image(fichier, xdecoupe,taille_y,(xdecoupe*i),0);
        //    disp.setImage(bout, (xdecoupe*i), 0);
        //}
        
        while (positions.size() != 0) {
            Random r = new Random();
            for(int i = 0; i < lsc.size(); i++){
                try {
                    Position p = positions.get(r.nextInt(positions.size()));
                    System.out.println(positions.size());
                    int posx = p.posx;
                    int posy = p.posy;
                    ComputeThread thread = new ComputeThread(lsc.get(i), fichier, tailledecoupex, tailledecoupey, posx, posy, scene, p);
                    //Image bout = lsc.get(i).calculer_image(fichier, tailledecoupex, tailledecoupey, posx, posy, scene);
                    thread.start();
                    //positions.remove(p);
                    //disp.setImage(bout, posx, posy);
                } catch (Exception e) {
                    lsc.remove(i);
                }
            }
        }

        return disp;
    
    }

    public static synchronized void setImage(Image bout, Disp disp, int posx, int posy){
        disp.setImage(bout, posx, posy);
    }

    public static synchronized void removepos(Position p){
        positions.remove(p);
    }


    public void enregistrerClient(ServiceCompute var1) throws RemoteException, InterruptedException{
        System.out.println("3");
    	String host = "pas d'ip trouvé";
	try{
		host = RemoteServer.getClientHost();
	}catch(ServerNotActiveException e) { 

    }
	System.out.println("ip de l'utilisateur : " + host);
	
    lsc.add(var1);
    }
}
