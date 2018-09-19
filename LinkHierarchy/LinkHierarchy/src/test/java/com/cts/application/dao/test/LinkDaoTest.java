package com.cts.application.dao.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cts.application.dao.LinkDao;
import com.cts.application.model.Link;
import com.cts.application.model.Page;

import junit.framework.TestCase;


@SpringBootTest
@PropertySource("classpath:sql.properties")
@RunWith(MockitoJUnitRunner.class)
public class LinkDaoTest extends TestCase {

	
	@Mock
	JdbcTemplate jdbc;
	@Mock
	RowMapper<Link> k;

	@Value("${query.approach1}")
	private String sql;

	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetLinks()  {
		LinkDao linkDao=new LinkDao();
		linkDao.setJdbc(this.jdbc);
		String linkname="someString";
		
		String sqla = sql + "'" + linkname + "'" + " )"; 
		
		List<Link> listOfLinks = new ArrayList<Link>();
		listOfLinks.add(new Link(1,"InvestmentBanking",0));
		listOfLinks.add(new Link(2,"RetailBanking",0));  
	//	k=new BeanPropertyRowMapper<Link>(Link.class);
		//linkDao.setBprm(k);
		
		k=new BeanPropertyRowMapper<Link>(Link.class);
		 //Mockito.when(new BeanPropertyRowMapper<Link>(Link.class)).thenReturn(k);
		 Mockito.when(this.jdbc.query(sqla,k)).thenReturn(listOfLinks);
		 assertEquals(listOfLinks.toString(),
				 linkDao.getLinks(linkname).toString());
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetSubLinks() {
		LinkDao linkDao=new LinkDao();
		linkDao.setJdbc(jdbc);
		String linkname="someString";
		String sqlq = sql + "'" + linkname + "'" + " )"; 
		List<Page> listOfLinks = new ArrayList<Page>();
		listOfLinks.add(new Page(1,"InvestmentBanking",0));//Mockito.any(BeanPropertyRowMapper.class)
		listOfLinks.add(new Page(2,"RetailBanking",0));
		BeanPropertyRowMapper<Page> k=new BeanPropertyRowMapper<Page>(Page.class);
		//BeanPropertyRowMapper<Page> k=Mockito.any(BeanPropertyRowMapper.class);
		
		
		Mockito.when(jdbc.query(sqlq,k)).thenReturn(listOfLinks);
		 assertEquals(listOfLinks,linkDao.getSubLinks(linkname, "A").toString());
	}

}
