<%@ page import="org.jasig.cas.web.flow.FlowExecutionUrlSavingAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body onload="document.forms[0].submit();">
<noscript>
    <p>Your browser has javascript disabled. Press Continue button</p>
</noscript>
<form action="login" method="post">
    <input type="hidden" name="_eventId" value="idpAuthnFinished"/>
    <input type="hidden" name="execution"
           value="<%= request.getAttribute(FlowExecutionUrlSavingAction.BASE_FLOW_EXECUTION_KEY) %>"/>
    <noscript>
        <input type="submit" value="Continue"/>
    </noscript>
</form>
</body>
</html>
