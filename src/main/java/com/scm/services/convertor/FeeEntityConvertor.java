package com.scm.services.convertor;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.bulls.scm.common.vo.FeeDetailVO;
import com.scm.services.common.DateUtil;
import com.scm.services.dao.entity.FeeDetail;
import com.scm.services.exception.ServiceException;

@Component
public class FeeEntityConvertor {

	private final static Logger LOGGER = Logger.getLogger(FeeEntityConvertor.class);
	
	public List<FeeDetailVO> convertToFeeDetailVO(List<FeeDetail> feeDetailEntity)throws ServiceException{
		if(!CollectionUtils.isEmpty(feeDetailEntity)) {
			return feeDetailEntity.stream().map(entity->convertToVO(entity)).collect(Collectors.toList());
		}
		return null;
	}
	
	public FeeDetailVO convertToVO(FeeDetail entity) throws ServiceException{
		FeeDetailVO vo = new FeeDetailVO();
		vo.setAmount(entity.getAmount());
		vo.setCollectedBy(entity.getCollectedBy().getUsername());
		vo.setCollectedDate(DateUtil.convertToLocalDateTimeViaInstant(entity.getCreatedDate()));
		vo.setStudentId(String.valueOf(entity.getStudentId().getId()));
		vo.setStudentName(entity.getStudentId().getName());
		return vo;
	}
}
