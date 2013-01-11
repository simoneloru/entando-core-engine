/*
*
* Copyright 2012 Entando S.r.l. (http://www.entando.com) All rights reserved.
*
* This file is part of Entando software.
* Entando is a free software; 
* you can redistribute it and/or modify it
* under the terms of the GNU General Public License (GPL) as published by the Free Software Foundation; version 2.
* 
* See the file License for the specific language governing permissions   
* and limitations under the License
* 
* 
* 
* Copyright 2012 Entando S.r.l. (http://www.entando.com) All rights reserved.
*
*/
package org.entando.entando.aps.system.init.model;

import com.agiletec.aps.system.ApsSystemUtils;
import com.agiletec.aps.util.FileTextReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javax.ws.rs.core.MediaType;

import org.entando.entando.aps.system.services.api.model.ApiMethod;

import org.jdom.Element;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author E.Santoboni
 */
public class SelfRestCallPostProcess implements IPostProcess {
	
	/*
	<selfRestCall langCode="" namespace="jacms" resourceName="contentType" 
			method="POST" expected="202" printresponse="true" >
		<query>
			<!--
			<parameter name="param1" value="param1Value" />
			<parameter name="param2" value="param2Value" />
			<parameter name="param3" value="param3Value" />
			-->
		</query>
		<contentBody content-type="application/xml" path="classpath:component/plugins/jacms/postprocess/ANN.xml" />
	</selfRestCall>
	<!--
	<selfRestCall langCode="" namespace="jacms" resourceName="contentType" 
				  method="POST" expected="202" printresponse="true" >
		<query>
			<parameter name="param1" value="param1Value" />
			<parameter name="param2" value="param2Value" />
			<parameter name="param3" value="param3Value" />
		</query>
		<contentBody content-type="application/xml">
			RequestBody.....
		</contentBody>
	</selfRestCall>
	-->
	*/
	
	@Override
	public String getCode() {
		return "selfRestCall";
	}
	
	@Override
	public void createConfig(Element element) {
		try {
			this.setLangCode(element.getAttributeValue("langCode"));
			this.setNamespace(element.getAttributeValue("namespace"));
			this.setResourceName(element.getAttributeValue("resourceName"));
			String methodString = element.getAttributeValue("method");
			if (null != methodString) {
                this.setMethod(Enum.valueOf(ApiMethod.HttpMethod.class, methodString.toUpperCase()));
            } else {
                this.setMethod(ApiMethod.HttpMethod.GET);
            }
			String expectedString = element.getAttributeValue("expected");
			if (null != expectedString) {
				try {
					this.setExpectedResult(Integer.parseInt(expectedString));
				} catch (Exception e) {}
			}
			this.setPrintResponse(Boolean.parseBoolean(element.getAttributeValue("printresponse")));
			Element parametersElement = element.getChild("query");
			if (null != parametersElement) {
				List<Element> parameterElements = parametersElement.getChildren("parameter");
				for (int i = 0; i < parameterElements.size(); i++) {
					Element parameterElement = parameterElements.get(i);
					String name = parameterElement.getAttributeValue("name");
					String value = parameterElement.getAttributeValue("value");
					if (null != name && null != value) {
						this.getQueryParameters().put(name, value);
					}
				}
			}
			Element contentBodyElement = element.getChild("contentBody");
			if (null != contentBodyElement) {
				String contentTypeString = element.getAttributeValue("content-type");
				this.setContentType(MediaType.valueOf(contentTypeString));
				String text = contentBodyElement.getText();
				if (null == text || text.trim().length() == 0) {
					String path = contentBodyElement.getAttributeValue("path");
					if (null != path) {
						InputStream is = null;
						PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
						Resource resource = resolver.getResource(path);
						try {
							is = resource.getInputStream();
							text = FileTextReader.getText(is);
						} catch (Throwable t) {
							ApsSystemUtils.logThrowable(t, this, "createConfig", 
									"Error loading contentBody from file '" + path + "'");
						} finally {
							if (null != is) {
								is.close();
							}
						}
					}
				}
				if (null != text) {
					this.setContentBody(text);
				}
			}
		} catch (Throwable t) {
			ApsSystemUtils.logThrowable(t, this, "createConfig");
			throw new RuntimeException("Error creating Self rest call", t);
		}
	}
	
	public String getLangCode() {
		return _langCode;
	}
	public void setLangCode(String langCode) {
		this._langCode = langCode;
	}
	
	public String getNamespace() {
		return _namespace;
	}
	public void setNamespace(String namespace) {
		this._namespace = namespace;
	}
	
	public String getResourceName() {
		return _resourceName;
	}
	public void setResourceName(String resourceName) {
		this._resourceName = resourceName;
	}
	
	public ApiMethod.HttpMethod getMethod() {
		return _method;
	}
	public void setMethod(ApiMethod.HttpMethod method) {
		this._method = method;
	}
	
	public Integer getExpectedResult() {
		return _expectedResult;
	}
	public void setExpectedResult(Integer expectedResult) {
		this._expectedResult = expectedResult;
	}
	
	public boolean isPrintResponse() {
		return _printResponse;
	}
	public void setPrintResponse(boolean printResponse) {
		this._printResponse = printResponse;
	}
	
	public Properties getQueryParameters() {
		return _queryParameters;
	}
	public void setQueryParameters(Properties queryParameters) {
		this._queryParameters = queryParameters;
	}
	
	public MediaType getContentType() {
		return _contentType;
	}
	public void setContentType(MediaType contentType) {
		this._contentType = contentType;
	}
	
	public String getContentBody() {
		return _contentBody;
	}
	public void setContentBody(String contentBody) {
		this._contentBody = contentBody;
	}
	
	private String _langCode;
	private String _namespace;
	private String _resourceName;
	private ApiMethod.HttpMethod _method;
	private Integer _expectedResult;
	private boolean _printResponse;
	private Properties _queryParameters = new Properties();
	private MediaType _contentType;
	private String _contentBody;
	
}