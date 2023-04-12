package com.greedy.springjpa.menu.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.greedy.springjpa.menu.entity.Category;
import com.greedy.springjpa.menu.entity.Menu;

@Repository
public class MenuRepository {
	
	public List<Menu> findAllMenu(EntityManager entityManager){
		
		String jpql = "SELECT m FROM Menu m ORDER BY m.menuCode ASC";
		
		return entityManager.createQuery(jpql, Menu.class).getResultList();
	}
	
	public List<Menu> searchMenu(EntityManager entityManager, String keyword) {
		
		String jpql = "SELECT m FROM Menu m WHERE m.menuName like '%' || :keyword || '%'";
		
		return entityManager.createQuery(jpql, Menu.class).setParameter("keyword", keyword).getResultList();
	}

	public void deleteMenu(EntityManager entityManager, Menu menu) {
		
		Menu deletedMenu = entityManager.find(Menu.class, menu.getMenuCode());
		
		entityManager.remove(deletedMenu);
	}

	public void modifyPriceMenu(EntityManager entityManager, Menu menu) {
		
		Menu selectedMenu = entityManager.find(Menu.class, menu.getMenuCode());
		selectedMenu.setMenuPrice(menu.getMenuPrice());
		
	}

	public Menu getMenuName(EntityManager entityManager, Menu menu) {
		
		Menu selectedMenu = entityManager.find(Menu.class, menu.getMenuCode());
		
		return selectedMenu;
	}
}
