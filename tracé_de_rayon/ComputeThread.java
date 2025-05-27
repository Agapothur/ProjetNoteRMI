import raytracer.Image;
import raytracer.Disp;
import raytracer.Scene;

public class ComputeThread extends Thread {
    ServiceCompute sc;
    String fichier; int taille_x; int taille_y; int pose_x; int pose_y; Scene s; Position p;

    public ComputeThread(ServiceCompute sc,String fichier, int taille_x, int taille_y, int pose_x, int pose_y, Scene s, Position p){
        this.sc = sc;
        this.fichier = fichier;
        this.taille_x = taille_x;
        this.taille_y = taille_y;
        this.pose_x = pose_x;
        this.pose_y = pose_y;
        this.s = s;
        this.p = p;
    }

    public void run(){
        try {
            Image bout = this.sc.calculer_image(fichier, taille_x, taille_y, pose_x, pose_y, s);
            Raytracer.setImage(bout, Raytracer.disp, pose_x, pose_y);
            Raytracer.removepos(p);
        } catch (Exception e) {
            System.out.println(e);
            Raytracer.lsc.remove(sc);
        }
    }
    
}
