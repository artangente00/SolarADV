package com.PlayGroundAdv.Solar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.MountingTypeEntity;
import com.PlayGroundAdv.Solar.entity.RackingAllowedRoofMaterial;
import com.PlayGroundAdv.Solar.entity.RailRackingOptionsEntity;

@Configuration
public class ApplicationConfig {

	@Bean
	public ModelMapper modelMapperAHJ() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.STRICT)
		.setFieldMatchingEnabled(true)
		.setFieldAccessLevel(AccessLevel.PRIVATE);
		modelMapper.addConverter(toLong);
		modelMapper.addConverter(mountingTypeModel);
		modelMapper.addConverter(allowedRoofMaterial);
		modelMapper.addConverter(stringArrayModel);
		modelMapper.addConverter(stringList);
		return modelMapper;

	}
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(toLong);
		modelMapper.addConverter(stringList);
		modelMapper.addConverter(mountingTypeModel);
		modelMapper.addConverter(allowedRoofMaterial);
		return modelMapper;

	}
	
	AbstractConverter<RailRackingOptionsEntity, Long> toLong = new AbstractConverter<RailRackingOptionsEntity, Long>() {
		protected Long convert(RailRackingOptionsEntity source) {
			return source == null ? null : source.getId();
		}
	};

	AbstractConverter<List<String>, List<String>> stringList = new AbstractConverter<List<String>, List<String>>() {
		protected  List<String> convert(List<String> l) {
			List<String> types = new ArrayList<>();
    		if(l != null) {
				types.addAll(l);
	    	}
			return types;
		}
	};
	
	AbstractConverter<List<MountingTypeEntity>, List<String>> mountingTypeModel = new AbstractConverter<List<MountingTypeEntity>, List<String>>() {
		protected  List<String> convert(List<MountingTypeEntity> mountingTypes) {
			List<String> types = new ArrayList<>();
    		for(MountingTypeEntity type :mountingTypes) {
				types.add(type.getMountingType());
	    	}
			return types;
		}
	};

	AbstractConverter<List<String>, List<String>> stringArrayModel = new AbstractConverter<List<String>, List<String>>() {
		protected  List<String> convert(List<String> stringArray) {
    		if(stringArray != null) {
    			List<String> items = new ArrayList<>();
    			for(String item :stringArray) {
        			items.add(item);
    	    	}
    			return items;
    		}else return new ArrayList<>();
			
		}
	};

	AbstractConverter<Set<RackingAllowedRoofMaterial>, List<Long>> allowedRoofMaterial = new AbstractConverter<Set<RackingAllowedRoofMaterial>, List<Long>>() {
		protected  List<Long> convert(Set<RackingAllowedRoofMaterial> allowedRoof) {
			List<Long> types = new ArrayList<>();
    		for(RackingAllowedRoofMaterial type :allowedRoof) {
				types.add(type.getRoofMaterial().getId());
	    	}
			return types;
		}
	};

	AbstractConverter<AuthentificationEntity, String> userName = new AbstractConverter<AuthentificationEntity, String>() {
		protected  String convert(AuthentificationEntity user) {
			return user != null ? user.getFirstName()+" "+user.getLastName() : null;
		}
	};
}