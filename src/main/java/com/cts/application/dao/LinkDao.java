package com.cts.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mockito.Mockito;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cts.application.model.Link;
import com.cts.application.model.Page;

@PropertySource("classpath:sql.properties")
@Component
public class LinkDao {

	private static final Logger log = Logger.getLogger(LinkDao.class);
	@Autowired
	JdbcTemplate jdbc;
	@Value("${query.approach1}")
	private String sql;
	@Value("${page.a.query}")
	private String sqla;
	@Value("${page.b.query}")
	private String sqlb;
	@Value("${page.c.query}")
	private String sqlc;
	@Value("${page.d.query}")
	private String sqld;

	private BeanPropertyRowMapper<Link> k;

	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public List<Link> getLinks(String linkName) { // Hierarchial data model-
													// Adjacency list

		List<Link> links = new ArrayList<Link>();
		try {
			String sql_f = sql + "'" + linkName + "'" + " )"; // Query
			System.out.println("sql check" + sql_f); // DynamicApproach
			k = new BeanPropertyRowMapper<Link>(Link.class);
			// BeanPropertyRowMapper<Link> k=Mockito.mock(classToMock)
			links = jdbc.query(sql_f, k); // Mapping
			System.out.println(links + sql); // with
			return links;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Page> getSubLinks(String linkName, String tableName) {

		List<Page> links = null;

		try {

			if (tableName.equals("A")) {

				return links = jdbc.query(sqla, new BeanPropertyRowMapper<Page>(Page.class));
			} else if (tableName.equals("B")) {

				System.out.println("Table B executed");
				System.out.println(sqlb);
				String sql_f = sqlb + "'" + linkName + "'" + ")";

				return links = jdbc.query(sql_f, new BeanPropertyRowMapper<Page>(Page.class));
			} else if (tableName.equals("C")) {

				String sql_f = sqlc + "'" + linkName + "'" + ")";
				return links = jdbc.query(sql_f, new BeanPropertyRowMapper<Page>(Page.class));
			} else if (tableName.equals("D")) {

				String sql_f = sqld + "'" + linkName + "'" + ")";
				return links = jdbc.query(sql_f, new BeanPropertyRowMapper<Page>(Page.class));
			} else
				return null;

		}

		catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return null;

	}

	public void setBprm(BeanPropertyRowMapper<Link> k) {
		this.k = k;
	}

}
