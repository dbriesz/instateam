package com.teamtreehouse.instateam.converter;

import com.teamtreehouse.instateam.dao.ProjectDao;
import com.teamtreehouse.instateam.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StringProjectConverter implements Converter<String, Project> {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public Project convert(String source) {
        return projectDao.findById(
                new Long(source)
        );
    }

    @Bean
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new StringProjectConverter());
        bean.setConverters(converters);
        return bean.getObject();
    }
}
