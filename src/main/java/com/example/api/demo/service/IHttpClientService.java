/**
 * 
 */
package com.example.api.demo.service;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote OkHttp HttpClientConnection Interface
 */
public interface IHttpClientService {
	
	String getHttpClientConnection(String url);

}
