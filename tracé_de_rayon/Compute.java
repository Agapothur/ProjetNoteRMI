import raytracer.Image;
import raytracer.Disp;
import raytracer.Scene;

public class Compute implements ServiceCompute {

    ServiceRaytracer sd;


    @Override
    public Image calculer_image(String fichier, int taille_x, int taille_y, int pose_x, int pose_y, Scene s) {
        System.out.println("Dessine à: x: " + pose_x + " y: " + pose_y);
        System.out.println("Taille: x " + taille_x + " y " + taille_y);
        Image image = s.compute(pose_x, pose_y, taille_x, taille_y);

        return image;
    }

    public void setsd(ServiceRaytracer s){
		this.sd = s;
	}
}