package com.spring.data.jpa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.data.jpa.dao.PeopleDao;
import com.spring.data.jpa.entity.People;
import com.spring.data.jpa.service.PeopleService;
import com.spring.data.jpa.utils.PageUtils;

@Service(value="peopleService")
public class PeopleServiceImpl implements PeopleService{

	@Resource(name="peopleDao")
//	@Autowired
	public PeopleDao peopleDao;

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)//java包的事务注解，此处表示该方法不支持事务处理
//	@Transactional(propagation = Propagation.NOT_SUPPORTED)//spring包的事务注解
	public People findById(Integer id) {
//		Optional<People> o = peopleDao.findById(id);
//		return o.get();
		return peopleDao.findOne(id);
	}

	@Override
	public com.spring.data.jpa.utils.Page<People> pageList(Integer pageNum, Integer pageSize, String searchName) {
        // 获得分页对象pageable，并且在pageable中页码是从0开始，设定按照sortType升序排列
        Pageable pageable = PageUtils.buildPageRequest(pageNum, pageSize, "");
        Page<People> people = peopleDao.findAll(new Specification<People>() {
            /**
             * 构造断言
             * @param root 实体对象引用
             * @param query 规则查询对象
             * @param cb 规则构建对象
             * @return 断言
             */
            @Override
            public Predicate toPredicate(Root<People> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>(); //所有的断言
                if(null != searchName){ //添加断言
                    Predicate likeNickName = cb.like(root.get("name").as(String.class),"%"+searchName+"%");
                    predicates.add(likeNickName);
                }
                Predicate notEq = cb.notEqual(root.get("opera").as(String.class), "-1");
                predicates.add(notEq);
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        }, pageable);
        com.spring.data.jpa.utils.Page<People> p = new com.spring.data.jpa.utils.Page<People>(pageNum, pageSize);
        p.setTotalRow((int)people.getTotalElements());
        p.setList(people.getContent());
		return p;
	}

//	@Transactional
	@Override
	public void save(People bean) {
		peopleDao.save(bean);
	}

//	@Transactional
	@Override
	public void update(People bean) {
		peopleDao.save(bean);
	}

	@Override
	public void opera(String ids, String type) {
		if(null != ids && ids.length() != 0){
			String[] _ids = ids.split(",");
			for(String id : _ids){
				if(null == id || "".equals(id)) continue;
				People p = findById(Integer.parseInt(id));
				p.setOpera(type);
				update(p);
			}
		}else{
			throw new NullPointerException(String.format("参数id为空！"));
		}
	}
}
