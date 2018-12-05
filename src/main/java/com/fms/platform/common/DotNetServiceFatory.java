package com.fms.platform.common;

import org.codehaus.xfire.service.OperationInfo;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

/**
 * xfire作为客户端调用时生成的soap头信息中SOAPAction为空，
 * 而.net要求这个不能为空。所以覆写父类方法生成SOAPAction合法内容。
 * 
 * @author dirful
 * @create 2016-03-08
 *
 */
public class DotNetServiceFatory extends ObjectServiceFactory {

private static final String NAME_SPACE = "http://tempuri.org/";

@Override
 protected String getAction(OperationInfo op) {
  return new StringBuffer(NAME_SPACE).append(op.getMethod().getName())
    .toString();
 }

@Override
 protected String getTargetNamespace(java.lang.Class clazz) {
  return NAME_SPACE;
 }


}
