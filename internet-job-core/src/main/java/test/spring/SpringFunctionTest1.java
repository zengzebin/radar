package test.spring;

import com.ssxt.common.entity.AreaEntity;
import com.ssxt.common.service.AreaService;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

public class SpringFunctionTest1 extends SpringTransactionalContextTests {
	
	@Autowired
	public AreaService service;
	
	@Rollback(false)
	@Test
	public void test1() {
		List<AreaEntity> areaList = service.getSingleStAreas();
		if (areaList != null && areaList.size() > 0) {
			for (AreaEntity area : areaList) {
				System.out.println(area.getAreaName() + "  " + area.getParentId());
			}
		}
	}

	public AreaService getService() {
		return service;
	}
	public void setService(AreaService service) {
		this.service = service;
	}
}
