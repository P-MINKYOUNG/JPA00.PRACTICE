package com.greedy.springjpa.menu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.springjpa.menu.dto.CategoryDTO;
import com.greedy.springjpa.menu.dto.MenuDTO;
import com.greedy.springjpa.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	private MenuService menuService;
	
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@GetMapping("/list")
	public String findAllMenu(Model model) {
		
		List<MenuDTO> menuList = menuService.findAllMenu();
		
		model.addAttribute("menuList", menuList);
		
		return "menu/list";
	}
	
	@GetMapping("/search")
	public void searchPage() {}
	
	@GetMapping("/searchMenu")
	public String searchMenu(Model model, @RequestParam String keyword) {
		
		List<MenuDTO> menuList = menuService.searchMenu(keyword);
		
		model.addAttribute("menuList", menuList);
		
		return "/menu/list";
	}
	
	@GetMapping("/delete")
	public void deletePage() {}
	
	@PostMapping("/delete")
	public String deleteMenu(@ModelAttribute MenuDTO deletedMenu) {
		
		menuService.deleteMenu(deletedMenu);
		
		return "redirect:/menu/list";
		
	}
	
	@GetMapping("/modifyPrice")
	public void modifyPricePage() {}
	
	@PostMapping("/modifyPrice")
	public String modifyPriceMenu(@ModelAttribute MenuDTO menu, Model model) {
		
		menuService.modifyPriceMenu(menu);
		
		return "redirect:/menu/" + menu.getMenuCode();
	}
	
	@GetMapping(value="menuName", produces="application/json; charset=UTF-8")
	@ResponseBody
	public MenuDTO getMenuName(@ModelAttribute MenuDTO menu){
		
		return menuService.getMenuName(menu);
	}
}
