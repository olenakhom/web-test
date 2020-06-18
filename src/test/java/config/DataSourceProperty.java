package config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataSourceProperty {

    @Getter
    @Value("${site.base.url}")
    private String siteUrl;

}
