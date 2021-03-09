package com.ecolepratique.rapport.service;

import java.time.LocalDate;
import java.util.stream.Stream;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecolepratique.rapport.dao.RedacteurChercheurDaoItf;
import com.ecolepratique.rapport.dao.UserDaoItf;
import com.ecolepratique.rapport.dao.UserRoleDaoItf;
import com.ecolepratique.rapport.entite.RedacteurChercheur;
import com.ecolepratique.rapport.entite.User;
import com.ecolepratique.rapport.entite.UserRole;
import com.ecolepratique.rapport.entite.Utilisateur;


@Service
public class RedacteurChercheurService implements RedacteurChercheurServiceItf {
	@Autowired
	private RedacteurChercheurDaoItf redacteurChercheurDao;
	@Autowired
	private UserRoleDaoItf utilisateurRoleDao;
	@Autowired
	private UserDaoItf userDao;

	@RolesAllowed("ROLE_RH")
	@Override
	public RedacteurChercheur createRedacteurChercheur(RedacteurChercheur redacteurChercheur, String password) {
		System.out.println("hello" + redacteurChercheur);
		redacteurChercheur.setDateEmbauche(LocalDate.now());
		utilisateurRoleDao.save(new UserRole(redacteurChercheur.getLogin(), "RC"));
		userDao.save(new User(redacteurChercheur.getLogin(), password));
		return redacteurChercheurDao.save(redacteurChercheur);
	}

	@RolesAllowed("ROLE_RH")
	@Override
	public Stream<Utilisateur> listRedacteurChercheur() {
		return redacteurChercheurDao.findAll().stream().filter((user) -> user.getClass().getName() == "com.ecolepratique.rapport.entite.RedacteurChercheur");
	}

	@RolesAllowed("ROLE_RH")
	@Override
	public RedacteurChercheur findRedacteurChercheurById(String id) {
		return (RedacteurChercheur) redacteurChercheurDao.findById(id).get();
	}

	@RolesAllowed("ROLE_RH")
	@Override
	public RedacteurChercheur updateRedacteurChercheurByid(String id, RedacteurChercheur redacteurChercheur) {
		RedacteurChercheur ancienRedacteurChercheur = findRedacteurChercheurById(id);
		redacteurChercheur.setLogin(id);
		redacteurChercheur.setDateEmbauche(ancienRedacteurChercheur.getDateEmbauche());
		return redacteurChercheurDao.save(redacteurChercheur);
	}

	@RolesAllowed("ROLE_RH")
	@Override
	public RedacteurChercheur deleteRedacteurChercheurById(String id) {
		RedacteurChercheur redacteurChercheur = findRedacteurChercheurById(id);
		redacteurChercheurDao.deleteById(id);
		return redacteurChercheur;
	}

}