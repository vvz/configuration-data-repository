
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Create SoftwareType</title>
    </head>
    <body>
        <div class="body">
            <h1>Create SoftwareType</h1>
            <div class="nav">
                %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>--}%
                <span class="menuButton"><g:link class="list" action="list">SoftwareType List</g:link></span>
            </div>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${softwareType}">
                <div class="errors">
                    <g:renderErrors bean="${softwareType}" as="list"/>
                </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='description'>Description:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:softwareType,field:'description','errors')}'>
                            <input type="text" id='description' name='description' value="${fieldValue(bean:softwareType,field:'description')}"/>
                            </td>
                            </tr>
                            
                            <tr class='prop'>
                                <td valign='top' class='name'>
                                    <label for='order'>Order:</label>
                                </td>
                                <td valign='top' class='value ${hasErrors(bean:softwareType,field:'order','errors')}'>
                            <input type='text' id='order' name='order' value="${fieldValue(bean:softwareType,field:'order')}" />
                            </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create"></input></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
