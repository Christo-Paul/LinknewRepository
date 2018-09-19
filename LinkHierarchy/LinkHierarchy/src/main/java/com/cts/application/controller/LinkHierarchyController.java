package com.cts.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.application.model.PageA;
import com.cts.application.model.PageB;
import com.cts.application.model.PageC;
import com.cts.application.model.PageD;
import com.cts.application.service.LinkHierarchyService;

@RestController
@RequestMapping("/Banking")
public class LinkHierarchyController {

	@Autowired
	private LinkHierarchyService hierarchyService;

	@GetMapping("/")
	public List<PageA> getAllArticles() {
		List<PageA> list = hierarchyService.getAllPageAContent();
		return list;
	}

	@RequestMapping("/{linknameB}")
	public List<PageB> getPageBcontent(@PathVariable("linknameB") String link_nameB) {
		List<PageB> list = hierarchyService.getAllPageBContent(link_nameB);
		return list;
	}

	@RequestMapping("{linknameB}/{linknameC}")
	public List<PageC> getPageCcontent(@PathVariable("linknameB") String link_nameB,
			@PathVariable("linknameC") String link_nameC) {
		List<PageC> list = hierarchyService.getAllPageCContent(link_nameC);
		return list;
	}

	@RequestMapping("{linknameB}/{linknameC}/{linknameD}")
	public List<PageD> getPageDcontent(@PathVariable("linknameB") String link_nameB,
			@PathVariable("linknameC") String link_nameC, @PathVariable("linknameD") String link_nameD) {
		List<PageD> list = hierarchyService.getAllPageDContent(link_nameD);
		return list;
	}

	@RequestMapping("{linknameB}/{linknameC}/{linknameD}/{noLinksAvailable}")
	public String getNullcontent(@PathVariable("linknameB") String link_nameB,
			@PathVariable("linknameC") String link_nameC, @PathVariable("linknameD") String link_nameD,
			@PathVariable("noLinksAvailable") String noLinksAvailable) {
		return "No More Further Subdivisions !!!";
	}

}