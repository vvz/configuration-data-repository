  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show TestResultType</title>
    </head>
    <body>
        <div class="body">
            <h1>Show TestResultType</h1>
            <div class="nav">
                %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>--}%
                %{--<span class="menuButton"><g:link class="list" action="list">TestResultType List</g:link></span>--}%
                <span class="menuButton"><g:link class="create" action="create">New TestResultType</g:link></span>
            </div>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        %{--<tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${testResultType.id}</td>
                            
                        </tr>--}%
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${testResultType.description}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order:</td>
                            
                            <td valign="top" class="value">${testResultType.order}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Results:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="r" in="${testResultType.results}">
                                    <li><g:link controller="changeRequest" action="show" id="${r.id}">${r}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form controller="testResultType">
                    <input type="hidden" name="id" value="${testResultType?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
