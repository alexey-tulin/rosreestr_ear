package ru.rosreestr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AppProperties {

    @Value("${datasource.name}")
    private String datasourceName;

    @Value("${datasource.driver-class-name}")
    private String datasourceDriverClassName;

    @Value("${datasource.url}")
    private String datasourceURL;

    @Value("${datasource.username}")
    private String datasourceUsername;

    @Value("${datasource.password}")
    private String datasourcePassword;

    public String getDatasourceDriverClassName() {
        return datasourceDriverClassName;
    }

    public void setDatasourceDriverClassName(String datasourceDriverClassName) {
        this.datasourceDriverClassName = datasourceDriverClassName;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    public String getDatasourcePassword() {
        return datasourcePassword;
    }

    public void setDatasourcePassword(String datasourcePassword) {
        this.datasourcePassword = datasourcePassword;
    }

    public String getDatasourceURL() {
        return datasourceURL;
    }

    public void setDatasourceURL(String datasourceURL) {
        this.datasourceURL = datasourceURL;
    }

    public String getDatasourceUsername() {
        return datasourceUsername;
    }

    public void setDatasourceUsername(String datasourceUsername) {
        this.datasourceUsername = datasourceUsername;
    }
}
