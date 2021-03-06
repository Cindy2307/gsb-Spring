package com.ecolepratique.rapport;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.ecolepratique.rapport.dao.RapportDaoItf;
import com.ecolepratique.rapport.dao.RedacteurChercheurDaoItf;
import com.ecolepratique.rapport.dao.RhDaoItf;
import com.ecolepratique.rapport.dao.UserDaoItf;
import com.ecolepratique.rapport.dao.UserRoleDaoItf;
import com.ecolepratique.rapport.dao.VisiteurDaoItf;
import com.ecolepratique.rapport.entite.Rapport;
import com.ecolepratique.rapport.entite.RedacteurChercheur;
import com.ecolepratique.rapport.entite.Rh;
import com.ecolepratique.rapport.entite.User;
import com.ecolepratique.rapport.entite.UserRole;
import com.ecolepratique.rapport.entite.Visiteur;
import com.ecolepratique.rapport.service.RapportServiceItf;
import com.ecolepratique.rapport.service.RhServiceItf;
import com.ecolepratique.rapport.service.UtilisateurServiceItf;

@SpringBootApplication
public class RapportVisiteBdApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(RapportVisiteBdApplication.class, args);
		RapportDaoItf  rapportDao = ctx.getBean(RapportDaoItf.class);
		VisiteurDaoItf visiteurDao = ctx.getBean(VisiteurDaoItf.class);
		UserRoleDaoItf utilisateurRoleDao = ctx.getBean(UserRoleDaoItf.class);
		UserDaoItf userDao = ctx.getBean(UserDaoItf.class);
		RedacteurChercheurDaoItf redacteurChercheurDao = ctx.getBean(RedacteurChercheurDaoItf.class);
		RhDaoItf rhDao = ctx.getBean(RhDaoItf.class);
		
		Visiteur visiteur1 = new Visiteur("Coco", "ollive", "cindy", "108 bd de la valbarelle BT D5", 13011, "Marseille", LocalDate.of(2021, 04, 20), LocalDate.of(1995, 07, 23));
		User user1 = new User("Coco", "Coucou_13");
		utilisateurRoleDao.save(new UserRole("Coco","VIS"));
		visiteurDao.save(visiteur1);
		userDao.save(user1);
		Rapport rapport1 = new Rapport(LocalDate.of(2018, 1, 14), "J'ai pr??sent?? le m??dicament HELICIDINE au g??n??raliste, Monsieur Baturin, qui a une action s??dative sur la toux. "
				+ "Il est utilis?? dans le traitement symptomatique des toux s??ches. Le praticien a paru ??tre tr??s interess??. Je lui ai laiss?? 4 ??chantillons afin qu'il puisse le tester"
				+ " J'ai ??t?? agr??ablement de son retour et esp??re qu'il sera un contact moteur pour l'avenir. Cette visite est une totale r??ussite."
				, "Ce praticien n'a pas encore ??t?? visit??");
		Rapport rapport2 = new Rapport(LocalDate.of(2020, 10, 22), "J'ai pr??sent?? notre nouveau m??dicament, HELICIDINE, au pneumologue, Monsieur Raphaelle, qui a une action s??dative sur la toux. \"\n" + 
				"				+ \"Il est utilis?? dans le traitement symptomatique des toux s??ches. Le praticien a paru septique. Je lui ai laiss?? 6 ??chantillons afin qu'il puisse le tester et lui prouv?? l'efficacit?? de ce m??dicament\"\n" + 
				"				+ \" Son retour n'a pas ??t?? bon, je lui ai dit que je le recontacterais dans 3 mois, le temps qu'il teste le m??dicament et qu'il puisse faire un prmeier retour. Cette visite a ??t?? d??licate.\"\n" + 
				"				, \"Ce praticien n'a pas encore ??t?? visit??", "Ce praticien ne connaissait pas notre nouveau m??dicament, l'HELICIDINE");
		rapportDao.save(rapport1);
		rapportDao.save(rapport2);
		visiteur1.addRapport(rapport1);
		visiteur1.addRapport(rapport2);
		visiteurDao.save(visiteur1);
		
		Visiteur visiteur2 = new Visiteur("Coco2", "ollive2", "cindy2", "108 bd de la valbarelle BT D4", 13012, "Marseille", LocalDate.of(2021, 04, 20), LocalDate.of(1995, 07, 25));
		User user2 = new User("Coco2", "Coucou2_13");
		utilisateurRoleDao.save(new UserRole("Coco2","VIS"));
		visiteurDao.save(visiteur2);
		userDao.save(user2);
		rapport1 = new Rapport(LocalDate.of(2016, 04, 10), "J'ai pr??sent?? notre m??dicament SPEDIFEN, au g??n??raliste Monsieur Touffart. Ce m??dicament est un anti-inflammatoire non st??ro??dien (AINS). Il lutte contre la douleur et fait baisser la fi??vre. Ses propri??t??s anti-inflammatoires se manifestent ?? forte dose.\n" + 
				"\n" + 
				"Il est utilis?? dans le traitement de courte dur??e de la fi??vre et de la douleur (maux de t??te, douleurs dentaires, courbatures, r??gles douloureuses...). Monsieur Touffart a sembl?? ??tre tr??s int??ress?? par les r??sultats des ??tudes que je lui ai pr??sent??. Je lui ai laiss?? 2 ??chantillons.", "Quand j'ai contact?? ce praticien, il ??tait demandeur d'une rencontre.");
		rapport2 = new Rapport(LocalDate.of(2018, 01, 12), "J'ai pr??sent?? notre nouveau m??dicament l'IXPRIM, ?? l'infirmi??re Madame Bichard qui officie ?? l'h??pital conception. Traitement symptomatique des douleurs mod??r??es ?? intenses.\n" + 
				"L'utilisation d'Ixprim doit ??tre limit??e aux patients dont la douleur mod??r??e ?? intense n??cessite un traitement par une association de parac??tamol et de tramadol. Madame Bichard avait tr??s peu de temps et ?? l'air d??bord??e en cette p??riode. en fin d'entretin je lui ai promis de revenir la voir quand elle aura plus de disponibilit??.", "Premi??re rencontre de cette praticienne. Permet d'??tablir un contact");
		Rapport rapport3 = new Rapport(LocalDate.of(2014, 01, 14), "J'ai rencontr?? le docteur Mabuse qui est cardilogue ?? l'h??pital bichat pour lui pr??senter notre m??dicament le DAFLON. Ce m??dicament est un veinotonique et un protecteur vasculaire. Il stimule la circulation du sang dans les veines et lutte contre l'alt??ration des vaisseaux capillaires. Le praticien a sembl?? tr??s "
				+ "int??ress?? par les ??tudes d'impact que je lui ai pr??sent??. Il avait des questions tr??s pr??cise et semble ??tre tr??s comp??tent dans son domaine",
				"Praticien qui a une grande influence dans le milieur de la cardiologie");
		rapportDao.save(rapport1);
		rapportDao.save(rapport2);
		rapportDao.save(rapport3);
		visiteur2.addRapport(rapport1);
		visiteur2.addRapport(rapport2);
		visiteur2.addRapport(rapport3);
		visiteurDao.save(visiteur2);
		
		RedacteurChercheur redacteur1 = new RedacteurChercheur("Coco3", "ollive3", "cindy3", "108 bd de la valbarelle BT D2", 13013, "Marseille", LocalDate.of(2021, 04, 20), LocalDate.of(1995, 07, 27));
		User user3 = new User("Coco3", "Coucou3_13");
		utilisateurRoleDao.save(new UserRole("Coco3","RC"));
		redacteurChercheurDao.save(redacteur1);
		userDao.save(user3);
		
		Rh rh1 = new Rh("Coco4", "ollive4", "cindy4", "108 bd de la valbarelle BT D1", 13014, "Marseille", LocalDate.of(2021, 04, 20), LocalDate.of(1995, 07, 31));
		User user4 = new User("Coco4", "Coucou4_13");
		utilisateurRoleDao.save(new UserRole("Coco4","RH"));
		rhDao.save(rh1);
		userDao.save(user4);
		
		Rh rh2 = new Rh("Coco5", "ollive5", "cindy5", "108 bd de la valbarelle BT D2", 13010, "Marseille", LocalDate.of(2021, 04, 20), LocalDate.of(1995, 07, 3));
		User user5 = new User("Coco5", "Coucou5_13");
		utilisateurRoleDao.save(new UserRole("Coco5","RH"));
		rhDao.save(rh2);
		userDao.save(user5);
		
		Visiteur visiteur3 = new Visiteur("Benji", "Benjamin", "desMarseillais", "108 bd de la valbarelle BT D5", 13011, "Marseille", LocalDate.of(2021, 04, 20), LocalDate.of(1995, 07, 23));
		User user6 = new User("Benji", "Coucou6_13");
		utilisateurRoleDao.save(new UserRole("Benji","VIS"));
		visiteurDao.save(visiteur3);
		userDao.save(user6);
		
		rapportDao.save(rapport2);
	}
}


