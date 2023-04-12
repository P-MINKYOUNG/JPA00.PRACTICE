package com.greedy.springjpa.menu.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.springjpa.menu.dto.CategoryDTO;
import com.greedy.springjpa.menu.dto.MenuDTO;
import com.greedy.springjpa.menu.entity.Category;
import com.greedy.springjpa.menu.entity.Menu;
import com.greedy.springjpa.menu.repository.MenuRepository;

@Service
public class MenuService {
	
	private MenuRepository menuRepository;
	private ModelMapper modelMapper;
	@PersistenceContext	// 스프링 부트가 영속성 컨텍스트를 관리하는 엔티티 매니저를 주입한다.
	private EntityManager entityManager;
	
	@Autowired
	public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
		this.menuRepository = menuRepository;
		this.modelMapper = modelMapper;
	}
	
	public List<MenuDTO> findAllMenu() {
		
		List<Menu> menuList = menuRepository.findAllMenu(entityManager);
		
		return menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
	}

	
	public List<MenuDTO> searchMenu(String keyword) {
		
		List<Menu> menuList = menuRepository.searchMenu(entityManager, keyword);
		
		return menuList.stream().map(row -> modelMapper.map(row, MenuDTO.class)).collect(Collectors.toList());
		
	}

	@Transactional
	public void deleteMenu(MenuDTO deletedMenu) {
		
		menuRepository.deleteMenu(entityManager, modelMapper.map(deletedMenu, Menu.class));
		
	}
	
	@Transactional
	public void modifyPriceMenu(MenuDTO menu) {
		
		menuRepository.modifyPriceMenu(entityManager, modelMapper.map(menu, Menu.class));
		
	}

	public MenuDTO getMenuName(MenuDTO menu) {
		
		Menu menuList = menuRepository.getMenuName(entityManager, modelMapper.map(menu, Menu.class));
		
		MenuDTO menuList2 = modelMapper.map(menuList, MenuDTO.class);
		
		return menuList2;
	}

}
