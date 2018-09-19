package com.cts.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import com.cts.application.controller.LinkHierarchyController;
import com.cts.application.dao.LinkHierarchyDAO;
import com.cts.application.model.PageA;
import com.cts.application.model.PageB;
import com.cts.application.model.PageC;
import com.cts.application.model.PageD;

@Service
public class LinkHierarchyService {

	@Autowired
	private LinkHierarchyDAO hierarchyDAO;

	public List<PageA> getAllPageAContent() {

		List<PageA> list = hierarchyDAO.getAllPageAContent();
		for (PageA pageA : list) {

			Link link = ControllerLinkBuilder.linkTo(LinkHierarchyController.class).slash(pageA.getPage_A_Name())
					.withSelfRel();
			pageA.add(link);

		}

		return list;
	}

	public List<PageB> getAllPageBContent(String link_name) {
		List<PageB> list = hierarchyDAO.getAllPageBContent(link_name);
		for (PageB pageB : list) {

			Link link = ControllerLinkBuilder.linkTo(LinkHierarchyController.class).slash(pageB.getPageA().getPage_A_Name())
					.slash(pageB.getPage_B_Name()).withSelfRel();
			pageB.add(link);

		}
		return list;

	}

	public List<PageC> getAllPageCContent(String link_name) {
		List<PageC> list = hierarchyDAO.getAllPageCContent(link_name);
		for (PageC pageC : list) {

			Link link = ControllerLinkBuilder.linkTo(LinkHierarchyController.class)
					.slash(pageC.getPageB().getPageA().getPage_A_Name()).slash(pageC.getPageB().getPage_B_Name())
					.slash(pageC.getPage_C_Name()).withSelfRel();
			pageC.add(link);

		}
		return list;

	}

	public List<PageD> getAllPageDContent(String link_nameD) {

		List<PageD> list = hierarchyDAO.getAllPageDContent(link_nameD);
		for (PageD pageD : list) {

			Link link = ControllerLinkBuilder.linkTo(LinkHierarchyController.class)
					.slash(pageD.getPageC().getPageB().getPageA().getPage_A_Name())
					.slash(pageD.getPageC().getPageB().getPage_B_Name()).slash(pageD.getPageC().getPage_C_Name())
					.slash(pageD.getPage_D_Name()).withSelfRel();
			pageD.add(link);

		}
		return list;

	}
}