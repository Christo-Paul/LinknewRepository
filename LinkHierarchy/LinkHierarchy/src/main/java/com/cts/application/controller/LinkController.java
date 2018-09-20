package com.cts.application.controller;

import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cts.application.model.Link;
import com.cts.application.model.Page;
import com.cts.application.service.LinkService;

@RestController
public class LinkController {

	@Autowired
	LinkService linkService;

	@GetMapping("/DynamicLinks/{linkName:.+}")
	public List<Link> getLinks(@PathVariable("linkName") String linkName) {

		System.out.println("LinkName" + linkName);

		return linkService.getLinks(linkName);

	}

	@GetMapping("/staticlinks")
	public List<Page> getLinkRoot() {
		return linkService.getSubLinks("root", "A");

	}

	@GetMapping("/staticlinks/{linkName1:.+}")
	public List<Page> getSubLinkLevel1(@PathVariable("linkName1") String linkName) {
		return linkService.getSubLinks(linkName, "B");
	}

	@GetMapping("/staticlinks/{linkName1:.+}/{linkName2:.+}")
	public List<Page> getSubLinkLevel2(@PathVariable("linkName2") String linkName) {
		return linkService.getSubLinks(linkName, "C");
	}

	@GetMapping("/staticlinks/{linkName1:.+}/{linkName2:.+}/{linkName3:.+}")
	public List<Page> getSubLinkLevel3(@PathVariable("linkName3") String linkName) {
		return linkService.getSubLinks(linkName, "D");
	}

}
