package com.rtmap.traffic.mfd.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.mfd.domain.dto.StartingOrDestinationDto;
import com.rtmap.traffic.mfd.domain.entity.Airline;
import com.rtmap.traffic.mfd.domain.entity.Country;
import com.rtmap.traffic.mfd.service.IBasService;

/**
 * 基础数据控制器
 * 
 * @author liqingshan 2016-01-06
 *
 */
@Controller
@RequestMapping("bas")
public class BasController extends UniformController {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Spring不但支持自己定义的@Autowired注解，还支持java5中的JSR-250规范定义的注解，它们分别是@Resource、
	 * 
	 * @PostConstruct以及@PreDestroy。
	 * 
	 * @Autowired，如果要允许null
	 * 值，可以设置它的required属性为false，如：@Autowired(required=false)
	 * ，如果我们想使用名称装配可以结合@Qualifier注解进行使用
	 * 
	 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按
	 * byName自动注入，按名称匹配不到bean时才按照类型进行装配。名称可以通过name属性进行指定，需要注意的是，如果name属性一旦指定，
	 * 就只会按照名称进行装配。
	 */
	@Resource
	IBasService basService;

	/**
	 * 获取服务器端当前时间
	 * 
	 * @return 返回服务器当前时间
	 */
	@ResponseBody
	@RequestMapping(value = "/currentDateTime.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Date getCurrentDateTime() {
		return new Date();
	}

	/**
	 * 获取国家集合
	 * 
	 * @return 返回国家集合
	 */
	@ResponseBody
	@RequestMapping(value = "/countries.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<Country> getCountries() {
		return basService.getCountries();
	}

	/**
	 * 获取国内分组城市集合
	 * 
	 * @return 城市集合
	 */
	@ResponseBody
	@RequestMapping(value = "/domCities.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Map<String, List<StartingOrDestinationDto>> getDomesticGroupedCities() {
		return basService.getDomesticGroupedCities(currAirportCode);
	}

	/**
	 * 获取国际分组城市集合
	 * 
	 * @return 城市集合
	 */
	@ResponseBody
	@RequestMapping(value = "/intCities.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Map<String, List<StartingOrDestinationDto>> getInternationalGroupedCities() {
		return basService.getInternationalGroupedCities(currAirportCode);
	}

	/**
	 * 获取国内分组航线的航空公司集合
	 * 
	 * @return 航空公司集合
	 */
	@ResponseBody
	@RequestMapping(value = "/domAirlines.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Map<String, List<Airline>> getDomesticGroupedAirlines() {
		return basService.getDomesticGroupedAirlines(currAirportCode);
	}

	/**
	 * 获取国际分组航线的航空公司集合
	 * 
	 * @return 航空公司集合
	 */
	@ResponseBody
	@RequestMapping(value = "/intAirlines.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Map<String, List<Airline>> getInternationalGroupedAirlines() {
		return basService.getInternationalGroupedAirlines(currAirportCode);
	}
}
