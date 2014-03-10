<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:directive.include file="includes/top.jsp"/>

<c:if test="${not pageContext.request.secure}">
    <div id="msg" class="errors">
        <h2>Non-secure Connection</h2>

        <p>You are currently accessing CAS over a non-secure connection. Single Sign On WILL NOT WORK. In order to have
            single sign on work, you MUST log in over HTTPS.</p>
    </div>
</c:if>

<div class="box fl-panel" id="login">

    <h2>Enter your organization alias -- OR -- your username </h2>

    <form:form method="post" id="fm1" cssClass="fm-v clearfix" htmlEscape="true">

        <c:if test="${not empty orgNotFound}">
            <div id="msg" class="errors">
                <p><spring:message code="error.discovery.org_not_found" /></p>
            </div>
        </c:if>

        <c:if test="${not empty emptyUsername}">
            <div id="msg" class="errors">
                <p><spring:message code="error.discovery.empty_username" /></p>
            </div>
        </c:if>

        <c:if test="${not empty emptyOrg}">
            <div id="msg" class="errors">
                <p><spring:message code="error.discovery.empty_org" /></p>
            </div>
        </c:if>

        <div class="loginBox">
            <div class="usernamePassword">
                <div class="ui-widget">
                    <label for="orgName" class="fl-label">Organization Alias</label>
                    <input type="text" id="orgName" name="orgKey" tabindex="1" size="25" accesskey="o"/>
                    <input class="btn-submit" name="_eventId_discoverByOrg" accesskey="s" value="SELECT" tabindex="2" type="submit"/>
                </div>
            </div>

            <div class="usernamePasswordRightSide">
                <div class="ui-widget">
                    <label for="username" class="fl-label">Username</label>
                    <input type="text" id="username" name="username" tabindex="3" size="25" accesskey="u" autocomplete="false"/>
                    <input class="btn-submit" name="_eventId_discoverByUsername" accesskey="s" value="SELECT" tabindex="4" type="submit"/>
                </div>
            </div>
            <input type="hidden" name="execution" value="${flowExecutionKey}"/>
        </div>
    </form:form>
</div>

<jsp:directive.include file="includes/bottom.jsp"/>
