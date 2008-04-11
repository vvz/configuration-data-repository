<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Show Environment</title>
    </head>
    <body>
        <div class="body">
            <h1>Show Environment</h1>
            <div class="nav">
                <span class="menuButton">
                    <g:link id="${environment.id}" class="create" action="addRelation">Add Configuration Item</g:link>
                </span>
                %{--<span class="menuButton">
                    <g:link id="${environment.id}" class="create" action="addRelation">Add Software</g:link>
                </span>
                <span class="menuButton">
                    <g:link id="${environment.id}" class="create" action="addRelation">Add Network</g:link>
                </span>
                <span class="menuButton">
                    <g:link id="${environment.id}" class="create" action="addRelation">Add Documentation</g:link>
                </span>
                <span class="menuButton">
                    <g:link id="${environment.id}" class="create" action="addRelation">Add ChangeRequest</g:link>
                </span>
                <span class="menuButton">
                    <g:link id="${environment.id}" class="create" action="addRelation">Add Test Result</g:link>--}%
                </span>
                %{--<span class="menuButton">
                    <g:link id="${environment.id}" class="report" action="addRelation">Environment Report</g:link>
                </span>--}%
            </div>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            <td valign="top" class="value">${environment.name}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>

                            <td valign="top" class="value">${environment.description}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Owner Name:</td>

                            <td valign="top" class="value">${environment.ownerName}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Owner Email:</td>

                            <td valign="top" class="value">${environment.ownerEmail}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Project:</td>

                            <td valign="top" class="value"><g:link controller="project" action="show" id="${environment.project?.id}">${environment.project}</g:link></td>

                        </tr>

                        %{--<tr class="prop">
                            <td valign="top" class="name">Configuration Items:</td>

                            <td valign="top" class="value">${environment.configurationItems}</td>

                        </tr>--}%
                        <tr class="prop">
                            <td valign="top" class="name">Hardware:</td>
                            <td valign="top" class=value>
                                <ul class="subItems">
                                    <%
                                        environment.configurationItems.each {item ->
                                    %>
                                    <li><g:link controller="hardware" action="show" params='["environment.id":params.id]' id="${item?.id}">${item}</g:link></li>
                                    <%
                                        }
                                    %>
                                </ul>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form controller="environment">
                    <input type="hidden" name="id" value="${environment?.id}"/>
                    <span class="button"><g:actionSubmit class="edit" value="Edit"/></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete"/></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
