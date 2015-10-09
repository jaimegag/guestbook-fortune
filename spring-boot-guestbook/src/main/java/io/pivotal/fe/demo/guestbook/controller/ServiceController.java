package io.pivotal.fe.demo.guestbook.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.pivotal.fe.demo.guestbook.service.FortuneService;

@Controller
public class ServiceController {

	private Cloud cloud;
	
    @Autowired
    FortuneService service;

    @RequestMapping(value = "/fortune", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String remoteFortune() {
    	ObjectMapper mapper = new ObjectMapper();
    	String json = "";
		try {
			json = mapper.writeValueAsString(service.remoteFortune());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return json;
    }

	@RequestMapping(value = "/cloudinfo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getCloudInfo(@RequestHeader ("host") String hostName, HttpServletRequest request) {
		String properties = "";
		try {
			cloud = new CloudFactory().getCloud();
		} catch (CloudException e) {
			//e.printStackTrace();
			return properties;
		}
		ApplicationInstanceInfo cloudInfo = cloud.getApplicationInstanceInfo();

		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> props = cloudInfo.getProperties();
			props.put("host_name", hostName);
			props.put("ip", request.getRemoteAddr());
			properties = mapper.writeValueAsString(props);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;

	}
	
    @RequestMapping(value="/killApp", method = RequestMethod.GET)
    @ResponseBody
    public String kill(){
		System.exit(-1);    	
    	return "Killed";

    }  

}
