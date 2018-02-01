package com.dogiant.cms.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.AdminUserDao;
import com.dogiant.cms.domain.admin.AdminUser;
import com.dogiant.cms.repo.AdminUserRepo;

@Service("adminUserDao")
public class AdminUserDaoImpl implements AdminUserDao {
	
	@Autowired
	private AdminUserRepo adminUserRepo;

	@Override
	public boolean checkAdminUser(String userName, String password) {
		int count = adminUserRepo.checkAdminUser(userName,password);
		return count > 0;
	}

	@Override
	public AdminUser getAdminUserByUserName(String userName) {
		return adminUserRepo.getAdminUserByUserName(userName);
	}

	@Override
	public AdminUser getAdminUserByNickname(String nickname) {
		return adminUserRepo.getAdminUserByNickname(nickname);
	}

	@Override
	public boolean modifyAdminUserPassword(String userName, String md5Hex) {
		AdminUser adminUser = getAdminUserByUserName(userName);
		adminUser.setPassword(md5Hex);
		try {
			adminUserRepo.save(adminUser);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Page<AdminUser> getAdminUserQueryResult(Integer page, Integer size, String beginTime,
			String endTime, String userName) {
		Sort sort = new Sort(Direction.DESC, "ctime");
		page = (page-1)<0?0:(page-1);
		Pageable pageable = new PageRequest(page, size, sort);
		Specification<AdminUser> spc = this
				.getAdminUserSpecification(beginTime, endTime, userName);

		Page<AdminUser> adminUserPage = adminUserRepo.findAll(spc, pageable);
		return adminUserPage;
	}
	

	private Specification<AdminUser> getAdminUserSpecification(final String beginTime,final String endTime,final String userName) {
		return new Specification<AdminUser>() {
			@Override
			public Predicate toPredicate(Root<AdminUser> paramRoot,
					CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder paramCriteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

//				list.add(paramCriteriaBuilder.equal(paramRoot.get("isValid").as(Integer.class), 1));
				Date startDate = null;
				Date endDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (StringUtils.isNotBlank(beginTime)){
					try {
						startDate = sdf.parse(beginTime);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

				}
				if(StringUtils.isNotBlank(endTime)){
					try {
						endDate = sdf.parse(endTime);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if (startDate != null) {
					list.add(paramCriteriaBuilder.greaterThanOrEqualTo(paramRoot.<Date>get("ctime"), startDate));
				}
				
				if (endDate != null) {
					list.add(paramCriteriaBuilder.lessThan(paramRoot.<Date>get("ctime"), endDate));
				}
				
				if (StringUtils.isNoneEmpty(userName))
					list.add(paramCriteriaBuilder.like(paramRoot.get("userName")
							.as(String.class), "%" + userName + "%"));
				
				Predicate[] p = new Predicate[list.size()];
				return paramCriteriaBuilder.and(list.toArray(p));
			}
		};
	}

	@Override
	public AdminUser getAdminUserByUserId(Integer userId) {
		return adminUserRepo.getOne(userId);
	}

	@Override
	public void saveAdminUser(AdminUser adminUser) {
		adminUserRepo.save(adminUser);
	}


}
