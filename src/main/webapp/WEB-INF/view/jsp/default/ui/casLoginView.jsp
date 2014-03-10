<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<jsp:directive.include file="includes/top.jsp"/>

<c:if test="${not pageContext.request.secure}">
    <div id="msg" class="errors">
        <h2>Non-secure Connection</h2>

        <p>You are currently accessing CAS over a non-secure connection. Single Sign On WILL NOT WORK. In order to have
            single sign on work, you MUST log in over HTTPS.</p>
    </div>
</c:if>

<div class="box fl-panel" id="login">

    <form:form method="post" id="fm1" cssClass="fm-v clearfix" commandName="${commandName}" htmlEscape="true">
        <form:errors path="*" id="msg" cssClass="errors" element="div"/>

        <div class="loginBox">
            <div class="usernamePassword">
                <!-- <spring:message code="screen.welcome.welcome" /> -->
                <h2>Enter your username and password</h2>

                <div class="row fl-controls-left">
                    <label for="username" class="fl-label">Username</label>
                    <form:input cssClass="required" cssErrorClass="error" id="username" size="25" tabindex="1"
                                accesskey="${userNameAccessKey}" path="username" autocomplete="false"
                                htmlEscape="true"/>
                </div>
                <div class="row fl-controls-left">
                    <label for="password" class="fl-label">Password</label>
                    <form:password cssClass="required" cssErrorClass="error" id="password" size="25" tabindex="2"
                                   path="password" accesskey="${passwordAccessKey}" htmlEscape="true"
                                   autocomplete="off"/>
                </div>

                <div class="row btn-row">

                    <input type="hidden" name="lt" value="${loginTicket}"/>
                    <input type="hidden" name="execution" value="${flowExecutionKey}"/>

                    <input class="btn-submit" name="_eventId_submit" accesskey="l" value="LOGIN" tabindex="4"
                           type="submit"/>
                    <input class="btn-reset" name="reset" accesskey="c" value="clear" tabindex="5" type="reset"/>
                </div>
            </div>
        </div>
    </form:form>
</div>

<jsp:directive.include file="includes/bottom.jsp"/>