package com.cts.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.application.dao.LinkDao;
import com.cts.application.model.Link;
import com.cts.application.model.Page;

@Component
public class LinkService {
	@Autowired
	LinkDao linkDao;

	public List<Link> getLinks(String linkName) {

		try {
			int indexOfHyphen = linkName.lastIndexOf("%"); // Getting the
															// lastIndexOfHyphen

			System.out.println("Hyphen index " + indexOfHyphen);
			String linkLastSlash = null;
			if (indexOfHyphen != -1)
				linkLastSlash = linkName.substring(indexOfHyphen + 1);
			else
				linkLastSlash = linkName;
			System.out.println("Last element " + linkLastSlash);
			List<Link> linkInfo = linkDao.getLinks(linkLastSlash);
			if (linkInfo.size() != 0)
				return linkInfo;
			else {
				linkInfo.add(new Link(0, "node does not exist", 0));
				return linkInfo;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Page> getSubLinks(String linkName, String tableName) {

		try {
			/*
			 * String tableName = linkName.substring(0,1);
			 * 
			 * String linkName1 = linkName.substring(1);
			 */

			// System.out.println(tableName+" \t "+linkName);
			return linkDao.getSubLinks(linkName, tableName);
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return null;
	}

}
