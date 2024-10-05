package repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domein.Ontwikkelingskaart;
/**
 * klasse voor ontwikkelingskaartRepository, die alle ontwikkelingskaarten aanmaakt per niveau
 * @author Quinten
 *
 */
public class OntwikkelingskaartRepository {
	
	private final static int AANTAL_ONTWIKKELINGSNIVEAUS = 3;
	private List<Ontwikkelingskaart> ontwikkelingskaartenNiv1 = new ArrayList<>();
	private List<Ontwikkelingskaart> ontwikkelingskaartenNiv2 = new ArrayList<>();
	private List<Ontwikkelingskaart> ontwikkelingskaartenNiv3 = new ArrayList<>();
	/**
	 * constructor voor de ontwikkelingskaartRepository, roept 3 methodes aan die de ontwikkelingskaarten voor alle niveaus aanmaken
	 */
	public OntwikkelingskaartRepository() {
		ontwikkelingskaartenNiveau1();
		ontwikkelingskaartenNiveau2();
		ontwikkelingskaartenNiveau3();
	}

	/**
	 * vraagt de alle ontwikkelingskaarten op, worden per niveau terug gegeven
	 * @return List, met per niveau een List van Ontwikkelingskaart
	 */
	public List<List<Ontwikkelingskaart>> getOntwikkelingskaarten() {
		List<List<Ontwikkelingskaart>> ontkaarten = new ArrayList<List<Ontwikkelingskaart>>();
		Collections.shuffle(ontwikkelingskaartenNiv1);
		Collections.shuffle(ontwikkelingskaartenNiv2);
		Collections.shuffle(ontwikkelingskaartenNiv3);
		for(int i=0;i<AANTAL_ONTWIKKELINGSNIVEAUS;i++) {
			ontkaarten.add(new ArrayList<Ontwikkelingskaart>());
		}
		ontkaarten.get(0).addAll(ontwikkelingskaartenNiv1);
		ontkaarten.get(1).addAll(ontwikkelingskaartenNiv2);
		ontkaarten.get(2).addAll(ontwikkelingskaartenNiv3);
		return ontkaarten;
	}
	/**
	 * maakt objecten van Ontwikkelingskaart aan voor elke ontwikkelingskaart van niveau 3 in het spel
	 */
	private void ontwikkelingskaartenNiveau3() {
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(4,"robijn",6,0,3,0,3,"ontwikkelingskaart31.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(5,"onyx",0,0,0,3,7,"ontwikkelingskaart32.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(4,"onyx",3,0,0,3,6,"ontwikkelingskaart33.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(4,"smaragd",3,3,6,0,0,"ontwikkelingskaart34.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(5,"diamant",0,3,0,7,0,"ontwikkelingskaart35.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(3,"diamant",3,0,3,3,5,"ontwikkelingskaart36.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(5,"robijn",7,0,0,0,3,"ontwikkelingskaart37.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(3,"saffier",3,3,0,5,3,"ontwikkelingskaart38.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(3,"smaragd",0,5,3,3,3,"ontwikkelingskaart39.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(4,"robijn",7,0,0,0,0,"ontwikkelingskaart310.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(4,"saffier",0,7,0,0,0,"ontwikkelingskaart311.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(4,"onyx",0,0,0,0,7,"ontwikkelingskaart312.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(4,"diamant",0,0,0,7,0,"ontwikkelingskaart313.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(5,"smaragd",3,0,7,0,0,"ontwikkelingskaart314.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(4,"diamant",0,3,0,6,3,"ontwikkelingskaart315.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(4,"saffier",0,6,3,3,0,"ontwikkelingskaart316.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(4,"smaragd",0,0,7,0,0,"ontwikkelingskaart317.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(3,"onyx",5,3,3,0,3,"ontwikkelingskaart318.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(3,"robijn",3,3,5,3,0,"ontwikkelingskaart319.PNG",3));
		ontwikkelingskaartenNiv3.add(new Ontwikkelingskaart(5,"saffier",0,7,3,0,0,"ontwikkelingskaart320.PNG",3));
		
	}
	/**
	 * maakt objecten van Ontwikkelingskaart aan voor elke ontwikkelingskaart van niveau 2 in het spel
	 */
	private void ontwikkelingskaartenNiveau2() {
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"onyx",0,5,0,0,0,"ontwikkelingskaart21.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(1,"onyx",2,3,2,0,0,"ontwikkelingskaart22.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(1,"robijn",0,0,3,3,2,"ontwikkelingskaart23.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(1,"smaragd",0,2,3,2,0,"ontwikkelingskaart24.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"diamant",1,0,0,2,4,"ontwikkelingskaart25.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(1,"diamant",0,2,3,0,3,"ontwikkelingskaart26.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(1,"smaragd",2,3,0,0,3,"ontwikkelingskaart27.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"saffier",0,2,0,4,1,"ontwikkelingskaart28.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"robijn",0,0,0,5,0,"ontwikkelingskaart29.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"robijn",0,3,0,5,0,"ontwikkelingskaart210.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(1,"saffier",3,0,2,3,0,"ontwikkelingskaart211.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(1,"robijn",0,2,0,3,2,"ontwikkelingskaart212.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(3,"smaragd",6,0,0,0,0,"ontwikkelingskaart213.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"robijn",2,1,4,0,0,"ontwikkelingskaart214.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"saffier",0,0,5,0,0,"ontwikkelingskaart215.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"smaragd",3,0,5,0,0,"ontwikkelingskaart216.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"smaragd",0,4,2,1,0,"ontwikkelingskaart217.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"diamant",0,0,0,0,5,"ontwikkelingskaart218.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(3,"diamant",0,6,0,0,0,"ontwikkelingskaart219.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"onyx",4,0,1,0,2,"ontwikkelingskaart220.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(3,"saffier",0,0,6,0,0,"ontwikkelingskaart221.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"onyx",5,0,0,0,3,"ontwikkelingskaart222.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(1,"diamant",3,0,0,2,2,"ontwikkelingskaart223.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(1,"saffier",2,0,2,0,3,"ontwikkelingskaart224.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(1,"onyx",3,3,0,2,0,"ontwikkelingskaart225.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"smaragd",5,0,0,0,0,"ontwikkelingskaart226.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"diamant",0,0,0,3,5,"ontwikkelingskaart227.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(3,"onyx",0,0,0,6,0,"ontwikkelingskaart228.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(2,"saffier",0,5,3,0,0,"ontwikkelingskaart229.PNG",2));
		ontwikkelingskaartenNiv2.add(new Ontwikkelingskaart(3,"robijn",0,0,0,0,6,"ontwikkelingskaart230.PNG",2));
		
	}
	/**
	 * maakt objecten van Ontwikkelingskaart aan voor elke ontwikkelingskaart van niveau 1 in het spel
	 */
	private void ontwikkelingskaartenNiveau1() {
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(1,"onyx",0,0,4,0,0,"ontwikkelingskaart11.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"saffier",1,1,0,1,2,"ontwikkelingskaart12.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"saffier",0,1,0,2,0,"ontwikkelingskaart13.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"onyx",1,0,0,1,3,"ontwikkelingskaart14.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"robijn",0,3,0,0,0,"ontwikkelingskaart15.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"robijn",1,2,0,2,0,"ontwikkelingskaart16.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"smaragd",0,2,1,0,0,"ontwikkelingskaart17.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"onyx",3,0,0,0,0,"ontwikkelingskaart18.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"diamant",0,0,2,2,0,"ontwikkelingskaart19.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"smaragd",0,0,0,0,3,"ontwikkelingskaart110.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"robijn",0,1,0,3,1,"ontwikkelingskaart111.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"robijn",1,0,2,0,0,"ontwikkelingskaart112.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"onyx",2,2,0,0,0,"ontwikkelingskaart113.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"smaragd",0,1,1,1,1,"ontwikkelingskaart114.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"diamant",1,0,1,1,1,"ontwikkelingskaart115.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"onyx",0,2,2,0,1,"ontwikkelingskaart116.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"smaragd",1,1,3,0,0,"ontwikkelingskaart117.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"diamant",2,0,1,1,1,"ontwikkelingskaart118.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"diamant",2,0,2,1,0,"ontwikkelingskaart119.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"diamant",0,0,3,0,0,"ontwikkelingskaart120.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"onyx",1,1,1,0,1,"ontwikkelingskaart121.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"diamant",0,3,1,1,0,"ontwikkelingskaart122.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"onyx",2,0,0,0,1,"ontwikkelingskaart123.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"smaragd",0,0,1,2,2,"ontwikkelingskaart124.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"saffier",0,0,0,3,0,"ontwikkelingskaart125.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"robijn",1,2,1,1,0,"ontwikkelingskaart126.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"smaragd",0,1,1,2,1,"ontwikkelingskaart127.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"diamant",0,0,0,1,2,"ontwikkelingskaart128.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"saffier",3,0,1,0,1,"ontwikkelingskaart129.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"saffier",2,1,0,0,2,"ontwikkelingskaart130.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(1,"diamant",4,0,0,0,0,"ontwikkelingskaart131.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(1,"smaragd",0,0,0,4,0,"ontwikkelingskaart132.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"smaragd",0,0,2,0,2,"ontwikkelingskaart133.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"robijn",0,2,0,0,2,"ontwikkelingskaart134.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(1,"robijn",0,4,0,0,0,"ontwikkelingskaart135.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"saffier",2,0,0,2,0,"ontwikkelingskaart136.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"saffier",1,1,0,1,1,"ontwikkelingskaart137.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(1,"saffier",0,0,0,0,4,"ontwikkelingskaart138.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"robijn",1,1,1,1,0,"ontwikkelingskaart139.PNG",1));
		ontwikkelingskaartenNiv1.add(new Ontwikkelingskaart(0,"onyx",1,1,2,0,1,"ontwikkelingskaart140.PNG",1));
	}
	
}
