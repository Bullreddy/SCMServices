package com.scm.services.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.common.MapperUtils;
import com.scm.services.dao.StudentDAO;
import com.scm.services.dao.entity.Admission;

@Repository
public class StudentDAOImpl extends BaseDAOImpl implements StudentDAO {


	public StudentDAOImpl() {
	}
	
	public StudentVO saveStudent(StudentVO studentVO) {
		Admission admission = mapper.map(studentVO, Admission.class);
		admission.setAcademicYearID(studentVO.getAcademicYearID());
		admission.setCaste(studentVO.getCasteID());
		admission.setPhaseID(studentVO.getPhaseID());
		admission.setTradeID(studentVO.getTradeID());
		admission.setTypeID(studentVO.getTypeID());
		if(admission.getId()==0)
			getEM().persist(admission);
		else
			getEM().merge(admission);
		studentVO = mapper.map(admission, StudentVO.class);
		return studentVO;
	}

	@Override
	public List<StudentVO> getStudents() {
		List<StudentVO> studentVOList = new ArrayList<StudentVO>();
		Query query = getEM().createNamedQuery("Admission.findAll");
		List<Admission> admissions = query.getResultList();
		admissions.forEach(admission -> {
			StudentVO studentVO = mapper.map(admission, StudentVO.class); 
			studentVO.setAcademicYearID(admission.getAcademicYearID());
			studentVO.setCasteID(admission.getCaste());
			studentVO.setPhaseID(admission.getPhaseID());
			studentVO.setTradeID(admission.getTradeID());
			studentVO.setTypeID(admission.getTypeID());
			studentVOList.add(studentVO);
		});
		return studentVOList;
	}

}
