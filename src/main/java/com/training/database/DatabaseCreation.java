package com.training.database;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseCreation {
	 /*private static final Logger LOG = (Logger) LoggerFactory
	            .getLogger(DatabaseCreation.class);*/
	    private DataSource dataSource;

	    public DataSource getDataSource() {
	        return dataSource;
	    }

	    public void setDataSource(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }

	    public void create() throws Exception {
	        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

	        String sql = "create table dataTable (\n" + "  id integer primary key,\n"
	                + "  Name varchar(50),\n" + "  Address varchar(100),\n"
	                + "  PhoneNum varchar(50)\n"
	                + ");\n";
	        //sql += "insert into dataTable values (1,'rahat','isb','123')";

	        System.out.println("Creating table dataTable ...");//LOG.info("Creating table dataTable ...");

	        try {
	            jdbc.execute("drop table dataTable");
	        } catch (Throwable e) {
	            // ignore
	        }

	        jdbc.execute(sql);
	        sql = "insert into dataTable values (1,'rahat','isb','123')";
	        jdbc.execute(sql);
	        System.out.println("Created table dataTable ...");//LOG.info("... created table dataTable");
	    }

	    public void destroy() throws Exception {
	        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

	        try {
	            jdbc.execute("drop table dataTable");
	        } catch (Throwable e) {
	            // ignore
	        }
	    }
}
