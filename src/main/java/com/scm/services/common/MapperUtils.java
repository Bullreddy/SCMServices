package com.scm.services.common;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MapperUtils extends ModelMapper{

public MapperUtils() {
	this.getConfiguration().setFieldMatchingEnabled(Boolean.TRUE).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
}
}
