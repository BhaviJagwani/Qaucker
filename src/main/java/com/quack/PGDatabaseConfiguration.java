package com.quack;

import java.net.URI;
import java.net.URISyntaxException;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.db.PooledDataSourceFactory;

public class PGDatabaseConfiguration implements DatabaseConfiguration {

	private static DatabaseConfiguration databaseConfiguration;

	public static DatabaseConfiguration create(String databaseUrl) throws URISyntaxException {
		DatabaseConfiguration databaseConfiguration = null;
		URI dbUri = new URI(databaseUrl);
		final String user = dbUri.getUserInfo().split(":")[0];
		final String password = dbUri.getUserInfo().split(":")[1];
		final String url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
				+ "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		databaseConfiguration = new DatabaseConfiguration() {
			DataSourceFactory dataSourceFactory;

			@Override
			public DataSourceFactory getDataSourceFactory(Configuration configuration) {
				if (dataSourceFactory != null) {
					return dataSourceFactory;
				}
				DataSourceFactory dsf = new DataSourceFactory();
				dsf.setUser(user);
				dsf.setPassword(password);
				dsf.setUrl(url);
				dsf.setDriverClass("org.postgresql.Driver");
				dataSourceFactory = dsf;
				return dsf;
			}
		};
		return databaseConfiguration;
	}

	@Override
	public PooledDataSourceFactory getDataSourceFactory(Configuration configuration) {
		return databaseConfiguration.getDataSourceFactory(null);
	}

}