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

	@Autowired
	private MapperUtils mapper;
	
	public StudentDAOImpl() {
	}
	
	public StudentVO saveStudent(StudentVO studentVO) {
		Admission admission = mapper.map(studentVO, Admission.class);
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
			studentVOList.add(mapper.map(admission, StudentVO.class));
		});
		return studentVOList;
	}

}
