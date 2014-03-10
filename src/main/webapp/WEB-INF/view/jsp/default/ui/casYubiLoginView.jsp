<jsp:directive.include file="includes/top.jsp"/>

<form:form method="post" id="yubi_form" cssClass="fm-v clearfix" htmlEscape="true">
    <input type="hidden" name="lt" value="${loginTicket}"/>
    <input type="hidden" name="execution" value="${flowExecutionKey}"/>

    <label for="otp">Yubikey Password</label>
    <input type="text" id="otp" name="otp" onload="this.focus()"/>
    <input type="submit" name="_eventId" value="submit">
</form:form>

<jsp:directive.include file="includes/bottom.jsp"/>